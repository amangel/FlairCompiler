package org.zza.visitor;

import org.zza.parser.semanticstack.nodes.*;
import org.zza.semanticchecker.SemanticWarning;
import org.zza.semanticchecker.SemanticWarningList;
import org.zza.semanticchecker.Symbol;
import org.zza.semanticchecker.SymbolTable;

public class TypeCheckingVisitor extends NodeVisitor {
    
    
    private SymbolTable table;
    private String scope;
    private static String EMPTY = "";
    
    public TypeCheckingVisitor() {
        table = SymbolTable.getInstance();
        scope = "program";
    }
    
    @Override
    public String visit(ProgramNode node) {
        node.getDeclarations().accept(this);
        node.getbody().accept(this);
        return EMPTY;
    }
    
    @Override
    public String visit(VariableDeclarationNode node) {
        return EMPTY;
    }
    
    @Override
    public String visit(FunctionNode node) {
        String oldScope = scope;
        node.getHeader().accept(this);
        node.getBody().accept(this);
        scope = oldScope;
        return EMPTY;
    }
    
    @Override
    public String visit(ParameterNode node) {
        return EMPTY;
    }
    
    @Override
    public String visit(AssignmentExpressionNode node) {
        String toReturn = "";
        String oldScope = scope;
        String leftHand = node.acceptVisitorLeftHand(this);
        String rightHand = node.acceptVisitorRightHand(this);
        if (leftHand.equals(rightHand)) {
            toReturn = leftHand;
        } else {
            if (leftHand.equals("real")) {
                toReturn = "real";                
            } else if (leftHand.equals("integer")) {
                SemanticWarningList.addWarning(SemanticWarning.makeNewWarning(
                        "Attempt to save a "+rightHand+" into an "+leftHand+": " 
                                + ((IdentifierNode)node.getLeftHand()).getValue()));
            }            
        }
        
        scope = oldScope;
        return toReturn;
    }
    
    private boolean isWithinProgramScope() {
        return scope.substring(0,7).equals("program");
    }
    
    @Override
    public String visit(CompoundStatementNode node) {
        boolean returnFound = false;
        for (SemanticNode sNode : node.getStatements()) {
            if (sNode instanceof ReturnStatementNode) {
                if (isWithinProgramScope()) {
                    errorEncountered(new Exception("Return statement found in the main program. No."));
                }
                returnFound = true;
            } else if (returnFound) {
                SemanticWarningList.addWarning(SemanticWarning.makeNewWarning(
                        "Unreachable code found. Return statement not at the end of function '"
                                +getFunctionName(scope)+"' call."));
            }
            sNode.accept(this);
        }
        if (!returnFound && !isWithinProgramScope()) {
            SemanticWarningList.addWarning(SemanticWarning.makeNewWarning("Function '" 
                    + getFunctionName(scope) + "' found with no return statement."));
        }
        return EMPTY;
    }
    
    private String getFunctionName(String string) {
        String[] parts = string.split("_");
        return parts[parts.length-1];
    }
    
    @Override
    public String visit(DivisionExpressionNode node) {
        return handleTwoFieldNode(node);
    }
    
    @Override
    public String visit(IdentifierNode node) {
        String tempScope = scope + "_" + node.getValue();
        Symbol symbol = table.getSymbol(tempScope);
        if (symbol != null) {
            return symbol.getType();
        } else {
            return node.getValue();
        }
    }
    
    @Override
    public String visit(IntegerNode node) {
        return "integer";
    }
    
    @Override
    public String visit(MinusExpressionNode node) {
        return handleTwoFieldNode(node);
    }
    
    @Override
    public String visit(MultiplicationExpressionNode node) {
        return handleTwoFieldNode(node);
    }
    
    @Override
    public String visit(PlusExpressionNode node) {
        return handleTwoFieldNode(node);
    }
    
    @Override
    public String visit(RealNode node) {
        errorEncountered(new Exception("This Flair compiler does not support the use of real numbers.\n\n"));
        return "real";
    }
    
    @Override
    public String visit(TypeNode node) {
        return EMPTY;
    }
    
    @Override
    public String visit(AllParametersNode node) {
        return EMPTY;
    }
    
    @Override
    public String visit(AllVariableDeclarationsNode node) {
        return EMPTY;
    }
    
