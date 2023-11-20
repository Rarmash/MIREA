package Work_7;

public class Book implements Printable{
    private String title;

    public Book(String title) {
        this.title = title;
    }

    @Override
    public void print() {
        System.out.println("Книга: " + title);
    }

    public static void printBooks(Printable[] printable){
        for(Printable item : printable){
            if(item instanceof Book){
                item.print();
            }
        }
    }
}
