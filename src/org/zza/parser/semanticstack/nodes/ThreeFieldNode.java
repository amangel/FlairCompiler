package org.zza.parser.semanticstack.nodes;

import org.zza.parser.semanticstack.SemanticStack;
import org.zza.visitor.NodeVisitor;

public abstract class ThreeFieldNode extends SemanticNode {
	
	protected SemanticNode rightHandSide;
	protected SemanticNode leftHandSide;
	protected SemanticNode middle;

	@Override
	public void runOnSemanticStack(SemanticStack stack) {
        rightHandSide = stack.pop();
        middle = stack.pop();
        leftHandSide = stack.pop();
        leftHandSide.setParent(this);
        middle.setParent(this);
        rightHandSide.setParent(this);
        stack.push(this);
		
	}

	public SemanticNode getLefthand(){
		return leftHandSide;
	}
	
	public SemanticNode getMiddle(){
		return middle;
	}
	
	public SemanticNode getRighthand(){
		return rightHandSide;
	}
	
	@Override
	public String getStringRepresentation() {
				return getName() + " {" + leftHandSide.getStringRepresentation() + ":" + middle.getStringRepresentation() + " : " + rightHandSide.getStringRepresentation() +"} ";
	}


}
