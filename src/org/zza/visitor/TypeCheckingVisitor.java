package org.zza.visitor;

import org.zza.parser.semanticstack.nodes.*;
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(FunctionNode node) {
        String id = node.getHeader().accept(this);
        node.getBody().accept(this);
        return null;
    }

    @Override
    public String visit(ParameterNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(AssignmentExpressionNode node) {
        String oldScope = scope;
        String leftHand = node.getLeftHand().accept(this);
        String rightHand = node.getRightHand().accept(this);
        if (leftHand.equals(rightHand)) {
            System.out.println("match");
        } else {
            System.out.println("no match");
        }
        scope = oldScope;
        return null;
    }

    @Override
    public String visit(CompoundStatementNode node) {
        for (SemanticNode sNode : node.getStatements()) {
            sNode.accept(this);
        }
        return null;
    }

    @Override
    public String visit(DivisionExpressionNode node) {
        return handleTwoFieldNode(node);
    }

    @Override
    public String visit(IdentifierNode node) {
        scope += "_" + node.getValue();
        System.out.println(scope);
        return table.getSymbol(scope).getType();
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(ArgumentNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(CompareNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(ComparisonNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(WhileExpressionNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(NegativeExpressionNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(ProgramHeaderNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(DeclarationsNode node) {
        node.getFunctionDeclarations().accept(this);
        return null;
    }

    @Override
    public String visit(PrintStatementNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(FunctionCallNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(FunctionHeadingNode node) {
        node.getLefthand().accept(this);
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
        node.getArguments().accept(this);
        return null;
    }

    @Override
    public String visit(IfStatementNode node) {
        // TODO Auto-generated method stub
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
        return compare(leftHandSide, rightHandSide, node);
    }
    
    private String compare(String leftHandSide, String rightHandSide, TwoFieldNode node) {
        if (leftHandSide.equals("integer")) {
            if (rightHandSide.equals("integer")) {
                return "integer";
            } else if (rightHandSide.equals("real")){
                //TODO: CONVERT LEFT HAND TO REAL?
                return "real";
            } else {
                System.out.println("comparing, else statement: "+leftHandSide + " " + rightHandSide);
            }
        } else if (leftHandSide.equals("real")) {
            if (rightHandSide.equals("integer") || rightHandSide.equals("real")) {
                //TODO: CONVERT RIGHT HAND TO REAL IF INT?
                return "real";
            } else  {
                System.out.println("comparing, else statement: "+leftHandSide + " " + rightHandSide);                
            }
        } else {
            System.out.println("comparing, else statement: "+leftHandSide + " " + rightHandSide);
        }
        return null;
    }
}
