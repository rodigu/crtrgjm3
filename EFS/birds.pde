class Bird{
  float x, y, speed;
  Sprites sprts, dsprts;
  int dead = 0;
  Bird(float tx, float ty, float ts){
    x = tx;
    y = ty;
    speed = ts;
    sprts = new Sprites("bird", 3, ref/2);
    dsorts = new Sprites("bird");
    if(speed < 0) sprts.scalex = -1;
  }
  void update(){
    if(dead = 0){
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

    }
  }
}
