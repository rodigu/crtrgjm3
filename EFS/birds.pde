class Bird{
  float x, y, speed;
  Bird(float tx, float ty, float ts){
    x = tx;
    y = ty;
    speed = ts;
  }
  void update(){
    x += speed;
    fill(230);
    rect(x, y, ref/2, ref/2);
  }
}
