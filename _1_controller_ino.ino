/*
  SNES Controller interface
  Based on NES Controller by Sebastian Tomczak
 
  Pinouts, clock times, communication protocol, etc...:
  http://www.gamefaqs.com/snes/916396-snes/faqs/5395
*/
 
// Controller buttons
// (based on button-to-clock pulse assignment)
#define SNES_B        32768  // 1000000000000000
#define SNES_Y        16384  // 0100000000000000
#define SNES_SELECT   8192   // 0010000000000000
#define SNES_START    4096   // 0001000000000000
#define SNES_UP       2048   // 0000100000000000
#define SNES_DOWN     1024   // 0000010000000000
#define SNES_LEFT      512   // 0000001000000000
#define SNES_RIGHT     256   // 0000000100000000
#define SNES_A         128   // 0000000010000000
#define SNES_X          64   // 0000000001000000
#define SNES_L          32   // 0000000000100000
#define SNES_R          16   // 0000000000010000
 
// Arduino pins vs. SNES controller pins
// (default is latch 2, clock 3, data 4)
//int LatchPin  = 7; // Latch
//int ClockPin  = 5; // Clock
//int DataPin   = 6; // Serial Data


int LatchPin  = 3; // Latch
int ClockPin  = 2; // Clock
int DataPin   = 4; // Serial Data
int numNs = 0;
int numNs1 = 0;

// Current controller data
unsigned int ControllerData = 0;
 
// Setup the controller and serial output
void setup() {
  Serial.begin(57600);
  pinMode(LatchPin,OUTPUT);
  pinMode(ClockPin,OUTPUT);
  pinMode(DataPin,INPUT);
 
  digitalWrite(LatchPin,HIGH);
  digitalWrite(ClockPin,HIGH);
  //int LatchPin  = 7; // Latch
//int ClockPin  = 5; // Clock
//int DataPin   = 6; // Serial Data
  
    LatchPin  = 7; // Latch
   ClockPin  = 5; // Clock
   DataPin   = 6; // Serial Data
  
  pinMode(LatchPin,OUTPUT);
  pinMode(ClockPin,OUTPUT);
  pinMode(DataPin,INPUT);
 
  digitalWrite(LatchPin,HIGH);
  digitalWrite(ClockPin,HIGH);
    Serial.print("nnn"); 

}
 
// Read controller
void controllerRead() {
  // Reset controller states and data
  ControllerData = 0;
  digitalWrite(LatchPin,LOW);
  digitalWrite(ClockPin,HIGH);
 
  // Controller needs to latch the state of all buttons
  digitalWrite(LatchPin,HIGH);
  delayMicroseconds(12);
  digitalWrite(LatchPin,LOW);
  delayMicroseconds(6);
 
  // Read controller data (initial reading)
  ControllerData = digitalRead(DataPin);
 
  // Send 16 clock pulses, one for each button. 
  for (int i = 0; i < 16; i ++) {
    
    digitalWrite(ClockPin,LOW);
    delayMicroseconds(6);
    ControllerData = ControllerData << 1;
    ControllerData = ControllerData + digitalRead(DataPin) ;
    delayMicroseconds(6);
    digitalWrite(ClockPin,HIGH);
  }

  // Do a NOT, so '1' will be pressed buttons and '0' to the rest
 ControllerData = ~ControllerData;

}
 
 




// Program code
void loop() {
  // Read controller data
   LatchPin  = 3; // Latch
   ClockPin  = 2; // Clock
   DataPin   = 4; // Serial Data
   numNs++;

    controllerRead();  
boolean o = ControllerData ==0;
boolean i = ControllerData ==0;
 
  // Print binary information
 // Serial.println(~Controller2Data, BIN);
 
  // Print user-friendly information
  if (ControllerData != 0) {
    
    numNs= 0;
    if (ControllerData & SNES_B) {
      Serial.print("B");
    }
    if (ControllerData & SNES_Y) {
      Serial.print("Y");
    }
    if (ControllerData & SNES_SELECT) {
      Serial.print("S");
    }
    if (ControllerData & SNES_START) {
      Serial.print("$");
    }
    if (ControllerData & SNES_UP) {
      Serial.print("u");
    }
    if (ControllerData & SNES_DOWN) {
      Serial.print("D");
    }
    if (ControllerData & SNES_LEFT) {
      Serial.print("L");
    }
    if (ControllerData & SNES_RIGHT) {
      Serial.print("R");
    }
    if (ControllerData & SNES_A) {
      Serial.print("a");
    }
    if (ControllerData & SNES_X) {
      Serial.print("X");
    }
    if (ControllerData & SNES_L) {
      Serial.print("l");
    }
    if (ControllerData & SNES_R) {
      Serial.print("r");
    }

  }
  



    LatchPin  = 7; // Latch
   ClockPin  = 5; // Clock
   DataPin   = 6; // Serial Data
  controllerRead();  
  o =  ControllerData == 0;
  // Print binary information
 // Serial.println(~ControllerData, BIN);
 
  // Print user-friendly information

  if (ControllerData != 0) {
    numNs= 0;
    if (ControllerData & SNES_B) {
      Serial.print("0");
    }
    if (ControllerData & SNES_Y) {
      Serial.print("1");
    }
    if (ControllerData & SNES_SELECT) {
      Serial.print("2");
    }
    if (ControllerData & SNES_START) {
      Serial.print("3");
    }
    if (ControllerData & SNES_UP) {
      Serial.print("4");
    }
    if (ControllerData & SNES_DOWN) {
      Serial.print("5");
    }
    if (ControllerData & SNES_LEFT) {
      Serial.print("6");
    }
    if (ControllerData & SNES_RIGHT) {
      Serial.print("7");
    }
    if (ControllerData & SNES_A) {
      Serial.print("8");
    }
    if (ControllerData & SNES_X) {
      Serial.print("9");
    }
    if (ControllerData & SNES_L) {
      Serial.print("!");
    }
    if (ControllerData & SNES_R) {
      Serial.print("@");
    }
    
  }else{

  }
  
  
  

 if(numNs == 3){
  Serial.print("nnn"); 
 }
 
 if(   !i || ControllerData != 0  ){
     Serial.println("");
 }
  delay(10);
}

