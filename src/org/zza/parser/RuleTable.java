package org.zza.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RuleTable {
    
    private static final String NON_TERMINAL_DELIMITER = "%;";
    private static final String TERMINAL_DELIMITER = "`";
    private static final String RULE_DELIMITER = "~";
    private HashMap<String, Map<String, Integer>> tableContents;
    private ArrayList<ArrayList<Entry>> ruleArray;
    private final String fileName = "parseTable.dat";
    
    public RuleTable() {
        buildRuleList();
        buildTable();
    }
    
    private void buildTable() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File(fileName)));
        } catch (final FileNotFoundException e) {
            System.out.println("Could not locate " + fileName + ". Unable to continue. Please locate this file.");
        }
        String nextLine = "";
        try {
            nextLine = reader.readLine();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        
        while (nextLine != null) {
            String nonterminal = "";
            String terminal = "";
            Integer ruleIndex = 0;
            
            final String[] lineParts = nextLine.split(NON_TERMINAL_DELIMITER);
            final String[] terminalParts = lineParts[1].split(TERMINAL_DELIMITER);
            nonterminal = lineParts[0];
            
            for (final String terminalIndexPair : terminalParts) {
                final String[] term = terminalIndexPair.split(RULE_DELIMITER);
                terminal = term[0];
                ruleIndex = Integer.parseInt(term[1]);
                try {
                    addToTable(nonterminal, terminal, ruleIndex);
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }
            
            try {
                nextLine = reader.readLine();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void addToTable(final String nonterminal, final String terminal, final Integer ruleIndex) throws Exception {
        if (tableContents.containsKey(nonterminal)) {
            if (tableContents.get(nonterminal).containsKey(terminal)) {
                throw new Exception("Attempting to overwrite rule in parsing table. :" + nonterminal + " , " + terminal + " , " + ruleIndex + ".");
            } else {
                tableContents.get(nonterminal).put(terminal, ruleIndex);
            }
        } else {
            final HashMap<String, Integer> newEntry = new HashMap<String, Integer>();
            newEntry.put(terminal, ruleIndex);
            tableContents.put(nonterminal, newEntry);
        }
    }
    
    private void buildRuleList() {
        final ArrayList<Entry> rule = new ArrayList<Entry>();
        ruleArray = new ArrayList<ArrayList<Entry>>();
        // <PROGRAM>::=program <identifier> ( <PARAMETERS> ) ; <DECLARATIONS>
        // <COMPOUND_STATEMENT> .
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("program"),
                new TerminalEntry("<identifier>"),
                new TerminalEntry("("),
                new NonterminalEntry("<PARAMETERS>"),
                new TerminalEntry(")"),
                new TerminalEntry(";"),
                new NonterminalEntry("<DECLARATIONS>"),
                new NonterminalEntry("<COMPOUND_STATEMENT")}));
        // <DECLARATIONS>::=<VARIABLE_DECLARATIONS> <FUNCTION_DECLARATIONS>
        rule.addAll(Arrays.asList(new Entry[] {new NonterminalEntry("<VARIABLE_DECLARATIONS>"), 
                new NonterminalEntry("<FUNCTION_DECLARATIONS>")}));
        // <FUNCTION_DECLARATIONS>::=<FXN_DECLARATION_LIST>
        rule.addAll(Arrays.asList(new Entry[] {new NonterminalEntry("<FXN_DECLARATION_LIST>")}));
        // <epsilon>
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("<epsilon>")}));
        // <FXN_DECLARATION_LIST_TAIL>::=<FXN_DECLARATION_LIST>
        rule.addAll(Arrays.asList(new Entry[] {new NonterminalEntry("<FXN_DECLARATION_LIST>")}));
        // <epsilon>
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("<epsilon>")}));
        // <FXN_DECLARATION_LIST>::=<FXN_DECLARATION>
        // <FXN_DECLARATION_LIST_TAIL>
        rule.addAll(Arrays.asList(new Entry[] {new NonterminalEntry("<FXN_DECLARATION>"), 
                new NonterminalEntry("<FXN_DECLARATION_LIST_TAIL>")}));
        // <FXN_DECLARATION>::=<FXN_HEADING> <FXN_BODY> ;
        rule.addAll(Arrays.asList(new Entry[] {new NonterminalEntry("FXN_HEADING>"), 
                new NonterminalEntry("<FXN_BODY>")}));
        // <FXN_HEADING>::=function <identifier> ( <PARAMETERS> ) : <TYPE>
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("function"),
                new TerminalEntry("<identifier>"),
                new TerminalEntry("("),
                new NonterminalEntry("<PAREMETERS>"),
                new TerminalEntry(")"),
                new TerminalEntry(":"),
                new NonterminalEntry("<TYPE>")}));
        // <FXN_BODY>::=<VARIABLE_DECLARATIONS> <COMPOUND_STATEMENT>
        rule.addAll(Arrays.asList(new Entry[] {new NonterminalEntry("<VARIABLE_DECLARATIONS>"), 
                new NonterminalEntry("<COMPOUND_STATEMENT>")}));
        // <VARIABLE_DECLARATIONS>::=var <VAR_DECLARATION_LIST>
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("var"), 
                new NonterminalEntry("<VAR_DECLARATION_LIST>")}));
        // <epsilon>
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("<epsilon>")}));
        // <VAR_DECLARATION_LIST_TAIL>::=<VAR_DECLARATION_LIST>
        rule.addAll(Arrays.asList(new Entry[] {new NonterminalEntry("<VAR_DECLARATION_LIST>")}));
        // <epsilon>
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("<epsilon>")}));
        // <VAR_DECLARATION_LIST>::=<VAR_DECLARATION>
        // <VAR_DECLARATION_LIST_TAIL>
        rule.addAll(Arrays.asList(new Entry[] {new NonterminalEntry("<VAR_DECLARATION>"), 
                new NonterminalEntry("<VAR_DECLARTION_LIST_TAIL>")}));
        // <VAR_DECLARATION>::=<identifier> : <TYPE> ;
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("<identifier>"),
                new TerminalEntry(":"),
                new NonterminalEntry("<TYPE>"),
                new TerminalEntry(";")}));
        // <PARAMETERS>::=<epsilon>
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("<epsilon>")}));
        // <PARAMETER_LIST>
        rule.addAll(Arrays.asList(new Entry[] {new NonterminalEntry("<PARAMETER_LIST>")}));
        // <PARAMETER_LIST>::=<PARAMETER> <PARAMETER_LIST_TAIL>
        rule.addAll(Arrays.asList(new Entry[] {new NonterminalEntry("<PARAMETER>"), 
                new NonterminalEntry("<PARAMETER_LIST_TAIL")}));
        // <PARAMETER_LIST_TAIL>::=, <PARAMETER_LIST>
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry(","), 
                new NonterminalEntry("<PARAMETER_LIST>")}));
        // <PARAMETER>::=<identifier> : <TYPE>
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("<identifier>"), 
                new TerminalEntry(":"), 
                new NonterminalEntry("<TYPE>")}));
        // <TYPE>::=integer
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("integer")}));
        // real
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("real")}));
        // <STATEMENT>::=<ASSIGNMENT_STATEMENT>
        rule.addAll(Arrays.asList(new Entry[] {new NonterminalEntry("<ASSIGNMENT_STATEMENT>")}));
        // <IF_STATEMENT>
        rule.addAll(Arrays.asList(new Entry[] {new NonterminalEntry("<IF_STATEMENT>")}));
        // <WHILE_STATEMENT>
        rule.addAll(Arrays.asList(new Entry[] {new NonterminalEntry("<WHILE_STATEMETN>")}));
        // <COMPOUND_STATEMENT>
        rule.addAll(Arrays.asList(new Entry[] {new NonterminalEntry("<COMPOUND_STATEMENT>")}));
        // <RETURN_STATEMENT>
        rule.addAll(Arrays.asList(new Entry[] {new NonterminalEntry("<RETURN_STATEMENT>")}));
        // <epsilon>
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("<epsilon>")}));
        // <STATEMENT_LIST>::=<STATEMENT> <STATEMENT_LIST_TAIL>
        rule.addAll(Arrays.asList(new Entry[] {new NonterminalEntry("<STATEMENT>"), 
                new NonterminalEntry("<STATEMENT_LIST_TAIL>")}));
        // <STATEMENT_LIST_TAIL>::=; <STATEMENT_LIST>
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry(";"), 
                new NonterminalEntry("<STATEMENT_LIST>")}));
        // <epsilon>
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("<epsilon>")}));
        // <ASSIGNMENT_STATEMENT>::=<identifier> := <EXPRESSION>
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("<identifier>"), 
                new TerminalEntry(":="), 
                new NonterminalEntry("<EXPRESSION>")}));
        // <IF_STATEMENT>::=if <COMPARISON> then <STATEMENT> else <STATEMENT>
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("if"),
                new NonterminalEntry("<COMPARISON>"),
                new TerminalEntry("then"),
                new NonterminalEntry("<STATEMENT>"),
                new TerminalEntry("else"),
                new NonterminalEntry("<STATEMENT>")}));
        // <WHILE_STATEMENT>::=while <COMPARISON> do <STATEMENT>
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("while"),
                new NonterminalEntry("<COMPARISON>"),
                new TerminalEntry("do"),
                new NonterminalEntry("<STATEMENT>")}));
        // <COMPOUND_STATEMENT>::=begin <STATEMENT_LIST> end
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("begin"), 
                new NonterminalEntry("<STATEMENT_LIST>"), 
                new TerminalEntry("end")}));
        // <RETURN_STATEMENT>::=return <EXPRESSION>
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("return"), 
                new NonterminalEntry("<EXPRESSION>")}));
        // <COMPARISON>::=<EXPRESSION> <COMPARE_OP> <EXPRESSION>
        rule.addAll(Arrays.asList(new Entry[] {new NonterminalEntry("<EXPRESSION>"), 
                new NonterminalEntry("<COMPARE_OP>"), 
                new NonterminalEntry("<EXPRESSION>")}));
        // <COMPARE_OP>::==
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("=")}));
        // <
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("<")}));
        // >
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry(">")}));
        // <=
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("<=")}));
        // >=
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry(">=")}));
        // !=
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("!=")}));
        // <ARGUMENTS>::=<epsilon>
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("<epsilon>")}));
        // <ARGUMENT_LIST>
        rule.addAll(Arrays.asList(new Entry[] {new NonterminalEntry("<ARGUMENT_LIST>")}));
        // <ARGUMENT_LIST>::=<EXPRESSION> <ARGUMENT_LIST_TAIL>
        rule.addAll(Arrays.asList(new Entry[] {new NonterminalEntry("<EXPRESSION>"), 
                new NonterminalEntry("<ARGUMENT_LIST_TAIL>")}));
        // <ARGUMENT_LIST_TAIL>::=<epsilon>
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("<epsilon>")}));
        // , <ARGUMENT_LIST>
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry(","), 
                new NonterminalEntry("<ARGUMENT_LIST>")}));
        // <EXPRESSION>::=- <EXPRESSION_BODY>
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("-"), 
                new NonterminalEntry("<EXPRESSION_BODY>")}));
        // <EXPRESSION_BODY>
        rule.addAll(Arrays.asList(new Entry[] {new NonterminalEntry("<EXPRESSION_BODY>")}));
        // <EXPRESSION_BODY>::=<TERM> <ADDITIVE_EXPRESSION>
        rule.addAll(Arrays.asList(new Entry[] {new NonterminalEntry("<TERM>"), 
                new NonterminalEntry("<ADDITIVE_EXPRESSION>")}));
        // <ADDITIVE_EXPRESSION>::=+ <TERM>
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("+"), 
                new NonterminalEntry("<TERM>")}));
        // - <TERM>
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("-"), 
                new NonterminalEntry("<TERM>")}));
        // <epsilon>
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("<epsilon>")}));
        // <TERM>::=<FACTOR> <MULTIPLICATIVE_EXPRESSION>
        rule.addAll(Arrays.asList(new Entry[] {new NonterminalEntry("<FACTOR>"), 
                new NonterminalEntry("<MULTIPLICATIVE_EXPRESSION>")}));
        // <MULTIPLICATIVE_EXPRESSION>::=* <TERM>
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("*"), 
                new NonterminalEntry("<TERM>")}));
        // / <TERM>
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("/"), 
                new NonterminalEntry("<TERM>")}));
        // <epsilon>
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("<epsilon>")}));
        // <FACTOR>::=( <EXPRESSION> )
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("("), 
                new NonterminalEntry("<EXPRESSION>"), 
                new TerminalEntry(")")}));
        // <FUNCTION_CALL>
        rule.addAll(Arrays.asList(new Entry[] {new NonterminalEntry("<FUNCTION_CALL>")}));
        // <identifier>
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("<identifier>")}));
        // <LITERAL>
        rule.addAll(Arrays.asList(new Entry[] {new NonterminalEntry("<LITERAL>")}));
        // <FUNCTION_CALL>::=<identifier> ( <ARGUMENTS> )
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("<identifier>"),
                new TerminalEntry("("),
                new NonterminalEntry("<ARGUMENTS>"),
                new TerminalEntry(")")}));
        // <LITERAL>::=<integer>
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("<integer>")}));
        // <real>
        rule.addAll(Arrays.asList(new Entry[] {new TerminalEntry("<real>")}));
    }
    
    public ArrayList<Entry> find(final String A, final String i) {
        final int index = tableContents.get(A).get(i);
        return ruleArray.get(index);
    }
    
}
