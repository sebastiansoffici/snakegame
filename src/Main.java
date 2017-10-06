/* Sebastian Soffici
 * COP3252 
 * Game Project
 */
import java.awt.Color;

import javax.swing.JFrame;



public class Main 
{
	public static void main(String[] args)
	{
		JFrame jframe = new JFrame("Snake");
		Play play = new Play();
	
		jframe.setLocationRelativeTo(null);
		jframe.setVisible(true);
		jframe.setBounds(10, 10, 905, 700);
		jframe.setBackground(Color.DARK_GRAY);
		jframe.setResizable(false);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(play);
		jframe.setLocationRelativeTo(null);
		
	}

}
