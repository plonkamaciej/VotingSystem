/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pl.polsl.lab1.votesystem;

import pl.polsl.lab1.votesystem.CustomException.IncorrectFileNameException;
import pl.polsl.lab1.votesystem.View.GUI;
import pl.polsl.lab1.votesystem.View.VoteSystemView;
import pl.polsl.lab1.votesystem.ModelList.VoteSystemModelList;
import pl.polsl.lab1.votesystem.Model.VoteSystemModel;
import pl.polsl.lab1.votesystem.Conteroller.VoteSystemController;
import pl.polsl.lab1.votesystem.fileMenager.FileManager;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static pl.polsl.lab1.votesystem.fileMenager.FileManager.Reader;

/**
 * @author Maciej-Plonka
 * @version 1.7
 */

public class VoteSystem {

    /**
     * This interface represents a simple command checker.
     * It provides a method to check whether a specified command
     * exists in a given array of arguments.
     */


    interface CommandChecker {
        /**
         * Checks if the specified command exists in the given array of arguments.
         *
         * @param args    The array of arguments to be checked.
         * @param command The command to be checked.
         * @return true if the command exists, false otherwise.
         */
        boolean checkCommand(String[] args, String command);
    }

    /**
     *main function
     * @param args command line arguments
     * if else based menu display diffrent kind of menus or massages based on given arguments
     * You are allowed to input args in any combination
     *  use -add to add candidate to the voting list
     *  use -show to show candidate list
     *  use -showV to show list of all voters
     *  use -show to show list of all candidates
     *  use -v with -u to vote
     * @throws IOException handle user input
     * @throws IncorrectFileNameException used when checking file existence
     */

    
    public static void main(String [] args) throws IncorrectFileNameException, IOException {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI();
            }
        });

            CommandChecker checkCommand = (args1, command) -> {
                for (String arg : args1) {
                    if (arg.equals(command)) {
                        return true;
                    }
                }
                return false;
            };

            String[] arguments = {"-add", "-show", "-showV", "-v", "-u"};

        File userFile = FileManager.getUserFile();
        File candidateFile = FileManager.getCandidateFile();

        if(!userFile.exists()){
            throw new IncorrectFileNameException("Error opening file " + userFile);
        }
        if(!candidateFile.exists()){
            throw new IncorrectFileNameException("Error opening file " + candidateFile);
        }

        int num = 0;
        String user = "";
        VoteSystemModelList model = retrieveFromDatabase(candidateFile);
        VoteSystemView view = new VoteSystemView();
        VoteSystemController controller = new VoteSystemController(model, view);
        List<List<String>> users = FileManager.Reader(userFile);


        boolean found = false;

        for (String arg : args) {
            if (checkCommand.checkCommand(arguments, arg)) {
                found = true;
                break;
            }
        }
        if (!found) {
            controller.viewError();
        }
        else handleInput(args, user, users, userFile, num, controller);
    }


    /**
     * Handles input processing for the VoteSystem.
     *
     * This method takes various parameters including command-line arguments, user information,
     * a file representing user data, a numeric value, and a controller for the VoteSystem.
     * It processes the input and performs necessary actions based on the provided parameters.
     *
     * @param args          The command-line arguments passed to the program.
     * @param user          The current user's identifier.
     * @param users         A list containing user information.
     * @param userFile      The file containing all users.
     * @param num           A numeric value used in the processing.
     * @param controller    The controller for the VoteSystem.
     * @throws IOException  If an I/O error occurs while processing the input or reading the user file.
     */

    public static void handleInput(String [] args, String user, List<List<String>> users, File userFile, int num,  VoteSystemController controller) throws IOException {
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
            else controller.updateView();
        }

        else if(args.length == 2 && args[0].equals("-add")){
            controller.addCandidate(args[1]);
            controller.updateView();
            System.out.println("Added " + args[1] + " to list");
            controller.toFile();
        }

        else if(args.length == 1 && args[0].equals("-show")){
            controller.updateView();
        }

        else if(args.length == 1 && args[0].equals("-showV")){
            controller.readUsers();
        }

        else if(args.length == 2 && args[0].equals("-del")){
            try {
                controller.delateCandidate(Integer.parseInt(args[1]));
            }catch (Exception E){
                System.out.println("Error!");
            }
            controller.updateView();
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

            if(verifyUser(user, users, userFile)) {
                controller.updateView();
                return;
            }
            controller.updateView();
            try {
                controller.vote(num);
                controller.updateView(num);
                controller.toFile();
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
    private static VoteSystemModelList retrieveFromDatabase(File candidateFile) {

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

