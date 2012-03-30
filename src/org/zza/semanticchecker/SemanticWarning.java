package org.zza.semanticchecker;


public class SemanticWarning {
    
    private String warning;
    
    public SemanticWarning(String s) {
        warning = s;
    }
    
    public String getWarning() {
        return warning;
    }
    
    public static SemanticWarning makeNewWarning(String message) {
        return new SemanticWarning(message);
    }
    
    public String toString() {
        return "Warning: "+warning;
    }
}
