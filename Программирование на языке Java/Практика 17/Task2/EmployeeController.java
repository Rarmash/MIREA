package Task2;

public class EmployeeController {
    private final Employee model;
    private final EmployeeView view;

    public EmployeeController(Employee model, EmployeeView view) {
        this.model = model;
        this.view = view;
    }

    public String getEmployeeName() {
        return model.getName();
    }

    public double getEmployeeSalary() {
        return model.getSalary();
    }

    public void setEmployeeName(String name) {
        model.setName(name);
    }

    public void setEmployeeSalary(double salary) {
        model.setSalary(salary);
    }

    public void updateView() {
        view.printEmployeeDetails(model.getName(), model.getSalary());
    }
}
