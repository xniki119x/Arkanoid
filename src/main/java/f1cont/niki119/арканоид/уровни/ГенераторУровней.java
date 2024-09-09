package f1cont.niki119.арканоид.уровни;

import f1cont.niki119.арканоид.Блок;
import f1cont.niki119.арканоид.Уровень;

import java.util.Random;

public class ГенераторУровней {
    public static Уровень получить_новый_уровень(){
        Уровень уровень = new Уровень();
        Random random = new Random();
        for(int i = 50 + random.nextInt(20); i > 0; i--){
            уровень.блоки.add(new Блок(-1,-1, 40, 20, 1));
        }
        return уровень;
    }
}
