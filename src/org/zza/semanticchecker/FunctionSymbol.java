package org.zza.semanticchecker;


public class FunctionSymbol extends Symbol {

    String returnType;
    
    public FunctionSymbol() {
    }

    public void setReturnType(String type) {
        returnType = type;
    }
    
    public String getReturnType() {
        return returnType;
    }  
    
    public String toString() {
        return "FunctionSymbol: \n\tid: "+id + " \n\tvalue: " + value + " \n\ttype: " + type + " \n\treturnType: " + returnType;
    }
}
