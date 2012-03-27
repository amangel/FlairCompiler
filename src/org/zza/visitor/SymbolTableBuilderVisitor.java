package org.zza.visitor;

import org.zza.parser.semanticstack.nodes.*;
import org.zza.semanticchecker.FunctionSymbol;
import org.zza.semanticchecker.Symbol;
import org.zza.semanticchecker.SymbolTable;


public class SymbolTableBuilderVisitor extends NodeVisitor {
    
    private String scope;
    
    private SymbolTable table;
    private SymbolFactory symbolFactory;
    
    private Symbol symbol;
    
    public SymbolTableBuilderVisitor() {
        symbol = null;
        scope = "";
        table = SymbolTable.getInstance();
        symbolFactory = SymbolFactory.getInstance();
    }

    @Override
    public String visit(ProgramNode node) {
        scope = "program";
        node.getHeader().accept(this);
        node.getDeclarations().accept(this);
        node.getbody().accept(this);
//        return "header: " + header + "\ndeclarations: " + declarations + "\nbody: "+body;
        return null;
    }

    @Override
    public String visit(VariableDeclarationNode node) {
        String id = node.getLeftHand().accept(this);
        String type = node.getRightHand().accept(this);
        symbol = symbolFactory.getSymbol(id, "variable", type);
        table.addSymbol(symbol, scope+"_"+id);
        return null;
    }

    @Override
    public String visit(FunctionNode node) {
        String oldScope = scope;
        scope = "function";
        node.getHeader().accept(this);
        node.getBody().accept(this);
        scope = oldScope;
        return null;
    }

    @Override
    public String visit(ParameterNode node) {
        String identifier = node.getLeftHand().accept(this);
        String type = node.getRightHand().accept(this);
        symbol = symbolFactory.getSymbol(identifier, "variable", type);
        table.addSymbol(symbol, scope+"_"+identifier);
        return type;
    }

    @Override
    public String visit(AssignmentExpressionNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(CompoundStatementNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(DivisionExpressionNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(IdentifierNode node) {
        return node.getValue();
    }

    @Override
    public String visit(IntegerNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(MinusExpressionNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(MultiplicationExpressionNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(PlusExpressionNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(RealNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(TypeNode node) {
        return node.getType();
    }

    @Override
    public String visit(AllParametersNode node) {
        for (SemanticNode pNode : node.getArray()) {
            pNode.accept(this);
        }
        return null;
    }

    @Override
    public String visit(AllVariableDeclarationsNode node) {
        for (SemanticNode vNode : node.getArray()) {
            vNode.accept(this);
        }
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
        node.getParameters().accept(this);
        return null;
    }

    @Override
    public String visit(DeclarationsNode node) {
        node.getVariableDeclarations().accept(this);
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
        String id = node.getLefthand().accept(this);
        scope += "_" + id;
        String parameters = node.getMiddle().accept(this);
        String returnType = node.getRighthand().accept(this);
        FunctionSymbol funcSymbol = (FunctionSymbol) symbolFactory.getSymbol(id, "function", parameters);
        funcSymbol.setReturnType(returnType);
        table.addSymbol(funcSymbol, scope);
        return null;
    }

    @Override
    public String visit(AllFunctionDeclarationsNode node) {
        for(SemanticNode fNode : node.getArray() ) {
            fNode.accept(this);
        }
        return null;
    }

    @Override
    public String visit(FunctionBodyNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(ReturnStatementNode node) {
        // TODO Auto-generated method stub
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
    
}
