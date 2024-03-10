package Task1;

public class MVCPatternDemo {
    public static void main(String[] args) {
        //MVC - это шаблон проектирования, который разделяет приложение на три основных компонента:
        // модель, представление и контроллер.
        Student model = retrieveStudentFromDataBASE();

        StudentView view = new StudentView();

        StudentController controller = new StudentController(model, view);

        controller.updateView();

        controller.setStudentName("Ivan The-Spacebiker");
        System.out.println("\nAfter updating, Student Details are as follows");
        controller.updateView();
    }

    //устанавливаем изначальное значение
    public static Student retrieveStudentFromDataBASE() {
        Student student = new Student();
        student.setName("Rarmash");
        student.setRollNo("Programmer");
        return student;
    }
}
