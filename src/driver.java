
public class driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String program = "program HelloWorld\nbegin\n\tprint(0)\n\tinteger i := 1\nifnot\nend";
		String secondProgram = "     program absolute;"+
     "var x : integer;"+
     "function abs( n : integer ) : integer"+
     "   begin"+
     "      if n < 0"+
     "      then"+
     "         return 0 - x"+
     "      else"+
     "         return x"+
     "   end;"+
     "begin"+
     "   x := 47;"+
     "   print( abs(x) )"+
     "end";
		CompilerStateScanner s = new CompilerStateScanner(secondProgram);
		

	}

}
