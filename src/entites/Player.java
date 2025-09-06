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
	 private boolean moving = false, attacking = false;
	 private boolean left, up, right, down;
	 private float playerSpeed = 2.0f;

	public Player(float x, float y) {
		super(x, y);
		loadAnimations();
		
	}
	
	public void update() {
		
        updateAnimationTick();
        updatePosition();
        setAnimation();
        
	}
	
	public void render(Graphics g) {
		g.drawImage(animations[playerAction][aniIndex], (int)x, (int)y, 128, 80, null); // each character is 64 x 40 px
	}
	
    private void updatePosition() {
    	
    	moving = false;
    	
    	if(left && !right) {
    		x -= playerSpeed;
    		moving = true;
    	}
    	else if( right && !left) {
    		x += playerSpeed;
    		moving = true;
    	}
    	
    	if(up && !down) {
    		y -= playerSpeed;
    		moving = true;
    	}
    	else if(down && !up) {
    		y += playerSpeed;
    		moving = true;
    	}
		
	}

	private void setAnimation() {
		
		int startAni = playerAction;
		
		if(moving) {
			playerAction = RUNNING;
		}
		else {
			playerAction = IDLE;
		}
		if(attacking) {
			playerAction = ATTACK_1;
		}	
		if(startAni != playerAction) {
			resetAniTick();
		}
		
	}


	private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;
		
	}

	private void updateAnimationTick() {
		aniTick++;
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if(aniIndex >= GetSpriteAmount(playerAction)) {
				aniIndex = 0;
				attacking = false;
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
    
    public void resetDirBooleans() {
    	left = false;
    	up = false;
    	right = false;
    	down = false;
    }
    
    public void setAttacking(boolean attacking) {
    	this.attacking = attacking;
    }

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}
    
    

}
