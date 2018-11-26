#include <Servo.h>
#include <Keypad.h>

//Variables
int mot_min = 0;   //min servo angle  (set yours)
int mot_max = 90; //Max servo angle   (set yours)
int character = 0;
int activated =0;

char primerCaracter;
char segundoCaracter;
char tercerCaracter;
char cuartoCaracter;
char quintoCaracter;

char datoRecibidoBT;

int contador = 0;

char Str[16] = {' ', ' ', ' ', ' ', ' ', ' ', '-', '*', '*', '*', ' ', ' ', ' ', ' ', ' ', ' '};  


volatile boolean sw1 = false;
volatile boolean sw2 = false;


uint8_t sw1ButtonState = 0;
uint8_t sw2ButtonState = 0;

uint8_t lastsw1ButtonState = 0;
uint8_t lastsw2ButtonState = 0;

//Pins
Servo myservo;
int sw1_pin = 12; //pin to inside open
int sw2_pin = 13; //pin to inside close

//Keypad config
const byte ROWS = 4; //four rows
const byte COLS = 4; //four columns
//define the cymbols on the buttons of the keypads
char hexaKeys[ROWS][COLS] = {
  {'1','4','7','*'},
  {'2','5','8','0'},
  {'3','6','9','#'},
  {'A','B','C','D'}
};
byte rowPins[COLS] = {5, 4, 3, 2}; //connect to the row pinouts of the keypad
byte colPins[ROWS] = {9, 8, 7, 6}; //connect to the column pinouts of the keypad

//initialize an instance of class NewKeypad
Keypad customKeypad = Keypad( makeKeymap(hexaKeys), rowPins, colPins, ROWS, COLS); 

void setup(){
  Serial.begin(9600);
  myservo.attach(10); //attach the servo to pin D10
  pinMode(sw1_pin,INPUT_PULLUP);
  pinMode(sw2_pin,INPUT_PULLUP); 
  //put the servo in the close position first
  myservo.write(mot_min);  
  
}
  
void loop(){
///////////////EMERGENCY OPEN/CLOSE/////////
     
     checkIfSw1ButtonIsPressed();
     checkIfSw2ButtonIsPressed();

     if (Serial.available() > 0) {
      datoRecibidoBT = Serial.read();
      if (datoRecibidoBT == '0') {
        myservo.write(mot_max);
      }
      if (datoRecibidoBT == '1') {
        myservo.write(mot_min);
      }
     }
     
     if( sw1){
      Serial.println("sw1");
       sw1 = false;
       myservo.write(0);
       delay(15);
     }
     else if( sw2){
      Serial.println("sw2");
       sw2 = false;
       myservo.write(90);
       delay(15);
     }
    
///////////////KEYPAD OPEN/CLOSE////////////  
  char customKey = customKeypad.getKey(); //this function reads the presed key

  if(customKey){          // se hace con custom key para que solo actualice los valores cuando presiono una tecla y no en cada iteracion
      primerCaracter = segundoCaracter;
   segundoCaracter = tercerCaracter;
   tercerCaracter = cuartoCaracter;
    cuartoCaracter = quintoCaracter;
    quintoCaracter = customKey; 
  }


     if(quintoCaracter == 'A' && cuartoCaracter == '4' && tercerCaracter == '3' && segundoCaracter == '2' && primerCaracter == '1' ){
      myservo.write(90);
      quintoCaracter = ' '; // Esto es para que en la proxima iteración no quede trabada como una clave valida
     }
  



  
  if(customKey == 'C'){
    myservo.write(0);
  }
  
  
  if (customKey){

    if (character ==0)
    {  
    Serial.println(customKey);
    Str[6]= customKey;
   
    }

    if (character ==1)
    {  
    Serial.println(customKey);
    Str[7]= customKey;
   
    }

    if (character ==2)
    {  
    Serial.println(customKey);
    Str[8]= customKey; 
   
    }

    if (character ==3)
    {  
    Serial.println(customKey);
    Str[9]= customKey;
   
    }

    if (character ==4)
    {  
    Serial.println(customKey);
    Str[10]= customKey;
    activated=1;
   
    }
    character = character + 1;
  }

  if (activated == 1)
    {
/*Change your password below!!! 
Change each of Str[6], Str[7], Str[8], Str[9]*/

    if(Str[10]='1' && character==5 && Str[6]=='1' && Str[7]=='1' && Str[8]=='1' && Str[9]=='1' )
    {
      myservo.write(mot_max);
      activated = 2;
      
    }
    else
    {
      character=0;
      Str[6]= '-';
      Str[7]= '*'; 
      Str[8]= '*'; 
      Str[9]= '*';
      Str[10]= ' ';
      activated = 0;
    }
  }
  if (activated == 2)
    {
    if(customKey == 'B' )
    {
      myservo.write(mot_min);
      activated = 0;
      character=0;  
     
    }
  }  
}


void checkIfSw1ButtonIsPressed()
{
    sw1ButtonState   = digitalRead(sw1_pin);
  
    if (sw1ButtonState != lastsw1ButtonState)
  {
    if ( sw1ButtonState == 0)
    {
      sw1=true;
    }
    delay(50);
  }
   lastsw1ButtonState = sw1ButtonState;
 }

void checkIfSw2ButtonIsPressed()
{
    sw2ButtonState   = digitalRead(sw2_pin);
  
    if (sw2ButtonState != lastsw2ButtonState)
  {
    if ( sw2ButtonState == 0)
    {
      sw2=true;
    }
    delay(50);
  }
   lastsw2ButtonState = sw2ButtonState;
 }
