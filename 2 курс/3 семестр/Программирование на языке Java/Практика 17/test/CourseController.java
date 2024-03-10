package test;

public class CourseController {
    private final Course model;
    private final CourseView view;

    public CourseController(Course model, CourseView view) {
        this.model = model;
        this.view = view;
    }

    public void setCourseName(String name) {
        model.setName(name);
    }

    public String getCourseName() {
        return model.getName();
    }

    public void setCourseId(String id) {
        model.setID(id);
    }

    public String getCourseId() {
        return model.getID();
    }

    public void setCourseCategory(String category) {
        model.setCategory(category);
    }

    public String getCourseCategory() {
        return model.getCategory();
    }

    public void updateView() {
        view.printCourseDetails(model.getName(), model.getID(), model.getCategory());
    }
}
