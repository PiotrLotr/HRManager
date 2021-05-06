import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditEmployeeFrame extends JFrame {

    public EditEmployeeFrame(Employee selectedValue, ArrayList<Employee> employees, int index) throws HeadlessException {
        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setTitle("Editing employee...");
        this.setSize(screenDim.width / 3, screenDim.height / 3);
        this.setLocation(screenDim.width / 3, screenDim.height / 5);
        this.setVisible(true);

        JPanel jPanel = new JPanel();
        this.add(jPanel);
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.PAGE_AXIS));

            JTextField insertName = new JTextField(selectedValue.getName());
            JTextField insertSurname = new JTextField(selectedValue.getSurname());
            JTextField insertPosition = new JTextField(String.valueOf(selectedValue.getPosition()));
            JTextField insertYearsOfExperience = new JTextField(String.valueOf(selectedValue.getTimeOfService()));
            JTextField insertSalary = new JTextField(String.valueOf(selectedValue.getSalary()));

        jPanel.add(insertName);
        jPanel.add(insertSurname);
        jPanel.add(insertPosition);
        jPanel.add(insertYearsOfExperience);
        jPanel.add(insertSalary);

        JButton acceptChangesButton = new JButton("Accept Changes");

        // EXCEPTION TO ADD!!!!
        acceptChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedValue.setName(insertName.getText());
                selectedValue.setSurname(insertSurname.getText());
                selectedValue.setPosition(Position.valueOf(insertPosition.getText()));
                selectedValue.setTimeOfService(Integer.valueOf(insertYearsOfExperience.getText()));
                selectedValue.setSalary(Integer.valueOf(insertSalary.getText()));
            }
        });

        jPanel.add(acceptChangesButton);

        JButton deleteEmployee = new JButton ("DELETE");
        deleteEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(
                        null,
                        "Do you want to delete that employee?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION){
                    employees.remove(index);
                }
            }
        });
        jPanel.add(deleteEmployee);



    }


}
