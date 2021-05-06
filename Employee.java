public class Employee {

    private String name;
    private String surname;
    private Enum Position;
    private int timeOfService;
    private double salary;


    public Employee(String name, String surname, Enum position, int timeOfService, double salary) {
        this.name = name;
        this.surname = surname;
        Position = position;
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

    public Enum getPosition() {
        return Position;
    }

    public void setPosition(Enum position) {
        Position = position;
    }

    public int getTimeOfService() {
        return timeOfService;
    }

    public void setTimeOfService(int timeOfService) {
        this.timeOfService = timeOfService;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


}
