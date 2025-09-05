package main;
import javax.swing.JPanel;

import inputs.KeyBoardInputs;
import inputs.MouseInputs;

import java.awt.Dimension;
import java.awt.Graphics;

public class GamePanel extends JPanel{

    private MouseInputs mouseInputs;
    private Game game;

    
    public GamePanel(Game game){
        mouseInputs = new MouseInputs(this);
        this.game = game;
        
        setPanelSize();
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

	private void setPanelSize() {
    	Dimension size = new Dimension(880, 500); // change it to 1280, 800 later
		setPreferredSize(size);
		
	}
	
	public void updateGame() {

	}

    @Override  
    public void paintComponent(Graphics g){
    	
        super.paintComponent(g);
        
        game.render(g);
       
    }
    
    public Game getGame() {
    	return game;
    }
       
}
