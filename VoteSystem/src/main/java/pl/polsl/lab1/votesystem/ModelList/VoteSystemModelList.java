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
    private List<VoteSystemModel> CandidatList;
    public VoteSystemModelList() {
        
        this.CandidatList = new ArrayList<>();
    }
    public List<String> getVoteSystemModelList() {
        return new ArrayList(CandidatList);
    }
    public void addVoteSystemModelList(VoteSystemModel candidat) {

        CandidatList.add(candidat);
    }
}
