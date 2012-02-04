
public class driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String program = "program HelloWorld\nbegin\n\tprint(0)\n\tinteger i := 1\nifnot\nend.";
		String secondProgram = 	 "     program absolute;\n"+
							     "var x        : integer;\n"+
							     "function abs( n : integer ) : integer\n"+
							     "begin 12e5\n"+
							     "\tif n < 0\n"+
							     "      then\n"+
							     "         return 0 - x\n"+
							     "      else\n"+
							     "         return x\n"+
							     "   end;\n"+
							     "begin\n"+
							     "   x := 47;\n"+
							     "   print( abs(x) )\n"+
							     "end.\n";
		CompilerStateScanner s = new CompilerStateScanner(secondProgram);
		

	}

}
