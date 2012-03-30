package org.zza.semanticchecker;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class SymbolTable {
    
    private static SymbolTable _instance;
    private Map<String, Symbol> table;
    
    private SymbolTable() {
        table = Collections.synchronizedMap(new HashMap<String, Symbol>(50) );
    }
    
    public static SymbolTable getInstance() {
        if (_instance == null) {
            _instance = new SymbolTable();
        }
        return _instance;
    }
    
    public void addSymbol(Symbol symbol, String symbolName) {
        table.put(symbolName, symbol);
    }
    
    public Symbol getSymbol(String symbolName) {
        if(table.containsKey(symbolName)) {
            return table.get(symbolName);
        } else {
            SemanticWarningList.addWarning(SemanticWarning.makeNewWarning("Use before declaration: couldn't find symbol: "+symbolName + " in the table."));
            return null;
        }
    }

    public void printTable() {
        System.out.println("\n\nSymbolTable: ");
        for (String s : table.keySet()) {
            System.out.println(s + ": "+table.get(s));
        }
    }
    
}
