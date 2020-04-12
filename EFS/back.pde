class Back{
  int qtt = 10;
  Brick[] bricks;
  Back(){
    bricks = new Brick[qtt];
    for (int i = 0; i < qtt; i++)
      bricks[i] = new Brick(random(0, width), height + ref*(random(0, 10*qtt)), random(1, 3));

  }
  void display(){
      for (int i = 0; i < qtt; i++){
        bricks[i].update();
        if (bricks[i].y < 0 - bricks[i].sis){
          bricks[i] = new Brick(random(0, width), height + ref*(random(0, 10*qtt)), random(1, 3));
        }
      }

  }
}

class Brick{
  float x, y, sis;
  Brick(float tx, float ty, float ts){
    sis = ts;
    x = tx;
    y = ty;
  }
  void update(){
    fill(230);
    noStroke();
    rect(x, y, sis, 6*sis);
    y -= 20;
  }
}
