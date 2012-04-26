package org.zza.codegenerator;


public class Address {
    
    public static final int STACK_FRAME = 15;
    public static final int PROGRAM_FRAME = 17;
    private int offset;
    private int register;

    public Address() {
    }
    
    public int getOffset() {
        return offset;
    }
    
    public void setOffset(int offset) {
        this.offset = offset;
    }
    
    public void setRegister(int register) {
        this.register = register;
    }
    
    public String getRegisterValue() {
        switch(register) {
            case STACK_FRAME:
                return "(3)";
            case PROGRAM_FRAME:
                return "(2)";
            default:
                return "";
        }
    }
}
