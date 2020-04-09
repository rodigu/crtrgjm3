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
int ref = 10, pixel_size = 5;
Back back;

public void setup(){
  
  
  back = new Back(0);
  //position (2), speed (2)
  p1 = new Player(width/2, 2*ref, ref/2);
  surface.setTitle("Endless Falling Simulator");
  surface.setResizable(true);
  background(20);
  frameRate(60);
}

public void draw(){
  background(20);
  back.display();
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
class Back{
  PImage back_img;
  int t, qtt = 10;
  Brick[] bricks;
  Back(int type){
    t = type;
    if (true){
      bricks = new Brick[qtt];
      for (int i = 0; i < qtt; i++)
        bricks[i] = new Brick(random(0, width), height + ref*(random(0, 10*qtt)), random(1, 3));
    }
  }
  public void display(){
    if (true){
      for (int i = 0; i < qtt; i++){
        bricks[i].update();
        if (bricks[i].y < 0 - bricks[i].sis){
          bricks[i] = new Brick(random(0, width), height + ref*(random(0, 10*qtt)), random(1, 3));
        }
      }
    }
  }
}

class Brick{
  float x, y, sis;
  Brick(float tx, float ty, float ts){
    sis = ts;
    x = tx;
    y = ty;
  }
  public void update(){
    fill(230);
    noStroke();
    rect(x, y, sis, 6*sis);
    y -= 20;
  }
}

class Player{
  Sprites p_sprt;
  float x, y;
  float speed;
  int count = 0, current = 0;
  int[] cntrl = {0, 0, 0, 0};
  /*
    0
  1   2
    3
  */
  Player (float temp_x, float temp_y, float temp_speed){
    speed = temp_speed;
    x = temp_x;
    y = temp_y;
    p_sprt = new Sprites ("player", 7);
  }

  public void display(){
    fill(230);
    if (count < 10)
      count ++;
    else{
      count = 0;
      current = PApplet.parseInt(random(0, 7));
    }
    p_sprt.display(current, x, y);
  }

  public void update(){
    if (cntrl[0] == 1 && y - speed > 0)
      y -= speed;
    if (cntrl[1] == 1 && x - speed > 0)
      x-= speed;
    if (cntrl[2] == 1 && x + speed < width)
      x += speed;
    if (cntrl[3] == 1 && y + speed < height)
      y += speed;
  }
}
class Sprites{
  PImage[] sprt_img;
  int qtt;
  int sis;
  Sprites(String file_name, int temp_qtt){
    qtt = temp_qtt;
    sprt_img = new PImage[qtt];
    sis = 5*ref;
    for (int i = 0; i < qtt; i++){
      sprt_img[i] = loadImage(file_name + "_" + i + ".png");
      //sprt_img[i].resize(sis, sis);
    }
  }
  public void display(int n, float tx, float ty){
    translate(tx, ty);
    rotate(PI);
    image(sprt_img[n], 0, 0);
    //translate(-tx, -ty);
  }
}
  public void settings() {  size(400, 600);  smooth(0); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "EFS" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
