import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class CompilerStateScanner {

	private static ArrayList<String> COMPILER_RESERVED_STRINGS;
	private ArrayList<String> tokens;
	private StringReader inputProgramBuffer;

	private Map<String, String> states;
	
	private String currentToken;
	private String state;

	public CompilerStateScanner(String input){
		inputProgramBuffer =new StringReader( input.replaceAll("\n\t", " ") );
		System.out.println( input.trim().replaceAll(" +\t\n", " "));
		createStateMap();
		generateTokens();
		for(String s : tokens){
			System.out.println("token: "+s);
		}
	}

	private void generateTokens() {
		currentToken = "";
		state = "start";

		int n;
		try {
			while( (n = inputProgramBuffer.read() ) != -1 ) {
				String next = Character.toString( (char) n );
				state = handleStateTransition(next, state);
				System.out.println(state + next);
			}
			handleStateTransition("", "EOF");
			tokens.add("EOF");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String handleStateTransition(String next, String state) {
		if(states.containsKey(state+next)){
			System.out.println("in if: currentToken: "+currentToken);
			currentToken += next;
			return states.get(state+next);
		} else {
			System.out.println("in else: "+currentToken+", "+next);
			tokens.add(currentToken.trim());
			currentToken = next;
			return "start";
		}
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
		
		tokens = new ArrayList<String>();
	}
}
