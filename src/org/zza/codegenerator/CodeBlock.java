package org.zza.codegenerator;

import java.util.ArrayList;

import org.zza.codegenerator.threeaddresscode.ThreeAddressCode;


public class CodeBlock {
    
    private ArrayList<ThreeAddressCode> list;
    private int size;

    public CodeBlock() {
        list = new ArrayList<ThreeAddressCode>();
        size = 0;
    }
    
    public void add(ThreeAddressCode code) {
        list.add(code);
        size += code.getEmittedSize();
    }
    
    public int getSize() {
        return size;
    }
    
    public void emitCode() {
        for (ThreeAddressCode code : list) {
            code.emitCode();
        }
    }
}
