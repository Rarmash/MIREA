public class main {
    public static void main(String[] args) {

        Factory factory = new Factory(){};

        Chair chair1 = factory.createChair(ChairTypes.VIC);
        Chair chair2 = factory.createChair(ChairTypes.MULTIF);
        Chair chair3 = factory.createChair(ChairTypes.MAGIC);

        Client person = new Client(null, "Ivan");
        person.sit(chair1);
        person.sit(chair2);
        person.sit(chair3);
        person.orderChair(ChairTypes.VIC);
    }
}
