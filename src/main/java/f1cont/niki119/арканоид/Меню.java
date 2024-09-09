package f1cont.niki119.арканоид;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class Меню extends JFrame {
    public int ширина = 500;
    public int высота = 500;
    Font шрифт = Font.createFont(Font.TRUETYPE_FONT, Меню.class.getResourceAsStream("/шрифт/minecraft.ttf")).deriveFont(24f);
    public JPanel главная_панель = new JPanel();
    public JLabel надпись_меню = new JLabel("Меню", SwingConstants.CENTER);
    public JButton кнопка_новая_игра = new JButton("Новая игра");
    public JLabel надпись_имя_игрока = new JLabel("Имя игрока:");
    public JButton кнопка_выход = new JButton("Выход");
    public JTextField поле_имя_игрока = new JTextField();
    public GridBagLayout gbl = new GridBagLayout();
    public GridBagConstraints gbc = new GridBagConstraints();
    Арканоид арканоид;
    public Меню(Арканоид арканоид) throws IOException, FontFormatException {
        super("Меню");
        this.арканоид = арканоид;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-ширина/2,Toolkit.getDefaultToolkit().getScreenSize().height/2-высота/2, ширина, высота);
        главная_панель.setLayout(gbl);
        add(главная_панель);
        поле_имя_игрока.setText("Игрок");
        надпись_меню.setFont(шрифт);
        кнопка_новая_игра.setFont(шрифт);
        поле_имя_игрока.setFont(шрифт);
        надпись_имя_игрока.setFont(шрифт);
        кнопка_выход.setFont(шрифт);
        надпись_меню.setForeground(Color.WHITE);
        надпись_имя_игрока.setForeground(Color.WHITE);
        кнопка_новая_игра.setBackground(Color.BLACK);
        кнопка_новая_игра.setForeground(Color.WHITE);
        кнопка_выход.setBackground(Color.BLACK);
        кнопка_выход.setForeground(Color.WHITE);
        gbc.insets = new Insets(5,0,0,0);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        главная_панель.add(надпись_меню, gbc);
        gbc.gridy = 2;
        главная_панель.add(надпись_имя_игрока, gbc);
        gbc.gridy = 3;
        главная_панель.add(поле_имя_игрока, gbc);
        gbc.gridy = 4;
        главная_панель.add(кнопка_новая_игра, gbc);
        gbc.gridy = 5;
        главная_панель.add(кнопка_выход, gbc);
        кнопка_новая_игра.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String имя_игрока = поле_имя_игрока.getText();
                if(имя_игрока.isEmpty()){
                    имя_игрока = "Игрок";
                }
                try {
                    арканоид.выключен = false;
                    арканоид.начало = false;
                    арканоид.старт(имя_игрока);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        кнопка_выход.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                арканоид.выключен = true;
                if(арканоид.главное_окно!=null) арканоид.главное_окно.dispose();
                dispose();
            }
        });
        setUndecorated(true);
        setBackground(Color.WHITE);
        главная_панель.setBackground(Color.BLACK);
    }
}
