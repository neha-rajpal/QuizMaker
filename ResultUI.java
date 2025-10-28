package ui;

import model.Result;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ResultUI extends JFrame {
    public ResultUI() {
        ArrayList<Result> results = FileHandler.loadAllResults();
        if (results.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No results found!");
            dispose();
            return;
        }

        setTitle("Quiz Results");
        setSize(500, 300);
        setLayout(new BorderLayout());

        String[] cols = {"Name", "Quiz", "Score", "Correct", "Incorrect", "Percentage"};
        String[][] data = new String[results.size()][cols.length];

        for (int i = 0; i < results.size(); i++) {
            Result r = results.get(i);
            data[i][0] = r.getUserName();
            data[i][1] = r.getQuizTitle();
            data[i][2] = String.valueOf(r.getTotalScore());
            data[i][3] = String.valueOf(r.getCorrect());
            data[i][4] = String.valueOf(r.getIncorrect());
            data[i][5] = String.format("%.2f%%", r.getPercentage());
        }

        JTable table = new JTable(data, cols);
        add(new JScrollPane(table), BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
