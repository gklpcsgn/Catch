import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Main {
    public static void main(String[] args) {
        new MyFrame();
    }
}
class MyFrame extends JFrame implements KeyListener
{
    long start;
    boolean opened = false;
    boolean end = true;

    RealSquare rs;
    BlueSquare bs;
    RedSquare reds;
    OrangeSquare os;
    GreenSquare gs;
    public MyFrame()
    {
        setSize(500, 538);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        add(new PixelPanels());  
        addKeyListener(this);

    }
    @Override
    public void keyPressed(KeyEvent arg0) {
    }
    @Override
    public void keyReleased(KeyEvent arg0) {    
       if(end){
            int key = arg0.getKeyCode();
            int x = rs.getX();
            int y = rs.getY();
            if (key == KeyEvent.VK_LEFT) {
                if(x > 0)
                rs.setLocation(x-10, y);
            }
        
            if (key == KeyEvent.VK_RIGHT) {
                if(x < 490)
                rs.setLocation(x+10, y);
            }
        
            if (key == KeyEvent.VK_UP) {
                if(y > 0)
                rs.setLocation(x, y-10);
            }
        
            if (key == KeyEvent.VK_DOWN) {
                if(y<490)
                rs.setLocation(x, y+10);
            }
    }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    class PixelPanels extends JPanel 
    {

        public PixelPanels()
        {
            setSize(10,10);
            setBackground(Color.white);
            setLayout(null);
            rs = new RealSquare();
            bs = new BlueSquare();
            reds = new RedSquare();
            os = new OrangeSquare();
            gs = new GreenSquare();
            add(rs);
            add(bs);
            add(reds);
            add(os);
            add(gs);
            rs.setLocation(240,240);
            bs.setLocation(490,0);
            reds.setLocation(0,0);
            os.setLocation(0,490);
            gs.setLocation(490,490);
            Thread bsThread = new Thread(){
                public void run()
                {
                    while(end)
                    {
                        try {
                            sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(bs.getX()-rs.getX() == 0)//go on y axis
                        {
                            bs.setLocation(bs.getX() , bs.getY() > rs.getY() ? bs.getY()-10 : bs.getY() + 10);
                        }
                        else
                        {
                            bs.setLocation(bs.getX() > rs.getX() ? bs.getX()-10 : bs.getX() + 10,bs.getY());
                        }
                        check();
                    }
                }
            };
            Thread osThread = new Thread(){
                public void run()
                {
                    while(end)
                    {
                        try {
                            sleep(1250);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(os.getX()-rs.getX() == 0)//go on y axis
                        {
                            os.setLocation(os.getX() , os.getY() > rs.getY() ? os.getY()-10 : os.getY() + 10);
                        }
                        else
                        {
                            os.setLocation(os.getX() > rs.getX() ? os.getX()-10 : os.getX() + 10,os.getY());
                        }
                        check();
                    }
                }
            };
            Thread gsThread = new Thread(){
                public void run()
                {
                    while(end)
                    {
                        try {
                            sleep(750);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(gs.getY()-rs.getY() == 0)//go on x axis
                        {
                            gs.setLocation(gs.getX() > rs.getX() ? gs.getX()-10 : gs.getX() + 10,gs.getY());

                        }
                        else
                        {
                            gs.setLocation(gs.getX() , gs.getY() > rs.getY() ? gs.getY()-10 : gs.getY() + 10);

                        }
                        check();
                    }
                }
            };
            Thread redsThread = new Thread(){
                public void run()
                {
                    
                    while(end)
                    {
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(reds.getY()-rs.getY() == 0)//go on x axis
                        {
                            reds.setLocation(reds.getX() > rs.getX() ? reds.getX()-10 : reds.getX() + 10,reds.getY());

                        }
                        else
                        {
                            reds.setLocation(reds.getX() , reds.getY() > rs.getY() ? reds.getY()-10 : reds.getY() + 10);

                        }
                        check();
                    }
                }
            };
            bsThread.start();
            osThread.start();
            redsThread.start();
            gsThread.start();
            start = System.currentTimeMillis();
            
        }
        public void check()
        {
            int x = rs.getX();
            int y = rs.getY();
            int bsx = bs.getX();
            int bsy = bs.getY();
            boolean b = x == bsx && y == bsy;
            if(b || (x == os.getX() && y == os.getY()) ||  
            (x == reds.getX() && y == reds.getY()) || (x == gs.getX() && y == gs.getY()))
            {
                end = false;
                if(!opened)
                {
                    new ScoreWindow(); 
                    opened= true;

                }
            }
        }
        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(Color.GRAY);
            for (int i = 0; i <= 500; i=i+10) {
                g.drawLine(0, i, 500, i);
            }
            for (int i = 0; i <= 500; i=i+10) {
                g.drawLine(i, 0, i, 500);
            }
        }
        
    } 
    class ScoreWindow extends JFrame{

        public ScoreWindow() {
            setSize(200, 200);
            setLayout(new BorderLayout());
            JLabel label = new JLabel("Skorunuz: " + (System.currentTimeMillis()-start)) ;
            add(label,BorderLayout.CENTER);  
            setVisible(true);
            
    }
}



class RealSquare extends JPanel
{
    public RealSquare(){
        setSize(10,10);
        setBackground(Color.black);
    }
}
class BlueSquare extends JPanel
{
    public BlueSquare(){
        setSize(10,10);
        setBackground(Color.BLUE);
    }
}
class GreenSquare extends JPanel
{
    public GreenSquare(){
        setSize(10,10);
        setBackground(Color.green);
    }
}
class OrangeSquare extends JPanel
{
    public OrangeSquare()
    {
        setSize(10,10);
        setBackground(Color.orange);
    }
}
class RedSquare extends JPanel
{
    public RedSquare(){
        setSize(10,10);
        setBackground(Color.red);
    }
}
}