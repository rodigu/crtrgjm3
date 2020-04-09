Player p1;
int ref = 10, pixel_size = 5;
Back back;

void setup(){
  size(400, 600);
  smooth(0);
  back = new Back(0);
  //position (2), speed (2)
  p1 = new Player(width/2, 2*ref, ref/2);
  surface.setTitle("Endless Falling Simulator");
  surface.setResizable(true);
  background(20);
  frameRate(60);
}

void draw(){
  background(20);
  back.display();
  p1.update();
  p1.display();
}

void keyPressed(){
  if (key == 'w')
    p1.cntrl[0] = 1;
  if (key == 'a')
    p1.cntrl[1] = 1;
  if (key == 'd')
    p1.cntrl[2] = 1;
  if (key == 's')
    p1.cntrl[3] = 1;
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
}
