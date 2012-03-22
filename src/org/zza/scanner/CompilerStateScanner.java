package org.zza.scanner;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CompilerStateScanner {
    
    private ArrayList<String> reservedStrings;
    private Map<String, String> states;
//    private ArrayList<CompilerToken> tokens;
    private String currentToken;
    private final CompilerTokenStream stream;
    private HashMap<String, String> tokenStateMap;
    
    public CompilerStateScanner(final StringBuffer buffer, CompilerTokenStream tokenStream) {
        stream = tokenStream;
        createScannerMaps();
        generateTokens(new StringReader(buffer.toString()));
    }
    
    private void addTokenToStream(CompilerToken token) {
        stream.addToken(token);
    }
    
    public CompilerTokenStream getTokenStream() {
        return stream;
    }
    
    private void generateTokens(final StringReader input) {
        currentToken = "";
        String state = "start";
        
        int n;
        try {
            while ((n = input.read()) != -1) {
                final String next = Character.toString((char) n);
                
                try {
                    state = handleStateTransition(next, state);
                } catch (final LexicalException e) {
                    e.printStackTrace();
                    currentToken = "";
                    state = "start";
                }
            }
            handleStateTransition("", state);
            currentToken = "EOF";
            handleStateTransition("EOF", "EOF");
            stream.finishedStream();
            
        } catch (final LexicalException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        
    }
    
    private String handleStateTransition(final String next, final String state) throws LexicalException {
        if (states.containsKey(state + next)) {
            currentToken += next;
            return states.get(state + next);
        } else {
            if (currentToken.trim().length() > 0) {
                //tokens.add(makeToken(currentToken.trim(), state));
                addTokenToStream(makeToken(currentToken.trim(), state));
            }
            if (states.containsKey("start" + next)) {
                currentToken = next;
                return states.get("start" + next);
            } else {
                currentToken = "";
                return "start";
            }
        }
    }
    
    private CompilerToken makeToken(final String token, final String state) throws LexicalException {
        if (state.equalsIgnoreCase("identifier")) {
            return processIdentifier(token);
            
        } else if (state.equalsIgnoreCase("integer")) {
            return processInteger(token);
            
        } else if (state.equalsIgnoreCase("realexponent")) {
            return processFloat(token, state);
            
        } else if (state.equalsIgnoreCase("real")) {
            return processFloat(token, state);
            
        } else if (state.equalsIgnoreCase("realexponentzero")) {
            return processFloat(token, state);
            
        } else if (state.equalsIgnoreCase("zerointeger")) {
            return processInteger(token);
            
        } else if (state.equalsIgnoreCase("bang")) {
            throw new LexicalException("Invalid modifier ! found.");
            
        } else {
            return new CompilerToken(tokenStateMap.get(state), token);
        }
    }
    
    private CompilerToken processFloat(String token, final String state) throws LexicalException {
        // state: real, realexponent, realexponentzero
        final String[] parts = token.split("\\.");
        final String NEW_EXPONENT = "e1";
        final String NEW_DECIMAL = ".0e";
        validateInteger(parts[0].split("e")[0]);
        // Validate the integer parts of the number
        if (token.indexOf(".") > 0) {
            validateInteger(parts[1].split("e")[0]);
        }
        if (state.equals("realexponent") || state.equals("realexponentzero")) {
            final String[] exponentParts = token.split("e");
            validateInteger(exponentParts[1]);
        }
        if (token.indexOf(".") > 0) {
            if (token.indexOf("e") > 0) {
            } else {
                token += NEW_EXPONENT;
            }
        } else {
            if (token.indexOf("e") > 0) {
                final String[] tokenParts = token.split("e");
                token = tokenParts[0] + NEW_DECIMAL + tokenParts[1];
            } else {
                throw new LexicalException("Real number created without . or e. This doesn't seem physically possible!");
            }
        }
        return new CompilerToken("real", token);
    }
    
    private CompilerToken processInteger(String token) throws LexicalException {
        token = validateInteger(token);
        return new CompilerToken("integer", token);
    }
    
    private String validateInteger(final String token) throws LexicalException {
        final BigInteger toReturn = new BigInteger(token);
        final BigInteger max = new BigInteger("4294967296");
        final BigInteger min = new BigInteger("-4294967297");
        
        // -1 less, 0 equal, 1 greater
        try {
            if (!((toReturn.compareTo(max) == -1) && (toReturn.compareTo(min) == 1))) {
                throw new LexicalException("LexicalException: Integers must be strictly less than " + max + " and greater than " + min + ". Received " + token);
            }
        } catch (final NumberFormatException e) {
            throw new LexicalException("LexicalException: Integers must be strictly less than " + max + " and greater than " + min + ". Received " + token);
        }
        return token;
    }
    
    private CompilerToken processIdentifier(final String token) throws LexicalException {
        if (reservedStrings.contains(token)) {
            return new CompilerToken(tokenStateMap.get(token), token);
        }
        if (token.length() <= 256) {
            return new CompilerToken("<identifier>", token);
        } else {
            throw new LexicalException("Identifier names must be under 256 characters long. " + token.length() + " is too many.");
        }
    }
    
    private void createScannerMaps() {
        states = new HashMap<String, String>(400);
        for (final char s : "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
            states.put("start" + s, "identifier");
            states.put("identifier" + s, "identifier");
            states.put("comment" + s, "comment");
        }
        
        for (final char s : "123456789".toCharArray()) {
            states.put("comment" + s, "comment");
            states.put("minus" + s, "integer");
            states.put("start" + s, "integer");
            states.put("integer" + s, "integer");
            states.put("realexponentstart" + s, "realexponent");
            states.put("realexponent" + s, "realexponent");
            states.put("real" + s, "real");
            states.put("realdecimal" + s, "real");
            states.put("identifier" + s, "identifier");
            states.put("realexponentnegative" + s, "realexponent");
            states.put("negativezerodecimal" + s, "real");
        }
        states.put("comment}", "closedcomment");
        states.put("comment" + 0, "comment");
        states.put("negativezerodecimal" + 0, "negativezerodecimal");
        states.put("realexponentnegative" + 0, "realexponent");
        states.put("minus" + 0, "negativezero");
        states.put("identifier" + 0, "identifier");
        states.put("realdecimal" + 0, "real");
        states.put("start0", "zerointeger");
        states.put("real" + 0, "real");
        states.put("integer" + "0", "integer");
        states.put("realexponent" + "0", "realexponent");
        states.put("realexponentstart" + 0, "realexponentzero");
        states.put("realexponentstart" + "-", "realexponentnegative");
        
        for (final char s : ".".toCharArray()) {
            states.put("comment" + s, "comment");
            states.put("integer" + s, "realdecimal");
            states.put("zerointeger" + s, "realdecimal");
            states.put("negativezero" + s, "negativezerodecimal");
            states.put("start" + s, "period");
        }
        
        for (final char s : "e".toCharArray()) {
            states.put("real" + s, "realexponentstart");
            states.put("integer" + s, "realexponentstart");
        }
        
        for (final char s : " ~`!@#$%^&*()_+-=,.?/<>;':\"\\][|".toCharArray()) {
            states.put("comment" + s, "comment");
        }
        
        states.put("start+", "plus");
        states.put("start-", "minus");
        states.put("start*", "multiplication");
        states.put("start/", "division");
        
        states.put("start!", "bang");
        states.put("bang=", "notequal");
        states.put("start=", "equal");
        states.put("start:", "colon");
        states.put("colon=", "assignment");
        
        states.put("start<", "lessthan");
        states.put("lessthan=", "lessthanoreq");
        states.put("start>", "greaterthan");
        states.put("greaterthan=", "greaterthanoreq");
        
        states.put("start{", "comment");
        states.put("start(", "leftparen");
        states.put("start)", "rightparen");
        
        states.put("start,", "comma");
        states.put("start;", "semicolon");
        
        reservedStrings = new ArrayList<String>(16);
        reservedStrings.addAll(Arrays.asList(new String[] {"program",
                "var",
                "return",
                "function",
                "integer",
                "real",
                "begin",
                "end",
                "if",
                "then",
                "else",
                "while",
                "do",
        "print"}));
        
//        tokens = new ArrayList<CompilerToken>(100);
        
        tokenStateMap = new HashMap<String, String>();
        tokenStateMap.put("program", "program");
        tokenStateMap.put("var", "var");
        tokenStateMap.put("return", "return");
        tokenStateMap.put("function", "function");
        tokenStateMap.put("integer", "<integer>");
        tokenStateMap.put("real", "<real>");
        tokenStateMap.put("begin", "begin");
        tokenStateMap.put("end", "end");
        tokenStateMap.put("if", "if");
        tokenStateMap.put("then", "then");
        tokenStateMap.put("else", "else");
        tokenStateMap.put("while", "while");
        tokenStateMap.put("do", "do");
        tokenStateMap.put("print", "print");
        
        tokenStateMap.put("plus", "+");
        tokenStateMap.put("minus", "-");
        tokenStateMap.put("multiplication", "*");
        tokenStateMap.put("division", "/");
        tokenStateMap.put("notequal", "!=");
        tokenStateMap.put("equal", "=");
        tokenStateMap.put("colon", ":");
        tokenStateMap.put("assignment", ":=");
        tokenStateMap.put("lessthan", "<");
        tokenStateMap.put("lessthanoreq", "<=");
        tokenStateMap.put("greaterthan", ">");
        tokenStateMap.put("greaterthanoreq", ">=");
        tokenStateMap.put("leftcurly", "{");
        tokenStateMap.put("rightcurly", "}");
        tokenStateMap.put("leftparen", "(");
        tokenStateMap.put("rightparen", ")");
        tokenStateMap.put("comma", ",");
        tokenStateMap.put("semicolon", ";");
        tokenStateMap.put("endofprogram", ".");
        tokenStateMap.put("EOF", "EOF");
        tokenStateMap.put("closedcomment", "COMMENT");
        tokenStateMap.put("period", ".");    
    }
}
