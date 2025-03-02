import java.io.File;

public class Task_4 {
    public static void main(String[] args) {
        listAndPrintFilesInDirectory("C:\\Users\\Rarmash\\IdeaProjects\\TG-CS2-Inventory-Watchdog");
    }

    public static void listAndPrintFilesInDirectory(String directoryPath) {
        File directory = new File(directoryPath);

        // Проверяем, что каталог существует
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files != null && files.length > 0) {
                System.out.println("Список файлов в каталоге:");
                int count = 0;

                for (File file : files) {
                    if (count < 5) {
                        System.out.println(file.getName());
                        count++;
                    } else {
                        break;
                    }
                }
            } else {
                System.out.println("Каталог пуст.");
            }
        } else {
            System.out.println("Каталог не существует.");
        }
    }
}
