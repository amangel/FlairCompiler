import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		StringBuffer sb = new StringBuffer();
		String programToUse ="";
		try {
			BufferedReader in = new BufferedReader(new FileReader(args[0]));
			String str;
			while ((str = in.readLine()) != null) {
				sb.append(str+"\n");
			}
			in.close();
			programToUse =  sb.toString();
		} catch (ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
			System.out.println("Scanner requires a file name as input to create a token list.");
			System.out.println("Example usage: [java driver ../sampleFlairPrograms/factorial.fl]");
			System.exit(-1);
		} catch (IOException e) {
		}

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
		CompilerStateScanner s = new CompilerStateScanner(programToUse);


	}

}
