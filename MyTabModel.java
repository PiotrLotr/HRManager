import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MyTabModel extends AbstractTableModel {

    private String[] columnNames = {"FIRST NAME",
            "LAST NAME",
            "POSITION",
            "TIME OF SERVICE [YEARS]",
            "SALARY[PLN]"};

    private ArrayList<Employee> employees;
    private ArrayList<Employee> tempEmployeesHolder;

    public MyTabModel() {
        employees = new ArrayList<Employee>();
        tempEmployeesHolder = new ArrayList<Employee>();
    }

    @Override
    public int getRowCount() {
        return employees.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return Enum.class;
            case 3:
                return int.class;
            default:
                return double.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return employees.get(rowIndex).getName();
            case 1:
                return employees.get(rowIndex).getSurname();
            case 2:
                return employees.get(rowIndex).getPosition();
            case 3:
                return employees.get(rowIndex).getTimeOfService();
            case 4:
                return employees.get(rowIndex).getSalary();
        }
        return -1;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                employees.get(rowIndex).setName((String) value);
                break;
            case 1:
                employees.get(rowIndex).setSurname((String) value);
                break;
            case 2:
                employees.get(rowIndex).setPosition((Position) value);
                break;
            case 3:
                employees.get(rowIndex).setTimeOfService((int) value);
                break;
            case 4:
                employees.get(rowIndex).setSalary((double) value);
                break;
        }
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public ArrayList<Employee> getTempEmployeesHolder() {
        return tempEmployeesHolder;
    }

    public String[] getColumnNames() {
        return columnNames;
    }


    public void searchByCategory(ArrayList<Employee> searchResultEmployees, String chosenCategory, String searchedValue ){
        switch (chosenCategory) {
            case "FIRST NAME":
                for (Employee emp : this.getEmployees()) {
                    if (emp.getName().equals(searchedValue))
                        searchResultEmployees.add(emp);
                }
                break;
            case "LAST NAME":
                for (Employee emp : this.getEmployees()) {
                    if (emp.getSurname().equals(searchedValue))
                        searchResultEmployees.add(emp);
                }
                break;
            case "POSITION":
                for (Employee emp : this.getEmployees()) {
                    var stringOfEmpPosition = emp.getPosition().toString();
                    if (stringOfEmpPosition.equals(searchedValue))
                        searchResultEmployees.add(emp);
                }
                break;
            case "TIME OF SERVICE [YEARS]":
                for (Employee emp : this.getEmployees()) {
                    var searchValToInt = Integer.valueOf(searchedValue);
                    if (emp.getTimeOfService() == searchValToInt)
                        searchResultEmployees.add(emp);
                }
                break;
            case "SALARY[PLN]":
                for (Employee emp : this.getEmployees()) {
                    var searchValToDouble = Double.valueOf(searchedValue);
                    if (emp.getSalary() == searchValToDouble)
                        searchResultEmployees.add(emp);
                }
                break;
        }
    }
    public void serializeData(JFileChooser fc) {
        try {
            File fileToCreate = new File(fc.getCurrentDirectory() + "/" + fc.getSelectedFile().getName() + ".json");
            fileToCreate.createNewFile();
            FileWriter fw = new FileWriter(fileToCreate);
            String json = new Gson().toJson(this.getEmployees());
            fw.write(json);
            fw.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void deserializeData(File selectedFile, JFileChooser fc) {
        String completeContent = null;
        try {
            completeContent = new String(Files.readAllBytes(Paths.get(selectedFile.getPath())));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        this.getEmployees().clear();
        Type foundListType = new TypeToken<List<Employee>>() {}.getType();

        List<Employee> importedEmployees = new Gson().fromJson(completeContent, foundListType);
        this.getEmployees().addAll(importedEmployees);
        this.fireTableDataChanged();
    }

    public void replaceTabDataForSearchResult(ArrayList<Employee> searchResultEmployees) {
            this.getTempEmployeesHolder().addAll(this.getEmployees());
            this.getEmployees().clear();
            this.getEmployees().addAll(searchResultEmployees);
            this.fireTableDataChanged();
    }

    public void refreshData(){
        if(getTempEmployeesHolder().isEmpty()){
            JOptionPane.showMessageDialog(null, "Table is up to date");
        }else{
            this.getEmployees().clear();
            this.getEmployees().addAll(this.getTempEmployeesHolder());
            this.getTempEmployeesHolder().clear();
            this.fireTableDataChanged();
        }
    }


}
