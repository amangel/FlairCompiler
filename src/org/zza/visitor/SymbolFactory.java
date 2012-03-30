package org.zza.visitor;

import java.util.HashMap;

import org.zza.semanticchecker.FunctionSymbol;
import org.zza.semanticchecker.Symbol;
import org.zza.semanticchecker.VariableSymbol;


public class SymbolFactory {
    
    private static SymbolFactory _instance;
    private HashMap<String, Class<? extends Symbol>> table;
    
    private SymbolFactory() {
        table = new HashMap<String, Class<? extends Symbol>>();
        table.put("variable", VariableSymbol.class);
        table.put("function", FunctionSymbol.class);
    }
    
    public static SymbolFactory getInstance() {
        if (_instance == null) {
            _instance = new SymbolFactory();
        }
        return _instance;
    }

    public Symbol getSymbol(String name, String nodeType, String symbolType) {
        Symbol newSymbol = null;
        try {
            newSymbol = table.get(nodeType).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        newSymbol.setIdentifier(name);
        newSymbol.setType(symbolType);
        return newSymbol;
    }
    
}
