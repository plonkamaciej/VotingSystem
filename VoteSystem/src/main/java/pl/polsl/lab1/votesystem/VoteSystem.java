/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pl.polsl.lab1.votesystem;

import pl.polsl.lab1.votesystem.View.VoteSystemView;
import pl.polsl.lab1.votesystem.ModelList.VoteSystemModelList;
import pl.polsl.lab1.votesystem.Model.VoteSystemModel;
import pl.polsl.lab1.votesystem.Conteroller.VoteSystemController;

/**
 *
 * @author SuperStudent-PL
 */


public class VoteSystem {

    public static void main(String[] args) {
          
      VoteSystemModel candidat  = retriveFromDatabase();
      VoteSystemModelList model = new VoteSystemModelList();
     
      VoteSystemModel Candidat1 = new VoteSystemModel();
      Candidat1.setName("john");
      Candidat1.setVoteCount(15);
      
      model.addVoteSystemModelList(candidat);
      model.addVoteSystemModelList(Candidat1);
      
      VoteSystemView view = new VoteSystemView();
      VoteSystemController controller = new VoteSystemController(model, view);
      System.out.println( controller.getModel().getVoteSystemModelList());
   }

   private static VoteSystemModel retriveFromDatabase(){
      VoteSystemModel Candit1 = new VoteSystemModel();
      Candit1.setName("Robert");
      Candit1.setVoteCount(10);
      return Candit1;
   }
  }

