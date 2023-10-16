public class Atelie {
    public void dressMan(Clothes[] clothes){
        System.out.println("Мужская одежда");
        for(Clothes a: clothes){
            if (a instanceof MenClothing) {
                System.out.println(a.colour + " " + a.prise + " " + a.size);
                ((MenClothing) a).dressMan();
            }
        }
    }
    public void dressWomen(Clothes[] clothes){
        System.out.println("Женская одежда");
        for (Clothes a: clothes) {
            if (a instanceof WomenClothing) {
                System.out.println(a.colour + " " + a.prise + " " + a.size);
                ((WomenClothing) a).dressWomen();
            }
        }
    }
}
