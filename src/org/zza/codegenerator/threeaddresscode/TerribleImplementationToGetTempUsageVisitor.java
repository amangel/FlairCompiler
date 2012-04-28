package org.zza.codegenerator.threeaddresscode;

import java.util.HashMap;
import java.util.Map;

import org.zza.parser.semanticstack.nodes.*;
import org.zza.visitor.NodeVisitor;


public class TerribleImplementationToGetTempUsageVisitor extends NodeVisitor {
    
    private int tempCount = 0;
    private int lineNumber =0;
    private int labelCount;
    private HashMap<String, Map<String, Integer>> usage;
    private int varDec =0;
    private String scope;
    
    public TerribleImplementationToGetTempUsageVisitor() {
        usage = new HashMap<String, Map<String, Integer>>();
        addToUsage("program");
    }
    
    public Integer getTempsCountFrom(String function) {
        return usage.get(function).get("temps");
    }
    
    public Integer getLocalsCountFrom(String function) {
        return usage.get(function).get("locals");
    }
    
    private void addToUsage(String name) {
//        System.out.println("adding "+name+" to usage");
        Map<String, Integer> newMap = new HashMap<String, Integer>();
        newMap.put("locals", 0);
        newMap.put("temps", 0);
        usage.put(name, newMap);
    }

    private void addLocalsInUsage(String function, int count) {
        usage.get(function).put("locals", usage.get(function).get("locals") + count);
    }
    
    private void addTempsInUsage(String function, int count) {
        usage.get(function).put("temps", usage.get(function).get("temps") + count);        
    }
    
    @Override
    public String visit(ProgramNode node) {
        scope = "program";
        String parameters = node.getHeader().accept(this);
        String[] splitParam = parameters.split(",");
        addLocalsInUsage(scope, splitParam.length);
        
        String body = node.getbody().accept(this);
        addTempsInUsage(scope, tempCount);
        tempCount = 0;
//        System.out.println("*end main program. Used: "+tempCount );
        String declarations = node.getDeclarations().accept(this);
//        System.out.println("*program used "+(splitParam.length + varDec));
//        System.out.println(lineNumber  + ":   HALT  0,0,0");
//        System.out.println("\n\nusage map: \n"+usage);
        return "";
    }

    @Override
    public String visit(VariableDeclarationNode node) {
        varDec ++;
        return "vardec";
    }
    
    @Override
    public String visit(FunctionNode node) {
        String oldScope = scope;
        String header = node.getHeader().accept(this);
        
//        System.out.println("*Entry function: " +header + " tempcount: "+tempCount + " " +scope);
        String body = node.getBody().accept(this); 
        addTempsInUsage(scope, tempCount);
        tempCount = 0;
//        System.out.println("*Finish function: "+header + " tempcount: "+tempCount);
        scope = oldScope;
        return "function : "+header + " " + body;
    }
    
    @Override
    public String visit(ParameterNode node) {
        
        return node.getLeftHand().accept(this);
//        return handleTwoFieldNode(node, "param");
    }
    
    @Override
    public String visit(AssignmentExpressionNode node) {
        String left = node.acceptVisitorLeftHand(this);
        String right = node.acceptVisitorRightHand(this);
        return "assignment";
    }
    
    @Override
    public String visit(CompoundStatementNode node) {
        String toReturn = "(";
        for (SemanticNode n : node.getStatements()) {
            toReturn += n.accept(this) + ",";
        }
        return trimEnd(toReturn)+")";
    }
    
    @Override
    public String visit(DivisionExpressionNode node) {
        return handleTwoFieldNode(node, "/");
    }
    
    @Override
    public String visit(IdentifierNode node) {
        return node.getValue();
    }
    
    @Override
    public String visit(IntegerNode node) {
        return node.getValue();
    }
    
    @Override
    public String visit(MinusExpressionNode node) {
        return handleTwoFieldNode(node, "-");
    }
    
    
    @Override
    public String visit(MultiplicationExpressionNode node) {
        return handleTwoFieldNode(node, "*");
    }
    
    @Override
    public String visit(PlusExpressionNode node) {
        return handleTwoFieldNode(node, "+");
    }
    
    @Override
    public String visit(RealNode node) {
        return node.getValue();
    }
    
    @Override
    public String visit(TypeNode node) {
        return "";
    }
    
