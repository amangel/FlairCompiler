package org.zza.visitor;

import org.zza.codegenerator.DataMemoryManager;
import org.zza.codegenerator.MemoryOutOfBoundsException;
import org.zza.codegenerator.ProgramFrame;
import org.zza.codegenerator.threeaddresscode.Addition3AC;
import org.zza.codegenerator.threeaddresscode.Assignment3AC;
import org.zza.codegenerator.threeaddresscode.Division3AC;
import org.zza.codegenerator.threeaddresscode.ComparisonHeader3AC;
import org.zza.codegenerator.threeaddresscode.IfRest3AC;
import org.zza.codegenerator.threeaddresscode.Multiplication3AC;
import org.zza.codegenerator.threeaddresscode.Negative3AC;
import org.zza.codegenerator.threeaddresscode.Print3AC;
import org.zza.codegenerator.threeaddresscode.Subtraction3AC;
import org.zza.codegenerator.threeaddresscode.TerribleImplementationToGetTempUsageVisitor;
import org.zza.codegenerator.threeaddresscode.WhileFooter3AC;
import org.zza.parser.semanticstack.nodes.*;


public class ThreeAddressCodeGenerator extends NodeVisitor {
    
    private int tempCount = 0;
    private int labelCount = 0;
    private int lineNumber = 1;
    private DataMemoryManager manager;
    private TerribleImplementationToGetTempUsageVisitor usageManager;
    

    public ThreeAddressCodeGenerator(TerribleImplementationToGetTempUsageVisitor terribleUsageVisitor) {
        usageManager = terribleUsageVisitor;
        manager = new DataMemoryManager();
    }
    
    @Override
    public String visit(ProgramNode node) {
        
        String declarations = node.getDeclarations().accept(this);
        String parameters = node.getHeader().accept(this);
        String[] splitParam = parameters.split(",");
        System.out.println("0:   LDA   7,"+lineNumber+"(6)");
        try {
            int localCount = usageManager.getLocalsCountFrom("program");
            int tempCount = usageManager.getTempsCountFrom("program");
            ProgramFrame program = new ProgramFrame(localCount, tempCount);
            manager.addStackFrame(program);
            initializeRegisters(program.getSize());
            handleCommandLineArguments(splitParam);
        } catch (MemoryOutOfBoundsException e) {
            e.printStackTrace();
        }

        String body = node.getbody().accept(this);
        System.out.println(lineNumber + ":   HALT  0,0,0");
        return "program: \n"+declarations +"\n" +body;
    }
    
    private void initializeRegisters(int size) {
        System.out.println(lineNumber++ + ":   LDC   2,1(6)");
        System.out.println(lineNumber++ + ":   LDC   3,1(6)");
        System.out.println(lineNumber++ + ":   LDC   4,"+(size+1)+"(6)");
    }

    private void handleCommandLineArguments(String[] splitParam) throws MemoryOutOfBoundsException {
        for (int i = splitParam.length; i > 0; i--) {
            manager.addLocalVariable(splitParam[i-1]);
        }
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
        return "*function : "+header + " " + body;
    }
    
    @Override
    public String visit(ParameterNode node) {
        
        return node.getLeftHand().accept(this);
    }
    
    @Override
    public String visit(AssignmentExpressionNode node) {
        String left = node.acceptVisitorLeftHand(this);
        String right = node.acceptVisitorRightHand(this);
        try {
            manager.addLocalVariable(left);
        } catch (MemoryOutOfBoundsException e) {
            e.printStackTrace();
            manager.dump();
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
        return node.acceptVisitorLeftHand(this) + "," + node.acceptVisitorMiddle(this) + "," +
                node.acceptVisitorRightHand(this);
    }
    
    @Override
    public String visit(WhileExpressionNode node) {
        String whilePart = node.acceptVisitorLeftHand(this);
        String[] whileParts = whilePart.split(",");
        int oldLineNumber = lineNumber;
        lineNumber += 4;
        node.acceptVisitorRightHand(this);
        //build footer
        WhileFooter3AC whileFooter = new WhileFooter3AC(lineNumber, manager);
        whileFooter.setParameters("", "", "", oldLineNumber);
        whileFooter.emitCode();
        lineNumber += whileFooter.getEmittedSize();
        ComparisonHeader3AC whileHeader = new ComparisonHeader3AC(oldLineNumber, manager);
        whileHeader.setParameters(whileParts[0], whileParts[2], whileParts[1], lineNumber - oldLineNumber - 4);
        whileHeader.emitCode();
        return "while";
    }

    @Override
    public String visit(NegativeExpressionNode node) {
        String temp = getNextTemporary();
        try {
            manager.addNewTemporaryVariable(temp);
        } catch (MemoryOutOfBoundsException e) {
            e.printStackTrace();
        }
        String content = node.getContent().accept(this);
        Negative3AC negation = new Negative3AC(lineNumber, manager);
        negation.setParameters(temp, content, "");
        lineNumber += negation.getEmittedSize();
        negation.emitCode();
        return temp;
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
        String ifPart = node.acceptVisitorLeftHand(this);
        String[] ifParts = ifPart.split(",");
        int oldLineNumber = lineNumber;
        lineNumber += 4; 
        node.acceptVisitorMiddle(this);
        ComparisonHeader3AC ifHeader = new ComparisonHeader3AC(oldLineNumber, manager);
        ifHeader.setParameters(ifParts[0], ifParts[2], ifParts[1], lineNumber - oldLineNumber - 2);
        ifHeader.emitCode();
        oldLineNumber = lineNumber;
        lineNumber += 2;
        node.acceptVisitorRightHand(this);
        
        IfRest3AC ifRest = new IfRest3AC(oldLineNumber, manager);
        ifRest.setParameters("", "", "", lineNumber - oldLineNumber - 2);
        ifRest.emitCode();
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
