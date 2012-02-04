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
				state = handleStateTransition(next, state);
			}
			handleStateTransition("", state);
			currentToken = "EOF";
			handleStateTransition("EOF", "EOF");

		} catch (LexicalException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String handleStateTransition(String next, String state) throws LexicalException{
		if(states.containsKey(state+next)){
			currentToken += next;
			return states.get(state+next);
		} else {
			if(currentToken.trim().length()>0){
				tokens.add( makeToken(currentToken.trim(), state) );
			}
			if(states.containsKey("start"+next)){
				currentToken = next;
				return states.get("start"+next);
			} else {
				currentToken = "";
				return "start";
			}
		}
	}

	private CompilerToken makeToken(String token, String state) throws LexicalException{
		if(state.equalsIgnoreCase("identifier")){
			return processIdentifier(token);
		} else if (state.equalsIgnoreCase("integer") ){
			return processInteger(token);
		} else if (state.equalsIgnoreCase("realexponent") ){
			return processFloat(token, state);
		} else if (state.equalsIgnoreCase("real") ){
			return processFloat(token, state);
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
		} else if (state.equalsIgnoreCase("endofprogram")){
			return new CompilerToken(".", token, CompilerToken.END_OF_PROGRAM);
		} else if (state.equalsIgnoreCase("EOF") ){
			return new CompilerToken("EOF", token, CompilerToken.EOF_SYMBOL);
		} else if (state.equalsIgnoreCase("realexponentzero")){
			return processFloat(token, state);
		} else {
			System.out.println("Unknown token type: "+token + " with state: "+state);
		}

		return new CompilerToken("", "", 1);
	}

	private CompilerToken processFloat(String token, String state) {
		//state: real, realexponent, realexponentzero
		System.out.println(token+", "+state);
		String[] parts = token.split("\\.");
		String front;
		String back;
		String exponent;
		try {
			front = validateInteger(parts[0]);
			if(token.indexOf(".") > 0){
				back = validateInteger(parts[1]);
			}
			if(state.equals("realexponent")){
				String[] exponentParts = token.split("e");
				exponent = validateInteger(exponentParts[1]);
				return new CompilerToken("real", token, CompilerToken.REAL_VALUE_WITH_EXPONENT);
			}

		} catch (LexicalException e) {
			e.printStackTrace();
		}
		return new CompilerToken("real", token, CompilerToken.REAL_VALUE);
	}

	private CompilerToken processInteger(String token) throws LexicalException{
		token = validateInteger(token);
		return new CompilerToken("integer", token, CompilerToken.INTEGER_VALUE);
	}

	private String validateInteger(String token) throws LexicalException {
		double toReturn;
		double max = Math.pow(2, 32);
		try{
			toReturn = Double.parseDouble(token);
			if(toReturn < max && toReturn >= -max){
				token = Double.toString(toReturn);
			} else {
				throw new LexicalException("LexicalException: Integers must be between -"+max+" and "+(max-1)+". Received "+token);
			}
		} catch (NumberFormatException e){
			throw new LexicalException("LexicalException: Integers must be between -"+max+" and "+(max-1)+". Received "+token);
		}
		return token;
	}

	private CompilerToken processIdentifier(String token) throws LexicalException {
		if(COMPILER_RESERVED_STRINGS.contains(token)){
			if(token.equalsIgnoreCase("program")){
				return new CompilerToken("program", token, CompilerToken.PROGRAM);
			} else if (token.equalsIgnoreCase("var")) {
				return new CompilerToken("var", token, CompilerToken.VAR);
			} else if (token.equalsIgnoreCase("function")) {
				return new CompilerToken("function", token, CompilerToken.FUNCTION);
			} else if (token.equalsIgnoreCase("integer")) {
				return new CompilerToken("integer declaration", token, CompilerToken.INTEGER_DECLARATION);
			} else if (token.equalsIgnoreCase("real")) {
				return new CompilerToken("real_declaration", token, CompilerToken.REAL_DECLARATION);
			} else if (token.equalsIgnoreCase("begin")) {
				return new CompilerToken("begin", token, CompilerToken.BEGIN);
			} else if (token.equalsIgnoreCase("end")) {
				return new CompilerToken("end", token, CompilerToken.END);
			} else if (token.equalsIgnoreCase("if")) {
				return new CompilerToken("if", token, CompilerToken.IF);
			} else if (token.equalsIgnoreCase("then")) {
				return new CompilerToken("then", token, CompilerToken.THEN);
			} else if (token.equalsIgnoreCase("else")) {
				return new CompilerToken("else", token, CompilerToken.ELSE);
			} else if (token.equalsIgnoreCase("while")) {
				return new CompilerToken("while", token, CompilerToken.WHILE);
			} else if (token.equalsIgnoreCase("do")) {
				return new CompilerToken("do", token, CompilerToken.DO);
			} else if (token.equalsIgnoreCase("print")) {
				return new CompilerToken("print", token, CompilerToken.PRINTOP);
			} else if (token.equalsIgnoreCase("return")) {
				return new CompilerToken("return", token, CompilerToken.RETURN);
			}
		}
		if(token.length() <= 256) {
			return new CompilerToken("identifier", token, CompilerToken.IDENTIFIER);
		} else {
			throw new LexicalException("Identifier names must be under 256 characters long. "+token.length() + " is too many.");
		}

	}

	private void createStateMap() {
		states = new HashMap<String, String>();
		for(char s : "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray() ) {
			states.put("start"+s, "identifier");
			states.put("identifier"+s, "identifier");
		}
		for(char s : "123456789".toCharArray() ) {
			states.put("start"+s, "integer");
			states.put("integer"+s, "integer");
			states.put("realexponentstart"+s, "realexponent");
			states.put("realexponent"+s,"realexponent");
			states.put("real"+s, "real");
			states.put("realdecimal"+s, "real");
		}
		states.put("realdecimal"+0, "real");
		states.put("start-", "integer");
		states.put("real"+0, "real");
		states.put("integer"+"0", "integer");
		states.put("realexponent"+"0", "realexponent");
		states.put("realexponentstart"+0, "realexponentzero");//TODO

		for(char s : ".".toCharArray() ) {
			states.put("integer"+s, "realdecimal");
		}
		for(char s : "e".toCharArray() ) {
			states.put("real"+s, "realexponentstart");//TODO
			states.put("integer"+s, "realexponentstart");
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
		states.put("start.", "endofprogram");

		COMPILER_RESERVED_STRINGS = new ArrayList<String>();
		COMPILER_RESERVED_STRINGS.addAll( Arrays.asList(new String[] {"program", "var",
				"function", "integer", "real", "begin", "end", "if", "then", "else", "while", "do", "print"}) );

		tokens = new ArrayList<CompilerToken>();
	}
}
