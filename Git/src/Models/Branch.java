package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Branch {
    private String name;
    //key design change
    //private Map<String, Package>content;
    private Map<String, Node> content;

    private List<Commit> commits = new ArrayList<>();

    public Branch(String name, Map<String, Node> content) {
        this.name = name;
        this.content= content;
    }

    public Branch(String name) {
        this.name = name;
        this.content = new HashMap<>();
        this.commits = new ArrayList<>();
    }
    public Branch deepCopy(){
        Map<String ,Node> copiedContent=new HashMap<>();
        for(Map.Entry<String,Node> entry:this.content.entrySet()){
                copiedContent.put(entry.getKey(),entry.getValue().deepCopy());
        }
        List<Commit> copiedCommits = new ArrayList<>(this.commits);
        return new Branch(this.name,copiedContent);

    }

    public List<Commit> getCommits() {
        return commits;
    }

    public void setCommits(List<Commit> commits) {
        this.commits = commits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Node> getContent() {
        return content;
    }

    public void setContent(Map<String, Node> content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "Models.Branch{" +
                "name='" + name + '\'' +
                ", Content=" + content +
                '}';
    }
}
