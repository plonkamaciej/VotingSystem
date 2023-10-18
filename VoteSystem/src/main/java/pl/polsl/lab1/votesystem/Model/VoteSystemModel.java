/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.lab1.votesystem.Model;


/**
 *
 * @author SuperStudent-PL
 */

public class VoteSystemModel {
    private String name;
    private int voteCount;
    public String getName() {
      return name;
   }
   
   public void setName(String name) {
      this.name = name;
   }
   
   public int getVoteCount() {
      return voteCount;
   }
   
   public void setVoteCount(int voteCount) {
      this.voteCount = voteCount;
   }

   public void vote(){this.voteCount++;}

    @Override
    public String toString() {
        return "VoteSystemModel{" + "name=" + name + ", voteCount=" + voteCount + '}';
    }


}
