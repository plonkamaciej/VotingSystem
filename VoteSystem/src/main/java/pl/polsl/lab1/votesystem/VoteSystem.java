/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pl.polsl.lab1.votesystem;

import pl.polsl.lab1.votesystem.View.VoteSystemView;
import pl.polsl.lab1.votesystem.ModelList.VoteSystemModelList;
import pl.polsl.lab1.votesystem.Model.VoteSystemModel;
import pl.polsl.lab1.votesystem.Conteroller.VoteSystemController;
import pl.polsl.lab1.votesystem.fileMenager.FileMenager;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static pl.polsl.lab1.votesystem.fileMenager.FileMenager.Reader;

/**
 *
 * @author SuperStudent-PL
 */


public class VoteSystem {
    static private final File userFile = new File("C:\\Users\\Maciek\\IdeaProjects\\VotingSystem\\VoteSystem\\src\\main\\java\\pl\\polsl\\lab1\\votesystem\\fileMenager\\Users.txt");
    static private final File candidateFile = new File("C:\\Users\\Maciek\\IdeaProjects\\VotingSystem\\VoteSystem\\src\\main\\java\\pl\\polsl\\lab1\\votesystem\\fileMenager\\Candidate.txt");



    public static void main(String [] args) throws IOException {
        int num = 2;
        String  user = "Ala3";
        VoteSystemModelList model = retriveFromDatabase();
        VoteSystemView view = new VoteSystemView();
        VoteSystemController controller = new VoteSystemController(model, view);


        if(args.length != 4){
            view.error();
            return;
        }

        try {
            for (int i = 0; i < args.length; i++) {
                if(args[i].equals("-v")) num = Integer.parseInt(args[i+1]);
                if(args[i].equals("-u")) user = args[i+1];
            }

        }catch (Exception e){
            view.error();
        }


        List<List<String>> users = FileMenager.Reader(userFile);

        if(findUser(user, users)){
            System.out.println("You have already voted");
            return;
        }
        else  FileMenager.addToFile(user, userFile);
        controller.updateView();

        try {
             if (num <= controller.getSize()) {
                    controller.vote(num);
                    controller.updateView(user, num);

                } else {
                    System.out.println("You entered wrong number");
                }

        } catch (Exception e) {
            view.error();
        }
    }

    private static VoteSystemModelList retriveFromDatabase(){

        VoteSystemModelList candidateList = new VoteSystemModelList();
        List<List<String>> Candidates = Reader(candidateFile);
        for (List<String>s:Candidates){
            candidateList.addVoteSystemModelList(new VoteSystemModel(s.get(0), Integer.parseInt(s.get(1))));
        }
        return candidateList;
    }



    public static boolean findUser(String user, List<List<String>> users){
    boolean found = false;
        for (List<String> innerList : users) {
            if (innerList.contains(user)) {
                 found =  true;
                 break;
            }
        }
        return found;
    }

}



