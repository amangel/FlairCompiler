import java.util.ArrayList;

public class CompilerTokenStream {
    
    private int                      index;
    private ArrayList<CompilerToken> tokens;
    private CompilerToken            current;
    
    public CompilerTokenStream(ArrayList<CompilerToken> list) {
        tokens = list;
        index = 0;
        current = null;
    }
    
    public CompilerToken getNext() {
        if (current != null) {
            return current;
        } else {
            return tokens.get(index++);
        }
    }
    
    public boolean hasNext() {
        return index < tokens.size();
    }
    
    public CompilerToken peek() {
        if (current == null) {
            current = getNext();
        }
        return current;
    }
}
