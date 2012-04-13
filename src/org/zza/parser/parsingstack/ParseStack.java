package org.zza.parser.parsingstack;

import java.util.ArrayList;
import java.util.List;

public class ParseStack {
    
    private final ArrayList<Entry> stack;
    
    public ParseStack() {
        stack = new ArrayList<Entry>();
    }
    
    public boolean push(final Entry rule) {
        
        return stack.add(rule);
    }
    
    public Entry pop() throws ArrayIndexOutOfBoundsException {
        return stack.remove(getLastIndex());
    }
    
    public Entry peek() throws ArrayIndexOutOfBoundsException {
        return stack.get(getLastIndex());
    }
    
    private int getLastIndex() throws ArrayIndexOutOfBoundsException {
        if (stack.size() > 0) {
            return stack.size() - 1;
        } else {
            throw new ArrayIndexOutOfBoundsException("Stack was empty");
        }
    }
    
    @Override
    public String toString() {
        return stack.toString();
    }
    
    public boolean notEmpty() {
        return stack.size() > 0;
    }

    public void addToParseStack(final List<Entry> tableEntry) {
        for (int i = tableEntry.size() - 1; i >= 0; i--) {
            if (!tableEntry.get(i).isEpsilon()) {
                push(tableEntry.get(i));
            }
        }
    }
}
