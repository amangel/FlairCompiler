package org.zza.optimizer;

import org.zza.parser.semanticstack.nodes.*;
import org.zza.semanticchecker.SemanticWarning;
import org.zza.semanticchecker.SemanticWarningList;
import org.zza.semanticchecker.Symbol;
import org.zza.semanticchecker.SymbolTable;
import org.zza.visitor.*;
import java.util.ArrayList;


public class OptimizingVisitor extends NodeVisitor {
    
    
    private SymbolTable table;
    private String scope;
    private static String EMPTY = "";
    
    public OptimizingVisitor() {
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
        node.getHeader().accept(this);
        node.getBody().accept(this);
        return EMPTY;
    }
    
    @Override
    public String visit(ParameterNode node) {
        return EMPTY;
    }
    
    @Override
    public String visit(AssignmentExpressionNode node) {
        String toReturn = "";
        String leftHand = node.acceptVisitorLeftHand(this);
        String rightHand = node.acceptVisitorRightHand(this);
        if (leftHand.equals(rightHand)) {
            toReturn = leftHand;
        } else {
            if (leftHand.equals("real")) 
                toReturn = "real";                           
        }
         return toReturn;
    }
    
    
    @Override
    public String visit(CompoundStatementNode node) {
        boolean returnFound = false;
        int indexOfReturn = -1;
        int i = 0;
        //Find index and see if return is in the compound statement
        for (SemanticNode sNode : node.getStatements()) {
        	if (sNode instanceof ReturnStatementNode) {
                  returnFound = true;
                  indexOfReturn = i;
                  //System.out.println("Index of return is : " + indexOfReturn);
            }
            i++;
            sNode.accept(this);
        }

        if(returnFound){

          ArrayList<SemanticNode> myStatements = new ArrayList<SemanticNode>();
          myStatements = node.getStatements();
          int stateSize = node.getStatements().size();
          
          //System.out.println("This is old: " + myStatements);
          //System.out.println("The size of it is" + myStatements.size());
          int indexOfStartDelete = indexOfReturn+1;
          
          for(int x = 0; x < stateSize-indexOfStartDelete; x++){
        	  myStatements.remove(indexOfStartDelete);
          }

          node.setStatements(myStatements);
          //System.out.println("This is new: " + node.getStatements());
        }
        return EMPTY;
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
                    SemanticWarningList.addWarning(SemanticWarning.makeNewWarning("Unknown type for variable '" + rightHandSide + "'. Undeclared variable."));
                }
            } else if (leftHandSide.equals("real")) {
                if (rightHandSide.equals("integer") || rightHandSide.equals("real")) {
                    return "real";
                } 
            } 
        return EMPTY;
    }
}

