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
//        System.out.println("adding to table: "+symbolName );
        table.put(symbolName, symbol);
    }
    
    public Symbol getSymbol(String symbolName) {
        String[] nameSplit = symbolName.split("_");
        if(table.containsKey(symbolName)) {
            return table.get(symbolName);
        } else if(table.containsKey("program_"+nameSplit[nameSplit.length-1])){
            return table.get("program_"+nameSplit[nameSplit.length-1]);
        } else {
//            System.out.println("Couldn't find symbol: "+symbolName);
            SemanticWarningList.addWarning(SemanticWarning.makeNewWarning("Use before declaration: couldn't find symbol: "+symbolName + " in the table."));
            return null;
        }
    }

    //TODO: OPTIMIZATION: check for unused symbols 
    //perhaps when each symbol is gotten, flag it as checked
    //make a function that will check all variables at once at the end.
    //generate warning if there are unused and provide the name
    
    public boolean contains(String symbolName) {
        return table.containsKey(symbolName);
    }
    
    public void printTable() {
        System.out.println("\n\nSymbolTable: ");
        for (String s : table.keySet()) {
            System.out.println(s + ": "+table.get(s));
        }
    }
    
}
