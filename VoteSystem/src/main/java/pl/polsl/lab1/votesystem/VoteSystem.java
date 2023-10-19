/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pl.polsl.lab1.votesystem;

//import org.jetbrains.annotations.NotNull;
import pl.polsl.lab1.votesystem.View.VoteSystemView;
import pl.polsl.lab1.votesystem.ModelList.VoteSystemModelList;
import pl.polsl.lab1.votesystem.Model.VoteSystemModel;
import pl.polsl.lab1.votesystem.Conteroller.VoteSystemController;
import pl.polsl.lab1.votesystem.fileMenager.FileMenager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static pl.polsl.lab1.votesystem.fileMenager.FileMenager.Reader;

/**
 *
 * @author SuperStudent-PL
 */


public class VoteSystem {

    public static void main(String [] args) throws IOException {
        int num = 0;
        String  user = "";

        if(args.length != 4){
            System.out.println("use -u [username] -v [Candidate number] to vote");
            System.out.println("for example 'javac vote -u Steve -v 0' to vote for position number 0 on list");
            return;
        }

        try {
            for (int i = 0; i < args.length; i++) {
                if(args[i].equals("-v")) num = Integer.parseInt(args[i+1]);
                if(args[i].equals("-u")) user = args[i+1];
            }

        }catch (Exception e){
            System.out.println("use -u [username] -v [Candidate number] to vote");
            System.out.println("for example 'javac vote -u Steve -v 0' to vote for position number 0 on list");
        }


        List<List<String>> users = FileMenager.Reader("src/main/java/pl/polsl/lab1/votesystem/Users.txt");
        if(users.contains(user)){
            System.out.println("You have already voted");
            return;
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

    private static VoteSystemModelList retriveFromDatabase() throws IOException {

        VoteSystemModelList candidateList = new VoteSystemModelList();
        List<List<String>> Candidates = Reader("/Users/maciejmac/IdeaProjects/VotingSystem/VoteSystem/src/main/java/pl/polsl/lab1/votesystem/fileMenager/Candidate.txt");

        //System.out.println(Candidates);
        for (List<String>s:Candidates){
            candidateList.addVoteSystemModelList(new VoteSystemModel(s.get(0), Integer.parseInt(s.get(1))));
        }
        return candidateList;
    }

}



