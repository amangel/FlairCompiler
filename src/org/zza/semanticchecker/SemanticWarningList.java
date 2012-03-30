package org.zza.semanticchecker;

import java.util.ArrayList;


public class SemanticWarningList {
    
    private static SemanticWarningList _instance;
    private static ArrayList<SemanticWarning> warnings;
    
    private SemanticWarningList() {
        warnings = new ArrayList<SemanticWarning>();
    }
    
    public static SemanticWarningList getInstance() {
        if (_instance == null) {
            _instance = new SemanticWarningList();
        }
        return _instance;
    }
    
    public static void addWarning(SemanticWarning warning) {
        warnings.add(warning);
    }
    
    public void printWarnings() {
        for (SemanticWarning warning : warnings) {
            System.out.println(warning);
        }
    }

    public boolean isEmpty() {
        return warnings.isEmpty();
    }
}
