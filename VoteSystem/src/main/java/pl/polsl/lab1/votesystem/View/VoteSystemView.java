/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.lab1.votesystem.View;

import pl.polsl.lab1.votesystem.ModelList.VoteSystemModelList;

/**
 *
 * @author SuperStudent-PL
 */

public class VoteSystemView {
   public void printStudentDetails(VoteSystemModelList Candidat){
      System.out.println("Candit List: ");
      System.out.println(Candidat.getVoteSystemModelList());
   }
}