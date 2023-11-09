/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.lab1.votesystem.Conteroller;

import pl.polsl.lab1.votesystem.ModelList.VoteSystemModelList;
import pl.polsl.lab1.votesystem.View.VoteSystemView;

import java.io.IOException;

/**
 * Controller for model and view
 * @author Maciej-Plonka
 */

public class VoteSystemController {
   private final VoteSystemModelList model;
   private final VoteSystemView view;

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
     */
    public void addCandidate(String name){
       model.addCandidate(name);
    }
}