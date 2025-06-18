import Models.*;
import Models.Package;

import java.util.*;

public class VirtualGitSystem {
    public static HashMap<String , Branch>gitManager=new HashMap<>();
    private static String currentBranchName = null;
    public String getCurrentBranchName(){
        return currentBranchName;
    }
    public Branch getCurrentBranch(){
        return gitManager.getOrDefault(currentBranchName,null);
    }
    public String createBranch(Scanner sc){
        System.out.print("Enter Branch Name: ");
        String branchName = sc.nextLine();
        if (gitManager.containsKey(branchName)) {
            return ("Branch already exists.");
        }
        if(gitManager.isEmpty()) {
            Branch branch = new Branch(branchName);
            gitManager.put(branchName, branch);
            currentBranchName = branchName;
            return ("Branch " + branchName + " Created Successfully");
        }else{
            Branch currentBranchCopy=gitManager.get(currentBranchName).deepCopy();
            currentBranchCopy.setName(branchName);
            gitManager.put(branchName, currentBranchCopy);
            return ("Branch " + branchName + " Created Successfully  From"+currentBranchName);
        }
    }
    public void displayBranches(){
        gitManager.forEach((name,branch)->{
            System.out.println(name);
        });
    }
    public String commitBranch(Scanner sc){
     Branch branch=getCurrentBranch();
     if(branch==null)return "No Active Branch Selected...";
     List<Commit> commitList=branch.getCommits();
     System.out.print("Enter the Commit message: ");
     String msg = sc.nextLine();
     Commit commit=new Commit(msg,branch.deepCopy());
     commitList.add(commit);
     return "Commit Success";
    }
    public String deleteFileOrFolder(Scanner sc) {
        System.out.print("Enter name of file or folder to delete: ");
        String name = sc.nextLine();
        Branch branch = getCurrentBranch();
        if (branch == null) return "No current branch selected.";
        Map<String, Node> content = branch.getContent();
        if (!content.containsKey(name)) return "No file or folder named '" + name + "' found.";
        Node node = content.get(name);
        if (node instanceof Package folder) {
            if (!folder.getContent().isEmpty()) {
                System.out.print("Folder is not empty. Delete it and its contents? (yes/no): ");
                if (!sc.nextLine().trim().equalsIgnoreCase("yes")) return "Delete cancelled.";
            }
        }
        content.remove(name);
        return "Deleted '" + name + "' successfully.";
    }

    public String createFolder(Scanner sc){
        System.out.print("Enter Folder Name: ");
        String folderName=sc.nextLine();
        Branch branch = getCurrentBranch();
        if (branch != null) {
            if(branch.getContent().containsKey(folderName)){
                return "Folder Already Exists";
            }
            Package newFolder=new Package(folderName);
            branch.getContent().put(folderName,newFolder);
            return "Folder Created Successfully";
        }
        return "No current branch selected.";
    }
    public String addFile(Scanner sc) {
        String fileName,fileContent;
        System.out.print("Enter File Name: ");
        fileName=sc.nextLine();
        System.out.print("Enter File Content: ");
        fileContent=sc.nextLine();
        Branch branch = getCurrentBranch();
        if (branch != null) {
            if (branch.getContent().containsKey(fileName)) {
                return "File already exists.";
            }

            File newFile = new File(fileName, fileContent);
            branch.getContent().put(fileName, newFile);
            return "File added successfully.";
        }
        return "No current branch selected.";
    }


    public String checkOutBranch(String targetBranch) {
        if(!gitManager.containsKey(targetBranch)) {
            return "Branch does'nt Exists";
        }

        //Todo(note): Simulating purging of uncommitted changes
        Branch current = getCurrentBranch();
        if(current!=null){
            List<Commit>commits=current.getCommits();
            if(!commits.isEmpty()){
                Branch lastSnapshot = commits.get(commits.size() - 1).getBranchSnapshot();
                current.setContent(lastSnapshot.getContent());
            }else{
                current.setContent(new HashMap<>());
            }
        }
        currentBranchName = targetBranch;
        return "Switched to branch " + targetBranch;
    }

    public String mergeBranches(Scanner sc) {
        Branch targetBranch = getCurrentBranch();
        if (targetBranch == null) {
            return "No active branch selected.";
        }
        System.out.print("Enter the branch to be merged with: ");
        String sourceBranchName=sc.nextLine();

        if (!gitManager.containsKey(sourceBranchName)) {
            return "Source branch doesn't exist.";
        }
        if (sourceBranchName.equals(currentBranchName)) {
            return "Cannot merge a branch with itself.";
        }
        Branch sourceBranch = gitManager.get(sourceBranchName);

        Map<String, Node> sourceContent = sourceBranch.getContent();
        Map<String, Node> targetContent = targetBranch.getContent();
        for (Map.Entry<String, Node> entry : sourceContent.entrySet()) {
            String name = entry.getKey();
            Node sourceNode = entry.getValue().deepCopy();

            //Todo change the overwrite -> git rebase/reset
            targetContent.put(name, sourceNode);
        }
        return "Merged branch '" + sourceBranchName + "' into '" + currentBranchName + "'";
    }
    public  void displayRootFolder(){
        Branch branch=getCurrentBranch();
        if (branch == null) {
            System.out.println("No active branch selected.");
            return;
        }
        Map<String, Node> content = branch.getContent();
        if (content.isEmpty()) {
            System.out.println("Root folder is empty.");
            return;
        }
        for(Map.Entry<String,Node>entry:content.entrySet()){
            String name = entry.getKey();
            Node node = entry.getValue();

            if (node instanceof Models.File) {
                System.out.println("[File]   " + name);
            } else if (node instanceof Models.Package) {
                System.out.println("[Folder] " + name);
            } else {
                System.out.println("[Unknown] " + name);
            }
        }
    }
}
