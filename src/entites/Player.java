package entites;

import static utilz.Constants.Direction.*;
import static utilz.Constants.PlayerConstants.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Player extends Entity{
	
	 private BufferedImage[][] animations;
	 private int aniTick, aniIndex, aniSpeed = 15;
	 private int playerAction = IDLE;
	 private int playerDir = -1;
	 private boolean moving = false;

	public Player(float x, float y) {
		super(x, y);
		loadAnimations();
		
	}
	
	public void update() {
        updateAnimationTick();
        
        setAnimation();
        updatePosition();
	}
	
	public void render(Graphics g) {
		g.drawImage(animations[playerAction][aniIndex], (int)x, (int)y, 128, 80, null); // each character is 64 x 40 px
	}
	
    private void updatePosition() {
		if(moving) {
			switch(playerDir) {
			case LEFT:
				x -= 1;
				break;
			case UP:
				y -= 1;
				break;
			case RIGHT:
				x += 1;
				break;
			case DOWN:
				y += 1;
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
	
    private void loadAnimations() {
    	
InputStream is = getClass().getResourceAsStream("/player_sprites.png");
		
		try {
			BufferedImage img = ImageIO.read(is);
			animations = new BufferedImage[9][6];
			
			for(int y = 0; y<animations.length; y++) {
				for(int x = 0; x < animations[y].length; x++) {
					animations[y][x] = img.getSubimage(x*64, y*40, 64, 40);
				}
			}
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

}
