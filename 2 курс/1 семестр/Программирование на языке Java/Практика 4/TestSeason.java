import java.lang.*;

public class TestSeason {
    public static void main(String[] arg) {
        Seasons season = Seasons.Summer;
        season.favoriteSeason(season);
        for (Seasons seasons : Seasons.values()) {
            System.out.println("Время года " + seasons);
            System.out.println("Средняя температура: " + seasons.getT());
            System.out.println("Описание: " + seasons.getDescription() + "\n");
        }
    }
}
