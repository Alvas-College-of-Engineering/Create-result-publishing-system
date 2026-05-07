import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ResultGUI extends JFrame {

    JTextField nameField, usnField, sectionField;
    JTextField[] markFields = new JTextField[5];
    JButton calculateBtn, clearBtn;

    DefaultTableModel model;
    JTable table;

    Color primary = new Color(33, 150, 243);
    Color background = new Color(245, 247, 250);

    public ResultGUI() {
        setTitle("Result Publishing System");
        setSize(900, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(background);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(mainPanel);

        // HEADER
        JLabel header = new JLabel("Result Publishing System", JLabel.CENTER);
        header.setOpaque(true);
        header.setBackground(primary);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 20));
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(header, BorderLayout.NORTH);

        // FORM PANEL
        JPanel formCard = new JPanel(new GridBagLayout());
        formCard.setBackground(Color.WHITE);
        formCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        // DETAILS TITLE
        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 2;

        JLabel detailsLabel = new JLabel("Student Details", JLabel.CENTER);
        detailsLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        detailsLabel.setForeground(primary);
        formCard.add(detailsLabel, gbc);

        gbc.gridwidth = 1;

        // NAME
        gbc.gridx = 0; gbc.gridy = row;
        formCard.add(createLabel("Student Name"), gbc);

        gbc.gridx = 1;
        nameField = createTextField();
        formCard.add(nameField, gbc);
        row++;

        // USN
        gbc.gridx = 0; gbc.gridy = row;
        formCard.add(createLabel("USN"), gbc);

        gbc.gridx = 1;
        usnField = createTextField();
        formCard.add(usnField, gbc);
        row++;

        // SECTION
        gbc.gridx = 0; gbc.gridy = row;
        formCard.add(createLabel("Section"), gbc);

        gbc.gridx = 1;
        sectionField = createTextField();
        formCard.add(sectionField, gbc);
        row++;

        // MARKS TITLE
        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 2;

        JLabel marksLabel = new JLabel("Marks", JLabel.CENTER);
        marksLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        marksLabel.setForeground(primary);
        formCard.add(marksLabel, gbc);

        gbc.gridwidth = 1;

        // SUBJECTS
        for (int i = 0; i < 5; i++) {
            gbc.gridx = 0; gbc.gridy = row;
            formCard.add(createLabel("Subject " + (i + 1)), gbc);

            gbc.gridx = 1;
            markFields[i] = createTextField();
            formCard.add(markFields[i], gbc);
            row++;
        }

        // BUTTONS
        calculateBtn = new JButton("Calculate");
        clearBtn = new JButton("Clear");

        calculateBtn.setPreferredSize(new Dimension(120, 35));
        clearBtn.setPreferredSize(new Dimension(120, 35));

        calculateBtn.setBackground(primary);
        calculateBtn.setForeground(Color.WHITE);

        clearBtn.setBackground(new Color(220, 53, 69));
        clearBtn.setForeground(Color.WHITE);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        btnPanel.setBackground(Color.WHITE);

        btnPanel.add(calculateBtn);
        btnPanel.add(clearBtn);

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        formCard.add(btnPanel, gbc);

        // ADD FORM DIRECTLY (NO SCROLL)
        mainPanel.add(formCard, BorderLayout.WEST);

        // TABLE
        String[] columns = {"Name", "USN", "Section", "Total", "Percentage", "Grade", "Status"};
        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JTableHeader th = table.getTableHeader();
        th.setBackground(primary);
        th.setForeground(Color.WHITE);
        th.setFont(new Font("Segoe UI", Font.BOLD, 13));

        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // ACTIONS
        calculateBtn.addActionListener(e -> calculateResult());

        clearBtn.addActionListener(e -> {
            clearFields();
        });

        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setPreferredSize(new Dimension(150, 30));
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        return label;
    }

    private JTextField createTextField() {
        JTextField tf = new JTextField();
        tf.setPreferredSize(new Dimension(200, 30));
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        return tf;
    }

    private void calculateResult() {

        String name = nameField.getText().trim();
        String usn = usnField.getText().trim();
        String section = sectionField.getText().trim();

        if (name.isEmpty() || usn.isEmpty() || section.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!");
            return;
        }

        if (!name.matches("[a-zA-Z ]+")) {
            JOptionPane.showMessageDialog(this, "Name must contain only letters!");
            return;
        }

        int total = 0;
        boolean pass = true;

        try {
            for (int i = 0; i < 5; i++) {
                String text = markFields[i].getText().trim();

                if (text.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Fill all marks!");
                    return;
                }

                int mark = Integer.parseInt(text);

                if (mark < 0 || mark > 100) {
                    JOptionPane.showMessageDialog(this, "Marks must be 0-100!");
                    return;
                }

                total += mark;

                if (mark < 35) pass = false;
            }

            float percentage = total / 5.0f;

            String grade;
            if (percentage >= 90) grade = "A";
            else if (percentage >= 75) grade = "B";
            else if (percentage >= 60) grade = "C";
            else if (percentage >= 50) grade = "D";
            else grade = "F";

            String status = pass ? "PASS" : "FAIL";

            model.addRow(new Object[]{name, usn, section, total, percentage, grade, status});

            clearFields();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Marks must be numbers!");
        }
    }

    private void clearFields() {
        nameField.setText("");
        usnField.setText("");
        sectionField.setText("");

        for (int i = 0; i < 5; i++) {
            markFields[i].setText("");
        }

        nameField.requestFocus();
    }

    public static void main(String[] args) {
        new ResultGUI();
    }
}