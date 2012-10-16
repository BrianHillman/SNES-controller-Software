import java.awt.EventQueue;
import java.awt.Graphics;
import java.io.IOException;

import jssc.SerialPortException;


public class main {

	/**
	 * @param args
	 */
	static mainWindow window;
	static controllerMGR controller;

	public static void main(String[] args) throws IOException, SerialPortException, InterruptedException {
		
		window = new mainWindow();
		window.frame.setVisible(true);
		

	
		connectController();
		window.textField.setText("connected!");
		while(true){
				controller.calc();

				// TODO Auto-generated catch block
	
			
		}
		
	}
	
	
	public static void connectController(){
		while(true){
			try{
				window.textField.setText("connecting....");
				controller = new controllerMGR();
				
				break;
				
			}catch(Exception e){
				continue;
				
			}
		}
	}
	
}


