/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pl.polsl.lab1.votesystem;

import pl.polsl.lab1.votesystem.CustomException.IncorrectFileNameException;
import pl.polsl.lab1.votesystem.View.VoteSystemView;
import pl.polsl.lab1.votesystem.ModelList.VoteSystemModelList;
import pl.polsl.lab1.votesystem.Model.VoteSystemModel;
import pl.polsl.lab1.votesystem.Conteroller.VoteSystemController;
import pl.polsl.lab1.votesystem.fileMenager.FileManager;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static pl.polsl.lab1.votesystem.fileMenager.FileManager.Reader;

/**
 *
 * @author SuperStudent-PL
 */


public class VoteSystem {


    /**
     *main function
     * @param args command line arguments
     * handle all kind of inputs.
     * if else based menu display diffrend kind of menus or massages based on given arguments
     * You are allowed to input args in any combination
     */
    public static void main(String [] args) throws IOException, IncorrectFileNameException {


        File userFile = FileManager.getUserFile();
        File candidateFile = FileManager.getCandidateFile();

        if(!userFile.exists()){
            throw new IncorrectFileNameException("Error opening file " + userFile);
        }
        if(!candidateFile.exists()){
            throw new IncorrectFileNameException("Error opening file " + candidateFile);
        }


        int num = 0;
        String  user = "";
        VoteSystemModelList model = retrieveFromDatabase(candidateFile);
        VoteSystemView view = new VoteSystemView();
        VoteSystemController controller = new VoteSystemController(model, view);
        List<List<String>> users = FileManager.Reader(userFile);
        


        if(args.length == 0){
            user = controller.askName();
            if (!verifyUser(user, users, userFile)) {
                controller.updateView();
                while (true) {
                    num = controller.askToVote();
                    if (num == 0) {

                        continue;
                    }
                    try {
                        controller.vote(num);
                    } catch (Exception e) {
                        controller.viewError(num);
                        continue;
                    }
                    controller.updateView(num);
                    controller.toFile();
                    return;
                }
            }
        }

        else if(args.length == 2 && args[0].equals("-add")){
            controller.addCandidate(args[1]);
            controller.updateView();
            System.out.println("Added " + args[1] + " to list");
            controller.toFile();
        }

        else if(args.length != 4){
            controller.viewError();
        }
        /* Handle user args in different combinations */
        else {
            try {
                for (int i = 0; i < args.length; i++) {
                    if (args[i].equals("-v")){ num = Integer.parseInt(args[i + 1]); i++;}
                    else if (args[i].equals("-u")) {user = args[i + 1];i++;}
                    else throw new IOException();
                }

            } catch (Exception e) {
                controller.viewError();
                return;
            }

            if(verifyUser(user, users, userFile)) return;
            controller.updateView();
                try {
                        controller.vote(num);
                        controller.updateView(num);
                }
                catch(NumberFormatException e){
                    controller.viewError(num);
                }

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
                FileManager.addToFile(user, userFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    /**
     * Get Candidates names and vote counts.
     */
    private static VoteSystemModelList retrieveFromDatabase(File candidateFile){

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

