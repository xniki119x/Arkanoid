package f1cont.niki119.арканоид;

import f1cont.niki119.арканоид.уровни.ГенераторУровней;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Арканоид {
    public boolean выключен = false;
    public boolean пауза = false;
    public ГлавноеОкно главное_окно;
    public Меню меню;
    public Каретка каретка;
    public Шар шар;
    public Игрок игрок;
    public Уровень уровень;
    public ТаблицаРекордов таблица_рекордов;
    public double скорость = 30;
    public boolean начало = false;
    Thread поток;
    Thread поток2;

    public Арканоид() throws IOException, FontFormatException {
        this.меню = new Меню(this);
    }

    long время_ожидания;
    long накопленное_время;

    public void цикл() throws InterruptedException {
        время_ожидания = 1000000000L / 60L;
        накопленное_время = 0;
        поток = new Thread(() -> {
            while (!выключен) {
                try {
                    long время = System.nanoTime();
                    if (начало) {
                        логика();
                    }
                    отрисовка();
                    накопленное_время += System.nanoTime() - время;
                    if (накопленное_время > время_ожидания) {
                        ожидание();
                        накопленное_время -= время_ожидания;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        поток.setDaemon(true);
        поток.start();
        Thread поток2 = new Thread(() -> {
            for (int i = 66; i > 0; i--) {
                try {
                    каретка.движение_вверх(1);
                    Thread.sleep(1000 / 66);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            начало = true;
        });
        поток2.setDaemon(true);
        поток2.start();
    }

    public void стоп() {
        поток.interrupt();
        поток2.interrupt();
    }

    public void ожидание() throws InterruptedException {
        Thread.sleep(время_ожидания / 1000000);
    }

    public void логика() {
        коллизия_шара();
        движение_шара();
    }

    public void коллизия_шара() {
        double икс_шара = шар.икс;
        double игрек_шара = шар.игрек;
        int радиус_шара = шар.радиус;
        if (игрек_шара + радиус_шара >= каретка.игрек) {
            if ((икс_шара + радиус_шара / 2d) >= каретка.икс && (икс_шара - радиус_шара / 2d) <= (каретка.икс + каретка.ширина)) {
                шар.скорость_по_игрек *= -1;
                шар.скорость_по_икс = -0.000002 * ((каретка.икс + каретка.ширина / 2d) - (икс_шара + радиус_шара / 2d));
            }
        }
        if (игрек_шара > главное_окно.высота) {
            выключен = true;
            закрыть_главное_окно();
            открыть_меню();
            JOptionPane.showMessageDialog(new JLabel("F"), "Lose");
        }
        if (игрек_шара < 0) {
            шар.скорость_по_игрек *= -1;
        }
        if (икс_шара < 0) {
            шар.скорость_по_икс *= -1;
        }
        if (икс_шара + радиус_шара > главное_окно.ширина) {
            шар.скорость_по_икс *= -1;
        }
        for (int i = 0; i < уровень.блоки.size(); i++) {
            Блок блок = уровень.блоки.get(i);
            if (блок.икс < (икс_шара) && блок.игрек < игрек_шара) {
                if (блок.икс + блок.ширина > (икс_шара) &&
                        блок.игрек + блок.высота > игрек_шара) {
                    уровень.блоки.remove(блок);
                    шар.скорость_по_икс *= -1;
                    шар.скорость_по_игрек *= -1;
                    System.out.println("АА");
                    return;
                }
            }
        }
    }

    public void движение_шара() {
        шар.движение();
    }

    public void отрисовка() throws InterruptedException {
        if (начало) рисовать_блоки();
        рисовать_шар();
        рисовать_каретку();
    }

    public void рисовать_каретку() {
        главное_окно.рисовать();
    }

    public void рисовать_шар() {
        главное_окно.рисовать();
    }

    public void рисовать_блоки() {
        главное_окно.рисовать_блоки();
    }

    public void старт(String имя_игрока) throws InterruptedException {
        игрок = new Игрок(имя_игрока, 0);
        каретка = new Каретка(this, 100, 0, 0);
        шар = new Шар(this, 30);
        уровень = ГенераторУровней.получить_новый_уровень();
        главное_окно = new ГлавноеОкно(this);
        закрыть_меню();
        открыть_главное_окно();
        цикл();
    }

    public void пауза() {
        пауза = true;
        главное_окно.панель_главная.setVisible(false);
        главное_окно.remove(главное_окно.панель_главная);
    }

    public void продолжить() {
        пауза = false;
        главное_окно.add(главное_окно.панель_главная);
        главное_окно.панель_главная.setVisible(true);
        главное_окно.requestFocus();
    }

    public void открыть_меню() {
        меню.setVisible(true);
    }

    public void закрыть_меню() {
        меню.setVisible(false);
    }

    public void открыть_главное_окно() {
        главное_окно.setVisible(true);
        главное_окно.ширина = главное_окно.getWidth();
        главное_окно.высота = главное_окно.getHeight();
        шар.setИкс(главное_окно.ширина / 2d);
        шар.setИгрек(главное_окно.высота / 2d);
        каретка.setИкс(главное_окно.ширина / 2);
        каретка.setИгрек(главное_окно.высота);
    }

    public void закрыть_главное_окно() {
        главное_окно.setVisible(false);
    }

    public void движение_влево() {
        каретка.движение_влево(скорость);
    }

    public void движение_вправо() {
        каретка.движение_вправо(скорость);
    }

    public static void main(String[] args) throws IOException, FontFormatException {
        Арканоид арканоид = new Арканоид();
        арканоид.открыть_меню();
    }
}
