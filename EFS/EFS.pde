Player p1;
int ref = 100;
Back back;
Manager manage;
int screen_shake = 0, shake_time = 0;
int SCORE = 0;
PFont arcade;

void setup(){
  size(400, 600);
  smooth(0);
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

void draw(){
  manage.display();
}
<<<<<<< Updated upstream

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
=======
>>>>>>> Stashed changes
