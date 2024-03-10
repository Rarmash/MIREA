public class Task_2 {

    public static <E> void  sid  (String s, E [] arr){

        E [] a = arr;
        AnyTape <E> sid = new AnyTape <E> ();
        sid.setArr(a);
    }

    public static void main(String [] args){

        String [] s = {"Hello", "World","!"};
        //sid("String" , s);

        Integer [] intr = new Integer[]{ 1,2,3,4,5,6,7,8};
        //sid("Integer" , intr);

        Double [] ad = {1.2,1.5,6.7};
        intr[0] = 5;
        AnyTape <String> s1 = new AnyTape<String>();
        AnyTape <Integer> s2 = new AnyTape<Integer>();
        AnyTape <Double> s3 = new AnyTape<Double>();

        s1.setArr(s);
        s1.printer();
        s2.setArr(intr);
        s2.printer();
        s3.setArr(ad);
        s3.printer();
    }
}