public class Student implements Comparable<Student>{
    private String name;
    private int id;
    private double GPA;

    @Override
    public int compareTo(Student otherStudent) {
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

    public Student(String name, int id, double GPA) {
        this.name = name;
        this.id = id;
        this.GPA = GPA;
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

    @Override
    public String toString() {
        return "Имя студента: " + name +
                ", ID студента: " + id + ", GPA студента: " + GPA;
    }
}