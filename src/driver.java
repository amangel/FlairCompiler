
public class driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String program = "program HelloWorld\nbegin\n\tprint(0)\n\tinteger i := 1\nifnot\nend";
		String secondProgram = 	 "program absolute;"+
							     "var x : integer;"+
							     "function abs( n : integer ) : integer\n"+
							     "begin\n"+
							     "\tif n < 0\n"+
							     "      then\n"+
							     "         return 0 - x\n"+
							     "      else\n"+
							     "         return x\n"+
							     "   end;\n"+
							     "begin\n"+
							     "   x := 47;\n"+
							     "   print( abs(x) )\n"+
							     "end\n";
		CompilerStateScanner s = new CompilerStateScanner(secondProgram);
		

	}

}
