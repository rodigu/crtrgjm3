import ddf.minim.*;
Minim minim;
AudioPlayer song;
Sprites bird_sprts, bird_dsprts;
Player p1;
int ref = 100;
Back back;
Manager manage;
int screen_shake = 0, shake_time = 0;
int SCORE = 0, state = 1;
PFont arcade;

void setup(){
  size(400, 600);
  minim = new Minim(this);
  song = minim.loadFile("fsim_ost.wav", 2048);
  song.loop();
  smooth(0);
  bird_sprts = new Sprites("bird", 3, ref/2);
  char rad;
  if(random(10) < 5) rad = 'd';
  else rad = 'b';
  bird_dsprts = new Sprites(rad + "bird", 5, ref/2);
  arcade = createFont("arcade.ttf", ref);
  manage = new Manager();
  back = new Back();
  //position (2), speed (2)
  p1 = new Player(width/2, ref, ref/5);
  surface.setTitle("Endless Falling Simulator");
  //surface.setResizable(true);
  background(20);
  frameRate(30);
}

void draw(){
  if (state == 1)
    manage.display();
  else if (state == 2) DeathMenu();
}
