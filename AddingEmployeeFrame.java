import javax.swing.*;
import java.awt.*;

class AddEmpPane extends JOptionPane {

    public AddEmpPane(MyTabModel table) throws HeadlessException {

        JPanel addEmpFrame = new JPanel();
        addEmpFrame.setLayout(new BoxLayout(addEmpFrame, BoxLayout.PAGE_AXIS));

        JTextField insertName = new JTextField("Name");
        JTextField insertSurname = new JTextField("Surname");
        JComboBox<Position> insertPosition = new JComboBox(Position.values());
        JTextField insertYearsOfExperience = new JTextField("Years of experience");
        JTextField insertSalary = new JTextField("Salary");

        addEmpFrame.add(insertName);
        addEmpFrame.add(insertSurname);
        addEmpFrame.add(insertPosition);
        addEmpFrame.add(insertYearsOfExperience);
        addEmpFrame.add(insertSalary);

        JOptionPane optionPane = new JOptionPane(addEmpFrame);
        int respond =  JOptionPane.showConfirmDialog(null, addEmpFrame, "Please enter new employee data", JOptionPane.OK_CANCEL_OPTION);

        var chosenPos = insertPosition.getSelectedItem().toString();

        if (respond == JOptionPane.OK_OPTION) {
            addEmp(table, insertName, insertSurname, chosenPos, insertYearsOfExperience, insertSalary);
        }
    }

    public void addEmp(
            MyTabModel table,
            JTextField insertName,
            JTextField insertSurname,
            String insertPosition,
            JTextField insertYearsOfExperience,
            JTextField insertSalary
    ) {
        try {

        var position = Position.valueOf(insertPosition);
        var salary = Double.parseDouble(insertSalary.getText());

            if (salary < position.getLowerEdge() || salary > position.getUpperEdge()) {
                JOptionPane.showMessageDialog(null, "Salary not in the position range...");
            } else {
                table.getEmployees().add(
                        new Employee(
                                insertName.getText(),
                                insertSurname.getText(),
                                position,
                                Double.parseDouble(insertYearsOfExperience.getText()),
                                salary)
                );
                table.fireTableStructureChanged();
                JOptionPane.showMessageDialog(null, "New employer added");

            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Wrong data format. Try again...");
        }
    }

}
