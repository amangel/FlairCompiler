package org.zza.parser;

import org.zza.scanner.CompilerToken;


public class Entry {
    
    private String entry;
    private int type;
    
    public Entry(String s) {
        entry = s;
        setType();
    }
    
    public int getType() {
        return type;
    }
    
    private void setType() {
        if (entry.equalsIgnoreCase("<IDENTIFIER>")) {
            type = CompilerToken.IDENTIFIER;
        } else if (entry.equalsIgnoreCase("*")) {
            type = CompilerToken.MULTIPLICATION;
        } else if (entry.equalsIgnoreCase("/")) {
            type = CompilerToken.DIVISION;
        } else if (entry.equalsIgnoreCase("=")) {
            type = CompilerToken.EQUAL;
        } else if (entry.equalsIgnoreCase("<")) {
            type = CompilerToken.LESS_THAN;
        } else if (entry.equalsIgnoreCase(">")) {
            type = CompilerToken.GREATER_THAN;
        } else if (entry.equalsIgnoreCase("<=")) {
            type = CompilerToken.LESS_THAN_OR_EQ;
        } else if (entry.equalsIgnoreCase(">=")) {
            type = CompilerToken.GREATER_THAN_OR_EQ;
        } else if (entry.equalsIgnoreCase("!=")) {
            type = CompilerToken.NOT_EQUAL;
        } else if (entry.equalsIgnoreCase("+")) {
            type = CompilerToken.PLUS;
        } else if (entry.equalsIgnoreCase("-")) {
            type = CompilerToken.MINUS;
        } else if (entry.equalsIgnoreCase("if")) {
            type = CompilerToken.IF;
        } else if (entry.equalsIgnoreCase("then")) {
            type = CompilerToken.THEN;
        } else if (entry.equalsIgnoreCase("else")) {
            type = CompilerToken.ELSE;
        } else if (entry.equalsIgnoreCase("function")) {
            type = CompilerToken.FUNCTION;
        } else if (entry.equalsIgnoreCase("while")) {
            type = CompilerToken.WHILE;
        } else if (entry.equalsIgnoreCase("begin")) {
            type = CompilerToken.BEGIN;
        } else if (entry.equalsIgnoreCase("end")) {
            type = CompilerToken.END;
        } else if (entry.equalsIgnoreCase("(")) {
            type = CompilerToken.OPEN_PAREN;
        } else if (entry.equalsIgnoreCase("integer")) {
            type = CompilerToken.INTEGER_DECLARATION;
        } else if (entry.equalsIgnoreCase("real")) {
            type = CompilerToken.REAL_DECLARATION;
        } else if (entry.equalsIgnoreCase("program")) {
            type = CompilerToken.PROGRAM;
        } else if (entry.equalsIgnoreCase("var")) {
            type = CompilerToken.VAR;
        } else if (entry.equalsIgnoreCase(",")) {
            type = CompilerToken.COMMA;
        } else if (entry.equalsIgnoreCase(".")) {
            type = CompilerToken.PERIOD;
        } else if (entry.equalsIgnoreCase(";")) {
            type = CompilerToken.SEMICOLON;
        } else if (entry.equalsIgnoreCase("<INTEGER>")) {
            type = CompilerToken.INTEGER_VALUE;
        } else if (entry.equalsIgnoreCase("<REAL>")) {
            type = CompilerToken.REAL_VALUE;
        }
        
    }
    
}
