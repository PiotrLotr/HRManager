import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class MyTabModel extends AbstractTableModel {

    private String[] columnNames = {"FIRST NAME",
            "LAST NAME",
            "POSITION",
            "TIME OF SERVICE [YEARS]",
            "SALARY[PLN]"};

    private ArrayList<Employee> employees;

    public MyTabModel(ArrayList<Employee> employees) {
        this.employees = employees;
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
        for (Employee e : employees) {
            switch (columnIndex) {
                case 0:
                    return e.getName();
                case 1:
                    return e.getSurname();
                case 2:
                    return e.getPosition();
                case 3:
                    return e.getTimeOfService();
                case 4:
                    return e.getSalary();
            }
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
                employees.get(rowIndex).setPosition((Enum) value);
                break;
            case 3:
                employees.get(rowIndex).setTimeOfService((int) value);
                break;
            case 4:
                employees.get(rowIndex).setSalary((double) value);
                break;
        }
    }
}
