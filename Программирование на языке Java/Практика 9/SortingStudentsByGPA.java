import java.util.Comparator;

public class SortingStudentsByGPA implements Comparator<Student> {
    @Override
    public int compare(Student student1, Student student2) {
        // Сравниваем студентов по убыванию GPA
        return Double.compare(student2.getGPA(), student1.getGPA());
    }
}