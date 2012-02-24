package org.zza.scanner;

import java.util.ArrayList;

public class CompilerTokenStream {
    
    private final ArrayList<CompilerToken> tokens;
    private CompilerToken current;
    
    public CompilerTokenStream(final ArrayList<CompilerToken> list) {
        tokens = list;
        //index = 0;
        current = null;
    }
    
    public CompilerToken getNext() {
        //System.out.println("stream: "+tokens);
        if (current != null) {
            return current;
        } else {
            //return tokens.get(index++);
            return tokens.remove(0);
        }
        
    }
    
    public boolean hasNext() {
        return tokens.size() > 0;
    }
    
    public CompilerToken peek() {
        if (current == null) {
            current = getNext();
        }
        return current;
    }
    
    public boolean isEmpty() {
        return tokens.size() == 0;
    }
}
