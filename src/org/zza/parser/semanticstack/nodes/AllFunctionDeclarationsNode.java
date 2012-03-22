package org.zza.parser.semanticstack.nodes;

import org.zza.parser.semanticstack.SemanticStack;
import org.zza.visitor.NodeVisitor;

public class AllFunctionDeclarationsNode extends ArrayNode {

    @Override
    protected void orderArray() {
        //do nothing
    }

    @Override
    protected boolean popFromSemanticStackUntil(SemanticStack stack) {
        return stack.peek().getName().equals("Function");
    }

    @Override
    public String getName() {
        return "FunctionDeclarations";
    }

  @Override
  public String accept(final NodeVisitor visitor) {
      return visitor.visit(this);
  }
}
