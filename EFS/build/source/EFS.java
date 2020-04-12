import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import ddf.minim.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class EFS extends PApplet {


Minim minim;
AudioPlayer song;

Player p1;
int ref = 100;
Back back;
Manager manage;
int screen_shake = 0, shake_time = 0;
int SCORE = 0;
PFont arcade;

public void setup(){
  
  minim = new Minim(this);
  song = minim.loadFile("fsim_ost.wav", 2048);
  song.loop();
  
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
class Back{
  int qtt = 10;
  Brick[] bricks;
  Back(){
    bricks = new Brick[qtt];
    for (int i = 0; i < qtt; i++)
      bricks[i] = new Brick(random(0, width), height + ref*(random(0, qtt)), random(3, 6));

  }
  public void display(){
      for (int i = 0; i < qtt; i++){
        bricks[i].update();
        if (bricks[i].y < 0 - bricks[i].sis){
          bricks[i] = new Brick(random(0, width), height + ref*(random(0, qtt)), random(3, 6));
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
    rect(x, y, sis/3, 6*sis);
    y -= 20;
  }
}
class Bird{
  float x, y, speed;
  Sprites sprts, dsprts;
  int dead, framecounter;
  Bird(float tx, float ty, float ts){
    framecounter = 0;
    dead = 0;
    x = tx;
    y = ty;
    speed = ts;
    sprts = new Sprites("bird", 3, ref/2);
    char rad;
    if(random(10) < 5) rad = 'd';
    else rad = 'b';
    dsprts = new Sprites(rad + "bird", 5, ref/2);
    if(speed < 0) sprts.scalex = -1;
  }
  public void update(){
    if(dead == 0){
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
    else{
      if(framecounter == 0) framecounter = frameCount;
      float tempcount = (frameCount - framecounter)/frameRate;
      if(tempcount <= 0.05f) dsprts.display(0, x, y);
      else if(tempcount <= 0.10f) dsprts.display(1, x, y);
      else if(tempcount <= 0.15f) dsprts.display(2, x, y);
      else if(tempcount <= 0.20f) dsprts.display(3, x, y);
      else if(tempcount <= 0.25f) dsprts.display(4, x, y);
      else dead = 2;
    }
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
        int col = collide(birds[i].x - ref/5, birds[i].y - ref/6.7f, ref/2.5f, ref/5,
                          p1.x - ref/2, p1.y + ref/3, ref, ref/2);
        if((birds[i].speed < 0 && birds[i].x < -ref) ||
           (birds[i].speed > 0 && birds[i].x > width + ref)){
          newBird(i);
        }
        if (p1.swo.step != 0 && col == 1){
          SCORE += PApplet.parseInt(abs(birds[i].speed)/5);
          birds[i].dead = 1;
        }
        int col2 = collide(birds[i].x - ref/5, birds[i].y - ref/6.7f, ref/2.5f, ref/5,
                           p1.x - ref/8, p1.y - ref/4, ref/5, ref/2);
        if((col2 == 1 || shake_time != 0) && birds[i].dead == 0){
          if(shake_time == 0) shake_time = frameCount;

          if(random(10) < 5) screen_shake = -PApplet.parseInt(random(5, 15));
          else screen_shake = PApplet.parseInt(random(5, 15));

          if((frameCount - shake_time)/frameRate >= 0.5f){
            shake_time = 0;
            screen_shake = 0;
            if(p1.current_health > 0) p1.current_health--;
          }
        }
        if(birds[i].dead == 2) newBird(i);
      }
      p1.update();
      p1.display();
      noFill();
      stroke(230, 30, 30);
      //rect(birds[1].x - ref/5, birds[1].y - ref/6.7, ref/2.5, ref/5);
      //rect(p1.x - ref/8, p1.y - ref/4, ref/5, ref/2);
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
  Sprites health_sprt;
  float x, y;
  int[] health = {1, 1, 1};
  int current_health = 3;
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
    health_sprt = new Sprites("health", 2, ref/4);
    y = temp_y;
    p_sprt = new Sprites ("player", 8, ref);
    p_sprt.scaley = -1;
  }

  public void display(){
    fill(230);
    if (count < 5)
      count ++;
    else{
      count = 0;
      current = PApplet.parseInt(random(0, 8));
    }
    p_sprt.display(current, x, y);
    if(swo.step > 0){
      swo.step(x, y);
    }
    for(int i = 0; i < 3; i++){
      if(health[i] == 1){
        health_sprt.display(0, ref/5, (i + 1)*ref/3);
      }
      else health_sprt.display(1, ref/5, (i + 1)*ref/3);
    }
  }

  public void update(){
    x = mouseX;
    y = mouseY;
    if(mousePressed && swo.step == 0 && rec >= 10){
      swo.step += 1;
      rec = 0;
    }
    else rec++;
    if(current_health == 2) health[2] = 0;
    if(current_health == 1) health[1] = 0;
    if(current_health == 0) health[0] = 0;
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
