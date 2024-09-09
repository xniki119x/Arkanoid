package f1cont.niki119.арканоид;

import javax.swing.*;
import java.awt.*;

public class Каретка {
    public int ширина;
    public int высота;
    public double икс;
    public double игрек;
    public boolean изменена = true;
    Арканоид арканоид;
    public Каретка(Арканоид арканоид, int размер, double икс, double игрек){
        this.арканоид = арканоид;
        this.ширина = размер;
        this.икс = икс;
        this.игрек = игрек;
        this.высота = 15;
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
    public void движение_вправо(double скорость){
        int ширина = арканоид.главное_окно.ширина- this.ширина;
        if(getИкс()==ширина) return;
        if (ширина<икс+скорость) setИкс(ширина);
        else setИкс(икс+скорость);
    }
    public void движение_влево(double скорость){
        if(getИкс()==0) return;
        if(икс-скорость<0) setИкс(0);
        else setИкс(икс-скорость);
    }
    public void движение_вверх(double скорость){
        setИгрек(игрек-скорость);
    }

    public int getИкс() {
        return (int) икс;
    }

    public int getИгрек() {
        return (int) игрек;
    }
}