    @Override
    public String visit(AllParametersNode node) {
        String toReturn = "";
        for (SemanticNode n : node.getArray()){
            toReturn += n.accept(this) + ",";
        }
        if (toReturn.length() > 0 ) {
            toReturn = toReturn.substring(0, toReturn.length()-1);
        }
        return toReturn;
    }
    
    @Override
    public String visit(AllVariableDeclarationsNode node) {
        String toReturn = "";
        
        for (SemanticNode n : node.getArray()) {
            toReturn += n.accept(this) + ",";
        }
        
        return trimEnd(toReturn);
    }
    
    @Override
    public String visit(ArgumentNode node) {
        String toReturn = "";
        for (SemanticNode n : node.getArguments()) {
            toReturn += n.accept(this) + ",";
        }
        return trimEnd(toReturn);
    }
    
    private String trimEnd(String toReturn) {
        int max = 0;
        if(toReturn.length() > 1) {
            max = toReturn.length() - 1;
        }
        return toReturn.substring(0, max);
    }

    @Override
    public String visit(CompareOperatorNode node) {
        return node.getValue();
    }
    
    @Override
    public String visit(ComparisonNode node) {
        return node.acceptVisitorLeftHand(this) + "," + node.acceptVisitorMiddle(this) + "," +
                node.acceptVisitorRightHand(this);
    }
    
    @Override
    public String visit(WhileExpressionNode node) {
        return handleTwoFieldNode(node, "do");
    }

    @Override
    public String visit(NegativeExpressionNode node) {
        getNextTemporary();
        return "negative:"+node.getContent().accept(this);
    }
    
    @Override
    public String visit(ProgramHeaderNode node) {
        return node.getParameters().accept(this);
    }
    
    @Override
    public String visit(DeclarationsNode node) {
        String variableDec = node.getVariableDeclarations().accept(this);
        addLocalsInUsage(scope, varDec);
        varDec = 0;
        String functionDec = node.getFunctionDeclarations().accept(this);
        return functionDec;
    }
    
    @Override
    public String visit(PrintStatementNode node) {
        String parameters = node.getArgument().accept(this);
        return "print";
    }
    
    @Override
    public String visit(FunctionCallNode node) {
        getNextTemporary();
        String params = node.acceptVisitorRightHand(this);
        String name = node.acceptVisitorLeftHand(this);
//        System.out.println("BEGIN_CALL: \nPARAMS "+params + "\nCALL "+name);
        return "RETURNVALUE("+name+params+")";//"call "+name+params;
    }
    
    @Override
    public String visit(FunctionHeadingNode node) {
        scope = "function"+node.acceptVisitorLeftHand(this);
        addToUsage(scope);
        String[] count = node.getMiddle().accept(this).split(",");
        addLocalsInUsage(scope, count.length);
        return node.acceptVisitorLeftHand(this);
        
    }

    @Override
    public String visit(AllFunctionDeclarationsNode node) {
        String toReturn = "";
        for (SemanticNode n : node.getArray()) {
            toReturn += n.accept(this) + ", ";
        }
        return toReturn;
    }
    
    @Override
    public String visit(FunctionBodyNode node) {
        
        String oldScope = scope;
        String variableDec = node.getVariables().accept(this);
        addLocalsInUsage(scope, varDec);
        
        String functionBody = node.getBody().accept(this);
        scope = oldScope;
        return functionBody;
    }
    
    @Override
    public String visit(ReturnStatementNode node) {
        getNextTemporary();
//        System.out.println("return: "+node.getArguments().accept(this));
        return "return";
    }
    
    @Override
    public String visit(IfStatementNode node) {
        return handleThreeFieldNode(node, "", "");
    }
    
    @Override
    public String visit(EmptyNode node) {
        // TODO Auto-generated method stub
        return null;
    }
    
    
    private String handleTwoFieldNode(TwoFieldNode node, String op) {
        String left = node.acceptVisitorLeftHand(this);
        String right = node.acceptVisitorRightHand(this);
        String nextTemp = getNextTemporary();
        return nextTemp;
    }

    private String getNextTemporary() {
        return "t"+tempCount++;
    }

    private String handleThreeFieldNode(ThreeFieldNode node, String op1, String op2) {
        String left = node.acceptVisitorLeftHand(this);
        String middle = node.acceptVisitorMiddle(this);
        String right = node.acceptVisitorRightHand(this);        
        String nextTemp = getNextTemporary();
        return nextTemp;
    }
}

