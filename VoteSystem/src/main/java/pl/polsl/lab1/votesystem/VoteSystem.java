/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pl.polsl.lab1.votesystem;

import org.jetbrains.annotations.NotNull;
import pl.polsl.lab1.votesystem.View.VoteSystemView;
import pl.polsl.lab1.votesystem.ModelList.VoteSystemModelList;
import pl.polsl.lab1.votesystem.Model.VoteSystemModel;
import pl.polsl.lab1.votesystem.Conteroller.VoteSystemController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SuperStudent-PL
 */


public class VoteSystem {

    public static void main(String [] args) {
        int num = 5;
        String  User = "";
        if(args.length != 4){
            System.out.println("use -u [username] -v [Candidate number] to vote");
            System.out.println("for example 'javac vote -u Steve -v 0' to vote for position number 0 on list");
            return;
        }

        try {
            for (int i = 0; i < args.length; i++) {
                if(args[i].equals("-v")) num = Integer.parseInt(args[i+1]);
                if(args[i].equals("-u")) User = args[i+1];
            }

        }catch (Exception e){
            System.out.println("use -u [username] -v [Candidate number] to vote");
            System.out.println("for example 'javac vote -u Steve -v 0' to vote for position number 0 on list");
        }


        try {
            List<String> Users = Reader();
            if(Users.contains(User)){
                System.out.println("You have already voted");
                return;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        VoteSystemModelList model = retriveFromDatabase();
        VoteSystemView view = new VoteSystemView();
        VoteSystemController controller = new VoteSystemController(model, view);

        try {
             if (num <= controller.getSize()) {
                    controller.vote(num);
                    controller.updateView();
                } else {
                    System.out.println("You entered wrong number");
                }

        } catch (Exception e) {
            System.out.println("use -u [username] -v [Candidate number] to vote");
            System.out.println("for example 'javac vote -u Steve -v 0' to vote for position number 0 on list");
        }
    }

    private static VoteSystemModelList retriveFromDatabase() {

        VoteSystemModelList CandidateList = new VoteSystemModelList();
        VoteSystemModel Candidate = new VoteSystemModel();
        VoteSystemModel Candidate1 = new VoteSystemModel();
        Candidate.setName("Robert");
        Candidate.setVoteCount(10);
        CandidateList.addVoteSystemModelList(Candidate);
        Candidate1.setName("John");
        Candidate1.setVoteCount(15);
        CandidateList.addVoteSystemModelList(Candidate1);
        return CandidateList;
    }

    private static List<String> Reader() throws IOException {
        List<String> listOfUsers;
        {
            listOfUsers = new ArrayList<String>();
            // load the data from file
            listOfUsers = Files.readAllLines(Paths.get("C:\\Users\\Maciek\\IdeaProjects\\VotingSystem\\VoteSystem\\src\\main\\java\\pl\\polsl\\lab1\\votesystem\\Users.txt"));
            // convert arraylist to array
            String[] array = listOfUsers.toArray(new String[0]);
        }
        return listOfUsers;
    }
}



