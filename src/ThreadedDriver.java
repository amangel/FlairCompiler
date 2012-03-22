import java.util.ArrayList;

import org.zza.parser.CompilerParser;
import org.zza.parser.semanticstack.nodes.SemanticNode;
import org.zza.scanner.CompilerStateScanner;
import org.zza.scanner.CompilerToken;
import org.zza.scanner.CompilerTokenStream;
import org.zza.visitor.StackPrintingVisitor;


public class ThreadedDriver {
    
    private CompilerTokenStream stream;
    private StringBuffer buffer;
    private SemanticNode program;
    
    public ThreadedDriver(StringBuffer sb) {
        buffer = sb;
        stream = new CompilerTokenStream(new ArrayList<CompilerToken>(100));
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                CompilerStateScanner scanner = new CompilerStateScanner(buffer, stream);
            }
            
        }).start();
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                CompilerParser parser = new CompilerParser(stream);
                program = parser.parseProgram();
                final StackPrintingVisitor printer = new StackPrintingVisitor();
                System.out.println(printer.visit(program));

                driver.endTime();
            }
        }).start();
        
    }
}
