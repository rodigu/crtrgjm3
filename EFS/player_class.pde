
class Player{
  float x, y;
  float xspeed, yspeed, accel, top_speed;
  float player_size;
  int[] cntrl = {0, 0, 0, 0};
  /*
    0
  1   2
    3
  */
  Player (float temp_x, float temp_y, float temp_xspeed, float temp_yspeed,
          float temp_accel, float temp_top, float temp_size){
    xspeed = temp_xspeed;
    yspeed = temp_yspeed;
    accel = temp_accel;
    x = temp_x;
    y = temp_y;
    player_size = temp_size;
  }

  void display(){
    fill(230);
    rect(x - player_size/2, y - player_size/2, player_size, 2*player_size);
  }

  void update(){
    if (cntrl[0] == 1 && yspeed >= -top_speed)
      yspeed -= accel;
    else if (yspeed < 0) yspeed ++;
    if (cntrl[3] == 1 && yspeed <= top_speed)
      yspeed += accel;
    else if (yspeed > 0) yspeed --;

    if (cntrl[1] == 1 && xspeed >= -top_speed)
      xspeed -= accel;
    else if (xspeed < 0) xspeed ++;
    if (cntrl[2] == 1 && xspeed <= top_speed)
      xspeed += accel;
    else if (xspeed > 0) xspeed --;
    
    x += xspeed;
    y += yspeed;
  }
}
