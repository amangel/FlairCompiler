package org.zza.parser;

import org.zza.scanner.CompilerTokenStream;


public class CompilerParser {
	
	private Stack ruleStack;
	private Table ruleTable;
	
    public CompilerParser(CompilerTokenStream tokenStream) {
        
    }
    
    public run(foo bar) {
    	ruleStack.push("$");
    	ruleStack.push("start symbol");
    	while(ruleStack.peek() != "$")
    	{
    		if(ruleStack.peek().terminal?)
    		{
    			if(ruleStack.peek() == i)
    			{
    				ruleStack.pop();
    				i.consume();
    			}
    			else
    			{
    				//FAIL SOME WAY
    			}
    		}
    		else
    		{
    			if(ruleTable.find(A,i).containsRule?)
    			{
    				ruleStack.pop();
    				ruleStack.push(ruleTable.find(A,i));
    			}
    			else
    			{
    				//Fail some way
    			}
    		}
    			
    		
    	}
    	
    }
    
}
