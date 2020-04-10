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
int ref = 100;
Back back;
Manager manage;

public void setup(){
  
  
  manage = new Manager(1);
  back = new Back();
  //position (2), speed (2)
  p1 = new Player(width/2, ref, ref/10);
  surface.setTitle("Endless Falling Simulator");
  //surface.setResizable(true);
  background(20);
  frameRate(60);
}

public void draw(){
  manage.display();
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
  if (key == ' ')
    p1.cntrl[4] = 1;
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
  if (key == ' ')
    p1.cntrl[4] = 0;
}
class Back{
  PImage back_img;
  int qtt = 10;
  Brick[] bricks;
  Back(){
    bricks = new Brick[qtt];
    for (int i = 0; i < qtt; i++)
      bricks[i] = new Brick(random(0, width), height + ref*(random(0, 10*qtt)), random(1, 3));

  }
  public void display(){
      for (int i = 0; i < qtt; i++){
        bricks[i].update();
        if (bricks[i].y < 0 - bricks[i].sis){
          bricks[i] = new Brick(random(0, width), height + ref*(random(0, 10*qtt)), random(1, 3));
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
class Bird{
  float x, y, speed;
  Bird(float tx, float ty, float ts){
    x = tx;
    y = ty;
    speed = ts;
  }
  public void update(){
    x += speed;
    fill(230);
    rect(x, y, ref/2, ref/2);
  }
}

public int collide(float x1, float y1, float sx1, float sy1,
            float x2, float y2, float sx2, float sy2){
  return 0;
}
class Manager{
  int state, bqtt = 10;
  Bird[] birds;
  Manager(int te){
    state = te;
    birds = new Bird[bqtt];
    for (int i = 0; i < bqtt; i++){
      float sx = 0, sp;
      if (random(0, 10) < 5) {
        sx = -ref;
        sp = random(3, 10);
      }
      else {
        sx = width + ref;
        sp = random(-10, -3);
      }
      birds[i] = new Bird(sx, random(0, height), sp);
    }
  }
  public void display(){
    if(state == 1){
      background(20);
      back.display();
      for(int i = 0; i < bqtt; i++){
        birds[i].update();
        if((birds[i].speed < 0 && birds[i].x < -ref) ||
           (birds[i].speed > 0 && birds[i].x > width + ref)){
          float sx, sp;
          if (random(0, 10) < 5) {
            sx = -ref;
            sp = random(3, 10);
          }
          else {
            sx = width + ref;
            sp = random(-3, -10);
          }
          birds[i] = new Bird(sx, random(0, height), sp);

        }
      }

      p1.update();
      p1.display();
    }

  }
}

class Player{
  Sprites p_sprt;
  float x, y;
  float speed;
  int count = 0, current = 0;
  int[] cntrl = {0, 0, 0, 0, 0};
  /*
    0
  1   2
    3
  */
  Player (float temp_x, float temp_y, float temp_speed){
    speed = temp_speed;
    x = temp_x;
    y = temp_y;
    p_sprt = new Sprites ("player", 7, ref);
  }

  public void display(){
    fill(230);
    if (count < 5)
      count ++;
    else{
      count = 0;
      current = PApplet.parseInt(random(0, 7));
    }
    p_sprt.display(current, x, y);
  }

  public void update(){
    if (cntrl[0] == 1 && y - speed > ref)
      y -= speed;
    if (cntrl[1] == 1 && x - speed > 0)
      x-= speed;
    if (cntrl[2] == 1 && x + speed < width)
      x += speed;
    if (cntrl[3] == 1 && y + speed < height + ref)
      y += speed;
  }
}
class Sprites{
  PImage[] sprt_img;
  int qtt;
  int sis;
  Sprites(String file_name, int temp_qtt, int ts){
    qtt = temp_qtt;
    sprt_img = new PImage[qtt];
    sis = ts;
    for (int i = 0; i < qtt; i++){
      sprt_img[i] = loadImage(file_name + "_" + i + ".png");
      sprt_img[i].resize(sis, sis);
    }
  }
  public void display(int n, float tx, float ty){
    translate(tx, ty);
    rotate(PI);
    image(sprt_img[n], -sis/2, sis/2);
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
