import java.util.concurrent.ConcurrentLinkedQueue;

import org.zza.parser.CompilerParser;
import org.zza.parser.semanticstack.nodes.SemanticNode;
import org.zza.scanner.CompilerStateScanner;
import org.zza.scanner.CompilerToken;
import org.zza.scanner.CompilerTokenStream;
import org.zza.semanticchecker.SymbolTable;
import org.zza.visitor.SymbolTableBuilderVisitor;
import org.zza.visitor.TypeCheckingVisitor;

public class ThreadedDriver {
    
    private final CompilerTokenStream stream;
    private final StringBuffer buffer;
    private SemanticNode program;
    
    public ThreadedDriver(final StringBuffer sb) {
        buffer = sb;
        stream = new CompilerTokenStream(new ConcurrentLinkedQueue<CompilerToken>());
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                final CompilerStateScanner scanner = new CompilerStateScanner(buffer, stream);
            }
            
        }).start();
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                final CompilerParser parser = new CompilerParser(stream);
                program = parser.parseProgram();
                final SymbolTableBuilderVisitor printer = new SymbolTableBuilderVisitor();
                printer.visit(program);
                SymbolTable.getInstance().printTable();
                final TypeCheckingVisitor typeChecker = new TypeCheckingVisitor();
                typeChecker.visit(program);
                driver.endTime();
            }
        }).start();
        
    }
}
