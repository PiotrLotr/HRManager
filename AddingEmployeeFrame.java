import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddingEmployeeFrame extends JFrame {

    public AddingEmployeeFrame(ArrayList <Employee> employees) throws HeadlessException {
        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setTitle("Adding new employee...");
        this.setSize(screenDim.width / 3, screenDim.height / 3);
        this.setLocation(screenDim.width / 3, screenDim.height / 5);
        this.setVisible(true);

        JPanel jPanel = new JPanel();
        this.add(jPanel);
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.PAGE_AXIS));

        JTextField insertName = new JTextField("Please insert name");
        JTextField insertSurname = new JTextField("Please insert surname");
        JTextField insertPosition = new JTextField("Please insert position");
        JTextField insertYearsOfExperience = new JTextField("Please insert years of experience");
        JTextField insertSalary = new JTextField("Please insert salary");

        jPanel.add(insertName);
        jPanel.add(insertSurname);
        jPanel.add(insertPosition);
        jPanel.add(insertYearsOfExperience);
        jPanel.add(insertSalary);

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    employees.add(new Employee(
                            insertName.getText(),
                            insertSurname.getText(),
                            (Position.valueOf(insertPosition.getText())),
                            Integer.parseInt(insertYearsOfExperience.getText()),
                            Integer.parseInt(insertSalary.getText())));
                } catch (WrongDataTypeException err) {
                    System.out.println("Wrong data type. Please try again...");
                }
                JOptionPane.showMessageDialog(null, "New employer added");
            }
        };

        JButton acceptButton = new JButton("Accept");
        acceptButton.addActionListener(actionListener);

        jPanel.add(acceptButton);

    }


}
