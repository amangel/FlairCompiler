import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.zza.parser.CompilerParser;
import org.zza.parser.semanticstack.nodes.SemanticNode;
import org.zza.scanner.CompilerStateScanner;

public class driver {
    
    private static long start;
    
    /**
     * @param args
     */
    public static void main(final String[] args) {
        startTime();
        final StringBuffer sb = new StringBuffer(50000);
        try {
            final BufferedReader in = new BufferedReader(new FileReader(args[0]));
            String str;
            while ((str = in.readLine()) != null) {
                sb.append(str + "\n");
            }
            in.close();
        } catch (final ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println("Scanner requires a file name as input to create a token list.");
            System.out.println("Example usage: [java driver ../sampleFlairPrograms/validPrograms/factorial.flr]");
            System.exit(-1);
        } catch (final IOException e) {
        }
        ThreadedDriver threaded = new ThreadedDriver(sb);
    }
    
    public static void startTime() {        
        start = System.currentTimeMillis();
    }
    
    public static void endTime() {        
        final long end = System.currentTimeMillis();
        System.out.println("runtime in ms: " + (end - start));
    }
}
