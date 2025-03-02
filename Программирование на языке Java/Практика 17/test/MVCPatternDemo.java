package test;

public class MVCPatternDemo {
    public static void main(String[] args) {
//        получить из базы данных запись о студенте на основе его номера в списке база данных
        Course model = retriveCourseFromDatabase();

//        Создайте представление : для записи информации о курсе на консоль
        CourseView view = new CourseView();

        CourseController controller = new CourseController(model, view);

        controller.updateView();

        controller.setCourseName("Python");
        System.out.println("\nAfter updating, Course Details are as follows");
        controller.updateView();
    }

    public static Course retriveCourseFromDatabase() {
        Course course = new Course();
        course.setName("Java");
        course.setID("01");
        course.setCategory("Programming");
        return course;
    }
}
