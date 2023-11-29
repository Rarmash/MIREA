package Task1;

import java.math.BigInteger;

public class INNException extends Exception{
    private final BigInteger innNum;

    public INNException(BigInteger inn){
        super("ИНН с номером " + inn + " не действителен");
        innNum = inn;
    }

    public BigInteger getInnNum(){
        return innNum;
    }
}

