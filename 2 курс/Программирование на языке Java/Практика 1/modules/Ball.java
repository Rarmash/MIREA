package modules;

import java.lang.*;

public class Ball {
    private String manufacturer;
    private int pressure;

    public Ball(String m, int p) {
        manufacturer = m;
        pressure = p;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getPressure() {
        return pressure;
    }

    public String toString() {
        return this.manufacturer+", pressure - "+this.pressure;
    }
}