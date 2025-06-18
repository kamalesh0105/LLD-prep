import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        VirtualGitSystem git=new VirtualGitSystem();
        while(true){
            System.out.println("""
                    Git CLI
                    Choose Your option\n
                    1.Create Git Branch
                    2.Switch Branch
                    3.Current Branch
                    4.Add a File to Package
                    5.Create a Folder
                    6.Delete File/Package
                    7.Display Branches
                    8.Commit
                    9.Merge
                    10.DisplayRootFolder
                    11.Exit
                    """);
            System.out.print("Enter your Choice:");
            int choice=sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1:
                    System.out.println("Create Branch");
                    System.out.println(git.createBranch(sc));
                    break;
                case 2:
                    System.out.println("Switch Branch");
                    git.displayBranches();
                    System.out.print("Enter the branch name to switch:");
                    String targetBranch=sc.nextLine();
                    System.out.println(git.checkOutBranch(targetBranch));
                    break;
                case 3:
                    System.out.println("Currently in branch "+git.getCurrentBranchName());
                    break;
                case 4:
                    System.out.println(git.addFile(sc));
                    break;
                case 5:
                    System.out.println(git.createFolder(sc));
                    break;
                case 6:
                    System.out.println(git.deleteFileOrFolder(sc));
                    break;
                case 7:
                    git.displayBranches();
                    break;
                case 8:
                    System.out.println(git.commitBranch(sc));
                    break;
                case 9:
                    System.out.println(git.mergeBranches(sc));
                    break;
                case 10:
                    System.out.println("Displaying root folder:");
                    git.displayRootFolder();
                    break;
                case 11:
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
    }
    }
}