package org.zza.parser;

import java.util.ArrayList;
import java.util.Arrays;


public class TableCellEntry {
    
    private ArrayList<Entry> entries;
    
    public TableCellEntry(String entry) {
        entries = new ArrayList<Entry>();
        for (String s : entry.split("\\s")) {
            entries.add(new Entry(s));
        }
    }
    
    
}
