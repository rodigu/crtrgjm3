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


Player player1;

public void setup(){
  
  player1 = new Player();
  frame.setTitle ("Endless Falling Simulator");
}

public void draw(){
  background(230);
  player1.update();
}
class Player{
  PImage[] fall_sprites;
  int sprite_number = 7;
  Player(){
    fall_sprites = new PImage[sprite_number];
    for (int i = 0; i < sprite_number; i++){
      fall_sprites[i] = loadImage("fall_" + i + ".png");
      fall_sprites[i].resize(100, 100);
    }
  }

  public void update(){

    image(fall_sprites[PApplet.parseInt(random(0, sprite_number))], width/2, height/2);
  }
}
  public void settings() {  size(500, 500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "EFS" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
