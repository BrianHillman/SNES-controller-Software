import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.TextField;
import java.awt.BorderLayout;
import javax.swing.JLayeredPane;
import java.awt.Button;
import javax.swing.JSeparator;
import java.awt.Checkbox;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;


public class mainWindow {
	TextField textField;
	JFrame frame;
	controllerUI control1;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public mainWindow() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void changeText(String newText){
		textField.setText(newText);
		return;
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		Font font1 = new Font("SansSerif", Font.BOLD, 20);

		
		textField = new TextField();
		textField.setFont(font1);

		textField.setBounds(0, 10, 434, 252);
		textField.setText("Not connected");
		textField.setEditable(false);
		frame.getContentPane().add(textField);
		

		
		
		
	}
}
