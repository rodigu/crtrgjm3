class Bird{
  float x, y, speed;
  Sprites sprts, dsprts;
  int dead, framecounter;
  Bird(float tx, float ty, float ts){
    framecounter = 0;
    dead = 0;
    x = tx;
    y = ty;
    speed = ts;
    sprts = new Sprites("bird", 3, ref/2);
    dsprts = new Sprites("dbird", 5, ref/2);
    if(speed < 0) sprts.scalex = -1;
  }
  void update(){
    if(dead == 0){
      x += speed;
      fill(230);
      if(frameCount%30 < 7.5)
        sprts.display(0, x, y);
      else if(frameCount%30 < 15)
        sprts.display(1, x, y);
      else if(frameCount%30 < 22.5)
        sprts.display(2, x, y);
      else
        sprts.display(1, x, y);
    }
    else{
      if(framecounter == 0) framecounter = frameCount;
      float tempcount = (frameCount - framecounter)/frameRate;
      if(tempcount <= 0.05) dsprts.display(0, x, y);
      else if(tempcount <= 0.10) dsprts.display(1, x, y);
      else if(tempcount <= 0.15) dsprts.display(2, x, y);
      else if(tempcount <= 0.20) dsprts.display(3, x, y);
      else if(tempcount <= 0.25) dsprts.display(4, x, y);
      else dead = 2;
    }
  }
}
