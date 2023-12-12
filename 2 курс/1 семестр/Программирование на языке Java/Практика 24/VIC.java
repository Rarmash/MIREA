public class VIC implements Chair
{

    @Override
    public void sit(Chair chair) {
        System.out.println("Это VIC");
    }

    @Override
    public String GetType() {
        return "vic";
    }
}
