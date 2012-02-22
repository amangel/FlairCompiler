package org.zza.parser;

import org.zza.scanner.CompilerTokenStream;


public class CompilerParser {
	
    private final String EOF = "EOF";
    private final String startSymbol = "program";
	private Stack ruleStack;
	private Table ruleTable;
	
    public CompilerParser(CompilerTokenStream tokenStream) {
        
    }
    
    public run(foo bar) {
    	ruleStack.push(new TerminalEntry(EOF));
    	ruleStack.push(new TerminalEntry(startSymbol));
    	while(ruleStack.peek().getType() != EOF)
    	{
    		if(ruleStack.peek().isTerminal())
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
    			if(ruleTable.find(A,i).containsRule())
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
