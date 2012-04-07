package org.zza.parser.semanticstack;

import java.util.HashMap;

import org.zza.parser.ParsingException;
import org.zza.parser.RecentTokensStack;
import org.zza.parser.parsingstack.Entry;
import org.zza.parser.semanticstack.nodes.*;

public class SemanticNodeFactory {
    
    private HashMap<String, Class<? extends SemanticNode>> nodeClassMap;
    private final RecentTokensStack recentTokens;
    
    public SemanticNodeFactory(final RecentTokensStack recentTokens) {
        this.recentTokens = recentTokens;
        buildNodeMap();
    }
    
    private void buildNodeMap() {
        nodeClassMap = new HashMap<String, Class<? extends SemanticNode>>();
        nodeClassMap.put("identifier", IdentifierNode.class);
        nodeClassMap.put("plus", PlusExpressionNode.class);
        nodeClassMap.put("minus", MinusExpressionNode.class);
        nodeClassMap.put("division", DivisionExpressionNode.class);
        nodeClassMap.put("multiplication", MultiplicationExpressionNode.class);
        nodeClassMap.put("assignment", AssignmentExpressionNode.class);
        nodeClassMap.put("type", TypeNode.class);
        nodeClassMap.put("function", FunctionNode.class);
        nodeClassMap.put("integer", IntegerNode.class);
        nodeClassMap.put("real", RealNode.class);
        nodeClassMap.put("ParameterNode", ParameterNode.class);
        nodeClassMap.put("AllParameters", AllParametersNode.class);
        nodeClassMap.put("variabledeclaration", VariableDeclarationNode.class);
        nodeClassMap.put("allvariables", AllVariableDeclarationsNode.class);
        nodeClassMap.put("argument", ArgumentNode.class);
        nodeClassMap.put("argumentbegin", MarkerNode.class);
        nodeClassMap.put("compare", CompareOperatorNode.class);
        nodeClassMap.put("comparison", ComparisonNode.class);
        nodeClassMap.put("while", WhileExpressionNode.class);
        nodeClassMap.put("negative", NegativeExpressionNode.class);
        nodeClassMap.put("compoundbegin", MarkerNode.class);
        nodeClassMap.put("compound", CompoundStatementNode.class);
        nodeClassMap.put("ProgramHeader", ProgramHeaderNode.class);
        nodeClassMap.put("Program", ProgramNode.class);
        nodeClassMap.put("declarations", DeclarationsNode.class);
        nodeClassMap.put("print", PrintStatementNode.class);
        nodeClassMap.put("functioncall", FunctionCallNode.class);
        nodeClassMap.put("functionheading", FunctionHeadingNode.class);
        nodeClassMap.put("allfunctions", AllFunctionDeclarationsNode.class);
        nodeClassMap.put("functionbody", FunctionBodyNode.class);
        nodeClassMap.put("return", ReturnStatementNode.class);
        nodeClassMap.put("if", IfStatementNode.class);
    }
    
    public SemanticNode getNewNode(final Entry node) throws ParsingException {
        return getNewNode(node.toString());
    }
    
    public SemanticNode getNewNode(final String nodeType) throws ParsingException {
        try {
            if (nodeClassMap.containsKey(nodeType)) {
                final SemanticNode node = nodeClassMap.get(nodeType).newInstance();
                node.setToken(recentTokens.getMostRecent());
                return node;
            } else {
                throw new ParsingException("Factory attempting to create node that doesn't exist: " + nodeType);
            }
        } catch (final InstantiationException e) {
            e.printStackTrace();
        } catch (final IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new ParsingException("Error while geting new node from node factory. Tried to retrieve: " + nodeType);
    }
}
