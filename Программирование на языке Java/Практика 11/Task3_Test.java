import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Task3_Test {
    public static void main(String[] args) {
        List<Task3_Student> list = new ArrayList<>();

        LocalDate birthDate1 = LocalDate.of(1995, 5, 15);
        Task3_Student student1 = new Task3_Student("John", "Doe", 12345, 3.4, "Group A", "2nd Year", "Computer Science", birthDate1);

        LocalDate birthDate2 = LocalDate.of(1998, 7, 21);
        Task3_Student student2 = new Task3_Student("Alice", "Smith", 56789, 3.5, "Group B", "3rd Year", "Engineering", birthDate2);

        list.add(student1);
        list.add(student2);

        for (Task3_Student student : list) {
            System.out.println(student);
        }
    }
}