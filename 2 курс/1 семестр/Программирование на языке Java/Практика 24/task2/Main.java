package task2;

class Main {
    public static void main(String[] args) {
        Client client = new Client();
        ChairFactory chairFactory = new ChairFactory();
        FunctionalChair functionalChair = chairFactory.createFunctionalChair();
        MagicChair magicChair = chairFactory.createMagicChair();
        VictorianChair victorianChair = chairFactory.createVictorianChair();


        System.out.println("\nsome information about functional chair:");
        client.setChair(functionalChair);
        client.sitChair();
        System.out.println(functionalChair.sum(1,2) + " sum\n");



        System.out.println("some information about victorian chair:");
        client.setChair(victorianChair);
        client.sitChair();
        System.out.println(victorianChair.getAge() + "\n");


        System.out.println("some information about magic chair:");
        client.setChair(magicChair);
        client.sitChair();
        magicChair.doMagic();



    }
}
