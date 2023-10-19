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
   public void print(VoteSystemModelList Candidate){
      System.out.println("Candidate List: ");
      Candidate.print();
      }
   }
