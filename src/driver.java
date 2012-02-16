
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.zza.scanner.*; 


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
            System.out.println("Example usage: [java driver ../sampleFlairPrograms/factorial.flr]");
            System.exit(-1);
        } catch (IOException e) {
        }
        
        CompilerStateScanner s = new CompilerStateScanner(programToUse);
        
        
    }
    
}
