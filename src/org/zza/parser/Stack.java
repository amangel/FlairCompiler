package org.zza.parser;

import java.util.ArrayList;


public class Stack {
	private ArrayList<Entry> stack;
	
	public Stack(){
		this.stack= new ArrayList<Entry>();
	}
	
	public boolean push(Entry rule){
		
	    return stack.add(rule);
	}
	
	public Entry pop() throws Exception{
		return stack.remove( getLastIndex() );
	}
	
	public Entry peek() throws Exception{
		return stack.get( getLastIndex() );
	}

	private int getLastIndex() throws Exception {
	    if(stack.size() > 0) {
	        return stack.size() - 1;
	    } else {
	        throw new ArrayIndexOutOfBoundsException("Stack was empty");
	    }
	}
}