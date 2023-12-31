/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.lab1.votesystem.Conteroller;

import pl.polsl.lab1.votesystem.ModelList.VoteSystemModelList;
import pl.polsl.lab1.votesystem.View.VoteSystemView;
import pl.polsl.lab1.votesystem.fileMenager.FileManager;

import java.io.IOException;
import java.util.List;

/**
 * Controller for model and view
 * @author Maciej-Plonka
 * @version 1.11
 */

public class VoteSystemController {

    /**
     * Model class
     */
    private final VoteSystemModelList model;

    /**
     * view class
     */
    private final VoteSystemView view;

    /**
     * constructor
     * @param model
     * @param view
     */
   public VoteSystemController(VoteSystemModelList model, VoteSystemView view){
      this.model = model;
      this.view = view;
   }

    /**
     * get size of the model
     */
   public int getSize(){
       return model.size();
   }
    /**
     * give vote to Candidate
     */
   public void vote(int i){model.vote(i-1);}

    /**
     * print candidate list
     */
    public void updateView(){
        view.print(model);
    }

    /**
     * print candidate list after voting with comment on who you voted for
     * @param num number of candidate
     */
    public void updateView(int num){
        view.print(model, num);
    }

    /**
     *print string to file
     */
    public void toFile() throws IOException {
       model.printToFile();
    }
    /**
     * print error message
     */
    public void  viewError(){
       view.error();
    }

    /**
     * print error message when user input wrong number
     */
    public void  viewError(int num){
        view.error(num, model);
    }

    /**
     * ask user for vote number
     */
    public int askToVote(){
       return view.askToVote();
    }
    /**
     *ask user for name
     */
    public String askName(){
        return view.askName();
    }

    /**
     * add candidate to candidate model list
     * @param name of candidate
     */
    public void addCandidate(String name) throws IOException {
       model.addCandidate(name);
    }

    /**
     * delete candidate
     * @param number of deleted candidate
     */
    public void delateCandidate(Integer number){
        model.delateCandidate(number-1);
    }

    /**
     * read user
     * use: fileManager class
     */
    public void readUsers(){
        for (List<String> strings : FileManager.Reader(FileManager.getUserFile())) {
            System.out.println(strings);
        }

    }
}