import java.util.concurrent.ConcurrentLinkedQueue;

import org.zza.codegenerator.CodeGeneratingVisitor;
import org.zza.parser.CompilerParser;
import org.zza.parser.semanticstack.nodes.SemanticNode;
import org.zza.scanner.CompilerStateScanner;
import org.zza.scanner.CompilerToken;
import org.zza.scanner.CompilerTokenStream;
import org.zza.semanticchecker.SemanticWarningList;
import org.zza.visitor.SymbolTableBuilderVisitor;
import org.zza.visitor.ThreeAddressCodeGenerator;
import org.zza.visitor.TypeCheckingVisitor;
import org.zza.optimizer.OptimizingVisitor;

public class ThreadedDriver {
    
    private final CompilerTokenStream stream;
    private final StringBuffer buffer;
    private SemanticNode program;
    private SemanticWarningList warningList;
    
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
                warningList = SemanticWarningList.getInstance();
                final SymbolTableBuilderVisitor printer = new SymbolTableBuilderVisitor();
                printer.visit(program);
                //                SymbolTable.getInstance().printTable();
                final TypeCheckingVisitor typeChecker = new TypeCheckingVisitor();
                typeChecker.visit(program);
                final OptimizingVisitor optimizer = new OptimizingVisitor();
                optimizer.visit(program);
                //                final CodeGeneratingVisitor code = new CodeGeneratingVisitor();
                ThreeAddressCodeGenerator code = new ThreeAddressCodeGenerator();
                code.visit(program);
                driver.endTime();
                if(!warningList.isEmpty()) {
                    warningList.printWarnings();
                }
            }
        }).start();
        
    }
}
