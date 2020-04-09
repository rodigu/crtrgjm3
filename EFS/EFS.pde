Player p1;
float ref = 10;

void setup(){
  size(400, 600);
  noSmooth();
  //position (2), speed (2), acceleration (1), top speed (1), size (1)
  p1 = new Player(width/2, 2*ref, 0, 0, ref, ref*2, ref);
  surface.setTitle("Endless Falling Simulator");
  surface.setResizable(true);
}

void draw(){
  background(20);
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
