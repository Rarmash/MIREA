public class TShirt extends Clothes implements MenClothing, WomenClothing {
    public TShirt(String colour,double prise,Size size){
        super(colour, prise, size);
    }

    public void dressMan(){
        System.out.println("Мужская футболка надета");
    }
    public void dressWomen(){
        System.out.println("Женская футболка надета");
    }
}
