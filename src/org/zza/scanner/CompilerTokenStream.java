package org.zza.scanner;

import java.util.ArrayList;

public class CompilerTokenStream {
    
    private final ArrayList<CompilerToken> tokens;
    private CompilerToken current;
    private boolean completed;
    private boolean waiting;
    
    public CompilerTokenStream(final ArrayList<CompilerToken> list) {
        tokens = list;
        current = null;
        completed = false;
    }
    
    public void addToken(CompilerToken token) {
        tokens.add(token);
        if(waiting) {
            notify();
            waiting = false;
        }
    }
    
    public void finishedStream() {
        completed = true;
    }
    
    public boolean isStreamFinished() {
        return completed;
    }
        
    public CompilerToken getNext() {
        if (current != null) {
            return current;
        } else {
            if(tokens.size() == 0 && !completed) {
                try {
                    waiting = true;
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return tokens.remove(0);
        }
    }
    
    public boolean hasNext() {
        return tokens.size() > 0 || !completed;
    }
    
    public CompilerToken peek() {
        if (current == null) {
            current = getNext();
        }
        return current;
    }
    
    public boolean isEmpty() {
        return tokens.size() == 0 && completed;
    }
}
