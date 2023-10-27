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

    public static void main(String [] args) throws IOException {

        int num = 0;
        String  user = "";
        VoteSystemModelList model = new VoteSystemModelList();
        model = retriveFromDatabase(model.getCandidateFile());
        VoteSystemView view = new VoteSystemView();
        VoteSystemController controller = new VoteSystemController(model, view);

        File userFile = controller.getUserFile();

        List<List<String>> users = FileMenager.Reader(userFile);

        if(args.length == 0){
            controller.updateView();
            user = controller.askName();
            if(verifyUser(user, users, userFile)) return;
            num = controller.askToVote();
            controller.vote(num);
            controller.updateView(num);
            controller.toFile();
            return;

        }

        else if(args.length == 2 && args[0].equals("-add")){
            controller.addCandidate(args[1]);
            controller.updateView();
            System.out.println("Added " + args[1] + " to list");
            controller.toFile();
            return;
        }

        else if(args.length != 4){
            controller.viewError();
            return;
        }

        else try {
            for (int i = 0; i < args.length; i++) {
                if(args[i].equals("-v")) num = Integer.parseInt(args[i+1]);
                if(args[i].equals("-u")) user = args[i+1];
            }

        }catch (Exception e){
            controller.viewError();
        }

        verifyUser(user, users, userFile);
        controller.updateView();

        try {
             if (num <= controller.getSize()) {
                    controller.vote(num);
                    controller.updateView(num);

                } else {
                    System.out.println("You entered wrong number");
                }

        } catch (Exception e) {
            System.out.println("error while voting");
        }
    }

    /**
     * Handle users
     * if user already voted return true and comment
     * add user to user file list
    */
    public static boolean verifyUser(String user, List<List<String>> users, File userFile) {
        if(findUser(user, users)){
            System.out.println("You have already voted");
            return true;
        }
        else {
            try {
                FileMenager.addToFile(user, userFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    /**
     * Get Candidates names and vote counts.
     */
    private static VoteSystemModelList retriveFromDatabase(File candidateFile){

        VoteSystemModelList candidateList = new VoteSystemModelList();
        List<List<String>> Candidates = Reader(candidateFile);
        for (List<String>s:Candidates){
            candidateList.addVoteSystemModelList(new VoteSystemModel(s.get(0), Integer.parseInt(s.get(1))));
        }
        return candidateList;
    }

    /**
     * Check if given user already voted
     * search for user in user file.
     */

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



