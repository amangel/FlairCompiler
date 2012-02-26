package org.zza.parser.semanticstack;

import java.util.HashMap;

import org.zza.parser.ParsingException;
import org.zza.parser.RecentTokensStack;


public class SemanticNodeFactory {
    
    private HashMap<String, Class<SemanticNode>> nodeMap;
    private RecentTokensStack recentTokens;
    
    public SemanticNodeFactory(RecentTokensStack recentTokens) {
        this.recentTokens = recentTokens;
    }
    
    public SemanticNode getNewNode(String nodeType) throws ParsingException{
        try {
            SemanticNode node =  nodeMap.get(nodeType).newInstance();
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
