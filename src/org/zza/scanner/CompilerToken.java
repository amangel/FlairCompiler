package org.zza.scanner;

public class CompilerToken {
    
    private final String stringType;
    private final String value;
    
    public CompilerToken(final String strType, final String v) {
        stringType = strType;
        value = v;
    }
    
    public String getStringType() {
        return stringType;
    }
    
    public String getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return stringType;
    }

    public boolean isComment() {
        return stringType.equals("COMMENT");
    }
}
