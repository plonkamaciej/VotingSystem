/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.lab1.votesystem.ModelList;

import pl.polsl.lab1.votesystem.Model.VoteSystemModel;
import pl.polsl.lab1.votesystem.fileMenager.FileMenager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SuperStudent-PL
 */
public class VoteSystemModelList {
    private List<VoteSystemModel> CandidateList;
    static private final File userFile = new File("C:\\Users\\Maciek\\IdeaProjects\\VotingSystem\\VoteSystem\\src\\main\\java\\pl\\polsl\\lab1\\votesystem\\fileMenager\\Users.txt");
    static private final File candidateFile = new File("C:\\Users\\Maciek\\IdeaProjects\\VotingSystem\\VoteSystem\\src\\main\\java\\pl\\polsl\\lab1\\votesystem\\fileMenager\\Candidate.txt");


    public VoteSystemModelList() {

        this.CandidateList = new ArrayList<>();
    }
    public File getUserFile() {
        return userFile;
    }
    public File getCandidateFile() {
        return candidateFile;
    }

    public void addCandidate(String name){
        VoteSystemModel candidate = new VoteSystemModel(name, 0);
        CandidateList.add(candidate);
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
    public void printToFile() throws IOException {
        StringBuilder content = new StringBuilder();
        for (VoteSystemModel voteSystemModel : CandidateList)
            content.append(voteSystemModel.getName()).append(" ").append(voteSystemModel.getVoteCount()).append("\n");
        FileMenager.ToFile(content.toString(), candidateFile);
    }
}
