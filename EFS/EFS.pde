
Manager manager;

void setup(){
  size(500, 500);
  manager = new Manager();
  frame.setTitle ("Endless Falling Simulator");
}

void draw(){
  background(230);
  player1.update();
}
