import javax.swing.*;
import java.awt.*;
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
        mainFrame.setSize(screenDim.width / 2, screenDim.height / 2);
        mainFrame.setLocation(screenDim.width / 2, screenDim.height / 4);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

// menu
        JPanel panel = new JPanel();
        mainFrame.setContentPane(panel);

        // menu buttons


// table implementation
        // list of employees
        ArrayList<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee("Zbigniew", "Stonoga", Position.APPRENTICE, 2, 2000);
        employees.add(employee1);

        MyTabModel myTabModel = new MyTabModel(employees);
        JTable table = new JTable(myTabModel);
        panel.add(table);

        //inserting values to table


        //scroll pane implementation
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        panel.add(scrollPane);

        JButton addWorkerButton = new JButton("Add New Worker");
        panel.add(addWorkerButton);

    }
}
