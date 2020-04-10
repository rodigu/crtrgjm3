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
  void display(){
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
            sp = random(3, 7);
          }
          else {
            sx = width + ref;
            sp = random(-7, -3);
          }
          birds[i] = new Bird(sx, random(0, height), sp);

        }
      }

      p1.update();
      p1.display();
    }

  }
}
