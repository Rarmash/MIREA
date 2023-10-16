public class Skirt extends Clothes implements WomenClothing {
    public Skirt(String colour,double prise,Size size){
        super(colour, prise, size);
    }

    public void dressWomen(){
        System.out.println("Женская юбка надета \n");
    }
}
