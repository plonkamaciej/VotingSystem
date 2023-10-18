/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pl.polsl.lab1.votesystem;

import org.jetbrains.annotations.NotNull;
import pl.polsl.lab1.votesystem.View.VoteSystemView;
import pl.polsl.lab1.votesystem.ModelList.VoteSystemModelList;
import pl.polsl.lab1.votesystem.Model.VoteSystemModel;
import pl.polsl.lab1.votesystem.Conteroller.VoteSystemController;

/**
 *
 * @author SuperStudent-PL
 */


public class VoteSystem {

    public static void main(String @NotNull [] args) {
      VoteSystemModelList model = retriveFromDatabase();
      VoteSystemView view = new VoteSystemView();
      VoteSystemController controller = new VoteSystemController(model, view);

      try {
      if(args[0].equals("-v")){
          int num = Integer.parseInt(args[1]);
          if (num <= controller.getSize()){
              controller.vote(num);
              controller.updateView();
          }
          else {
              System.out.println("You entered wrong number");
          }
      }
      else {
          System.out.println("use -v to vote");
          System.out.println("for example 'javac VoteSystem -v 0' to vote for position number 0 on list");
      }}
      catch (Exception e) {
          System.out.println("use -v to vote");
          System.out.println("for example './vote -v 0' to vote for position number 0 on list");
      }
   }

   private static VoteSystemModelList retriveFromDatabase(){

      VoteSystemModelList CandidateList = new VoteSystemModelList();
      VoteSystemModel Candidate = new VoteSystemModel();
      VoteSystemModel Candidate1 = new VoteSystemModel();
      Candidate.setName("Robert");
      Candidate.setVoteCount(10);
      CandidateList.addVoteSystemModelList(Candidate);
      Candidate1.setName("John");
      Candidate1.setVoteCount(15);
      CandidateList.addVoteSystemModelList(Candidate1);
      return CandidateList;
   }
  }

