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
int screen_shake = 0, shake_time = 0;
int SCORE = 0;
PFont arcade;

public void setup(){
  
  
  arcade = createFont("arcade.ttf", ref);
  manage = new Manager(1);
  back = new Back();
  //position (2), speed (2)
  p1 = new Player(width/2, ref, ref/5);
  surface.setTitle("Endless Falling Simulator");
  //surface.setResizable(true);
  background(20);
  frameRate(30);
}

public void draw(){
  manage.display();
}
/*
void keyPressed(){
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
void keyReleased(){
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
*/
class Back{
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
  Sprites sprts;
  Bird(float tx, float ty, float ts){
    x = tx;
    y = ty;
    speed = ts;
    sprts = new Sprites("bird", 3, ref/2);
    if(speed < 0) sprts.scalex = -1;
  }
  public void update(){
    x += speed;
    fill(230);
    if(frameCount%30 < 7.5f)
      sprts.display(0, x, y);
    else if(frameCount%30 < 15)
      sprts.display(1, x, y);
    else if(frameCount%30 < 22.5f)
      sprts.display(2, x, y);
    else
      sprts.display(1, x, y);
  }
}

public int collide(float x1, float y1, float sx1, float sy1,
            float x2, float y2, float sx2, float sy2){
  if ((x1 >= x2 && y1 >= y2 && x1 <= x2 + sx2 && y1 <= y2 +sy2) ||
      (x1 + sx1 >= x2 && y1 >= y2 && x1 + sx1 <= x2 + sx2 && y1 <= y2 + sy2) ||
      (x1 + sx1 >= x2 && y1 + sy1 >= y2 && x1 + sx1 <= x2 + sx2 && y1 + sy1 <= y2 +sy2) ||
      (x1 >= x2 && y1 + sy1 >= y2 && x1 <= x2 + sx2 && y1 + sy1 <= y2 +sy2))
    return 1;
  
  return 0;
}
class Manager{
  int state, bqtt = 5;
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
      textFont(arcade);
      textSize(ref/2);
      text(SCORE, (width/2) - ref/4, ref);
      back.display();
      for(int i = 0; i < bqtt; i++){
        birds[i].update();
        int col = collide(birds[i].x - ref/4, birds[i].y - ref/4, ref/2, ref/3,
                          p1.x - ref/2, ref/3 + p1.y - ref/3, ref/2, ref/2);
        if(((birds[i].speed < 0 && birds[i].x < -ref) ||
           (birds[i].speed > 0 && birds[i].x > width + ref)) ||
           (p1.swo.step != 0 &&
            col == 1)){
          if(col == 1) SCORE += PApplet.parseInt(abs(birds[i].speed)/5);
          newBird(i);
        }
        int col2 = collide(birds[i].x - ref/4, birds[i].y - ref/4, ref/2, ref/3,
                          p1.x - ref/8, p1.y - ref/4, ref/4, ref/2);
        if(col2 == 1 || shake_time != 0){
          if(shake_time == 0) shake_time = frameCount;
          if(random(10) < 5)
            screen_shake = -PApplet.parseInt(random(5, 15));
          else screen_shake = PApplet.parseInt(random(5, 15));
          if((frameCount - shake_time)/frameRate >= 0.5f){
            shake_time = 0;
            screen_shake = 0;
          }
        }
      }
      p1.update();
      p1.display();

    }
  }
  public void newBird(int i){
    float sx, sp;
    if (random(0, 10) < 5) {
      sx = -ref;
      sp = random(6, 15);
    }
    else {
      sx = width + ref;
      sp = random(-15, -6);
    }
    birds[i] = new Bird(sx, random(0, height), sp);
  }
}

class Player{
  Sprites p_sprt;
  float x, y;
  float speed;
  int count = 0, current = 0, rec = 0;
  int[] cntrl = {0, 0, 0, 0, 0};
  Swoosh swo;
  /*
    0
  1   2
    3
  */
  Player (float temp_x, float temp_y, float temp_speed){
    swo = new Swoosh(0);
    speed = temp_speed;
    x = temp_x;
    y = temp_y;
    p_sprt = new Sprites ("player", 7, ref);
    p_sprt.scaley = -1;
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
    if(swo.step > 0){
      swo.step(x, y);
    }
  }

  public void update(){
    x = mouseX;
    y = mouseY;
    if(mousePressed && swo.step == 0 && rec >= 15){
      swo.step += 1;
      rec = 0;
    }
    else rec++;
    /*
    if (cntrl[0] == 1 && y - speed > 0)
      y -= speed;
    if (cntrl[1] == 1 && x - speed > 0)
      x-= speed;
    if (cntrl[2] == 1 && x + speed < width)
      x += speed;
    if (cntrl[3] == 1 && y + speed < height)
      y += speed;
    if (cntrl[4] == 1 && swo.step == 0 && rec >= 15){
      swo.step += 1;
      rec = 0;
    }
    else rec++;
    */
  }
}

class Swoosh{
  //swoosh has 4 steps: 0 is deactivated, 1 through 3 represent the sprite states
  int step;
  Sprites sprt;
  Swoosh(int s){
    step = s;
    sprt = new Sprites("swoosh", 3, ref);
    sprt.scaley = -1;
  }
  public void step(float tx, float ty){
    if(step >= 4){
      step = 0;
      return;
    }
    sprt.display(step - 1, tx, ty + ref/3);
    step++;
  }

}
class Sprites{
  PImage[] sprt_img;
  int qtt, sis, scalex = 1, scaley = 1;
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
    pushMatrix();
    translate(tx + screen_shake, ty + screen_shake);
    scale(scalex, scaley);
    image(sprt_img[n], -sis/2, -sis/2);
    popMatrix();
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
