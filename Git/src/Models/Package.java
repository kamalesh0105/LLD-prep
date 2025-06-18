package Models;

import java.util.HashMap;
import java.util.Map;

public class Package implements Node {
        private String name;
        private HashMap<String,Node>content;
    public Package(String name,HashMap<String,Node>content) {
        this.name = name;
        this.content=content;
    }

    public Package(String name) {
        this.name = name;
        content=new HashMap<>();
    }
    @Override
    public Node deepCopy(){
        HashMap<String,Node>copiedContent=new HashMap<>();
        for(Map.Entry<String,Node>entry:this.content.entrySet()){
            copiedContent.put(entry.getKey(),entry.getValue().deepCopy());
        }
        return new Package(this.name,copiedContent);
    }
    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Node> getContent() {
        return content;
    }

    public void setContent(HashMap<String, Node> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Models.Package{" +
                "name='" + name + '\'' +
                ", Content=" + content +
                '}';
    }
}
