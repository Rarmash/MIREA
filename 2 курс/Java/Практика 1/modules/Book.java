package modules;

import java.lang.*;

public class Book {
    private String title;
    private int yearofRelease;

    public Book(String t, int yoR) {
        title = t;
        yearofRelease = yoR;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYearofRelease(int yearofRelease) {
        this.yearofRelease = yearofRelease;
    }

    public String getTitle() {
        return title;
    }

    public int getYearofRelease() {
        return yearofRelease;
    }

    public String toString() {
        return this.title+", year of release - "+this.yearofRelease;
    }
}