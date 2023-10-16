import java.lang.*;
public enum Seasons {
    Winter(-6,"Холодное время года"),
    Spring(7, "Теплое время года"),
    Summer(20,"Теплое время года"),
    Autumn(7, "Холодное время года")
    ;
    private final double t;
    private final String description;
    private Seasons(double t, String description){
        this.t = t;
        this.description = description;
    }
    public double getT(){
        return this.t;
    }
    public String getDescription(){
        return description;
    }
    public void favoriteSeason(Seasons favoriteseason) {
        System.out.println("Информации о любимом времени года:");
        System.out.println("Любимое время года: " + favoriteseason);
        System.out.println("Средняя температура: " + getT());
        System.out.println("Описание: " + getDescription() + "\n");
    }
}
