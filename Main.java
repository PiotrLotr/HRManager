import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javax.swing.BoxLayout.Y_AXIS;

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
        MyTabModel myTabModel = new MyTabModel();

        Employee employee1 = new Employee("Zbigniew", "Stonoga", Position.APPRENTICE, 2, 2000);
        Employee employee2 = new Employee("Bogdan", "Boner", Position.MANAGER, 5, 10000);
        myTabModel.getEmployees().add(employee1);
        myTabModel.getEmployees().add(employee2);

        JTable table = new JTable(myTabModel);

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        tablePanel.add(scrollPane);

//  menu panel buttons
        JButton addNewEmployeeButton = new JButton("Add New Employee");
        ActionListener actionListener1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddingEmployeeFrame(myTabModel);
            }
        };
        addNewEmployeeButton.addActionListener(actionListener1);

//  editing employee
        JButton editEmployeeData = new JButton("Edit Employee Data");
        editEmployeeData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = table.getSelectedRow();
                index = table.convertRowIndexToModel(index);
                new EditEmployeeFrame(myTabModel, index);
            }
        });

//  deleting employee
        JButton deleteEmployeeButton = new JButton("Delete Employee");
        ActionListener actionListener2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myTabModel.getEmployees().remove(table.getSelectedRow());
                myTabModel.fireTableDataChanged();
            }
        };
        deleteEmployeeButton.addActionListener(actionListener2);

// sorting
        table.setAutoCreateRowSorter(true);

//  exporting/ importing

        JButton exportButton = new JButton("EXPORT");

        JMenuBar menuBar;
        JMenu menu;

        menuBar = new JMenuBar();
        mainFrame.setJMenuBar(menuBar);

        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_N);
        menu.getAccessibleContext().setAccessibleDescription(
                "This menu does nothing");
        menuBar.add(menu);

        JMenuItem menuItem1 = new JMenuItem("Export data");
        menu.add(menuItem1);

        JMenuItem menuItem2 = new JMenuItem("Import data");
        menu.add(menuItem2);

        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file;
                int response;
                JFileChooser jFileChooser = new JFileChooser(".");

                jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                response = jFileChooser.showOpenDialog(null);

                if(response == JFileChooser.APPROVE_OPTION) {
                    file = jFileChooser.getSelectedFile();
                    if (file.isFile()) {
                        try {
                            FileWriter fw = new FileWriter(file);
                            for (Employee emp : myTabModel.getEmployees()) {
                                fw.write(emp.toString() + "\n");
                            }
                            fw.close();
                        } catch (IOException exc) {
                            System.out.println("An error occurred.");
                            exc.printStackTrace();
                        }
                    }
                }
            }
        });

        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file;
                Scanner scanner;
                int response;
                JFileChooser jFileChooser = new JFileChooser(".");

                jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                response = jFileChooser.showOpenDialog(null);

//                if(response == JFileChooser.APPROVE_OPTION) {
//                    file = jFileChooser.getSelectedFile();
//                    if (file.isFile()) {
//                        try {
//                            scanner = new Scanner(file);
//                            while(scanner.hasNextLine()){
//                                Pattern pattern = Pattern.compile("");
//
//                                }
//                            }
//
//                        } catch (FileNotFoundException fileNotFoundException) {
//                            fileNotFoundException.printStackTrace();
//                        }
//
//                    }
//                }
//            }
//        });



//  layout
        Container container = mainFrame.getContentPane();
        container.setLayout(new BoxLayout(container ,Y_AXIS));

        container.add(addNewEmployeeButton);
        container.add(editEmployeeData);
        container.add(deleteEmployeeButton);


    }

//                try {
//        FileWriter fw = new FileWriter(fileLocation);
//        for (Employee emp : myTabModel.getEmployees()) {
//            fw.write(emp.toString() + "\n");
//        }
//        fw.close();
//    } catch (IOException exc) {
//        System.out.println("An error occurred.");
//        exc.printStackTrace();
//    }

}


