
class Player{
  Sprites p_sprt;
  float x, y;
  float speed;
  int count = 0, current = 0, rec = 0;
  int[] cntrl = {0, 0, 0, 0, 0};
  Swoosh swo;
  /*
    0
  1   2
    3
  */
  Player (float temp_x, float temp_y, float temp_speed){
    swo = new Swoosh(0);
    speed = temp_speed;
    x = temp_x;
    y = temp_y;
    p_sprt = new Sprites ("player", 7, ref);
    p_sprt.scaley = -1;
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
    if(swo.step > 0){
      swo.step(x, y);
    }
  }

  void update(){
    if (cntrl[0] == 1 && y - speed > 0)
      y -= speed;
    if (cntrl[1] == 1 && x - speed > 0)
      x-= speed;
    if (cntrl[2] == 1 && x + speed < width)
      x += speed;
    if (cntrl[3] == 1 && y + speed < height)
      y += speed;
    if (cntrl[4] == 1 && swo.step == 0 && rec >= 15){
      swo.step += 1;
      rec = 0;
    }
    else rec++;
  }
}

class Swoosh{
  //swoosh has 4 steps: 0 is deactivated, 1 through 3 represent the sprite states
  int step;
  Sprites sprt;
  Swoosh(int s){
    step = s;
    sprt = new Sprites("swoosh", 3, ref);
    sprt.scaley = -1;
  }
  void step(float tx, float ty){
    if(step >= 4){
      step = 0;
      return;
    }
    sprt.display(step - 1, tx, ty + ref/3);
    step++;
  }

}