    @Override
    public String visit(ArgumentNode node) {
        String toReturn = "";
        for (SemanticNode aNode : node.getArguments()) {
            toReturn += aNode.accept(this) + "_";
        }
        if(toReturn.length() > 0) {
            toReturn = toReturn.substring(0, toReturn.length() -1);
        }
        return toReturn;
    }
    
    @Override
    public String visit(CompareOperatorNode node) {
        return EMPTY;
    }
    
    @Override
    public String visit(ComparisonNode node) {
        String left = node.acceptVisitorLeftHand(this);
        String right = node.acceptVisitorRightHand(this);
        return compare(left, right);
    }
    
    @Override
    public String visit(WhileExpressionNode node) {
        node.acceptVisitorLeftHand(this);
        node.acceptVisitorRightHand(this);
        return EMPTY;
    }
    
    @Override
    public String visit(NegativeExpressionNode node) {
        return node.getContent().accept(this);
    }
    
    @Override
    public String visit(ProgramHeaderNode node) {
        return EMPTY;
    }
    
    @Override
    public String visit(DeclarationsNode node) {
        node.getFunctionDeclarations().accept(this);
        return EMPTY;
    }
    
    @Override
    public String visit(PrintStatementNode node) {
        node.getArgument().accept(this);
        return EMPTY; 
    }
    
    @Override
    public String visit(FunctionCallNode node) {
        String id = ((IdentifierNode)node.getLeftHand()).getValue();
        String functionType = "";
        functionType = table.getSymbol("function_"+id).getType();
        String parameters = node.acceptVisitorRightHand(this);
        if (functionType.equals(parameters)) {
            return functionType;
        } else {
            SemanticWarningList.addWarning(SemanticWarning.makeNewWarning("Function '"+id
                    +"' requires parameters '"+functionType +"'. Got: '"+ parameters+"'"));
            return EMPTY;
        }
    }
    
    @Override
    public String visit(FunctionHeadingNode node) {
        String s = ((IdentifierNode)node.getLefthand()).getValue();
        scope = scope + "_" + s;
        return EMPTY;
    }
    
    @Override
    public String visit(AllFunctionDeclarationsNode node) {
        String oldScope = scope;
        scope = "function";
        for(SemanticNode fNode : node.getArray()) {
            fNode.accept(this);
        }
        scope = oldScope;
        return EMPTY;
    }
    
    @Override
    public String visit(FunctionBodyNode node) {
        node.getBody().accept(this);
        return EMPTY;
    }
    
    @Override
    public String visit(ReturnStatementNode node) {
        return node.getArguments().accept(this);
    }
    
    @Override
    public String visit(IfStatementNode node) {
        node.acceptVisitorLeftHand(this);
        node.acceptVisitorMiddle(this);
        node.acceptVisitorRightHand(this);
        return EMPTY;
    }
    
    @Override
    public String visit(EmptyNode node) {
        // TODO Throw an error if this is encountered?
        return null;
    }
    
    private String handleTwoFieldNode(TwoFieldNode node) {
        String leftHandSide = node.acceptVisitorLeftHand(this);
        String rightHandSide = node.acceptVisitorRightHand(this);
        return compare(leftHandSide, rightHandSide);
    }
    
    private String compare(String leftHandSide, String rightHandSide) {
        if (leftHandSide.equals("integer")) {
            if (rightHandSide.equals("integer")) {
                return "integer";
            } else if (rightHandSide.equals("real")){
                return "real";
            } else {
                SemanticWarningList.addWarning(SemanticWarning.makeNewWarning(
                        "Unknown type for variable '" + rightHandSide + "'. Undeclared variable."));
            }
        } else if (leftHandSide.equals("real")) {
            if (rightHandSide.equals("integer") || rightHandSide.equals("real")) {
                return "real";
            } else  {
                SemanticWarningList.addWarning(SemanticWarning.makeNewWarning(
                        "Unknown type for variable '" + rightHandSide + "'. Undeclared variable."));                
            }
        } else {
            SemanticWarningList.addWarning(SemanticWarning.makeNewWarning(
                    "Unknown type for variable '" + leftHandSide + "'. Undeclared variable."));
        }
        return EMPTY;
    }
    
    public static void errorEncountered(final Exception e) {
        System.out.println("An error was encountered while parsing the program.");
        System.out.println(e.getMessage());
        System.exit(-1);
    }
}
