public class Tie extends Clothes implements MenClothing {
    public Tie(String colour,double prise,Size size){
        super(colour, prise, size);
    }

    public void dressMan(){
        System.out.println("Мужской галстук надет");
    }
}
