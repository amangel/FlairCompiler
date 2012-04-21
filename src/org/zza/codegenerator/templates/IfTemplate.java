package org.zza.codegenerator.templates;
import org.zza.semanticchecker.SymbolTable;

public class IfTemplate implements Template {

	private String left;
	private String right;
	private String targetVar;
	private String operator;
	private String leftAddress;
	private String rightAddress;
	private String targetVarAddress;
	private CodeBlock thenBlock;
	private CodeBlock elseBlock;
	private SymbolTable table;
	private int elseBlockSize;
	private int thenBlockSize;
	private int mySize;


	public IfTemplate(String left, String right, String operator, String targetVar, SymbolTable table, CodeBlock thenBlock, CodeBlock elseBlock){
		this.left = left;
		this.right = right;
		this.operator = operator;
		this.targetVar = targetVar;
		this.table = table;
		this.thenBlock = thenBlock;
		this.elseBlock = elseBlock;
		elseBlockSize = elseBlock.getSize();
		thenBlockSize = thenBlock.getSize();
		mySize = 5 + elseBlockSize + thenBlockSize;
		leftAddress = table.getAddress(left);
		rightAddress = table.getAddress(right);		
		targetVarAddress = table.getAddress(targetVar);
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
			//SUB 3, 1, 2        left -right < 0
			//JGE 3, ?(7)         
			//JGE 3, #(7)

			System.out.println(":    SUB 0, 0, 1");
			System.out.println(":    JGE 0" + ", " +  (thenBlockSize + 1) + "(7)");
			thenBlock.emit();
			System.out.println(":    LD  7, "+  elseBlockSize + "(7)");
			elseBlock.emit();
		}
		else if (operator == ">") {
			//SUB 3, 2, 1       0 > right - left
			//JLE 3, ?(7)
			//JLE 3, #(7)
			System.out.println(":    SUB 0, 1, 0");
			System.out.println(":    JLE 0" + ", " +  (thenBlockSize + 1) + "(7)");
			thenBlock.emit();
			System.out.println(":    LD  7, " + elseBlockSize + "(7)");      
			elseBlock.emit();
		}
		else if (operator == "=") {
			//SUB 3, 1, 2      left - right  = 0
			//JNE 3, ?(7)
			//JNE 3, #(7)
			System.out.println(":    SUB 0, 0, 1");
			System.out.println(":    JNE 0" + ", " +  (thenBlockSize + 1) + "(7)");     
			thenBlock.emit();
			System.out.println(":    LD  7, " + elseBlockSize + "(7)");      
			elseBlock.emit();
		}
		else if (operator == "<="){
			//SUB 3, 1, 2
			//JGT 3, ?(7)
			//JGT 3, #(7)
			System.out.println(":    SUB 0, 0, 1");
			System.out.println(":    JGT 0" + ", " +  (thenBlockSize + 1) + "(7)");    
			thenBlock.emit();
			System.out.println(":    LD  7, " + elseBlockSize + "(7)");      
			elseBlock.emit();
		}
		else if (operator == ">="){
			//SUB 3, 2, 1
			//JLT 3, ?(7)
			//JLT 3, #(7)
			System.out.println(":    SUB 0, 1, 0");
			System.out.println(":    JLT 0" + ", " +  (thenBlockSize + 1) + "(7)"); 
			thenBlock.emit();
			System.out.println(":    LD  7, " + elseBlockSize + "(7)");      
			elseBlock.emit();
		}
		else if (operator == "!="){
			//SUB 3, 1, 2
			//JEQ 3, ?(7)
			//JEQ 3, #(7)
			System.out.println(":    SUB 0, 0, 1");
			System.out.println(":    JEQ 0" + ", " + (thenBlockSize + 1)+ "(7)");
			thenBlock.emit();
			System.out.println(":    LD  7, " + elseBlockSize + "(7)");      
			elseBlock.emit(); 
		}	
	}

}
