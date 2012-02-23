package org.zza.parser;

import java.util.ArrayList;

public class RecentTokensStack {
    
    private final int MAX_SIZE = 5;
    private final ArrayList<Entry> stack;
    
    public RecentTokensStack() {
        stack = new ArrayList<Entry>();
    }
    
    public void push(final Entry e) {
        if (stack.size() == MAX_SIZE) {
            stack.remove(0);
        }
        stack.add(e);
    }
}
