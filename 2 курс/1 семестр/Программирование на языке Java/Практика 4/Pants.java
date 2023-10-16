public class Pants extends Clothes implements MenClothing, WomenClothing {

    public Pants(String colour,double prise,Size size){
        super(colour, prise, size);
    }

    public void dressMan(){
        System.out.println("Мужские штаны надеты");
    }

    public void dressWomen(){
        System.out.println("Женские штаны надеты");
    }
}
