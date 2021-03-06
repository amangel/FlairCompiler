package org.zza.visitor;

import org.zza.parser.semanticstack.nodes.*;
import org.zza.semanticchecker.FunctionSymbol;
import org.zza.semanticchecker.Symbol;
import org.zza.semanticchecker.SymbolTable;


public class SymbolTableBuilderVisitor extends NodeVisitorStringAdapter {
    
    private String scope;
    
    private SymbolTable table;
    private SymbolFactory symbolFactory;
    
    private Symbol symbol;
    
    private final String EMPTY = "";
    
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
//        node.getbody().accept(this);
//        return "header: " + header + "\ndeclarations: " + declarations + "\nbody: "+body;
        return EMPTY;
    }

    @Override
    public String visit(VariableDeclarationNode node) {
        String id = node.acceptVisitorLeftHand(this);
        String type = node.acceptVisitorRightHand(this);
        symbol = symbolFactory.getSymbol(id, "variable", type);
        table.addSymbol(symbol, scope+"_"+id);
        return EMPTY;
    }

    @Override
    public String visit(FunctionNode node) {
        String oldScope = scope;
        scope = "function";
        node.getHeader().accept(this);
        node.getBody().accept(this);
        scope = oldScope;
        return EMPTY;
    }

    @Override
    public String visit(ParameterNode node) {
        String identifier = node.acceptVisitorLeftHand(this);
        String type = node.acceptVisitorRightHand(this);
        symbol = symbolFactory.getSymbol(identifier, "variable", type);
        table.addSymbol(symbol, scope+"_"+identifier);
        return type;
    }

    @Override
    public String visit(IdentifierNode node) {
        return node.getValue();
    }

    @Override
    public String visit(TypeNode node) {
        return node.getType();
    }

    @Override
    public String visit(AllParametersNode node) {
        String toReturn = "";
        for (SemanticNode pNode : node.getArray()) {
            toReturn += pNode.accept(this) + "_";
        }
        if(toReturn.length() > 0) {
            toReturn = toReturn.substring(0, toReturn.length() -1);
        }
        return toReturn;
    }

    @Override
    public String visit(AllVariableDeclarationsNode node) {
        for (SemanticNode vNode : node.getArray()) {
            vNode.accept(this);
        }
        return EMPTY;
    }

    @Override
    public String visit(ProgramHeaderNode node) {
        node.getParameters().accept(this);
        return EMPTY;
    }

    @Override
    public String visit(DeclarationsNode node) {
        node.getVariableDeclarations().accept(this);
        node.getFunctionDeclarations().accept(this);
        return EMPTY;
    }

    @Override
    public String visit(FunctionHeadingNode node) {
        String id = node.acceptVisitorLeftHand(this);
        scope += "_" + id;
        String parameters = node.acceptVisitorMiddle(this);
        String returnType = node.acceptVisitorRightHand(this);
        FunctionSymbol funcSymbol = (FunctionSymbol) symbolFactory.getSymbol(id, "function", parameters);
        funcSymbol.setReturnType(returnType);
        table.addSymbol(funcSymbol, scope);
        return EMPTY;
    }

    @Override
    public String visit(AllFunctionDeclarationsNode node) {
        for(SemanticNode fNode : node.getArray() ) {
            fNode.accept(this);
        }
        return EMPTY;
    }
    
}
