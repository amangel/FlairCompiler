package org.zza.visitor;

import org.zza.parser.semanticstack.nodes.*;
import org.zza.semanticchecker.SemanticWarning;
import org.zza.semanticchecker.SemanticWarningList;
import org.zza.semanticchecker.Symbol;
import org.zza.semanticchecker.SymbolTable;

public class TypeCheckingVisitor extends NodeVisitor {
    
    private SymbolTable table;
    private String scope;
    
    public TypeCheckingVisitor() {
        table = SymbolTable.getInstance();
    }
    
    @Override
    public String visit(ProgramNode node) {
        scope = "program";
        node.getDeclarations().accept(this);
        node.getbody().accept(this);
        return null;
    }
    
    @Override
    public String visit(VariableDeclarationNode node) {
        return null;
    }
    
    @Override
    public String visit(FunctionNode node) {
        String oldScope = scope;
        String id = node.getHeader().accept(this);
        node.getBody().accept(this);
        scope = oldScope;
        return null;
    }
    
    @Override
    public String visit(ParameterNode node) {
        return null;
    }
    
    @Override
    public String visit(AssignmentExpressionNode node) {
        String toReturn = "";
        String oldScope = scope;
        String leftHand = node.getLeftHand().accept(this);
        String rightHand = node.getRightHand().accept(this);
        if (leftHand.equals(rightHand)) {
            toReturn = leftHand;
        } else {
            if (leftHand.equals("real")) {
                toReturn = "real";                
            } else if (leftHand.equals("integer")) {
                SemanticWarningList.addWarning(SemanticWarning.makeNewWarning("Attempt to save a real into an integer: " 
                        + ((IdentifierNode)node.getLeftHand()).getValue()));
            }            
        }
        
        scope = oldScope;
        return toReturn;
    }
    
    @Override
    public String visit(CompoundStatementNode node) {
        boolean returnFound = false;
        for (SemanticNode sNode : node.getStatements()) {
            if (sNode instanceof ReturnStatementNode) {
                if (scope.substring(0,7).equals("program")) {
                    SemanticWarningList.addWarning(SemanticWarning.makeNewWarning("Return statement found in the main program. No."));
                }
                returnFound = true;
            } else if (returnFound) {
                StackPrintingVisitor spv = new StackPrintingVisitor();
//                System.out.println((sNode.getClass().cast(spv.visit(sNode)))); 
                SemanticWarningList.addWarning(SemanticWarning.makeNewWarning("Unreachable code found. Return statement not at the end of function call."));
            }
            sNode.accept(this);
        }
        if (!returnFound && !scope.substring(0,7).equals("program")) {
            SemanticWarningList.addWarning(SemanticWarning.makeNewWarning("Function found with no return statement."));
        }
        return null;
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
        return "real";
    }
    
    @Override
    public String visit(TypeNode node) {
        return null;
    }
    
    @Override
    public String visit(AllParametersNode node) {
        return null;
    }
    
    @Override
    public String visit(AllVariableDeclarationsNode node) {
        return null;
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
        return null;
    }
    
    @Override
    public String visit(ComparisonNode node) {
        String left = node.getLefthand().accept(this);
        String right = node.getRighthand().accept(this);
        return compare(left, right);
    }
    
    @Override
    public String visit(WhileExpressionNode node) {
        String comparison = node.getLeftHand().accept(this);
        node.getRightHand().accept(this);
        return null;
    }
    
    @Override
    public String visit(NegativeExpressionNode node) {
        return node.getContent().accept(this);
    }
    
    @Override
    public String visit(ProgramHeaderNode node) {
        return null;
    }
    
    @Override
    public String visit(DeclarationsNode node) {
        node.getFunctionDeclarations().accept(this);
        return null;
    }
    
    @Override
    public String visit(PrintStatementNode node) {
        node.getArgument().accept(this);
        return null;
    }
    
    @Override
    public String visit(FunctionCallNode node) {
        String id = ((IdentifierNode)node.getLeftHand()).getValue();
        String functionType = "";
        functionType = table.getSymbol("function_"+id).getType();
        String parameters = node.getRightHand().accept(this);
        if (functionType.equals(parameters)) {
            return functionType;
        } else {
            SemanticWarningList.addWarning(SemanticWarning.makeNewWarning("Function '"+id
                    +"' requires parameters '"+functionType +"'. Got: '"+ parameters+"'"));
            return null;
        }
    }
    
    @Override
    public String visit(FunctionHeadingNode node) {
        String s = ((IdentifierNode)node.getLefthand()).getValue();
        scope = scope + "_" + s;
        return null;
    }
    
    @Override
    public String visit(AllFunctionDeclarationsNode node) {
        String oldScope = scope;
        scope = "function";
        for(SemanticNode fNode : node.getArray()) {
            fNode.accept(this);
        }
        scope = oldScope;
        return null;
    }
    
    @Override
    public String visit(FunctionBodyNode node) {
        node.getBody().accept(this);
        return null;
    }
    
    @Override
    public String visit(ReturnStatementNode node) {
        return node.getArguments().accept(this);
    }
    
    @Override
    public String visit(IfStatementNode node) {
        node.getLefthand().accept(this);
        node.getMiddle().accept(this);
        node.getRighthand().accept(this);
        return null;
    }
    
    @Override
    public String visit(EmptyNode node) {
        // TODO Auto-generated method stub
        return null;
    }
    
    private String handleTwoFieldNode(TwoFieldNode node) {
        String leftHandSide = node.getLeftHand().accept(this);
        String rightHandSide = node.getRightHand().accept(this);
        return compare(leftHandSide, rightHandSide);
    }
    
    private String compare(String leftHandSide, String rightHandSide) {
        if (leftHandSide != null && rightHandSide != null) {
            if (leftHandSide.equals("integer")) {
                if (rightHandSide.equals("integer")) {
                    return "integer";
                } else if (rightHandSide.equals("real")){
                    //TODO: CONVERT LEFT HAND TO REAL?
                    return "real";
                } else {
                    SemanticWarningList.addWarning(SemanticWarning.makeNewWarning("Unknown type for variable '" + rightHandSide + "'. Undeclared variable."));
                }
            } else if (leftHandSide.equals("real")) {
                if (rightHandSide.equals("integer") || rightHandSide.equals("real")) {
                    //TODO: CONVERT RIGHT HAND TO REAL IF INT?
                    return "real";
                } else  {
                    SemanticWarningList.addWarning(SemanticWarning.makeNewWarning("Unknown type for variable '" + rightHandSide + "'. Undeclared variable."));                
                }
            } else {
                SemanticWarningList.addWarning(SemanticWarning.makeNewWarning("Unknown type for variable '" + leftHandSide + "'. Undeclared variable."));
            }
        }
        return null;
    }
}
