import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.zza.parser.CompilerParser;
import org.zza.scanner.CompilerStateScanner;

public class driver {
    
    /**
     * @param args
     */
    public static void main(final String[] args) {
        long start = System.currentTimeMillis();
        final StringBuffer sb = new StringBuffer();
        String programToUse = "";
        try {
            final BufferedReader in = new BufferedReader(new FileReader(args[0]));
            String str;
            while ((str = in.readLine()) != null) {
                sb.append(str + "\n");
            }
            in.close();
            programToUse = sb.toString();
        } catch (final ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println("Scanner requires a file name as input to create a token list.");
            System.out.println("Example usage: [java driver ../sampleFlairPrograms/factorial.flr]");
            System.exit(-1);
        } catch (final IOException e) {
        }
        
        final CompilerStateScanner s = new CompilerStateScanner(programToUse);
        final CompilerParser p = new CompilerParser(s.getTokenStream());
        long end = System.currentTimeMillis();
        System.out.println("time: "+(end-start));
    }
    
}
