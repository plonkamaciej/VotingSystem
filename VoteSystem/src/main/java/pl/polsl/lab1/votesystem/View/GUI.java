package pl.polsl.lab1.votesystem.View;

import pl.polsl.lab1.votesystem.Model.VoteSystemModel;
import pl.polsl.lab1.votesystem.ModelList.VoteSystemModelList;
import pl.polsl.lab1.votesystem.fileMenager.FileManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * Graphical User Interface (GUI) class for a voting system application.
 */
public class GUI {

    private final File userFile = FileManager.getUserFile();
    private final File candidateFile = FileManager.getCandidateFile();
    private final VoteSystemModelList model;
    private final List<List<String>> users;

    /**
     * Constructs a GUI instance, initializes the model and users, and displays the GUI.
     */
    public GUI() {
        model = retrieveFromDatabase(candidateFile);
        users = FileManager.Reader(userFile);
        show();
    }

    /**
     * Retrieves candidate data from the specified file and creates a VoteSystemModelList.
     *
     * @param candidateFile The file containing candidate data.
     * @return A VoteSystemModelList containing the retrieved candidates.
     */
    private static VoteSystemModelList retrieveFromDatabase(File candidateFile) {
        VoteSystemModelList candidateList = new VoteSystemModelList();
        List<List<String>> candidates = FileManager.Reader(candidateFile);
        for (List<String> s : candidates) {
            candidateList.addVoteSystemModelList(new VoteSystemModel(s.get(0), Integer.parseInt(s.get(1))));
        }
        return candidateList;
    }

    /**
     * Checks if a user has already voted.
     *
     * @param username The username to check.
     * @return True if the user has already voted; false otherwise.
     */
    private boolean isUserAlreadyVoted(String username) {
        for (List<String> user : users) {
            if (user.get(0).equals(username)) {
                return true; // User already voted
            }
        }
        return false; // User not found in the list
    }

    /**
     * Deletes the selected candidate from the table and the file.
     *
     * @param selectedRow The index of the selected row in the table.
     */
    private void deleteCandidate(int selectedRow,DefaultTableModel tableModel) {
        if (selectedRow != -1) {

            tableModel.removeRow(selectedRow);

            // Remove the candidate from the model and update the file
            model.delateCandidate(selectedRow);
            try {
                model.printToFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    /**
     * Displays the main GUI window with components for adding candidates and casting votes.
     */
    private void show() {
        JFrame frame = new JFrame("Swing App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);

        JPanel topPanel = new JPanel(new FlowLayout());
        JTextField textField = new JTextField(20);
        JTextField usernameField = new JTextField(20); // Added username text field
        JButton addButton = new JButton("Add");
        JButton voteButton = new JButton("Vote");

        topPanel.add(new JLabel("[ADD] Candidate:"));
        topPanel.add(textField);
        topPanel.add(new JLabel("[VOTE] Username:"));
        topPanel.add(usernameField);
        topPanel.add(addButton);
        topPanel.add(voteButton);

        frame.getContentPane().add(topPanel, BorderLayout.NORTH);

        String[] columnNames = {"Candidate", "Votes"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column) {

                return false;
            }
        };

        JTable table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);


        tableModel = (DefaultTableModel) table.getModel();
        List<List<String>> candidates = FileManager.Reader(candidateFile);
        for (List<String> s : candidates) {
            Object[] rowData = {s.get(0), Integer.parseInt(s.get(1))};
            tableModel.addRow(rowData);
        }

        DefaultTableModel finalTableModel2 = tableModel;
        voteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String username = usernameField.getText();
                if (!username.isEmpty()) {
                    // Check if the username already exists in the user list
                    if (isUserAlreadyVoted(username)) {
                        JOptionPane.showMessageDialog(frame, "User '" + username + "' has already voted.");
                    } else {

                        int currentVotes = Integer.parseInt(finalTableModel2.getValueAt(selectedRow, 1).toString());
                        finalTableModel2.setValueAt(currentVotes + 1, selectedRow, 1);



                        model.vote(selectedRow);
                        try {
                            model.printToFile();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        try {
                            FileManager.addToFile(username, userFile);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        List<String> list = List.of(username);
                        users.add(list);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter a username.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a candidate to vote.");
            }
        });

        DefaultTableModel finalTableModel1 = tableModel;
        addButton.addActionListener(e -> {
            String text = textField.getText();
            if (!text.isEmpty()) {
                Object[] rowData = {text, "0"};
                finalTableModel1.addRow(rowData);
                textField.setText("");
                try {
                    model.addCandidate(text);
                    model.printToFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JButton deleteButton = new JButton("Delete"); // Moved Delete button to the bottom
        DefaultTableModel finalTableModel = tableModel;
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            deleteCandidate(selectedRow, finalTableModel);
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(deleteButton);
        frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        // Set keyboard shortcuts
        addButton.setMnemonic(KeyEvent.VK_A); // Alt + A for Add
        voteButton.setMnemonic(KeyEvent.VK_V); // Alt + V for Vote
        deleteButton.setMnemonic(KeyEvent.VK_D); // Alt + D for Delete

        // Set tooltips
        addButton.setToolTipText("Add a new candidate (Alt + A)");
        voteButton.setToolTipText("Vote for a candidate (Alt + V)");
        deleteButton.setToolTipText("Delete the selected candidate (Alt + D)");

        frame.setVisible(true);
    }
}