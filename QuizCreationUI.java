package ui;

import model.Question;
import model.Quiz;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class QuizCreationUI extends JFrame {
    private JTextField titleField, descField;
    private ArrayList<Question> questionList = new ArrayList<>();

    public QuizCreationUI() {
        setTitle("Create Quiz");
        setSize(400, 250);
        setLayout(new GridLayout(5, 2, 5, 5));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        add(new JLabel("Quiz Title:"));
        titleField = new JTextField();
        add(titleField);

        add(new JLabel("Description:"));
        descField = new JTextField();
        add(descField);

        JButton addQuestionBtn = new JButton("Add Question");
        JButton saveBtn = new JButton("Save Quiz");
        add(addQuestionBtn);
        add(saveBtn);

        addQuestionBtn.addActionListener(e -> addQuestion());
        saveBtn.addActionListener(e -> saveQuiz());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addQuestion() {
        JTextField questionField = new JTextField();
        JTextField opt1 = new JTextField();
        JTextField opt2 = new JTextField();
        JTextField opt3 = new JTextField();
        JTextField opt4 = new JTextField();
        JTextField correct = new JTextField();
        JTextField marks = new JTextField();

        Object[] msg = {
                "Question:", questionField,
                "Option 1:", opt1,
                "Option 2:", opt2,
                "Option 3:", opt3,
                "Option 4:", opt4,
                "Correct Option (1-4):", correct,
                "Marks:", marks
        };

        int res = JOptionPane.showConfirmDialog(this, msg, "Add Question", JOptionPane.OK_CANCEL_OPTION);
        if (res == JOptionPane.OK_OPTION) {
            String[] options = {opt1.getText(), opt2.getText(), opt3.getText(), opt4.getText()};
            int correctAns = Integer.parseInt(correct.getText()) - 1;
            int markVal = Integer.parseInt(marks.getText());
            questionList.add(new Question(questionField.getText(), options, correctAns, markVal));
            JOptionPane.showMessageDialog(this, "Question Added!");
        }
    }

    private void saveQuiz() {
        if (titleField.getText().isEmpty() || questionList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Add title and at least one question!");
            return;
        }
        Quiz quiz = new Quiz(titleField.getText(), descField.getText());
        for (Question q : questionList) quiz.addQuestion(q);
        FileHandler.saveQuiz(quiz);
        JOptionPane.showMessageDialog(this, "Quiz saved successfully!");
        dispose();
    }
}
