import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;
import java.util.Random;


public class controllerUI extends Panel {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paint(Graphics g) { 
		System.out.println("11");
			Color r = new Color(255,0,0);//red
			Color b = new Color(0,0,255);//blue
		  	Random random = new Random();
		  	if(random.nextBoolean()){
		  		g.setColor(r);
		  	}else{
		  		g.setColor(b);
		  	}
		    
		    g.fillOval((int)controllerUI.LEFT_ALIGNMENT+200 , (int)controllerUI.TOP_ALIGNMENT+75, 10, 10); 
	  } 
	public void repaint(Graphics g) { 
		paint(g);
	}
}


