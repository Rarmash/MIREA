public class BookTest {

    public static void main(String[] args) {

        Book b1 = new Book("TLOTR.", "J. R. R. Tolkin", 110);

    }
}

class Book implements Nameble, Priceble{

    private String name;
    int price;
    String author;

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                '}';
    }

    Book(String name, String author, int price){

        this.name = name;
        this.author = author;
        this.price = price;
    }

    public String getName(){
        return this.name;
    }

    public int getPrice(){
        return this.price;
    }
}
