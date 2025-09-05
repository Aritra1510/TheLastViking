package main;
import javax.imageio.ImageIO;
//import javax.swing.*;
import javax.swing.JPanel;

import inputs.KeyBoardInputs;
import inputs.MouseInputs;

import java.awt.Dimension;
import java.awt.Graphics;
//import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
//import java.awt.event.KeyListener;
import java.io.InputStream;
import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Direction.*;

public class GamePanel extends JPanel{

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = IDLE;
    private int playerDir = -1;
    private boolean moving = false;
    
    public GamePanel(){
        mouseInputs = new MouseInputs(this);
        
        importImg();
        loadAnimations();
        
        setPanelSize();
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void loadAnimations() {
		animations = new BufferedImage[9][6];
		
		for(int y = 0; y<animations.length; y++) {
			for(int x = 0; x < animations[y].length; x++) {
				animations[y][x] = img.getSubimage(x*64, y*40, 64, 40);
			}
		}
	}

	private void importImg() {
		InputStream is = getClass().getResourceAsStream("/player_sprites.png");
		
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch(IOException e){
				e.printStackTrace();
			}
		}
	}

	private void setPanelSize() {
    	Dimension size = new Dimension(880, 500); // change it to 1280, 800 later
		setPreferredSize(size);
		
	}
	
	public void updateGame() {
        updateAnimationTick();
        
        setAnimation();
        updatePosition();
	}

    @Override  
    public void paintComponent(Graphics g){
    	
        super.paintComponent(g);
       
        g.drawImage(animations[playerAction][aniIndex], (int)xDelta, (int)yDelta, 128, 80, null); // each character is 64 x 40 px

    }
    
    private void updatePosition() {
		if(moving) {
			switch(playerDir) {
			case LEFT:
				xDelta -= 5;
				break;
			case UP:
				yDelta -= 5;
				break;
			case RIGHT:
				xDelta += 5;
				break;
			case DOWN:
				yDelta += 5;
				break;
			}
		}
		
	}

	private void setAnimation() {
		if(moving) {
			playerAction = RUNNING;
		}
		else {
			playerAction = IDLE;
		}
		
	}

	public void setDirection(int direction) {
    	this.playerDir = direction;
    	moving = true;
    }
    
    public void setMoving(boolean moving) {
    	this.moving = moving;
    }

	private void updateAnimationTick() {
		aniTick++;
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if(aniIndex >= GetSpriteAmount(playerAction)) {
				aniIndex = 0;
			}
		}
		
	}
    
}
