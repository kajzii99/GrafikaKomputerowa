package lab1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Transforms2D extends JPanel {

    private class Display extends JPanel {

        private static final double N = 19;     //deklaracja zmiennej o wartosci 19
        private int r = 200;        //promien o wartosci 200

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;
            g2.translate(300,300);  // Moves (0,0) to the center of the display.
            int whichTransform = transformSelect.getSelectedIndex();

            // TODO Apply transforms here, depending on the value of whichTransform!

            Polygon polygon = new Polygon();
            for (int i = 0; i < N; i++) //dodawanie punktów
            {
                polygon.addPoint((int) (r * Math.cos(i * 2 * Math.PI / N)), //wzór z wykładu
                        (int) (r * Math.sin(i * 2 * Math.PI / N)));
            }

            if(whichTransform == 1)
            {
                g2.scale(0.5, 0.5);
            }
            else if(whichTransform == 2)
            {
                g2.rotate(Math.toRadians(-45));
            }
            else if(whichTransform == 3)
            {
                g2.rotate(Math.toRadians(180));
                g2.transform(AffineTransform.getScaleInstance(1, -1)); //obracanie lustrzane po osi Y
                g2.scale(0.5, 1);
            }
            else if(whichTransform == 4)
            {
                g2.shear(0,0.2); //pochylenie o 0.2 na osi Y
            }
            else if(whichTransform == 5)
            {
                g2.scale(1,0.5);
                g2.translate(0,-400); //przesuwanie po osi Y w góre
            }
            else if(whichTransform == 6)
            {
                g2.rotate(Math.toRadians(-90));
                g2.shear(0.2,0);
            }
            else if(whichTransform == 7)
            {
                g2.scale(0.5, 1);
                g2.rotate(Math.toRadians(180));
            }
            else if(whichTransform == 8)
            {
                g2.rotate(Math.toRadians(-30));
                g2.scale(1, 0.5);
                g2.translate(-100, 200);
            }
            else if(whichTransform == 9)
            {
                g2.rotate(Math.toRadians(180));
                g2.translate(-100,0);
                g2.shear(0.1,0);
            }

            g2.fill(polygon);
        }
    }

    private Display display;
    private JComboBox<String> transformSelect;

    public Transforms2D() throws IOException {
        display = new Display();
        display.setBackground(Color.YELLOW);
        display.setPreferredSize(new Dimension(600,600));
        transformSelect = new JComboBox<String>();
        transformSelect.addItem("None");
        for (int i = 1; i < 10; i++) {
            transformSelect.addItem("No. " + i);
        }
        transformSelect.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                display.repaint();
            }
        });
        setLayout(new BorderLayout(3,3));
        setBackground(Color.GRAY);
        setBorder(BorderFactory.createLineBorder(Color.GRAY,10));
        JPanel top = new JPanel();
        top.setLayout(new FlowLayout(FlowLayout.CENTER));
        top.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        top.add(new JLabel("Transform: "));
        top.add(transformSelect);
        add(display,BorderLayout.CENTER);
        add(top,BorderLayout.NORTH);
    }


    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame("2D Transforms");
        window.setContentPane(new Transforms2D());
        window.pack();
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation( (screen.width - window.getWidth())/2, (screen.height - window.getHeight())/2 );
        window.setVisible(true);
    }

}