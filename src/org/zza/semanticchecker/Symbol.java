package org.zza.semanticchecker;


public abstract class Symbol {
    
    protected String id;
    protected String value;
    protected String type;
    
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdentifier() {
        return id;
    }

    public void setIdentifier(String id) {
        this.id = id;
    }

    
    public String toString() {
        return "Symbol: \n\tid: "+id + " \n\tvalue: " + value + " \n\ttype: " +type;
    }
}
