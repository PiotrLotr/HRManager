public class Employee {

    private String name;
    private String surname;
    private Position position;
    private double timeOfService;
    private double salary;

    public Employee(String name, String surname, Position position, double timeOfService, double salary) {
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.timeOfService = timeOfService;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public double getTimeOfService() {
        return timeOfService;
    }

    public void setTimeOfService(double timeOfService) {
        this.timeOfService = timeOfService;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +

                ", timeOfService=" + timeOfService +
                ", salary=" + salary +
                '}';
    }
}
