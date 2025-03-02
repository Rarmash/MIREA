import java.text.SimpleDateFormat;
import java.util.Date;

public class Task1 {
    public static void main(String[] args) {
        String secondName = "Grishin";
        // Дата и время сдачи задания
        Date dateReceived = new Date();

        // Дата и время сдачи задания
        long currentTimeMillis = System.currentTimeMillis();
        long oneWeekInMillis = 6 * 24 * 60 * 60 * 1000L; // 7 дней
        Date dateDue = new Date(currentTimeMillis + oneWeekInMillis);

        SimpleDateFormat dateFormat = new SimpleDateFormat();

        System.out.println("Фамилия разработчика: " + secondName);
        System.out.println("Дата и время получения задания: " + dateFormat.format(dateReceived));
        System.out.println("Дата и время сдачи задания: " + dateFormat.format(dateDue));
    }
}