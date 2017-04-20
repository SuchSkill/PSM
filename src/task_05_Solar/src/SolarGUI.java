package task_05_Solar.src;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class SolarGUI extends JPanel implements Runnable, MouseMotionListener, MouseListener, KeyListener {
    int offX=400, offY=400;
    int oldOffX=400, oldOffY=400;
    double scale=10;
    int fstClickX, fstClickY;
    SolarSystem s;
    public SolarGUI(){
        s = new SolarSystem();
        setFocusable(true);
        requestFocus();
        addMouseMotionListener(this);
        addMouseListener(this);
        addKeyListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int i=0;
        int diameter=10;
//		double centraY = s.lista.get(0).y;
//		double centraX = s.lista.get(0).x;
        for(Planet p : s.lista){
            if(i!=0) diameter=5;
            int x = (int)(offX-diameter/2+p.x*scale);
            int y = (int)(offY-diameter/2+p.y*scale);
            g.fillOval(x, y,diameter,diameter);
            g.drawString(p.name, x, y);
            i++;
            for(int k=1; k<p.positions.size(); k++){
                Point pt0 = p.positions.get(k-1);
                Point pt1 = p.positions.get(k);
                g.drawLine((int)(offX+pt0.x*scale), (int)(offY+pt0.y*scale), (int)(offX+pt1.x*scale), (int)(offY+pt1.y*scale));
            }

        }


    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(true){
            s.timestep+=0.005f;
            s.updatePositions();
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        JFrame f = new JFrame("Sistema solare");
        f.setBounds(0, 0, 800, 800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SolarGUI p = new SolarGUI();
        f.add(p);
        new Thread(p).start();
        f.setVisible(true);

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        offX=oldOffX+e.getX()-fstClickX;
        offY=oldOffY+e.getY()-fstClickY;
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        fstClickX=e.getX();
        fstClickY=e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        oldOffX=offX;
        oldOffY=offY;
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar()=='q') scale+=0.5;
        if(e.getKeyChar()=='a') scale-=0.5;
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
