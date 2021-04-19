import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
// main frame spec
        JFrame mainFrame = new JFrame();
        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setTitle("HRManager");
        mainFrame.setSize(screenDim.width / 2, screenDim.height / 2);
        mainFrame.setLocation(screenDim.width / 2, screenDim.height / 4);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

//     table panel
        JPanel tablePanel = new JPanel();
        mainFrame.setContentPane(tablePanel);

// table implementation
        // table input
        ArrayList<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee("Zbigniew", "Stonoga", Position.APPRENTICE, 2, 2000);
        Employee employee2 = new Employee("Bogdan", "Boner", Position.MANAGER, 5, 10000);
        employees.add(employee1);
        employees.add(employee2);

//        table spec
        MyTabModel myTabModel = new MyTabModel(employees);
        JTable table = new JTable(myTabModel);
        tablePanel.add(table);

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        tablePanel.add(scrollPane);


// menu panel buttons
        Button addNewEmployeeButton = new Button("Add New Employee");
        tablePanel.add(addNewEmployeeButton);
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == addNewEmployeeButton){
                    employees.add(new Employee("Piotr", "Wrona", Position.CHIEF, 5, 2000));
                    JOptionPane.showMessageDialog(null, "Operation was sucesfully");
                }
            }
        };
        addNewEmployeeButton.addActionListener(actionListener);


//         addNewEmployeeButton.addActionListener(this);

        tablePanel.add(new Button("Edit Employee data"));
        tablePanel.add(new Button("Delete Employee"));


    }
}
