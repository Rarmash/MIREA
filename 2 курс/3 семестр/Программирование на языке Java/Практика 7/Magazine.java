package Work_7;

public class Magazine implements Printable{
    private String title;

    public Magazine(String title) {
        this.title = title;
    }

    @Override
    public void print() {
        System.out.println("Журнал: " + title);
    }

//    Printable - это интерфейс, который может быть создан в вашем коде для обеспечения
//    способности объектов разных классов выполнять одну и ту же операцию, такую как печать
//    (или какую-либо другую операцию), независимо от их конкретных типов.
//    Он используется для создания общего интерфейса для разных классов,
//    что облегчает работу с объектами этих классов и упрощает их использование в общем контексте.
    public static void printMagazines(Printable[] printable){
        for(Printable item : printable){
//            instanceof - это оператор в Java, который используется для проверки,
//            является ли объект экземпляром определенного класса или его подкласса
            if(item instanceof Magazine){
                item.print();
            }
        }
    }

    public static void main(String[] args) {
        Printable[] printableItems = new Printable[]{
                new Magazine("Журнал 1"),
                new Book("Книга 1"),
                new Magazine("Журнал 2"),
                new Book("Книга 2")
        };
        printMagazines(printableItems);
        Book.printBooks(printableItems);
    }
}
