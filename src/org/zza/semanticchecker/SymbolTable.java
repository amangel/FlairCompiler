package org.zza.semanticchecker;


public class SymbolTable {
    
    private static SymbolTable _instance;
    
    private SymbolTable() {
        
    }
    
    public static SymbolTable getInstance() {
        if (_instance == null) {
            _instance = new SymbolTable();
        }
        return _instance;
    }
    
    public void addSymbol(Symbol symbol) {
        //TODO
    }
    
    public Symbol getSymbol(String symbolName) {
        //TODO
        return null;
    }
    
}
