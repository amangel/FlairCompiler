package org.zza.visitor;

import org.zza.parser.semanticstack.nodes.AllFunctionDeclarationsNode;
import org.zza.parser.semanticstack.nodes.AllParametersNode;
import org.zza.parser.semanticstack.nodes.AllVariableDeclarationsNode;
import org.zza.parser.semanticstack.nodes.ArgumentNode;
import org.zza.parser.semanticstack.nodes.AssignmentExpressionNode;
import org.zza.parser.semanticstack.nodes.CompareNode;
import org.zza.parser.semanticstack.nodes.ComparisonNode;
import org.zza.parser.semanticstack.nodes.CompoundStatementNode;
import org.zza.parser.semanticstack.nodes.DeclarationsNode;
import org.zza.parser.semanticstack.nodes.DivisionExpressionNode;
import org.zza.parser.semanticstack.nodes.EmptyNode;
import org.zza.parser.semanticstack.nodes.FunctionBodyNode;
import org.zza.parser.semanticstack.nodes.FunctionCallNode;
import org.zza.parser.semanticstack.nodes.FunctionHeadingNode;
import org.zza.parser.semanticstack.nodes.FunctionNode;
import org.zza.parser.semanticstack.nodes.IdentifierNode;
import org.zza.parser.semanticstack.nodes.IfStatementNode;
import org.zza.parser.semanticstack.nodes.IntegerNode;
import org.zza.parser.semanticstack.nodes.MinusExpressionNode;
import org.zza.parser.semanticstack.nodes.MultiplicationExpressionNode;
import org.zza.parser.semanticstack.nodes.NegativeExpressionNode;
import org.zza.parser.semanticstack.nodes.ParameterNode;
import org.zza.parser.semanticstack.nodes.PlusExpressionNode;
import org.zza.parser.semanticstack.nodes.PrintStatementNode;
import org.zza.parser.semanticstack.nodes.ProgramHeaderNode;
import org.zza.parser.semanticstack.nodes.ProgramNode;
import org.zza.parser.semanticstack.nodes.RealNode;
import org.zza.parser.semanticstack.nodes.ReturnStatementNode;
import org.zza.parser.semanticstack.nodes.SemanticNode;
import org.zza.parser.semanticstack.nodes.TwoFieldNode;
import org.zza.parser.semanticstack.nodes.TypeNode;
import org.zza.parser.semanticstack.nodes.VariableDeclarationNode;
import org.zza.parser.semanticstack.nodes.WhileExpressionNode;

public class TypeCheckingVisitor extends NodeVisitor {

	@Override
	public String visit(AssignmentExpressionNode node) {
		return null; //TODO

	}

	@Override
	public String visit(CompoundStatementNode node) {
		return null; //TODO

	}

	@Override
	public String visit(DivisionExpressionNode node) {
		return null; //TODO

	}

	@Override
	public String visit(FunctionNode node) {
		return null; //TODO

	}

	@Override
	public String visit(IdentifierNode node) {
		return null; //TODO

	}

	@Override
	public String visit(IntegerNode node) {
		return null; //TODO

	}

	@Override
	public String visit(MinusExpressionNode node) {
		return null; //TODO

	}

	@Override
	public String visit(MultiplicationExpressionNode node) {
		return null; //TODO

	}

	@Override
	public String visit(ParameterNode node) {
		return null; //TODO

	}

	@Override
	public String visit(PlusExpressionNode node) {
		return null; //TODO

	}

	@Override
	public String visit(ProgramNode node) {
		return null; //TODO

	}

	@Override
	public String visit(RealNode node) {
		return null; //TODO

	}

	@Override
	public String visit(TypeNode node) {
		return null; //TODO

	}

	@Override
	public String visit(VariableDeclarationNode node) {
		return null; //TODO

	}

    @Override
    public String visit(AllParametersNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(AllVariableDeclarationsNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(ArgumentNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(CompareNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(ComparisonNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(WhileExpressionNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(NegativeExpressionNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(ProgramHeaderNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(DeclarationsNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(PrintStatementNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(FunctionCallNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(FunctionHeadingNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(AllFunctionDeclarationsNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(FunctionBodyNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(ReturnStatementNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(IfStatementNode node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String visit(EmptyNode node) {
        // TODO Auto-generated method stub
        return null;
    }

}
