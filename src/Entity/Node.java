package Entity;

import java.util.ArrayList;
import java.util.List;

public class Node {  
    /** 
     * 到时候配置到tree标签的nodeIdProperty 
     */  
    private String id;  
    /** 
     * 到时候配置到tree标签的nodeTitleProperty 
     */  
    private String name;
    
	private List<Node> children = new ArrayList<Node>();  
      
    public String getId() {  
        return id;  
    }  
    public void setId(String id) {  
        this.id = id;  
    }  
    public List<Node> getChildren() {  
        return children;  
    }  
    public void setChildren(List<Node> children) {  
        this.children = children;  
    }  
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }
}