class Manager{
  int state;
  Tower tower;
  Manager(int te){
    state = te;
    tower = new Tower();
  }
  void display(){
    if(state == 1){
      background(20);
      back.display();
      p1.update();
      p1.display();
    }
  }
}
