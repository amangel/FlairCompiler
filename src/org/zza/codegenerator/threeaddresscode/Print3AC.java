package org.zza.codegenerator.threeaddresscode;


public class Print3AC extends ThreeAddressCode {

    private ThreeAddressCode toUse;
    
    public Print3AC(int lineNumber) {
        super(lineNumber);
    }

    @Override
    public void setParameters(String first, String second, String third) {
//        firstParam = first;
//        secondParam = second;
//        thirdParam = third;
        
        if ("0123456789".contains(Character.toString(first.charAt(0)))) {
            toUse = new PrintLiteral3AC(lineNumber);
        } else {
            toUse = new PrintVariable3AC(lineNumber);
        }
        toUse.setParameters(first, second, third);
    }
    
    @Override
    public void emitCode() {
        toUse.emitCode();
    }

    @Override
    public void emitComments() {
        toUse.emitComments();
    }

    @Override
    public int getEmittedSize() {
        return toUse.getEmittedSize();
    }
    
}
