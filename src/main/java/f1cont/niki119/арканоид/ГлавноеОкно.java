package f1cont.niki119.арканоид;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ГлавноеОкно extends JFrame {
    public int ширина = 500;
    public int высота = 500;
    Арканоид арканоид;
    public JPanel панель_главная = new JPanel(){
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.WHITE);
            g2d.fillOval(арканоид.шар.getИкс(), арканоид.шар.getИгрек(), арканоид.шар.радиус,арканоид.шар.радиус);
            g2d.fillRect(арканоид.каретка.getИкс(), арканоид.каретка.getИгрек(), арканоид.каретка.ширина,арканоид.каретка.высота);
            List<Блок> блоки = арканоид.уровень.блоки;
            int row = 1;
            int column = 1;
            for(int i = 0; i < блоки.size(); i++){
                Блок блок = блоки.get(i);
                if((20*column+блок.ширина*column + 20+блок.ширина) > ширина) {
                    column = 1;
                    row++;
                }
                блок.икс = column * (int) блок.ширина + 20*column;
                блок.игрек = (int) блок.высота*row + 20* row;
                g2d.fillRect((int) блок.икс,(int) блок.игрек,(int) блок.ширина,(int) блок.высота);
                column++;
            }
        }
    };
    public ГлавноеОкно(Арканоид арканоид){
        super("Арканоид");
        this.арканоид = арканоид;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-ширина/2,Toolkit.getDefaultToolkit().getScreenSize().height/2-высота/2, ширина, высота);
        панель_главная.setBackground(Color.BLACK);
        add(панель_главная);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                арканоид.открыть_меню();
            }
        });
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                super.windowStateChanged(e);
                ширина = e.getWindow().getWidth();
                высота = e.getWindow().getHeight();
            }
        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int код = e.getKeyCode();
                switch (код){
                    case 27:{ // ESC
                        арканоид.выключен = true;
                        арканоид.закрыть_главное_окно();
                        арканоид.открыть_меню();
                        break;
                    }
                    case 65: { //A
                        арканоид.движение_влево();
                        break;
                    }
                    case 68: { //D
                        арканоид.движение_вправо();
                        break;
                    }
                }
            }
        });
    }
    public synchronized void рисовать_блоки(){
        if(арканоид.уровень.изменена) {
            панель_главная.repaint();
            арканоид.уровень.изменена = false;
        }
    }
    public synchronized void рисовать(){
        if (арканоид.каретка.изменена){
            панель_главная.repaint();
            арканоид.каретка.сохранена();
        }
        if (арканоид.шар.изменена){
            панель_главная.repaint();
            арканоид.шар.сохранена();
        }
    }
}
