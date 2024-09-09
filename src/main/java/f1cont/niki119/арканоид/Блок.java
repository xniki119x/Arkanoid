package f1cont.niki119.арканоид;

import java.awt.*;

public class Блок {
    public double икс, игрек, ширина, высота;
    public Color цвет = Color.WHITE;
    public int здоровье;
    public Блок(double икс, double игрек, double ширина, double высота, int здоровье){
        this.икс = икс;
        this.игрек = игрек;
        this.ширина = ширина;
        this.высота = высота;
        this.здоровье = здоровье;
    }
}
