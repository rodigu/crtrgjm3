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
        int col = collide(birds[i].x - ref/5, birds[i].y - ref/6.7, ref/2.5, ref/5,
                          p1.x - ref/2, p1.y + ref/3, ref, ref/2);
        if((birds[i].speed < 0 && birds[i].x < -ref) ||
           (birds[i].speed > 0 && birds[i].x > width + ref)){
          newBird(i);
        }
        if (p1.swo.step != 0 && col == 1){
          SCORE += int(abs(birds[i].speed)/5);
          birds[i].dead = 1;
        }
        int col2 = collide(birds[i].x - ref/5, birds[i].y - ref/6.7, ref/2.5, ref/5,
                          p1.x - ref/8, p1.y - ref/4, ref/4, ref/2);
        if((col2 == 1 || shake_time != 0) && birds[i].dead == 0){
          if(shake_time == 0) shake_time = frameCount;

          if(random(10) < 5) screen_shake = -int(random(5, 15));
          else screen_shake = int(random(5, 15));

          if((frameCount - shake_time)/frameRate >= 0.5){
            shake_time = 0;
            screen_shake = 0;
          }
        }
        if(birds[i].dead == 2) newBird(i);
      }
      p1.update();
      p1.display();
      noFill();
      stroke(230, 30, 30);
      //rect(birds[0].x - ref/5, birds[0].y - ref/6.7, ref/2.5, ref/5);
      //rect(p1.x - ref/8, p1.y - ref/4, ref/4, ref/2);
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
