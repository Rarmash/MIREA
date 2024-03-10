import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task3_Student implements Comparable<Task3_Student>{
    private String name;
    private String secondName;
    private int id;
    private double GPA;

    private String group;
    private String course;
    private String special;
    private LocalDate birthDate;

    @Override
    public int compareTo(Task3_Student otherStudent) {
        // Сравниваем студентов по их итоговым баллам
        if (this.GPA < otherStudent.GPA) {
            return -1;
        }
        else if (this.GPA > otherStudent.GPA) {
            return 1;
        }
        else {
            return 0;
        }
    }

    public Task3_Student(String name, String secondName, int id, double GPA, String group, String course, String special, LocalDate birthDate) {
        this.name = name;
        this.secondName = secondName;
        this.id = id;
        this.GPA = GPA;
        this.group = group;
        this.course = course;
        this.special = special;
        this.birthDate = birthDate;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getGPA() {
        return GPA;
    }

    public String formatBirthDate(DateTimeFormatter formatter) {
        return birthDate.format(formatter);
    }

    @Override
    public String toString() {
        return "Студент: " + name + " " + secondName + ", Дата рождения: " + formatBirthDate(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}