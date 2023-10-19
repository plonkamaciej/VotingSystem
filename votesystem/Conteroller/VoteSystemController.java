/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.lab1.votesystem.Conteroller;

import pl.polsl.lab1.votesystem.Model.VoteSystemModel;
import pl.polsl.lab1.votesystem.ModelList.VoteSystemModelList;
import pl.polsl.lab1.votesystem.View.VoteSystemView;

/**
 *
 * @author SuperStudent-PL
 */

public class VoteSystemController {
   private VoteSystemModelList model;
   private VoteSystemView view;

   public VoteSystemController(VoteSystemModelList model, VoteSystemView view){
      this.model = model;
      this.view = view;
   }

   public void setCandidate(VoteSystemModel candidate){
      model.addVoteSystemModelList(candidate);
   }

   public int getSize(){
       return model.size();
   }
   public void vote(int i){model.vote(i);}
    public void updateView(){
        view.print(model);
    }
    public void updateView(String user, int num){
        view.print(model, user, num);
    }
}