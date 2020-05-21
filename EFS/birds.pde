class Bird{
  float x, y, speed;
  int dead, framecounter;
  Bird(float tx, float ty, float ts){
    framecounter = 0;
    dead = 0;
    x = tx;
    y = ty;
    speed = ts;
  }
  void update(){
    if(dead == 0){
      x += speed;
      fill(230);
      if(speed < 0) bird_sprts.scalex = -1;
      else bird_sprts.scalex = 1;
      if(frameCount%30 < 7.5)
        bird_sprts.display(0, x, y);
      else if(frameCount%30 < 15)
        bird_sprts.display(1, x, y);
      else if(frameCount%30 < 22.5)
        bird_sprts.display(2, x, y);
      else
        bird_sprts.display(1, x, y);
    }
    else{
      if(framecounter == 0) framecounter = frameCount;
      float tempcount = (frameCount - framecounter)/frameRate;
      if(tempcount <= 0.05) bird_dsprts.display(0, x, y);
      else if(tempcount <= 0.10) bird_dsprts.display(1, x, y);
      else if(tempcount <= 0.15) bird_dsprts.display(2, x, y);
      else if(tempcount <= 0.20) bird_dsprts.display(3, x, y);
      else if(tempcount <= 0.25) bird_dsprts.display(4, x, y);
      else dead = 2;
    }
  }
}
