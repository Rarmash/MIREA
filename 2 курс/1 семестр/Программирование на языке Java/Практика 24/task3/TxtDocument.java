package task3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class TxtDocument extends AbstractDocument{
    // Реализация специфичных методов или свойств для текстового документа
    String font;
    int fontSize;
    String fondTypes;

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public String getFondTypes() {
        return fondTypes;
    }

    public void setFondTypes(String fondTypes) {
        this.fondTypes = fondTypes;
    }
    boolean flag;
    public void open() {
        System.out.println(" txt document is open");
        flag = true;
    }
    @Override
    public void close() {
        if (!flag) {
            System.out.println("the txt document was not opened");
        } else {
            System.out.println("txt document closed");
            flag = false;
        }

    }
    @Override
    public void save() {
        System.out.println("txt document saved");

    }

    @Override
    public void print() {
        System.out.println("!some txt document content!");

    }

    @Override
    public String getInfo() {
        return  "font = " + font  + ", fontSize = " + fontSize + ", fondTypes = " + fondTypes;
    }




//    private final JTextArea textArea;
//
//    public TextEditorApp() {
//        JFrame frame = new JFrame("Text Editor");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 400);
//
//        textArea = new JTextArea();
//        frame.add(textArea);
//
//        JMenuBar menuBar = new JMenuBar();
//
//        JMenu colorMenu = new JMenu("Цвет");
//        JMenuItem pinkItem = new JMenuItem("Розовый");
//        JMenuItem redItem = new JMenuItem("Красный");
//        JMenuItem blackItem = new JMenuItem("Черный");
//
//        pinkItem.addActionListener(new ColorActionListener(Color.PINK));
//        redItem.addActionListener(new ColorActionListener(Color.RED));
//        blackItem.addActionListener(new ColorActionListener(Color.BLACK));
//
//        colorMenu.add(pinkItem);
//        colorMenu.add(redItem);
//        colorMenu.add(blackItem);
//
//        JMenu fontMenu = new JMenu("Шрифт");
//        JMenuItem timesNewRomanItem = new JMenuItem("Times New Roman");
//        JMenuItem msSansSerifItem = new JMenuItem("MS Sans Serif");
//        JMenuItem courierNewItem = new JMenuItem("Courier New");
//
//        timesNewRomanItem.addActionListener(new FontActionListener("Times New Roman"));
//        msSansSerifItem.addActionListener(new FontActionListener("MS Sans Serif"));
//        courierNewItem.addActionListener(new FontActionListener("Courier New"));
//
//        fontMenu.add(timesNewRomanItem);
//        fontMenu.add(msSansSerifItem);
//        fontMenu.add(courierNewItem);
//
//        menuBar.add(colorMenu);
//        menuBar.add(fontMenu);
//
//        frame.setJMenuBar(menuBar);
//        frame.setVisible(true);
//    }
//
//    private class ColorActionListener implements ActionListener {
//        private final Color color;
//
//        public ColorActionListener(Color color) {
//            this.color = color;
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//
//            textArea.setForeground(color);
//        }
//    }
}
