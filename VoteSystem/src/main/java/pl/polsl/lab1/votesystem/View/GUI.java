package pl.polsl.lab1.votesystem.View;

import pl.polsl.lab1.votesystem.Conteroller.VoteSystemController;
import pl.polsl.lab1.votesystem.Model.VoteSystemModel;
import pl.polsl.lab1.votesystem.ModelList.VoteSystemModelList;
import pl.polsl.lab1.votesystem.fileMenager.FileManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import static pl.polsl.lab1.votesystem.fileMenager.FileManager.Reader;


public class GUI {

    private File userFile = FileManager.getUserFile();
    private File candidateFile = FileManager.getCandidateFile();
    private int num = 0;
    private String user = "";
    private  VoteSystemModelList model = retrieveFromDatabase(candidateFile);
    private VoteSystemView view = new VoteSystemView();
   private  VoteSystemController controller = new VoteSystemController(model, view);
   private  List<List<String>> users = FileManager.Reader(userFile);

   public GUI(){
        show();
   }

   private void updateList(){

   }

    private static VoteSystemModelList retrieveFromDatabase(File candidateFile) {

        VoteSystemModelList candidateList = new VoteSystemModelList();
        List<List<String>> Candidates = Reader(candidateFile);
        for (List<String>s:Candidates){
            candidateList.addVoteSystemModelList(new VoteSystemModel(s.get(0), Integer.parseInt(s.get(1))));
        }
        return candidateList;
    }



    private void show(){

        JFrame frame = new JFrame("Swing App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.NORTH);

        JTextField textField = new JTextField(20);
        panel.add(textField);

        JButton addButton = new JButton("Add");
        panel.add(addButton);

        String[] columnNames = {"Candidate", "Votes"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        //tableModel.addRow();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                if (!text.isEmpty()) {
                    Object[] rowData = {text, "0"};
                    tableModel.addRow(rowData);
                    textField.setText("");
                    //VoteSystemModel tmp = new VoteSystemModel();
                }
            }
        });

        frame.setVisible(true);
    }
}





