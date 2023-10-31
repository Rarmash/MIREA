class Car implements Nameble, Priceble {
    private String name;
    int price;
    String model;ё

    Car(String name, String model, int price){

        this.name = name;
        this.model = model;
        this.price = price;
    }

    public String getName(){
        return this.name;
    }

    public int getPrice(){
        return this.price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", model='" + model + '\'' +
                '}';
    }
}
