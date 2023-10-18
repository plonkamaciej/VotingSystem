/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.lab1.votesystem.ModelList;

import pl.polsl.lab1.votesystem.Model.VoteSystemModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SuperStudent-PL
 */
public class VoteSystemModelList {
    private List<VoteSystemModel> CandidateList;
    public VoteSystemModelList() {

        this.CandidateList = new ArrayList<>();
    }
    public List<String> getVoteSystemModelList() {
        return new ArrayList(CandidateList);
    }
    public void addVoteSystemModelList(VoteSystemModel candidate) {

        CandidateList.add(candidate);
    }
    public int size(){
        return CandidateList.size();
    }

    public void vote(int i){CandidateList.get(i).vote();}

    public void print(){
        for(int i = 0; i < CandidateList.size(); i++) {
            System.out.println(i+1 + ". " + CandidateList.get(i).getName() + " " + CandidateList.get(i).getVoteCount());
        }
    }

}
