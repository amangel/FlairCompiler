package org.zza.parser.semanticstack;

import java.util.HashMap;

import org.zza.parser.ParsingException;
import org.zza.parser.RecentTokensStack;
import org.zza.parser.semanticstack.nodes.*;


public class SemanticNodeFactory {
    
    private HashMap<String, Class<?>> nodeMap;
    private RecentTokensStack recentTokens;
    
    public SemanticNodeFactory(RecentTokensStack recentTokens) {
        this.recentTokens = recentTokens;
        buildNodeMap();
    }
    
    private void buildNodeMap() {
        nodeMap = new HashMap<String, Class<?>>();
        nodeMap.put("identifier", IdentifierNode.class);
        nodeMap.put("plus", PlusExpressionNode.class);
        nodeMap.put("minus", MinusExpressionNode.class);
        nodeMap.put("division", DivisionExpressionNode.class);
        nodeMap.put("multiplication", MultiplicationExpressionNode.class);
        nodeMap.put("assignment", AssignmentExpressionNode.class);
        nodeMap.put("type", TypeNode.class);
        nodeMap.put("function", FunctionNode.class);
        nodeMap.put("compoundstatement", CompoundStatementNode.class);
        nodeMap.put("integer", IntegerNode.class);
        nodeMap.put("real", RealNode.class);
        nodeMap.put("parameter", ParameterNode.class);
        nodeMap.put("allparameters", AllParametersNode.class);
        nodeMap.put("program", ProgramNode.class);
        nodeMap.put("variabledeclaration", VariableDeclarationNode.class);
        nodeMap.put("allvariables", AllVariableDeclarationsNode.class);
        nodeMap.put("argument", ArgumentNode.class);
        nodeMap.put("argumentbegin", MarkerNode.class);
        nodeMap.put("compare", CompareNode.class);
        nodeMap.put("comparison", ComparisonNode.class);
        nodeMap.put("while", WhileExpressionNode.class);
        nodeMap.put("negative", NegativeExpressionNode.class);
        nodeMap.put("compoundbegin", MarkerNode.class);
        nodeMap.put("compound", CompoundStatementNode.class);
        nodeMap.put("programheader", ProgramHeaderNode.class);
        nodeMap.put("program", ProgramNode.class);
        nodeMap.put("declarations", DeclarationsNode.class);
        nodeMap.put("print", PrintStatementNode.class);
        nodeMap.put("functioncall", FunctionCallNode.class);
        nodeMap.put("functionheading", FunctionHeadingNode.class);
        nodeMap.put("function", FunctionNode.class);
        nodeMap.put("allfunctions", AllFunctionDeclarationsNode.class);
        nodeMap.put("functionbody", FunctionBodyNode.class);
    }
    
    public SemanticNode getNewNode(String nodeType) throws ParsingException{
        try {
            if(nodeMap.containsKey(nodeType)) {
                SemanticNode node =  (SemanticNode) nodeMap.get(nodeType).newInstance();
                node.setToken(recentTokens.getMostRecent());
                return node;
            } else {
                throw new ParsingException("Factory attempting to create node that doesn't exist: "+nodeType);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("error while geting new node from node factory, returning null");
        return null;//TODO: make the catch handle the situtation and die gracefully instead of returning null?
    }
}
