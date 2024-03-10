public class AnyTape<E> {
    private E [] arr;
    private int h;

    public E getArrIndex(int i){
        return  arr[i] ;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getH() {
        return h;
    }

    public void setArr(E [] arr){
        this.arr =  arr;
    }

    public int getLength(){
        return  arr.length ;
    }

    public E[] getArr() {
        return arr;
    }
    public void printer() {
        for (Object str : arr) {
            System.out.print(str + " ");
        }
    }
    public void replace(int i, E h) {
        arr[i] = h;
    }


}
