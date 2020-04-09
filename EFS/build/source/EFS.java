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

Player p1;
float ref = 10;

public void setup(){
  
  
  //position (2), speed (2), acceleration (1), top speed (1), size (1)
  p1 = new Player(width/2, 2*ref, 0, 0, ref, ref*2, ref);
  surface.setTitle("Endless Falling Simulator");
  surface.setResizable(true);
}

public void draw(){
  background(20);
  p1.update();
  p1.display();
}

public void keyPressed(){
  if (key == 'w')
    p1.cntrl[0] = 1;
  if (key == 'a')
    p1.cntrl[1] = 1;
  if (key == 'd')
    p1.cntrl[2] = 1;
  if (key == 's')
    p1.cntrl[3] = 1;
}
public void keyReleased(){
  if (key == 'w')
    p1.cntrl[0] = 0;
  if (key == 'a')
    p1.cntrl[1] = 0;
  if (key == 'd')
    p1.cntrl[2] = 0;
  if (key == 's')
    p1.cntrl[3] = 0;
}

class Player{
  float x, y;
  float xspeed, yspeed, accel, top_speed;
  float player_size;
  int[] cntrl = {0, 0, 0, 0};
  /*
    0
  1   2
    3
  */
  Player (float temp_x, float temp_y, float temp_xspeed, float temp_yspeed,
          float temp_accel, float temp_top, float temp_size){
    xspeed = temp_xspeed;
    yspeed = temp_yspeed;
    accel = temp_accel;
    x = temp_x;
    y = temp_y;
    player_size = temp_size;
  }

  public void display(){
    fill(230);
    rect(x - player_size/2, y - player_size/2, player_size, 2*player_size);
  }

  public void update(){
    if (cntrl[0] == 1 && yspeed >= -top_speed)
      yspeed -= accel;
    else if (yspeed < 0) yspeed ++;
    if (cntrl[3] == 1 && yspeed <= top_speed)
      yspeed += accel;
    else if (yspeed > 0) yspeed --;

    if (cntrl[1] == 1 && xspeed >= -top_speed)
      xspeed -= accel;
    else if (xspeed < 0) xspeed ++;
    if (cntrl[2] == 1 && xspeed <= top_speed)
      xspeed += accel;
    else if (xspeed > 0) xspeed --;
    
    x += xspeed;
    y += yspeed;
  }
}
class Sprites{
  PImage[] sprt_img;
  int qtt;
  Sprites(String file_name, int temp_qtt){
    qtt = temp_qtt;
    sprt_img = new PImage[qtt];
    for (int i = 0; i < qtt; i++){
      sprt_img[i] = loadImage(file_name + "_" + i + ".png");
    }
  }
}
  public void settings() {  size(400, 600);  noSmooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "EFS" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
