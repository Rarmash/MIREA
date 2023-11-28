package Task1;

import java.math.BigInteger;

public class InnError extends Exception{
    private BigInteger innNum;

    public InnError(BigInteger inn){
        super("ИНН с номером " + inn + " не действителен");
        innNum = inn;
    }

    public BigInteger getInnNum(){
        return innNum;
    }
}

