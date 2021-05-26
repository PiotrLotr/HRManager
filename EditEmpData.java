import javax.swing.*;
import java.awt.*;

public class EditEmpData extends JOptionPane {

    public EditEmpData(MyTabModel tabel, int index) throws HeadlessException {
        JPanel editEmpFrame = new JPanel();
        editEmpFrame.setLayout(new BoxLayout(editEmpFrame, BoxLayout.PAGE_AXIS));

        var selectedEmployee = tabel.getEmployees().get(index);

        JTextField insertName = new JTextField(selectedEmployee.getName());
        JTextField insertSurname = new JTextField(selectedEmployee.getSurname());
        JComboBox<Position> insertPosition = new JComboBox(Position.values());
        JTextField insertYearsOfExperience = new JTextField(String.valueOf(selectedEmployee.getTimeOfService()));
        JTextField insertSalary = new JTextField(String.valueOf(selectedEmployee.getSalary()));

        editEmpFrame.add(insertName);
        editEmpFrame.add(insertSurname);
        editEmpFrame.add(insertPosition);
        editEmpFrame.add(insertYearsOfExperience);
        editEmpFrame.add(insertSalary);

        JOptionPane optionPane = new JOptionPane(editEmpFrame);
        int respond = JOptionPane.showConfirmDialog(null, editEmpFrame, "Please enter new employee data", JOptionPane.OK_CANCEL_OPTION);

        var choosenPos = insertPosition.getSelectedItem().toString();

        if (respond == optionPane.OK_OPTION) {
            editEmp(selectedEmployee, insertName, insertSurname, choosenPos, insertYearsOfExperience, insertSalary);
        }
    }

    public void editEmp(
            Employee selectedEmployee,
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
                selectedEmployee.setName(insertName.getText());
                selectedEmployee.setSurname(insertSurname.getText());
                selectedEmployee.setPosition(position);
                selectedEmployee.setTimeOfService(Double.parseDouble(insertYearsOfExperience.getText()));
                selectedEmployee.setSalary(salary);

                JOptionPane.showMessageDialog(null, "Data edited successfully");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Wrong data format. Try again...");
        }
    }
}




