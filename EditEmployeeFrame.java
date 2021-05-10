import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditEmployeeFrame extends JFrame {

    public EditEmployeeFrame(MyTabModel tabel, int index) throws HeadlessException {
        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setTitle("Editing employee...");
        this.setSize(screenDim.width / 3, screenDim.height / 3);
        this.setLocation(screenDim.width / 3, screenDim.height / 5);
        this.setVisible(true);

        JPanel jPanel = new JPanel();
        this.add(jPanel);
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.PAGE_AXIS));

            var selectedEmployee= tabel.getEmployees().get(index);

            JTextField insertName = new JTextField(selectedEmployee.getName());
            JTextField insertSurname = new JTextField(selectedEmployee.getSurname());
            JTextField insertPosition = new JTextField(String.valueOf(selectedEmployee.getPosition()));
            JTextField insertYearsOfExperience = new JTextField(String.valueOf(selectedEmployee.getTimeOfService()));
            JTextField insertSalary = new JTextField(String.valueOf(selectedEmployee.getSalary()));

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
                selectedEmployee.setName(insertName.getText());
                selectedEmployee.setSurname(insertSurname.getText());
                selectedEmployee.setPosition(Position.valueOf(insertPosition.getText()));
                selectedEmployee.setTimeOfService(Integer.valueOf(insertYearsOfExperience.getText()));
                selectedEmployee.setSalary(Integer.valueOf(insertSalary.getText()));

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
                    tabel.getEmployees().remove(index);
                }
            }
        });
        jPanel.add(deleteEmployee);



    }


}
