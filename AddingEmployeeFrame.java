import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddingEmployeeFrame extends JFrame {

    public AddingEmployeeFrame(MyTabModel table) throws HeadlessException {
        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setTitle("Adding new employee...");
        this.setSize(screenDim.width / 3, screenDim.height / 3);
        this.setLocation(screenDim.width / 3, screenDim.height / 5);
        this.setVisible(true);

        JPanel jPanel = new JPanel();
        this.add(jPanel);
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.PAGE_AXIS));

        JTextField insertName = new JTextField("Name");
        JTextField insertSurname = new JTextField("Surname");
        JTextField insertPosition = new JTextField("Position");
        JTextField insertYearsOfExperience = new JTextField("Years of experience");
        JTextField insertSalary = new JTextField("Salary");
        jPanel.add(insertName);
        jPanel.add(insertSurname);

        jPanel.add(insertPosition);
        jPanel.add(insertYearsOfExperience);
        jPanel.add(insertSalary);

//        ComboBox<Position> comboBox = new ComboBox<>();
//        comboBox.setItems

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    table.getEmployees().add(new Employee(
                            insertName.getText(),
                            insertSurname.getText(),
                            (Position.valueOf(insertPosition.getText())),
                            Integer.parseInt(insertYearsOfExperience.getText()),
                            Integer.parseInt(insertSalary.getText())));
                    table.fireTableStructureChanged();
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
