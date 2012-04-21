package org.zza.codegenerator.templates;
import org.zza.codegenerator.CodeBlock;
import org.zza.codegenerator.DataMemoryManager;
import org.zza.semanticchecker.SymbolTable;

public class IfTemplate implements Template {

	private String leftHand;
	private String rightHand;
	private String targetVarName;
	private String operator;
	private int leftAddress;
	private int rightAddress;
	private int targetVarAddress;
	private CodeBlock thenCodeBlock;
	private CodeBlock elseCodeBlock;
	private DataMemoryManager manager;
	private int elseBlockSize;
	private int thenBlockSize;
	private int mySize;


	public IfTemplate(String left, String right, String operator,
			String targetVar, DataMemoryManager manager,
			CodeBlock thenBlock, CodeBlock elseBlock){
		this.leftHand = left;
		this.rightHand = right;
		this.operator = operator;
		this.targetVarName = targetVar; 
		this.manager = manager;
		this.thenCodeBlock = thenBlock;
		this.elseCodeBlock = elseBlock;
		elseBlockSize = elseBlock.getSize();
		thenBlockSize = thenBlock.getSize();
		mySize = 5 + elseBlockSize + thenBlockSize;
		leftAddress = manager.getAddressOfVar(left);
		rightAddress = manager.getAddressOfVar(right);		
		targetVarAddress = manager.getAddressOfVar(targetVar);
	}

	@Override
	public int getSize() {
		return mySize;
	}

	@Override
	public void generateTM() {

		System.out.println(":    LD  0," + leftAddress + "(6)");//Register 6 holds a 0;
		System.out.println(":    LD  1," + rightAddress + "(6)"); 
		if(operator == "<"){
			System.out.println(":    SUB 0, 0, 1");
			System.out.println(":    JGE 0" + ", " +  (thenBlockSize + 1) + "(7)");
			thenCodeBlock.emitCode();
			System.out.println(":    LD  7, "+  elseBlockSize + "(7)");
			elseCodeBlock.emitCode();
		}
		else if (operator == ">") {
			System.out.println(":    SUB 0, 1, 0");
			System.out.println(":    JLE 0" + ", " +  (thenBlockSize + 1) + "(7)");
			thenCodeBlock.emitCode();
			System.out.println(":    LD  7, " + elseBlockSize + "(7)");      
			elseCodeBlock.emitCode();
		}
		else if (operator == "=") {
			System.out.println(":    SUB 0, 0, 1");
			System.out.println(":    JNE 0" + ", " +  (thenBlockSize + 1) + "(7)");     
			thenCodeBlock.emitCode();
			System.out.println(":    LD  7, " + elseBlockSize + "(7)");      
			elseCodeBlock.emitCode();
		}
		else if (operator == "<="){
			System.out.println(":    SUB 0, 0, 1");
			System.out.println(":    JGT 0" + ", " +  (thenBlockSize + 1) + "(7)");    
			thenCodeBlock.emitCode();
			System.out.println(":    LD  7, " + elseBlockSize + "(7)");      
			elseCodeBlock.emitCode();
		}
		else if (operator == ">="){
			System.out.println(":    SUB 0, 1, 0");
			System.out.println(":    JLT 0" + ", " +  (thenBlockSize + 1) + "(7)"); 
			thenCodeBlock.emitCode();
			System.out.println(":    LD  7, " + elseBlockSize + "(7)");      
			elseCodeBlock.emitCode();
		}
		else if (operator == "!="){
			System.out.println(":    SUB 0, 0, 1");
			System.out.println(":    JEQ 0" + ", " + (thenBlockSize + 1)+ "(7)");
			thenCodeBlock.emitCode();
			System.out.println(":    LD  7, " + elseBlockSize + "(7)");      
			elseCodeBlock.emitCode(); 
		}	
	}

}
