
class Player{
  Sprites p_sprt;
  Sprites health_sprt;
  float x, y;
  int[] health = {1, 1, 1};
  int current_health = 3;
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
    health_sprt = new Sprites("health", 2, ref/4);
    y = temp_y;
    p_sprt = new Sprites ("player", 8, ref);
    p_sprt.scaley = -1;
  }

  void display(){
    fill(230);
    if (count < 5)
      count ++;
    else{
      count = 0;
      current = int(random(0, 8));
    }
    p_sprt.display(current, x, y);
    if(swo.step > 0){
      swo.step(x, y);
    }
    for(int i = 0; i < 3; i++){
      if(health[i] == 1){
        health_sprt.display(0, ref/5, (i + 1)*ref/3);
      }
      else health_sprt.display(1, ref/5, (i + 1)*ref/3);
    }
  }

  void update(){
    x = mouseX;
    y = mouseY;
    if(mousePressed && swo.step == 0 && rec >= 10){
      swo.step += 1;
      rec = 0;
    }
    else rec++;
    if(current_health == 2) health[2] = 0;
    if(current_health == 1) health[1] = 0;
    if(current_health == 0){
      state = 2;
      health[0] = 0;
    }
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
