
public class CompilerToken {

    public static final int REAL_DECLARATION    = 0;
    public static final int INTEGER_DECLARATION = 1;
    public static final int PRINTOP             = 2;
    public static final int ASSIGNMENTOP        = 3;	
    public static final int IDENTIFIER          = 4;
    public static final int REAL_VALUE          = 5;
    public static final int INTEGER_VALUE       = 6;
    public static final int EOF_SYMBOL          = 7;
    public static final int OPEN_PAREN			= 8;
    public static final int CLOSE_PAREN			= 9;
    public static final int IF					= 10;
    public static final int THEN				= 11;
    public static final int ELSE				= 12;
    public static final int WHILE				= 13;
    public static final int DO					= 14;
    public static final int BEGIN				= 15;
    public static final int END					= 16;
    public static final int PROGRAM				= 17;
    public static final int VAR					= 18;
    public static final int FUNCTION			= 19;
    
    public static final int PLUS         		= 20;
    public static final int MINUS				= 21;
    public static final int MULTIPLICATION		= 22;
    public static final int DIVISION			= 23;
    
    public static final int GREATER_THAN	    = 30;
    public static final int LESS_THAN			= 31;
    public static final int NOT_EQUAL			= 32;
    public static final int EQUAL				= 33;
    public static final int GREATER_THAN_OR_EQ	= 34;
    public static final int LESS_THAN_OR_EQ		= 35;
    
    public static final int COLON				= 40;
    
    public static final int LEFT_CURLY			= 41;
    public static final int RIGHT_CURLY			= 42;
    public static final int COMMA				= 43;
    public static final int SEMICOLON			= 44;
    
    public static final int END_OF_PROGRAM		= 45;
    
    public static final int REAL_VALUE_WITH_EXPONENT = 46;
    public static final int RETURN				= 47;
    
    private String identifier;
    private String value;
    private int type;
	
	public CompilerToken(String id, String v, int t){
		identifier = id;
		value = v;
		type = t;
	}
	
	public String getId(){
		return identifier;
	}
	
	public String getValue(){
		return value;
	}
	
	public int getType(){
		return type;
	}
	
	public String toString(){
		return "Token:{ id: "+identifier+
			   "  value: "+value+
			   "  type: "+getTypeName()+" }\n";
	}
	
	private String getTypeName(){
		switch(type) {
		case REAL_DECLARATION:
			return "REAL_DECLARATION";
		case INTEGER_DECLARATION:
			return "INTEGER_DECLARATION";
		case PRINTOP:
			return "PRINTOP";
		case ASSIGNMENTOP:
			return "ASSIGNMENTOP";
		case PLUS:
			return "PLUS";
		case MINUS:
			return "MINUS";
		case MULTIPLICATION:
			return "MULTIPLICATION";
		case DIVISION:
			return "DIVISION";
		case IDENTIFIER:
			return "IDENTIFIER";
		case REAL_VALUE:
			return "REAL_VALUE";
		case INTEGER_VALUE:
			return "INTEGER_VALUE";
		case EOF_SYMBOL:
			return "EOF_SYMBOL";		
		case OPEN_PAREN:
			return "OPEN_PAREN";
		case CLOSE_PAREN:
			return "CLOSE_PAREN";
		case IF:
			return "IF";
		case THEN:
			return "THEN";
		case ELSE:
			return "ELSE";
		case WHILE:
			return "WHILE";
		case DO:
			return "DO";
		case VAR:
			return "VAR";
		case PROGRAM:
			return "PROGRAM";
		case BEGIN:
			return "BEGIN";
		case END:
			return "END";
		case FUNCTION:
			return "FUNCTION";
		case GREATER_THAN:
			return "GREATER_THAN";
		case GREATER_THAN_OR_EQ:
			return "GREATER THAN OR EQ";
		case LESS_THAN:
			return "LESS THAN";
		case LESS_THAN_OR_EQ:
			return "LESS_THAN OR EQ";
		case EQUAL:
			return "EQUAL";
		case NOT_EQUAL:
			return "NOT EQUAL";
		case COLON:
			return "COLON";
		case LEFT_CURLY:
			return "LEFT_CURLY";
		case RIGHT_CURLY:
			return "RIGHT_CURLY";
		case COMMA:
			return "COMMA";
		case SEMICOLON:
			return "SEMICOLON";
		case END_OF_PROGRAM:
			return "END_OF_PROGRAM";
		case REAL_VALUE_WITH_EXPONENT:
			return "REAL_VALUE_WITH_EXPONENT";
		case RETURN:
			return "RETURN";
		}
		return "UNKNOWN TYPE";
	}
}
