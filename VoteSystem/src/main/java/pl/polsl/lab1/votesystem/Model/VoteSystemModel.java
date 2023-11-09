/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.lab1.votesystem.Model;


/**
 * Model used later for list of model - candiates
 * @author Maciej-Plonka
 */

public class VoteSystemModel {
    private String name;
    private int voteCount;

    public VoteSystemModel(String name, int voteCount) {
        this.name = name;
        this.voteCount = voteCount;
    }

    /**
     *
     * @return name of signle candidate
     */
    public String getName() {
      return name;
   }

    /**
     *
     * @return numebr of votes that candidate already have
     */
   public int getVoteCount() {
      return voteCount;
   }

    /**
     * increase vote count of candidate
     */
   public void vote(){this.voteCount++;}

    @Override
    public String toString() {
        return "VoteSystemModel{" + "name=" + name + ", voteCount=" + voteCount + '}';
    }


}
