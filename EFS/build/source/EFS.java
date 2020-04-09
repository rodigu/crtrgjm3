import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class EFS extends PApplet {


public void setup(){
  
  surface.setTitle("Endless Falling Simulator");
  surface.setResizable(true);
}

public void draw(){
  //if (mousePressed)
    //surface.setSize(mouseX + 10, mouseY + 10);
}
  public void settings() {  size(300, 700); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "EFS" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
