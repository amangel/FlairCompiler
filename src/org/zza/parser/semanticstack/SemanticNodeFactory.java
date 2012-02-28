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
    }

    public SemanticNode getNewNode(String nodeType) throws ParsingException{
        try {
            SemanticNode node =  (SemanticNode) nodeMap.get(nodeType).newInstance();
            node.setToken(recentTokens.getMostRecent());
            return node;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("error while geting new node from node factory, returning null");
        return null;//TODO: make the catch handle the situtation and die gracefully instead of returning null?
    }
}
