package org.zza.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Table {
    
    private HashMap<String, Map<String, Integer>> tableContents;
    private ArrayList<ArrayList<Entry>> ruleArray;
    
    public Table() {
        
    }
    
    public ArrayList<Entry> find(final String A, final String i) {
        final int index = tableContents.get(A).get(i);
        return ruleArray.get(index);
    }
    
}
