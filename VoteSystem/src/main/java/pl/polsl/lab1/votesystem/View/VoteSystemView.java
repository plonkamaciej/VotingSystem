/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.lab1.votesystem.View;

//import org.jetbrains.annotations.NotNull;
import pl.polsl.lab1.votesystem.ModelList.VoteSystemModelList;

/**
 *
 * @author SuperStudent-PL
 */

public class VoteSystemView {
   public void print(VoteSystemModelList candidate){
      System.out.println("Candidate List Before your vote: ");
      candidate.print();
      }

   public void print(VoteSystemModelList candidate, String user, int num){
      System.out.println("Candidate List After your vote: ");
      System.out.println("You voted for: nr. " + num);
      candidate.print();
   }

   public void error() {
      System.out.println("use -u [username] -v [Candidate number] to vote");
      System.out.println("for example 'javac vote -u Steve -v 0' to vote for position number 0 on list");
   }


   }
