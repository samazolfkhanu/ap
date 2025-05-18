package ap.exercises.ex2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Scanner;

public class Main_EX2_PM_3_2 extends JFrame implements KeyListener
{
    Point pacmanPoint = new Point();
    private int k;
    int width, height, boxSize = 5;
    static int direction = 1;
    final int LEFT = 1, RIGHT = 2, TOP = 3, BOTTOM = 4;
    Point dotPoint = new Point();
    int score=0;
    int maxScore;
    int maxTime;
    long start;

    public Main_EX2_PM_3_2 ()
    {
        setK();
        setMax();
        addKeyListener(this);
        pacmanPoint.setLocation((width / boxSize) / 2, (height / boxSize) / 2);
        getNewDotPointLocation();
        setSize(width, height);
        start=System.currentTimeMillis();
        JOptionPane.showMessageDialog(this,"choose to move:\n1.w=Up\n2.d=Right\n3.s=Down\n4.a=Left\n5.q=quit from game\n");
    }

    public void setK()
    {
        Scanner s=new Scanner(System.in);
        System.out.println("enter the side of board:");
        k=s.nextInt();
        width =k;
        height =k;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g.clearRect(0, 0, width, height);
        logic();
        drawPacman(g2D);
        drawDotPoint(g2D);
        drawScore(g2D);
        setVisible(true);
    }

    private void drawPacman(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        g2d.fillRect(pacmanPoint.x * boxSize, pacmanPoint.y * boxSize, boxSize, boxSize);
    }

    private void drawDotPoint(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.fillRect(dotPoint.x * boxSize, dotPoint.y * boxSize, boxSize, boxSize);
    }

    private void drawScore(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        String s = "Score: " +score;
        g2d.drawString(s, 25, 50);
    }

    private void logic()
    {
        long f=(System.currentTimeMillis()-start)/1000;
        if(f>maxTime)
        {
            System.out.println("game time is over!");
            JOptionPane.showMessageDialog(this,"game time is over!");
            System.exit(0);
        }
        if(dotPoint.x==pacmanPoint.x && dotPoint.y==pacmanPoint.y)
        {
            score++;
            if(score>=maxScore)
            {
                System.out.println("congratulation\nyou got all scores!");
                JOptionPane.showMessageDialog(this,"congratulation\nyou got all foods!\n Score: "+score);
                System.exit(0);
            }
            getNewDotPointLocation();
            System.out.println("Score: "+score);
            repaint();
        }
        movePacman();
    }

    private void movePacman() {
        int xMovement = 1;
        int yMovement = 0;
        switch (direction) {
            case LEFT:
                xMovement = -1;
                yMovement = 0;
                break;
            case RIGHT:
                xMovement = 1;
                yMovement = 0;
                break;
            case TOP:
                xMovement = 0;
                yMovement = -1;
                break;
            case BOTTOM:
                xMovement = 0;
                yMovement = 1;
                break;
            default:
                xMovement = yMovement = 0;
                break;
        }
        pacmanPoint.setLocation(pacmanPoint.x + xMovement, pacmanPoint.y + yMovement);
        handleCrossBorder();
    }

    private void getNewDotPointLocation() {
        Random rand = new Random();
        int delta = boxSize * 2;
        dotPoint.setLocation(rand.nextInt(width / boxSize - 2 * delta) + delta, rand.nextInt(height / boxSize - 2 * delta) + delta);
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W)
            direction = 3;
        else if (e.getKeyCode() == KeyEvent.VK_S)
            direction = 4;
        else if (e.getKeyCode() == KeyEvent.VK_A)
            direction = 1;
        else if (e.getKeyCode() == KeyEvent.VK_D)
            direction = 2;
        else if (e.getKeyCode() == KeyEvent.VK_P)
            direction = 0;
        else if (e.getKeyCode()==KeyEvent.VK_Q)
        {
            System.out.println("exiting the game!");
            System.exit(0);
        }
        else
            direction = -1;

        System.out.println("direction:" + direction + "    <- e.getKeyCode()=" + e.getKeyCode());

        repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void handleCrossBorder()
    {
        if(pacmanPoint.x<0)
        {
            pacmanPoint.x=(width/boxSize)-1;
        }
        else if(pacmanPoint.x>=(width/boxSize))
        {
            pacmanPoint.x=0;
        }
        if(pacmanPoint.y<0)
        {
            pacmanPoint.y=(height/boxSize)-1;
        }
        else if(pacmanPoint.y>=(height/boxSize))
        {
            pacmanPoint.y=0;
        }
    }

    public void setMax()
    {
        Scanner s=new Scanner(System.in);
        System.out.print("enter maximum number of dots:");
        maxScore=s.nextInt();
        System.out.print("enter maximum time of game:");
        maxTime=s.nextInt();
    }

    public static void main(String[] args) {
        Main_EX2_PM_3_2 frame = new Main_EX2_PM_3_2();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

