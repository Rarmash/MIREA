package Work_7;

public class MathFunc implements MathCalculable{
    @Override
    public int pow(double base, int exponent) {
        int result = 1;

        if(exponent > 0){
            for(int i = 0; i < exponent; i++){
                result *= base;
            }
        }
        else if(exponent == 0){
            result = 1;
        }
        else{
            exponent = -exponent;
            for(int i = 1; i <= exponent; i++){
                result *= (1/base);
            }
        }
        return result;
    }
    @Override
    public double complNum(double a, double b) {
        return Math.sqrt(a * a + b * b);
    }
    public double cirleLenth(double radius){
        return 2*PI*radius;
    }
}
