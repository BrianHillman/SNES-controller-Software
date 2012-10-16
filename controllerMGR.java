import jssc.SerialPortList;
import jssc.SerialPort;
import jssc.SerialPortException;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;



public class controllerMGR {
	   String[] keys =  {"u","D","R","L","l","r","S","$","Y","X","a","B","0","1","2","3","4","5","6","7","8","9","!","@"};
       boolean[] keyStates = new boolean[24];
       
       //standard keys
      // int[] keyCodes = {KeyEvent.VK_UP,KeyEvent.VK_DOWN,KeyEvent.VK_RIGHT,KeyEvent.VK_LEFT,KeyEvent.VK_L,KeyEvent.VK_R,KeyEvent.VK_S,KeyEvent.VK_T,KeyEvent.VK_Y,KeyEvent.VK_X,KeyEvent.VK_A,KeyEvent.VK_B,
      // 		   KeyEvent.VK_0,KeyEvent.VK_1,KeyEvent.VK_2,KeyEvent.VK_3,KeyEvent.VK_4,KeyEvent.VK_5,KeyEvent.VK_6,KeyEvent.VK_7,KeyEvent.VK_8,KeyEvent.VK_9,KeyEvent.VK_Q,KeyEvent.VK_W,	};
       int[] keyCodes = {KeyEvent.VK_W,KeyEvent.VK_S,KeyEvent.VK_D,KeyEvent.VK_A,KeyEvent.VK_LEFT,KeyEvent.VK_ESCAPE,KeyEvent.VK_SPACE,KeyEvent.VK_Q,KeyEvent.VK_LEFT,KeyEvent.VK_UP,KeyEvent.VK_RIGHT,KeyEvent.VK_DOWN,
       		   KeyEvent.VK_0,KeyEvent.VK_1,KeyEvent.VK_2,KeyEvent.VK_3,KeyEvent.VK_4,KeyEvent.VK_5,KeyEvent.VK_6,KeyEvent.VK_7,KeyEvent.VK_8,KeyEvent.VK_9,KeyEvent.VK_Q,KeyEvent.VK_W,	};
          
       boolean[] keyStatesBuffer = new boolean[24];
       String last5Keypresses = "  123     ";
       String code = "uuDDLRLRBa";
       boolean mouseMode = false;
       int mouseX = 100;
       int mouseY = 100;
		boolean B_pressed = false;
		int bBuffer = 0;
		int aBuffer = 0;
		boolean a_pressed = false;
		boolean keyboardOn = false;
	    ProcessBuilder pb = null;
	    Robot kb = new Robot();
	
	    SerialPort port = null;

    public controllerMGR() throws AWTException, IOException, InterruptedException, SerialPortException {
        //Method getPortNames() returns an array of strings. Elements of the array is already sorted.
    	
        String[] portNames = SerialPortList.getPortNames();

        for(int i = 0; i < portNames.length; i++){
            System.out.println(portNames[i]);
        }
        /*CONTROLLER 1 arduino values
         * keys:
         * left-pad
         * uDRL 
         * 
         * triggers:
         * lr
         * 
         * select:
         * S
         * start:
         * $
         * 
         * right-pad:
         * YXaB
         */
     
	
            port = new SerialPort("COM3");
            port.openPort();
            port.setParams(SerialPort.BAUDRATE_57600, 
                                 SerialPort.DATABITS_8,
                                 SerialPort.STOPBITS_1,
                                 SerialPort.PARITY_NONE);
            //port.writeBytes("Test");
            
            
       
        
    }
    public void calc() throws IOException, SerialPortException, InterruptedException{
            while(true){
            	if(port.getInputBufferBytesCount() < 1){
            		Thread.sleep(10);
            		continue;
            	}

            	
            	if( port.getInputBufferBytesCount() > 0 && !mouseMode){
               
		            String buffer = port.readString();
		            //System.out.println(buffer);
		            if(buffer.contains("n")){
		            	for(int x = 0; x < 24 ; x++){

		            		if(keyStates[x]){
		            			kb.keyRelease(keyCodes[x]);
		            			keyStates[x] = false;
		            		}
		            		
		            	}
		            	
		            }
		            for(int x = 0; x < 24 ; x++){
		            	if(keyStates[x] && buffer.contains(keys[x])){
		            		keyStatesBuffer[x] = false;
		            		continue;
		            	}
		            	if(keyStates[x] && keyStatesBuffer[x] && !buffer.contains(keys[x])){
		            		kb.keyRelease(keyCodes[x]);
		            		keyStatesBuffer[x] = false;
		            		keyStates[x] = false;

		            		continue;
		            	}
		            	if(keyStates[x] && !keyStatesBuffer[x] && !buffer.contains(keys[x])){
		            		keyStatesBuffer[x] = true;
		            		
		            		continue;
		            		
		            		
		            	}
		            	if(!keyStates[x] && buffer.contains(keys[x])){
		            		//System.out.print(keys[x]);
		            	//	System.out.println(last5Keypresses);
	            			keyStates[x] = true; 
		            		kb.keyPress(keyCodes[x]);
		            		keyStatesBuffer[x] = true;
		            		continue;
		            	}
		            	
		            }
		            
		            	
		            
            	}
            	
          
            	
            }
        }
}



 