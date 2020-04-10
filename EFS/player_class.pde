
class Player{
  Sprites p_sprt;
  float x, y;
  float speed;
  int count = 0, current = 0;
  int[] cntrl = {0, 0, 0, 0, 0};
  /*
    0
  1   2
    3
  */
  Player (float temp_x, float temp_y, float temp_speed){
    speed = temp_speed;
    x = temp_x;
    y = temp_y;
    p_sprt = new Sprites ("player", 7, ref);
  }

  void display(){
    fill(230);
    if (count < 5)
      count ++;
    else{
      count = 0;
      current = int(random(0, 7));
    }
    p_sprt.display(current, x, y);
  }

  void update(){
    if (cntrl[0] == 1 && y - speed > ref)
      y -= speed;
    if (cntrl[1] == 1 && x - speed > 0)
      x-= speed;
    if (cntrl[2] == 1 && x + speed < width)
      x += speed;
    if (cntrl[3] == 1 && y + speed < height + ref)
      y += speed;
  }
}
