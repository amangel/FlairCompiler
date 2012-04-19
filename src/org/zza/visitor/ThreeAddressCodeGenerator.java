package org.zza.visitor;

import org.zza.parser.semanticstack.nodes.*;


public class ThreeAddressCodeGenerator extends NodeVisitor {
    
    private int tempCount = 0;

    @Override
    public String visit(ProgramNode node) {
        System.out.println("*begin main program");
        String body = node.getbody().accept(this);
        System.out.println("*end main program");
        String declarations = node.getDeclarations().accept(this);
        System.out.println("*HALT");
        return "program: \n"+declarations +"\n" +body;
    }
    
    @Override
    public String visit(VariableDeclarationNode node) {
        return handleTwoFieldNode(node, "vardec");
    }
    
    @Override
    public String visit(FunctionNode node) {
        String header = node.getHeader().accept(this);
        System.out.println("*Entry function: " +header);
        String body = node.getBody().accept(this); 
        System.out.println("*finish function: "+header);
        
        return "function : "+header + " " + body;
    }
    
    @Override
    public String visit(ParameterNode node) {
        return handleTwoFieldNode(node, "param");
    }
    
    @Override
    public String visit(AssignmentExpressionNode node) {
        return handleTwoFieldNode(node, ":=");
//        System.out.println(node.acceptVisitorLeftHand(this) + " := " +node.acceptVisitorRightHand(this));
//        return "assignment";
    }
    
    @Override
    public String visit(CompoundStatementNode node) {
        String toReturn = "(";
        for (SemanticNode n : node.getStatements()) {
            toReturn += n.accept(this) + ", ";
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
        return handleTwoFieldNode(node , "-");
    }
    
    
    @Override
    public String visit(MultiplicationExpressionNode node) {
        return handleTwoFieldNode(node , "*");
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
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public String visit(AllParametersNode node) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public String visit(AllVariableDeclarationsNode node) {
        return null;
    }
    
    @Override
    public String visit(ArgumentNode node) {
        String toReturn = "(";
        for (SemanticNode n : node.getArguments()) {
            toReturn += n.accept(this) + ", ";
        }
        return trimEnd(toReturn)+")";
    }
    
    private String trimEnd(String toReturn) {
        int max = 0;
        if(toReturn.length() > 2) {
            max = toReturn.length() - 2;
        }
        return toReturn.substring(0, max);
    }

    @Override
    public String visit(CompareOperatorNode node) {
        return node.getValue();
    }
    
    @Override
    public String visit(ComparisonNode node) {
        return handleThreeFieldNode(node, "", "");
    }
    
    @Override
    public String visit(WhileExpressionNode node) {
//        return handleTwoFieldNode(node, "do");
        String whilePart = node.acceptVisitorLeftHand(this);
        String doPart = node.acceptVisitorRightHand(this);
        System.out.println("while: "+whilePart + " do " +doPart);
        return "while";
    }
    
    @Override
    public String visit(NegativeExpressionNode node) {
        return "negative:"+node.getContent().accept(this);
    }
    
    @Override
    public String visit(ProgramHeaderNode node) {
        return node.getIdentifier().accept(this);
    }
    
    @Override
    public String visit(DeclarationsNode node) {
        String functionDec = node.getFunctionDeclarations().accept(this);
        return functionDec;
    }
    
    @Override
    public String visit(PrintStatementNode node) {
        System.out.println("print: "+node.getArgument().accept(this));
        return "print";
    }
    
    @Override
    public String visit(FunctionCallNode node) {
//        return handleTwoFieldNode(node, "funccall");
        String params = node.acceptVisitorRightHand(this);
        String name = node.acceptVisitorLeftHand(this);
        System.out.println("BEGIN_CALL: \nPARAMS "+params + "\nCALL "+name);
        return "RETURNVALUE";//"call "+name+params;
    }
    
    @Override
    public String visit(FunctionHeadingNode node) {
//        return handleThreeFieldNode(node, "", "");
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
        String functionBody = node.getBody().accept(this);
        return functionBody;
    }
    
    @Override
    public String visit(ReturnStatementNode node) {
        System.out.println("return: "+node.getArguments().accept(this));
        return "return";
    }
    
    @Override
    public String visit(IfStatementNode node) {
        return handleThreeFieldNode(node, "then", "else");
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
        System.out.println(nextTemp + " := " + left +" "+ op +" "+ right);
//        return "twofield:\n"+left + " "+op + " " + right;
        return nextTemp;
    }

    private String getNextTemporary() {
        return "t"+tempCount ++;
    }

    private String handleThreeFieldNode(ThreeFieldNode node, String op1, String op2) {
        String left = node.acceptVisitorLeftHand(this);
        String middle = node.acceptVisitorMiddle(this);
        String right = node.acceptVisitorRightHand(this);        
        String nextTemp = getNextTemporary();
        System.out.println(nextTemp + " := " + left +" "+ middle +" "+ right);
//        System.out.println(getNextTemporary() + " := " + middle + op2 + right);
//        return "threefield: \n"+left + " " +op1 + " " +middle + " " + op2 + " " +right;
        return nextTemp;
    }
}
