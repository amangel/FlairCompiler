package org.zza.scanner;

import java.util.concurrent.ConcurrentLinkedQueue;

public class CompilerTokenStream {
    
    // private final ArrayList<CompilerToken> tokens;
    private final ConcurrentLinkedQueue<CompilerToken> otherTokens;
    private CompilerToken current;
    private boolean completed;
    private boolean waiting;
    
    public CompilerTokenStream(final ConcurrentLinkedQueue<CompilerToken> list) {
        otherTokens = list;
        // tokens = list;
        current = null;
        completed = false;
    }
    
    public void addToken(final CompilerToken token) {
        otherTokens.add(token);
        if (waiting) {
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
            if ((otherTokens.peek() == null) && !completed) {
                try {
                    waiting = true;
                    wait();
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return otherTokens.remove();
        }
    }
    
    public boolean hasNext() {
        return (otherTokens.peek() != null) || !completed;
    }
    
    public CompilerToken peek() {
        if (current == null) {
            current = getNext();
        }
        return current;
    }
    
    public boolean isEmpty() {
        return (otherTokens.peek() == null) && completed;
    }
}
