import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {

//  main frame spec
        JFrame mainFrame = new JFrame();
        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setTitle("HRManager");
        mainFrame.setSize(640, 700);
        mainFrame.setLocation(screenDim.width / 2, screenDim.height / 4);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);


//  table panel
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridBagLayout());

        MyTabModel myTabModel = new MyTabModel();

        JTable table = new JTable(myTabModel);
        GridBagConstraints gbc = new GridBagConstraints();

        mainFrame.add(tablePanel);

// table input
        myTabModel.getEmployees().add(new Employee("Igor", "Sikora", Position.APPRENTICE, 0.5, 2500));
        myTabModel.getEmployees().add(new Employee("Bogdan", "Boner", Position.CHIEF, 15.5, 12000));
        myTabModel.getEmployees().add(new Employee("Jakub", "Brzezinski", Position.DESIGNER, 2, 4200));
        myTabModel.getEmployees().add(new Employee("Kornelia", "Kozlowska", Position.SALESMAN, 5, 7800));
        myTabModel.getEmployees().add(new Employee("Faustyna", "Michalak", Position.APPRENTICE, 1.2, 3000));
        myTabModel.getEmployees().add(new Employee("Julian", "Malinowski", Position.MANAGER, 8.8, 8450));
        myTabModel.getEmployees().add(new Employee("Kevin", "Kubiak", Position.SALESMAN, 4.7, 6640));
        myTabModel.getEmployees().add(new Employee("Joanna", "Mazurek", Position.DESIGNER, 5, 5500));
        myTabModel.getEmployees().add(new Employee("Judyta", "Kowalczyk", Position.MANAGER, 10, 9760));
        myTabModel.getEmployees().add(new Employee("Alfred", "Jakubowski", Position.APPRENTICE, 1.5, 3340));
        myTabModel.getEmployees().add(new Employee("Gniewomir", "Jakubowski", Position.DESIGNER, 4.1, 5900));
        myTabModel.getEmployees().add(new Employee("Martin", "Szymczak", Position.DESIGNER, 3.6, 5490));
        myTabModel.getEmployees().add(new Employee("Marlena", "Gorecka", Position.SALESMAN, 7.6, 6824));
        myTabModel.getEmployees().add(new Employee("Amalia", "Jasinska", Position.MANAGER, 6.8, 8520 ));
        myTabModel.getEmployees().add(new Employee("Rafal", "Cieslak", Position.DESIGNER, 5.1, 5715));
        myTabModel.getEmployees().add(new Employee("Bianka", "Krajewska", Position.APPRENTICE, 4, 3950));
        myTabModel.getEmployees().add(new Employee("Eryk", "Ziolkowski", Position.SALESMAN, 7.1, 7430));
        myTabModel.getEmployees().add(new Employee("Jakub", "Zalewski", Position.MANAGER, 5, 7140));

// sorting
        table.setAutoCreateRowSorter(true);

//        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

//  adding employee
        JButton addNewEmployeeButton = new JButton("Add New Employee");
        addNewEmployeeButton.addActionListener(e -> new AddEmpPane(myTabModel));

//  editing employee
        JButton editEmployeeData = new JButton("Edit Employee Data");
        editEmployeeData.addActionListener(e -> {
            int index = table.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(null, "No employee selected...");
            } else {
                index = table.convertRowIndexToModel(index);
                new EditEmpData(myTabModel, index);
                myTabModel.fireTableDataChanged();
            }
        });

//  deleting employee
        JButton deleteEmployeeButton = new JButton("Delete Employee");
        deleteEmployeeButton.addActionListener(e -> {
            myTabModel.getEmployees().remove(table.getSelectedRow());
            myTabModel.fireTableDataChanged();
        });

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        tablePanel.add(addNewEmployeeButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        tablePanel.add(editEmployeeData, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        tablePanel.add(deleteEmployeeButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;

        JScrollPane jsp = new JScrollPane(table);
        tablePanel.add(jsp, gbc);

//  exporting/ importing
        JMenuBar menuBar;
        JMenu menu;

        menuBar = new JMenuBar();
        mainFrame.setJMenuBar(menuBar);

        menu = new JMenu("File");
        menuBar.add(menu);

        JMenuItem menuItem1 = new JMenuItem("Save to file");
        menu.add(menuItem1);

        JMenuItem menuItem2 = new JMenuItem("Import");
        menu.add(menuItem2);

        // exporting data
        menuItem1.addActionListener(e -> {
                    JFileChooser fc = new JFileChooser();

                    JFrame parentFrame = new JFrame();
                    fc.setDialogTitle("Specify file to save");
                    int userSelection = fc.showSaveDialog(parentFrame);

                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                        myTabModel.serializeData(fc);
                    }
                }
        );

        // importing data
        menuItem2.addActionListener(e -> {
            File selectedFile;
            int response;

            JFileChooser fc = new JFileChooser(".");
            fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            response = fc.showOpenDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                selectedFile = fc.getSelectedFile();
                myTabModel.deserializeData(selectedFile, fc);
            }
        });

        // searching based on category
        JTextField searchInput = new JTextField("Insert searched phase");
        JButton searchButton = new JButton("SEARCH");
        JComboBox categoryToChose = new JComboBox(myTabModel.getColumnNames());

        gbc.insets = new Insets(1, 1, 1, 1);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;

        tablePanel.add(searchInput, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 2;
        tablePanel.add(categoryToChose, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 2;
        gbc.gridy = 2;
        tablePanel.add(searchButton, gbc);


        searchButton.addActionListener(e -> {
            ArrayList<Employee> searchResultEmployees = new ArrayList<>();
            var searchedValue = searchInput.getText();
            var chosenCategory = (String) categoryToChose.getSelectedItem();

            myTabModel.searchByCategory(searchResultEmployees, chosenCategory, searchedValue);
            myTabModel.replaceTabDataForSearchResult(searchResultEmployees);
        });

        JButton refreshButton = new JButton("REFRESH");
        refreshButton.addActionListener(e -> myTabModel.refreshData());

// find salary higher/lower than the value
        JTextField insertEdgeSalVal = new JTextField("Insert salary value");

        JButton showHigherSalThanVal = new JButton("Higher records");
        JButton showLowerSalThanVal = new JButton("Lower records");

        // showing records with higher value
        showHigherSalThanVal.addActionListener(e -> {
            var userInputVal = Double.valueOf(insertEdgeSalVal.getText());
            ArrayList<Employee> searchResultEmployees = new ArrayList<>();

            for (Employee emp : myTabModel.getEmployees()) {
                if (emp.getSalary() > userInputVal)
                    searchResultEmployees.add(emp);
            }
            myTabModel.replaceTabDataForSearchResult(searchResultEmployees);
        });

        // showing records with lower value
        showLowerSalThanVal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var userInputVal = Double.valueOf(insertEdgeSalVal.getText());
                ArrayList<Employee> searchResultEmployees = new ArrayList<>();

                for (Employee emp : myTabModel.getEmployees()) {
                    if (emp.getSalary() < userInputVal)
                        searchResultEmployees.add(emp);
                }
                myTabModel.replaceTabDataForSearchResult(searchResultEmployees);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 3;
        tablePanel.add(insertEdgeSalVal, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        tablePanel.add(showLowerSalThanVal, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        tablePanel.add(showHigherSalThanVal, gbc);

        gbc.insets = new Insets(20, 5, 5, 5);
        gbc.gridwidth = 1;
        gbc.gridx = 2;
        gbc.gridy = 4;
        tablePanel.add(refreshButton, gbc);


    }
}

