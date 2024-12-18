import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.IOException;
import javax.imageio.*;
import javax.swing.*;

public class AppPanel extends JPanel {
    ImageIcon backgroundImage;
    BufferedImage MycarImage;
    BufferedImage Car1Image;
    BufferedImage TruckImage;
    BufferedImage gameOverImage;

    Timer timer;
    int xAxis = 400;
    int yAxis = 0;
    int xMyCar = xAxis + 210;
    int yMyCar = 570+120;
    int xCar1 = xAxis + (500 - (70 + 65));
    int yCar1 = 0;
    int xTruck = xAxis + 52;
    int yTruck = yAxis - 70;
    int collision = 0;
    int score = 0;

    AppPanel() {
        setSize(500, 700);
        setBounds(400, 0, 500, 700);
        showBg();
        repaint();
        loopPaint();
        loopPaint1();
        keyboardControl();
        setFocusable(true);
    }

    void showBg() {
        try {
            backgroundImage = new ImageIcon(AppPanel.class.getResource("3_Lane_Road1.gif"));
            MycarImage = ImageIO.read(AppPanel.class.getResource("myCar.png"));
            Car1Image = ImageIO.read(AppPanel.class.getResource("enemyCar1.png"));
            TruckImage = ImageIO.read(AppPanel.class.getResource("enemyCar2.png"));
            gameOverImage = ImageIO.read(AppPanel.class.getResource("gameOver.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), xAxis+150, yAxis, 500, 900, null);

        if (!isColision(xMyCar, yMyCar, xCar1, yCar1, xTruck, yTruck)) {
            g.drawImage(MycarImage, xMyCar+150, yMyCar, 90, 120, null);
            g.drawImage(Car1Image, xCar1+150, yCar1, 90, 120, null);
            g.drawImage(TruckImage, xTruck+150, yTruck, 90, 120, null);


            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.setColor(Color.BLACK);
            g.drawString("Score: " + score, 10, 30);
        } else {
            g.drawImage(gameOverImage, xAxis+150, yAxis + 200, 500, 300, null);

        }
    }

    void loopPaint() {
        timer = new Timer(150, (abcd) -> {
            if (yCar1 != 860) {
                yCar1 += 50;
            }
            if (yCar1 >= 860) {
                yCar1 = 0;
                score++;
            }
            repaint();
        });
        timer.start();
    }

    void loopPaint1() {
        timer = new Timer(200, (abcd) -> {
            if (yTruck != 860) {
                yTruck += 50;
            }
            if (yTruck >= 860) {
                yTruck = 0;
                score++;
            }
            repaint();
        });
        timer.start();
    }

    void keyboardControl() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (xMyCar != 400) {
                        xMyCar -= 10;
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (xMyCar != 900 - 70) {
                        xMyCar += 10;
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (yMyCar != 0) {
                        yMyCar -= 10;
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (yMyCar != 840-120) {
                        yMyCar += 10;
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {

                    if (collision >= 1) {
                        resetGame();
                    }
                }
                repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    boolean isColision(int xMyCar, int yMyCar, int xCar1, int yCar1, int xTruck, int yTruck) {
        if (((xMyCar == xCar1) && (yMyCar == yCar1 + 70)) || ((Math.abs(xMyCar - xCar1) <= 70) && (yMyCar == yCar1 + 70)) ||
                ((yMyCar == yCar1) && (Math.abs(xMyCar - xCar1) <= 70)) || ((Math.abs(yMyCar - yCar1) <= 70) && (Math.abs(xMyCar - xCar1) <= 70)) ||
                ((xMyCar == xTruck) && (yMyCar == yTruck + 70)) || ((Math.abs(xMyCar - xTruck) <= 70) && (yMyCar == yTruck + 70)) ||
                ((yMyCar == yTruck) && (Math.abs(xMyCar - xTruck) <= 70)) || ((Math.abs(yMyCar - yTruck) <= 70) && (Math.abs(xMyCar - xTruck) <= 70))) {
            collision++;
        }
        return collision >= 1;
    }

    void resetGame() {
        collision = 0;
        score = 0;
        xMyCar = xAxis + 210;
        yMyCar = 570+120;
        yCar1 = 0;
        yTruck = -70;
        repaint();
    }
}
