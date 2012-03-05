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
    private ArrayList<CompilerToken> tokens;
    private String currentToken;
    private final CompilerTokenStream stream;
    
    public CompilerStateScanner(final String input) {
        final StringReader inputProgramBuffer = new StringReader(input.trim().replaceAll("\\s+", " "));
        createScannerMaps();
        generateTokens(inputProgramBuffer);
        stream = new CompilerTokenStream(tokens);
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
            
        } catch (final LexicalException e) {
            e.printStackTrace();
            // System.exit(-1);
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
                tokens.add(makeToken(currentToken.trim(), state));
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
            
        } else if (state.equalsIgnoreCase("plus")) {
            return new CompilerToken("+", token);
            
        } else if (state.equalsIgnoreCase("minus")) {
            return new CompilerToken("-", token);
            
        } else if (state.equalsIgnoreCase("multiplication")) {
            return new CompilerToken("*", token);
            
        } else if (state.equalsIgnoreCase("division")) {
            return new CompilerToken("/", token);
            
        } else if (state.equalsIgnoreCase("bang")) {
            throw new LexicalException("Invalid modifier ! found.");
            
        } else if (state.equalsIgnoreCase("notequal")) {
            return new CompilerToken("!=", token);
            
        } else if (state.equalsIgnoreCase("equal")) {
            return new CompilerToken("=", token);
            
        } else if (state.equalsIgnoreCase("colon")) {
            return new CompilerToken(":", token);
            
        } else if (state.equalsIgnoreCase("assignment")) {
            return new CompilerToken(":=", token);
            
        } else if (state.equalsIgnoreCase("lessthan")) {
            return new CompilerToken("<", token);
            
        } else if (state.equalsIgnoreCase("lessthanoreq")) {
            return new CompilerToken("<=", token);
            
        } else if (state.equalsIgnoreCase("greaterthan")) {
            return new CompilerToken(">", token);
            
        } else if (state.equalsIgnoreCase("greaterthanoreq")) {
            return new CompilerToken(">=", token);
            
        } else if (state.equalsIgnoreCase("leftcurly")) {
            return new CompilerToken("{", token);
            
        } else if (state.equalsIgnoreCase("rightcurly")) {
            return new CompilerToken("}", token);
            
        } else if (state.equalsIgnoreCase("leftparen")) {
            return new CompilerToken("(", token);
            
        } else if (state.equalsIgnoreCase("rightparen")) {
            return new CompilerToken(")", token);
            
        } else if (state.equalsIgnoreCase("comma")) {
            return new CompilerToken(",", token);
            
        } else if (state.equalsIgnoreCase("semicolon")) {
            return new CompilerToken(";", token);
            
        } else if (state.equalsIgnoreCase("endofprogram")) {
            return new CompilerToken(".", token);
            
        } else if (state.equalsIgnoreCase("EOF")) {
            return new CompilerToken("EOF", token);
            
        } else if (state.equalsIgnoreCase("realexponentzero")) {
            return processFloat(token, state);
            
        } else if (state.equalsIgnoreCase("zerointeger")) {
            return processInteger(token);
            
        } else if (state.equalsIgnoreCase("closedcomment")) {
            return new CompilerToken("COMMENT", token);
            
        } else if (state.equalsIgnoreCase("period")) {
            return new CompilerToken(".", token);
            
        } else {
            System.out.println("Unknown token type: " + token + " with state: " + state);
        }
        return new CompilerToken("", "");
    }
    
    private CompilerToken processFloat(String token, final String state) throws LexicalException {
        // state: real, realexponent, realexponentzero
        final String[] parts = token.split("\\.");
        final String NEW_EXPONENT = "e1";
        final String NEW_DECIMAL = ".0e";
        try {
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
        } catch (final LexicalException e) {
            e.printStackTrace();
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
            if (token.equalsIgnoreCase("program")) {
                return new CompilerToken("program", token);
                
            } else if (token.equalsIgnoreCase("var")) {
                return new CompilerToken("var", token);
                
            } else if (token.equalsIgnoreCase("function")) {
                return new CompilerToken("function", token);
                
            } else if (token.equalsIgnoreCase("integer")) {
                return new CompilerToken("<integer>", token);
                
            } else if (token.equalsIgnoreCase("real")) {
                return new CompilerToken("<real>", token);
                
            } else if (token.equalsIgnoreCase("begin")) {
                return new CompilerToken("begin", token);
                
            } else if (token.equalsIgnoreCase("end")) {
                return new CompilerToken("end", token);
                
            } else if (token.equalsIgnoreCase("if")) {
                return new CompilerToken("if", token);
                
            } else if (token.equalsIgnoreCase("then")) {
                return new CompilerToken("then", token);
                
            } else if (token.equalsIgnoreCase("else")) {
                return new CompilerToken("else", token);
                
            } else if (token.equalsIgnoreCase("while")) {
                return new CompilerToken("while", token);
                
            } else if (token.equalsIgnoreCase("do")) {
                return new CompilerToken("do", token);
                
            } else if (token.equalsIgnoreCase("print")) {
                return new CompilerToken("print", token);
                
            } else if (token.equalsIgnoreCase("return")) {
                return new CompilerToken("return", token);
            }
        }
        if (token.length() <= 256) {
            return new CompilerToken("<identifier>", token);
        } else {
            throw new LexicalException("Identifier names must be under 256 characters long. " + token.length() + " is too many.");
        }
    }
    
    private void createScannerMaps() {
        states = new HashMap<String, String>();
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
        states.put("bang=", "notEqual");
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
        
        reservedStrings = new ArrayList<String>();
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
        
        tokens = new ArrayList<CompilerToken>();
    }
}
