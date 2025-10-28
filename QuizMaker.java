package main;

import ui.QuizCreationUI;
import ui.QuizAttemptUI;
import ui.ResultUI;
import javax.swing.*;
import java.awt.*;

public class QuizMaker {
    public void launch() {
        JFrame frame = new JFrame("Quiz Maker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(4, 1, 10, 10));

        JLabel title = new JLabel("QUIZ MAKER", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));

        JButton createBtn = new JButton("Create Quiz");
        JButton attemptBtn = new JButton("Attempt Quiz");
        JButton resultBtn = new JButton("View Results");

        createBtn.addActionListener(e -> new QuizCreationUI());
        attemptBtn.addActionListener(e -> new QuizAttemptUI());
        resultBtn.addActionListener(e -> new ResultUI());

        frame.add(title);
        frame.add(createBtn);
        frame.add(attemptBtn);
        frame.add(resultBtn);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
