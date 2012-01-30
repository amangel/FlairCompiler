import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;


public class CompilersScanner {

	private StringReader inputProgramBuffer;
	private static ArrayList<String> COMPILER_RESERVED_STRINGS;
	private static ArrayList<String> COMPILER_RESERVED_CHARACTERS;
	private static ArrayList<String> WHITE_SPACE;
	private static ArrayList<String> COMPILER_ALGEBRAIC;
	private static ArrayList<String> COMPILER_COMPARATOR;
	
	public CompilersScanner(String s){
		generateCompilerReseverdStrings();
		generateCompilerReservedCharacters();
		generateWhiteSpace();
		inputProgramBuffer =new StringReader( s );
		//System.out.println(s);
		ArrayList<CompilerToken> tokens = null;
		try {
			tokens = generateTokens();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (CompilerToken token : tokens){
			System.out.println(token);
		}
	}
	private void generateWhiteSpace() {
		WHITE_SPACE = new ArrayList<String>();
		WHITE_SPACE.addAll(Arrays.asList( new String[] {" ", "\t", "\n"} ) );
	}
	
	private void generateCompilerReservedCharacters() {
		COMPILER_RESERVED_CHARACTERS = new ArrayList<String>();
		COMPILER_RESERVED_CHARACTERS.addAll(Arrays.asList(new String[] {"=", "!=", "<", "<=", ">", ">=",
				"+", "-", "*", "//", "{", "}", ";", ".", ",", ":", "(", ")", ":=", "!"}) );
		COMPILER_ALGEBRAIC = new ArrayList<String>();
		COMPILER_ALGEBRAIC.addAll(Arrays.asList(new String[] {"+", "-", "/", "*"}));
		COMPILER_COMPARATOR = new ArrayList<String>();
		COMPILER_COMPARATOR.addAll(Arrays.asList(new String[] {">", "<", "=", "!=", "<=", ">=", }));
	}
	
	private void generateCompilerReseverdStrings() {
		COMPILER_RESERVED_STRINGS = new ArrayList<String>();
		COMPILER_RESERVED_STRINGS.addAll( Arrays.asList(new String[] {"program", "var",
				"function", "integer", "real", "begin", "end", "if", "then", "else", "while", "do", "print"}) );
	}
	private ArrayList<CompilerToken> generateTokens() throws Exception {
		boolean readLetter = false;
		boolean readPeriodForFloat = false;
		boolean readingToken = false;
		String currentToken = "";
		
		ArrayList<CompilerToken> tokens = new ArrayList<CompilerToken>(1);
		int n;
		try {
			while( (n = inputProgramBuffer.read() ) != -1 ) {
				String next = Character.toString( (char) n );
				//System.out.println("working with: "+next);
				if(WHITE_SPACE.contains(currentToken)){
					currentToken = next;
				} else if(COMPILER_RESERVED_STRINGS.contains(currentToken) && WHITE_SPACE.contains(next)){
					//System.out.println("adding:reserved "+currentToken);
					tokens.add( makeToken(currentToken, readLetter, readPeriodForFloat, true) );
					currentToken = "";
					readingToken = false;
					//System.out.println("setting read token to false");
				} else if (COMPILER_RESERVED_CHARACTERS.contains(currentToken)) {
					if(COMPILER_RESERVED_CHARACTERS.contains(currentToken + next)){
						tokens.add( makeToken(currentToken + next, readLetter, readPeriodForFloat, true));
						currentToken = "";
						readingToken = false;
					} else {
						tokens.add( makeToken(currentToken, readLetter, readPeriodForFloat, true));
						currentToken = next;
						readingToken = true;
					}
				} else if( readingToken && WHITE_SPACE.contains(next)  ){
					//System.out.println("adding;ws "+currentToken);
					tokens.add( makeToken(currentToken, readLetter, readPeriodForFloat, false) );
					currentToken = "";
					readingToken = false;
					readLetter = false;
					readPeriodForFloat = false;
					//System.out.println("setting read token to false");

				} else if(readingToken && COMPILER_RESERVED_CHARACTERS.contains(next)) {
//					System.out.println("considering: "+currentToken + next);
					if(COMPILER_RESERVED_CHARACTERS.contains(currentToken + next) ){
	//					System.out.println("adding;readRes "+currentToken+next);
						tokens.add( makeToken(currentToken + next, readLetter, readPeriodForFloat, true) );
						currentToken = "";
						readingToken = false;
						readLetter = false;
						readPeriodForFloat = false;
		//				System.out.println("setting read token to false");
					} else if (COMPILER_RESERVED_CHARACTERS.contains(currentToken)) {
						tokens.add( makeToken(currentToken, readLetter, readPeriodForFloat, true) );
						currentToken = next;
						readLetter = false;
						readPeriodForFloat = false;
					} else {
			//			System.out.println("adding;else "+currentToken);
						tokens.add( makeToken(currentToken, readLetter, readPeriodForFloat, false) );
						currentToken = next;
						readLetter = true;
						readPeriodForFloat = false;
					}
				} else if (!readingToken && COMPILER_RESERVED_CHARACTERS.contains(next) ){
				//	System.out.println("false read token, and chars contains next");
					currentToken = next;
					readingToken = true;
					readLetter = true;
					//System.out.println("setting read token to true");
			    } else if (readingToken && !readLetter) {
					if( Character.isLetter(next.charAt(0)) || COMPILER_RESERVED_CHARACTERS.contains(next)) {
						readLetter = true;
					} else if(next.equals(".")){
						readPeriodForFloat = true;
					}
					currentToken += next;
				} else if (!readingToken && !WHITE_SPACE.contains(next)){
					currentToken = next;
					readingToken = true;
					//System.out.println("setting read token to true");
 				} else if (readingToken){
 					if(COMPILER_RESERVED_CHARACTERS.contains(currentToken) && ! COMPILER_RESERVED_CHARACTERS.contains(currentToken+next) ){
 						tokens.add( makeToken(currentToken, readLetter, readPeriodForFloat, true) );
 						currentToken = "";
 					}
					currentToken += next;
 				}
			}
			tokens.add( makeToken("EOF", readLetter, readPeriodForFloat, true) );
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return tokens;
	}
	
	private CompilerToken makeToken(String value, boolean hasLetters, boolean isFloat, boolean isReserved) throws Exception{
		if(!isReserved){
			//not reserved:
			if(hasLetters){
				if(isFloat){
					//if hasletters and isfloat, error
					throw new Exception();
				} else {
					//if hasletters - identifier
					return new CompilerToken(value, "", CompilerToken.IDENTIFIER);//(id, v, t);
				}
			} else {
				if(isFloat){
					//if isFloat - float
					return new CompilerToken("", value, CompilerToken.REAL_VALUE);
				} else {
					//if not has letters, and not isFloat - integer
					try{
						int intParsing = Integer.parseInt(value);
					} catch (Exception e){
						e.printStackTrace();
					}
					return new CompilerToken("", value, CompilerToken.INTEGER_VALUE);
				}
			}		
		} else {
			//reserved
			
			if(hasLetters){
				if(isFloat){
					throw new Exception();
				} else {
					if(value.equalsIgnoreCase("integer")){
						return new CompilerToken("integer", "", CompilerToken.INTEGER_DECLARATION);
					} else if (value.equalsIgnoreCase("float")){
						return new CompilerToken("float", "", CompilerToken.REAL_DECLARATION);
//					} else if (COMPILER_ALGEBRAIC.contains(value)){
//						return new CompilerToken("", value, CompilerToken.ALGEBRAIC_OP);
					} else if (value.equalsIgnoreCase("print")){
						return new CompilerToken("print", value, CompilerToken.PRINTOP);
					} else if (value.equalsIgnoreCase(":=")){
						return new CompilerToken("assignment", value, CompilerToken.ASSIGNMENTOP);
//					} else if (COMPILER_COMPARATOR.contains(value)){
//						return new CompilerToken("comparator", value, CompilerToken.COMPARATOR_OP);
					} else if (value.equalsIgnoreCase("(")){
						return new CompilerToken("openParen", value, CompilerToken.OPEN_PAREN);
					} else if (value.equalsIgnoreCase(")")){
						return new CompilerToken("closeParen", value, CompilerToken.CLOSE_PAREN);
					} else if (value.equalsIgnoreCase("if")){
						return new CompilerToken("if", value, CompilerToken.IF);
					} else if (value.equalsIgnoreCase("then")){
						return new CompilerToken("then", value, CompilerToken.THEN);
					} else if (value.equalsIgnoreCase("else")){
						return new CompilerToken("else", value, CompilerToken.ELSE);
					} else if (value.equalsIgnoreCase("while")){
						return new CompilerToken("while", value, CompilerToken.WHILE);
					} else if (value.equalsIgnoreCase("do")){
						return new CompilerToken("do", value, CompilerToken.DO);
					} else if (value.equalsIgnoreCase("begin")){
						return new CompilerToken("begin", value, CompilerToken.BEGIN);
					} else if (value.equalsIgnoreCase("end")){
						return new CompilerToken("end", value, CompilerToken.END);
					} else if (value.equalsIgnoreCase("var")){
						return new CompilerToken("var", value, CompilerToken.VAR);
					} else if (value.equalsIgnoreCase("program")){
						return new CompilerToken("program", value, CompilerToken.PROGRAM);
					} else if (value.equalsIgnoreCase("function")){
						return new CompilerToken("function", value, CompilerToken.FUNCTION);
					} else if (value.equalsIgnoreCase("EOF")){
						return new CompilerToken("EOF", value, CompilerToken.EOF_SYMBOL);
					}
				}
			} else {
				//check to see if it's a reserved character
				//throw new Exception();
			}
			
			//if has letters - operator, find out which?
			//if isFloat - error
			//if hasLetters and isFloat - error
			//if no letters, no float - error
		//if reserved and value == "EOF", is end of file
		}
		System.out.println("Error making token. Given [value:"+value+"  hasLetters: "+hasLetters+" isFloat:"+isFloat+" isReserved: "+isReserved);
		return null;
	}
	
}
