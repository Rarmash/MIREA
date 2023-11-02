public class Task1 {
    public static void num_2(String s){
        char str = 0;
        for(int i = 0; i < s.length(); i++){
            if(i == s.length()-1){
                str = s.charAt(i);
            }
        }
        String result = Character.toString(str);
        System.out.println("2) Последний символ строки: " + result);
    }
    public static void num_3(String s){
        boolean retVal;

        retVal = s.endsWith("!!!");
        System.out.println("3) Заканчивается ли строка подстрокой '!!!': " + retVal);
    }
    public static void num_4(String s){
        boolean retVal;

        retVal = s.startsWith("I like");
        System.out.println("4) Ничинается ли строка подстрокой 'I like': " + retVal);
    }
    public static void num_5(String s){
        boolean retVal;

        retVal = s.contains("Java");
        System.out.println("5) Содержит ли строка подстроку 'Java': " + retVal);
    }
    public static void num_6(String s){
        int index = s.indexOf("Java");
        System.out.println("6) Индекс подстроки 'Java': " + index);
    }
    public static void num_7(String s){
        String newStr = s.replace('a', 'o');
        System.out.println("7) Измененная строка: " + newStr);
    }
    public static void num_8(String s){
        s = s.toUpperCase();
        System.out.println("8) Строка с верхним регистром: " + s);
    }
    public static void num_9(String s){
        s = s.toLowerCase();
        System.out.println("9) Строка с нижним регистром: " + s);
    }
    public static void num_10(String s){
        int index = s.indexOf("Java");
        if (index != -1) {
            String result = s.substring(0, index) + s.substring(index + 4);
            System.out.println("10) Строка без подстроки 'Java': " + result);
        } else {
            System.out.println("10) Подстрока 'Java' не найдена в строке.");
        }    }

    public static void main(String[] args) {
        String s = "I like Java!!!";
        System.out.println("1) Строка, с которой работаем: " + s + "\n");
        num_2(s); num_3(s); num_4(s); num_5(s);
        num_6(s); num_7(s); num_8(s); num_9(s); num_10(s);
    }
}