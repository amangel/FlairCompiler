package org.zza.visitor;

import org.zza.codegenerator.DataMemoryManager;
import org.zza.codegenerator.MemoryOutOfBoundsException;
import org.zza.codegenerator.ProgramFrame;
import org.zza.codegenerator.threeaddresscode.Addition3AC;
import org.zza.codegenerator.threeaddresscode.Assignment3AC;
import org.zza.codegenerator.threeaddresscode.Division3AC;
import org.zza.codegenerator.threeaddresscode.Multiplication3AC;
import org.zza.codegenerator.threeaddresscode.Print3AC;
import org.zza.codegenerator.threeaddresscode.Subtraction3AC;
import org.zza.parser.semanticstack.nodes.*;


public class ThreeAddressCodeGenerator extends NodeVisitor {
    
    private int tempCount = 0;
    private int labelCount = 0;
    private int lineNumber = 0;
    private DataMemoryManager manager;

    public ThreeAddressCodeGenerator() {
        manager = new DataMemoryManager();
    }
    
    @Override
    public String visit(ProgramNode node) {
        String parameters = node.getHeader().accept(this);
        String[] splitParam = parameters.split(",");
        
        System.out.println("*begin main program");
        try {
            manager.addStackFrame(new ProgramFrame(splitParam.length, 5, 5));
        } catch (MemoryOutOfBoundsException e) {
            e.printStackTrace();
        }
        String body = node.getbody().accept(this);
        System.out.println("*end main program. Used: "+tempCount);
        String declarations = node.getDeclarations().accept(this);
        System.out.println(lineNumber + ":   HALT  0,0,0");
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
        System.out.println("*Finish function: "+header);
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
        try {
            manager.addLocalVariable(left);
        } catch (MemoryOutOfBoundsException e) {
            e.printStackTrace();
        }
        Assignment3AC assign = new Assignment3AC(lineNumber, manager);
        assign.setParameters(right, left, "");
        lineNumber += assign.getEmittedSize();
        assign.emitCode();
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
        String temp = getNextTemporary();
        try {
            manager.addNewTemporaryVariable(temp);
        } catch (MemoryOutOfBoundsException e) {
            e.printStackTrace();
        }
        String left = node.acceptVisitorLeftHand(this);
        String right = node.acceptVisitorRightHand(this);
        Division3AC division = new Division3AC(lineNumber, manager);
        division.setParameters(temp, left, right);
        lineNumber += division.getEmittedSize();
        division.emitCode();
        return temp;
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
        String temp = getNextTemporary();
        try {
            manager.addNewTemporaryVariable(temp);
        } catch (MemoryOutOfBoundsException e) {
            e.printStackTrace();
        }
        String left = node.acceptVisitorLeftHand(this);
        String right = node.acceptVisitorRightHand(this);
        Subtraction3AC subtraction = new Subtraction3AC(lineNumber, manager);
        subtraction.setParameters(temp, left, right);
        lineNumber += subtraction.getEmittedSize();
        subtraction.emitCode();
        return temp;
    }
    
    
    @Override
    public String visit(MultiplicationExpressionNode node) {
        String temp = getNextTemporary();
        try {
            manager.addNewTemporaryVariable(temp);
        } catch (MemoryOutOfBoundsException e) {
            e.printStackTrace();
        }
        String left = node.acceptVisitorLeftHand(this);
        String right = node.acceptVisitorRightHand(this);
        Multiplication3AC multiplication = new Multiplication3AC(lineNumber, manager);
        multiplication.setParameters(temp, left, right);
        lineNumber += multiplication.getEmittedSize();
        multiplication.emitCode();
        return temp;
    }
    
    @Override
    public String visit(PlusExpressionNode node) {
        String temp = getNextTemporary();
        try {
            manager.addNewTemporaryVariable(temp);
        } catch (MemoryOutOfBoundsException e) {
            e.printStackTrace();
        }
        String left = node.acceptVisitorLeftHand(this);
        String right = node.acceptVisitorRightHand(this);
        Addition3AC addition = new Addition3AC(lineNumber, manager);
        addition.setParameters(temp, left, right);
        lineNumber += addition.getEmittedSize();
        addition.emitCode();
        return temp;
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
        return null;
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
        return handleThreeFieldNode(node, "", "");
    }
    
    @Override
    public String visit(WhileExpressionNode node) {
//        return handleTwoFieldNode(node, "do");
        String whilePart = node.acceptVisitorLeftHand(this);
        String label = getNextLabel();
        System.out.println("whileLabel: "+label);
        String doPart = node.acceptVisitorRightHand(this);
        System.out.println("while: "+whilePart + " goto " +label);
        return "while";
    }

    @Override
    public String visit(NegativeExpressionNode node) {
        return "negative:"+node.getContent().accept(this);
    }
    
    @Override
    public String visit(ProgramHeaderNode node) {
        return node.getParameters().accept(this);
    }
    
    @Override
    public String visit(DeclarationsNode node) {
        String functionDec = node.getFunctionDeclarations().accept(this);
        return functionDec;
    }
    
    @Override
    public String visit(PrintStatementNode node) {
//        String command = "print"+node.getArgument().accept(this);
        String parameters = node.getArgument().accept(this);
        String[] params = parameters.split(",");
        Print3AC printer = null;
        for (String param : params) {
            printer = new Print3AC(lineNumber, manager);
            printer.setParameters(param, "", "");
            lineNumber += printer.getEmittedSize();
            printer.emitCode();
        }
        return "print";
    }
    
    @Override
    public String visit(FunctionCallNode node) {
//        return handleTwoFieldNode(node, "funccall");
        String params = node.acceptVisitorRightHand(this);
        String name = node.acceptVisitorLeftHand(this);
        System.out.println("BEGIN_CALL: \nPARAMS "+params + "\nCALL "+name);
        return "RETURNVALUE("+name+params+")";//"call "+name+params;
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
//        return handleThreeFieldNode(node, "then", "else");
        
        String ifPart = node.acceptVisitorLeftHand(this);
        String thenPart = node.acceptVisitorMiddle(this);
        String elsePart = node.acceptVisitorRightHand(this);
        System.out.println("if: "+ifPart);
        System.out.println("then: "+thenPart);
        System.out.println("else: " +elsePart);
        return "if";
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
        return "t"+tempCount++;
    }

    
    private String getNextLabel() {
        return "LABEL"+labelCount++;
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
