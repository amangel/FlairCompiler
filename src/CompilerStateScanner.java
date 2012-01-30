import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class CompilerStateScanner {

	private static ArrayList<String> COMPILER_RESERVED_STRINGS;
	private ArrayList<CompilerToken> tokens;
	private StringReader inputProgramBuffer;

	private Map<String, String> states;
	
	private String currentToken;
	//private String state;

	public CompilerStateScanner(String input){
		inputProgramBuffer =new StringReader( input.trim().replaceAll("\\s+", " ") );
		System.out.println( input.trim().replaceAll("\\s+", " ") );
		createStateMap();
		generateTokens();
		for(CompilerToken s : tokens){
			System.out.println(s);
		}
	}

	private void generateTokens() {
		currentToken = "";
		String state = "start";

		int n;
		try {
			while( (n = inputProgramBuffer.read() ) != -1 ) {
				String next = Character.toString( (char) n );
				//System.out.println("starting transition: '"+state+next+"'");
				state = handleStateTransition(next, state);
				//System.out.println(state + next);
			}
			handleStateTransition("", "EOF");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String handleStateTransition(String next, String state) {
		if(states.containsKey(state+next)){
			//System.out.println("in if: currentToken: "+currentToken);
			currentToken += next;
			return states.get(state+next);
		} else {
			//System.out.println("in else: "+currentToken+", "+next);
			tokens.add( makeToken(currentToken.trim(), state) );
			if(states.containsKey("start"+next)){
				currentToken = next;
				return states.get("start"+next);
			} else {
				currentToken = "";
				return "start";
			}
		}
	}

//	private String makeToken(String token, String state) {
//		return token;
//	}

	private CompilerToken makeToken(String token, String state) {
		if(state.equalsIgnoreCase("identifier")){
			return processIdentifier(token);
		} else if (state.equalsIgnoreCase("integer") ){
			return processInteger(token);
		} else if (state.equalsIgnoreCase("floatexponent") ){
			return processFloat(token);
		} else if (state.equalsIgnoreCase("float") ){
			return processFloat(token);
		} else if (state.equalsIgnoreCase("plus") ){
			return new CompilerToken("+", token, CompilerToken.PLUS);
		} else if (state.equalsIgnoreCase("minus") ){
			return new CompilerToken("-", token, CompilerToken.MINUS);
		} else if (state.equalsIgnoreCase("multiplication") ){
			return new CompilerToken("*", token, CompilerToken.MULTIPLICATION);
		} else if (state.equalsIgnoreCase("division") ){
			return new CompilerToken("/", token, CompilerToken.DIVISION);
		} else if (state.equalsIgnoreCase("bang") ){
			System.out.println("bang?");
		} else if (state.equalsIgnoreCase("notequal") ){
			return new CompilerToken("!=", token, CompilerToken.NOT_EQUAL);
		} else if (state.equalsIgnoreCase("equal") ){
			return new CompilerToken("=", token, CompilerToken.EQUAL);
		} else if (state.equalsIgnoreCase("colon") ){
			return new CompilerToken(":", token, CompilerToken.COLON);
		} else if (state.equalsIgnoreCase("assignment") ){
			return new CompilerToken(":=", token, CompilerToken.ASSIGNMENTOP);
		} else if (state.equalsIgnoreCase("lessthan") ){
			return new CompilerToken("<", token, CompilerToken.LESS_THAN);
		} else if (state.equalsIgnoreCase("lessthanoreq") ){
			return new CompilerToken("<=", token, CompilerToken.LESS_THAN_OR_EQ);
		} else if (state.equalsIgnoreCase("greaterthan") ){
			return new CompilerToken(">", token, CompilerToken.GREATER_THAN);
		} else if (state.equalsIgnoreCase("greaterthanoreq") ){
			return new CompilerToken(">=", token, CompilerToken.GREATER_THAN_OR_EQ);
		} else if (state.equalsIgnoreCase("leftcurly") ){
			return new CompilerToken("{", token, CompilerToken.LEFT_CURLY);
		} else if (state.equalsIgnoreCase("rightcurly") ){
			return new CompilerToken("}", token, CompilerToken.RIGHT_CURLY);
		} else if (state.equalsIgnoreCase("leftparen") ){
			return new CompilerToken("(", token, CompilerToken.OPEN_PAREN);
		} else if (state.equalsIgnoreCase("rightparen") ){
			return new CompilerToken(")", token, CompilerToken.CLOSE_PAREN);
		} else if (state.equalsIgnoreCase("comma") ){
			return new CompilerToken(",", token, CompilerToken.COMMA);
		} else if (state.equalsIgnoreCase("semicolon") ){
			return new CompilerToken(";", token, CompilerToken.SEMICOLON);
		} else if (state.equalsIgnoreCase("EOF") ){
			return new CompilerToken("EOF", token, CompilerToken.EOF_SYMBOL);
		} else {
			System.out.println("Unknown token type: "+token + " with state: "+state);
		}
		
		return new CompilerToken("", "", 1);
	}
	
	private CompilerToken processFloat(String token) {
		// TODO Auto-generated method stub
		return new CompilerToken("float", token, CompilerToken.FLOAT_VALUE);
	}

	private CompilerToken processInteger(String token) {
		//TODO
		return new CompilerToken("integer", token, CompilerToken.INTEGER_VALUE);
	}

	private CompilerToken processIdentifier(String token) {
		//"program", "var", "function", "integer", "real", "begin", "end", "if", "then", "else", "while", "do", "print"}
//		if(COMPILER_RESERVED_STRINGS.contains(token)){
//			if(token.equalsIgnoreCase("program")){
//				
//			}
//		}
		return new CompilerToken("identifier", token, CompilerToken.IDENTIFIER);
	}

	private void createStateMap() {
		states = new HashMap<String, String>();
		for(char s : "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray() ) {
			states.put("start"+s, "identifier");
			states.put("identifier"+s, "identifier");
		}
		for(char s : "0123456789".toCharArray() ) {
			states.put("start"+s, "integer");
			states.put("integer"+s, "integer");
			states.put("floatexponent"+s, "floatexponent");
		}
		for(char s : ".".toCharArray() ) {
			states.put("integer"+s, "float");
		}
		for(char s : "e".toCharArray() ) {
			states.put("float"+s, "floatexponent");
		}
		states.put("start+", "plus");
		states.put("start-", "minus");
		states.put("start*", "multiplication");
		states.put("start//","division");

		states.put("start!", "bang");
		states.put("bang=", "notEqual");

		states.put("start=", "equal");

		states.put("start:", "colon");

		states.put("colon=", "assignment");

		states.put("start<", "lessthan");
		states.put("lessthan=", "lessthanoreq");

		states.put("start>", "greaterthan");
		states.put("greaterthan=", "greaterthanoreq");

		states.put("start{", "leftcurly");
		states.put("start}", "rightcurly");

		states.put("start(", "leftparen");
		states.put("start)", "rightparen");

		states.put("start,", "comma");
		states.put("start;", "semicolon");

		//start
		//identifier
		//integer
		//float, allows 'e' after the .
		//comparators
		//math
		COMPILER_RESERVED_STRINGS = new ArrayList<String>();
		COMPILER_RESERVED_STRINGS.addAll( Arrays.asList(new String[] {"program", "var",
				"function", "integer", "real", "begin", "end", "if", "then", "else", "while", "do", "print"}) );
		
		tokens = new ArrayList<CompilerToken>();
	}
}
