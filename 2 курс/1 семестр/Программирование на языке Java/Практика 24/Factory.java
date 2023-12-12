public class Factory
{
    public  Chair createChair (ChairTypes type) {
        Chair chair = null;

        switch (type) {
            case VIC:
                chair = new VIC();
                break;
            case MULTIF:
                chair = new MULTIF();
                break;
            case MAGIC:
                chair = new MAGIC();
                break;
        }

        return chair;
    }
}
