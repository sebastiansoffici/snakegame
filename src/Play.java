/* Sebastian Soffici
 * COP3252 
 * Game Project
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Play extends JPanel implements KeyListener, ActionListener
{
	private int[] xlength = new int[750];
	private int[] ylength = new int[750];
	private int moves = 0;
	
	private boolean left = false, right = false, up = false, down = false, isPlaying = true, pause = false;
	
	private ImageIcon rspear, lspear, uspear, dspear, body, title, gator;
	private int lengthsnake = 3;
	private Timer timer;
	private int delay = 100, score = 0;
	private Random random = new Random();
	
	//fruit arrays
	private int [] xgator = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
	private int [] ygator = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};
	
	//total number of x positions randomized
	private int xpos = random.nextInt(34);
		
	//total number of y position randomized
	private int ypos = random.nextInt(23);
	
	public Play()
	{
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer (delay, this);
		timer.start();
		
	}
	
	public void paint(Graphics g)
	{
		
		//if game hasn't started, initialize snake in left corner
		if (moves == 0)
		{
			xlength[2] = 50;
			xlength[1] = 75;
			xlength[0] = 100;
			
			ylength[2] = 100;
			ylength[1] = 100;
			ylength[0] = 100;
			
		}
		
		
		//draw border
		g.setColor(Color.WHITE);
		g.drawRect(24, 10, 851, 55);
		
		//draw title
		title = new ImageIcon(getClass().getResource("title.jpg"));
		title.paintIcon(this, g, 25, 11);
		
		//draw border for game board
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 851, 577);
		
		//draw background, height width are multiples of 25 because of asset size
		g.setColor(Color.BLACK);
		g.fillRect(25, 75, 850, 575);
		
		//draw score
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Score: " + score, 750 , 30);
		
		//draw length
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Snake Length: " + lengthsnake, 750 , 50);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Pause: P", 50 , 50);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Quit: X", 50 , 30);
		
		
		
		for (int i = 0; i < lengthsnake; i++)
		{
			if (i == 0 && right)
			{
				rspear = new ImageIcon(getClass().getResource("rightspear.png"));
				rspear.paintIcon(this, g, xlength[i], ylength[i]);
			}
			if (i == 0 && left)
			{
				lspear = new ImageIcon(getClass().getResource("leftspear.png"));
				lspear.paintIcon(this, g, xlength[i], ylength[i]);
			}
			if (i == 0 && down)
			{
				dspear = new ImageIcon(getClass().getResource("downspear.png"));
				dspear.paintIcon(this, g, xlength[i], ylength[i]);
			}
			if (i == 0 && up)
			{
				uspear = new ImageIcon(getClass().getResource("upspear.png"));
				uspear.paintIcon(this, g, xlength[i], ylength[i]);
				
			}
			if (i != 0)
			{
				body = new ImageIcon(getClass().getResource("snekbody.png"));
				body.paintIcon(this, g, xlength[i], ylength[i]);
			}
			
		}
			
		//draw fruit
		gator = new ImageIcon(getClass().getResource("enemy copy.png"));
		gator.paintIcon(this, g, xgator[xpos], ygator[ypos]);
		//detect if snake hits fruit
		if ((xgator[xpos] == xlength[0]) && (ygator[ypos] == ylength[0]))
		{
			score++;
			lengthsnake++;
			xpos = random.nextInt(34);
			ypos = random.nextInt(23);
		}
		
		//if snake hits itself
		for (int i = 1; i < lengthsnake; i++)
			if (xlength[i] == xlength[0] && ylength[i] == ylength[0])
			{
				right = false;
				left = false;
				up = false;
				down = false;
				isPlaying = false;
				g.setColor((Color.WHITE));
				g.setFont(new Font("arial", Font.BOLD, 50));
				g.drawString("Game Over", 300, 300);
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("Hit Space to Restart", 350, 340);
			}
		
		g.dispose();
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//movement functions
		
		if (isPlaying && pause == false)
		{
		timer.start();
		
		if (right)
		{
			for (int i = lengthsnake - 1; i >= 0; i--)
				ylength[i+1] = ylength[i];
				
			for (int i = lengthsnake; i >= 0; i--)
			{
				if (i == 0)
					xlength[i] = xlength[i]+25;
				else
					xlength[i] = xlength[i-1];
				
				if (xlength[i] > 850)
					xlength[i] = 25;
			}
			
			repaint();
		}
		
		if (left)
		{
			for (int i = lengthsnake - 1; i >= 0; i--)
				ylength[i+1] = ylength[i];
				
			for (int i = lengthsnake; i >= 0; i--)
			{
				if (i == 0)
					xlength[i] = xlength[i]-25;
				else
					xlength[i] = xlength[i-1];
				
				if (xlength[i] < 25)
					xlength[i] = 850;
			}
			
			repaint();
			
		}
		
		if (up)
		{
			for (int i = lengthsnake - 1; i >= 0; i--)
				xlength[i+1] = xlength[i];
				
			for (int i = lengthsnake; i >= 0; i--)
			{
				if (i == 0)
					ylength[i] = ylength[i]-25;
				else
					ylength[i] = ylength[i-1];
				
				if (ylength[i] < 75)
					ylength[i] = 625;
			}
			
			repaint();
			
		}
		
		if (down)
		{
			for (int i = lengthsnake - 1; i >= 0; i--)
				xlength[i+1] = xlength[i];
				
			for (int i = lengthsnake; i >= 0; i--)
			{
				if (i == 0)
					ylength[i] = ylength[i]+25;
				else
					ylength[i] = ylength[i-1];
				
				if (ylength[i] > 625)
					ylength[i] = 75;
			}
			
			repaint();
		}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
	
		//restart game
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			if (!isPlaying)
			{
				moves = 0;
				score = 0;
				lengthsnake = 3;
				isPlaying = true;
				repaint();
			}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			moves++;
			right = true;
			
			//this loop assures user doesnt go left after immediately going right 
			if (!left)
				right = true;
			else
			{
				right = false;
				left = true;
			}
			
			up = false;
			down = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			moves++;
			left = true;
			
			//this loop assures user doesnt go right after immediately going left
			if (!right)
				left = true;
			else
			{
				left = false;
				right = true;
			}
			
			up = false;
			down = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			moves++;
			up = true;
			
			//this loop assures user doesnt go down after immediately going up
			if (!down)
				up = true;
			else
			{
				up = false;
				down = true;
			}
			
			left = false;
			right = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			moves++;
			down = true;
			
			//this loop assures user doesnt go down after immediately going up
			if (!up)
				down = true;
			else
			{
				down = false;
				up = true;
			}
			
			left = false;
			right = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_P) 
		    pause = !pause;
		
		if(e.getKeyCode() == KeyEvent.VK_X) 
		    System.exit(0);
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

}
