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

   public void setCandidat(VoteSystemModel candidat){
      model.addVoteSystemModelList(candidat);
   }

    @Override
    public String toString() {
        return "VoteSystemController{" + "model=" + model + '}';
    }

    public VoteSystemModelList getModel() {
        return model;
    }
    
   
    
}