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
  void display(){
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
          if(col == 1) SCORE += int(abs(birds[i].speed)/5);
          newBird(i);
        }
        int col2 = collide(birds[i].x - ref/4, birds[i].y - ref/4, ref/2, ref/3,
                          p1.x - ref/8, p1.y - ref/4, ref/4, ref/2);
        if(col2 == 1 || shake_time != 0){
          if(shake_time == 0) shake_time = frameCount;
          if(random(10) < 5)
            screen_shake = -int(random(5, 15));
          else screen_shake = int(random(5, 15));
          if((frameCount - shake_time)/frameRate >= 0.5){
            shake_time = 0;
            screen_shake = 0;
          }
        }
      }
      p1.update();
      p1.display();

    }
  }
  void newBird(int i){
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
