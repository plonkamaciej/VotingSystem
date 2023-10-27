/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.lab1.votesystem.View;

//import org.jetbrains.annotations.NotNull;
import pl.polsl.lab1.votesystem.ModelList.VoteSystemModelList;
import java.util.Scanner;

/**
 * handle all view operation
 * @author SuperStudent-PL
 */

public class VoteSystemView {
   public void print(VoteSystemModelList candidate){
      System.out.println("Candidate List Before your vote: ");
      candidate.print();
      }

   public void print(VoteSystemModelList candidate, int num){
      System.out.println("Candidate List After your vote: ");
      System.out.println("You voted for: nr. " + (num));
      candidate.print();
   }

   public void error() {
      System.out.println("use -u [username] -v [Candidate number] to vote");
      System.out.println("for example 'javac vote -u Steve -v 0' to vote for position number 0 on list");
   }

   public int askToVote(){
      Scanner myObj = new Scanner(System.in);  // Create a Scanner object
      System.out.println("Enter vote [1,2,...,n]: ");
      String vote = myObj.nextLine();  // Read user input
      return Integer.parseInt(vote);
   }
   public String askName(){
      Scanner myObj = new Scanner(System.in);  // Create a Scanner object
      System.out.println("Enter your name: ");
       return myObj.nextLine();
   }

   }
