import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });

    }

    private static void createAndShowGUI() {

//  main frame spec
        JFrame mainFrame = new JFrame();
        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setTitle("HRManager");
        mainFrame.setSize(screenDim.width / 2, screenDim.height / 2);
        mainFrame.setLocation(screenDim.width / 2, screenDim.height / 4);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);


//  table panel
        JPanel tablePanel = new JPanel();
        mainFrame.setContentPane(tablePanel);

        // table input
        ArrayList<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee("Zbigniew", "Stonoga", Position.APPRENTICE, 2, 2000);
        Employee employee2 = new Employee("Bogdan", "Boner", Position.MANAGER, 5, 10000);
        employees.add(employee1);
        employees.add(employee2);

        MyTabModel myTabModel = new MyTabModel(employees);
        JTable table = new JTable(myTabModel);

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        tablePanel.add(scrollPane);



//  menu panel buttons
        Button addNewEmployeeButton = new Button("Add New Employee");
        tablePanel.add(addNewEmployeeButton);


        ActionListener actionListener1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addNewEmployeeButton) {
                    new AddingEmployeeFrame(employees);
                    myTabModel.fireTableDataChanged();
                }
            }
        };
        addNewEmployeeButton.addActionListener(actionListener1);

//  editing employee
        Button editEmployeeData = new Button("Edit Employee Data");

        editEmployeeData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = table.getSelectedRow();
                new EditEmployeeFrame(employees.get(index), employees, index);
                myTabModel.fireTableDataChanged();
            }
        });

        mainFrame.add(editEmployeeData);

//  deleting employee
        Button deleteEmployeeButton = new Button ("Delete Employee");
        tablePanel.add(deleteEmployeeButton);
        ActionListener actionListener2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };





    }

}
