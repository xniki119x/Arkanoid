package f1cont.niki119.арканоид;

import javax.swing.*;
import java.awt.*;

public class Шар {
    public int радиус;
    public double икс = 10;
    public double игрек = 10;
    public double скорость_по_икс;
    public double скорость_по_игрек;
    public boolean изменена = true;
    Арканоид арканоид;
    public Шар(Арканоид арканоид, int радиус){
        this.арканоид = арканоид;
        this.радиус = радиус;
        скорость_по_икс = 0;
        скорость_по_игрек = 0.00007;
    }
    public int getИкс() {
        return (int) икс;
    }

    public int getИгрек() {
        return (int) игрек;
    }
    public void setИкс(double икс) {
        this.икс = икс;
        изменена = true;
    }

    public void setИгрек(double игрек) {
        this.игрек = игрек;
        изменена = true;
    }
    public void сохранена(){
        изменена = false;
    }
    public void движение(){
        setИкс(икс+скорость_по_икс);
        setИгрек(игрек+скорость_по_игрек);
    }
}
