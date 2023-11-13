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
 * @author Maciej-Plonka
 */

public class VoteSystemView {

   /**
    *print Candidate list
    */
   public void print(VoteSystemModelList candidate){
      System.out.println("Candidate List: ");
      candidate.print();
      }

   /**
    *print candidate list after user voted
    * @param candidate lis of candidates
    * @param num user vote
    */
   public void print(VoteSystemModelList candidate, int num){
      System.out.println("Candidate List After your vote: ");
      System.out.println("You voted for: nr. " + (num));
      candidate.print();
   }
   /**
    * print error message
    */
   public void error() {
      System.out.println("use -u [username] -v [Candidate number] to vote");
      System.out.println("for example 'javac vote -u Steve -v 0' to vote for position number 0 on list");
   }

   /**
    * print error message with user invalid input
    * @param num user invalid vote
    * @param candidate candidate list
    *
    */
   public void error(int num, VoteSystemModelList candidate) {
      System.out.println("You have entered wrong number");
      System.out.println("You have entered: " + num);
      System.out.println("Those are the possible Candidates and their number");
      candidate.print();
   }

   /**
    *handle user input of candidate number
    * @return candidate number that user voted for
    */
   public int askToVote() throws NumberFormatException {
      Scanner myObj = new Scanner(System.in);  // Create a Scanner object
      System.out.println("Enter vote [1,2,...,n]: ");
      String  vote = myObj.nextLine();
      try {
         return Integer.parseInt(vote);
      }catch (NumberFormatException e){
         System.out.println("Invalid input");
      }
      return 0;
   }
   /**
    * ask name of the user
    * @return username
    */
   public String askName(){
      Scanner myObj = new Scanner(System.in);  // Create a Scanner object
      System.out.println("Enter your name: ");
       return myObj.nextLine();
   }

   }
