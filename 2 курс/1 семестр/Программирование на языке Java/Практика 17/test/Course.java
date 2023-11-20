package test;

public class Course {
    private String courseName;
    private String courseID;
    private String courseCategory;

    public String getName() {
        return courseName;
    }

    public void setName(String courseName) {
        this.courseName = courseName;
    }

    public String getID() {
        return courseID;
    }

    public void setID(String courseID) {
        this.courseID = courseID;
    }

    public String getCategory() {
        return courseCategory;
    }

    public void setCategory(String courseCategory) {
        this.courseCategory = courseCategory;
    }
}
