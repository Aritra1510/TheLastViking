package main;
import javax.swing.*;

import inputs.KeyBoardInputs;
import inputs.MouseInputs;

import java.awt.*;
//import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel{

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private float xDir = 1f, yDir = 1f;
    private Color color = new Color(100,124,255);
    private Random random;

    public GamePanel(){
    	random = new Random();
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void changeXDelta(int value){
        this.xDelta += value;
        //repaint();
    }

        public void changeYDelta(int value){
        this.yDelta += value;
        //repaint();
    }
        
    public void setRectPos(int x, int y) {
    	this.xDelta = x;
    	this.yDelta = y;
    	//repaint();
    }

    @Override
    
    public void paintComponent(Graphics g){
    	
        super.paintComponent(g);
        
        updateRectangle();
        g.setColor(color);
        g.fillRect((int) xDelta, (int) yDelta, 200, 50);

    }

	private void updateRectangle() {
		xDelta += xDir;
		if(xDelta > 400 || xDelta < 0) {
			xDir *= -1;
			color = getRandomColor();
		}
		yDelta += yDir;
		if(yDelta > 400 || yDelta < 0) {
			yDir *= -1;
			color = getRandomColor();
		}
		
	}

	private Color getRandomColor() {
		int r = random.nextInt(255);
		int g = random.nextInt(255);
		int b = random.nextInt(255);
		
		return new Color(r, g, b);
	}
}
