class Player{
  PImage[] fall_sprites;
  int sprite_number = 7;
  Player(){
    fall_sprites = new PImage[sprite_number];
    for (int i = 0; i < sprite_number; i++){
      fall_sprites[i] = loadImage("fall_" + i + ".png");
      fall_sprites[i].resize(100, 100);
    }
  }

  void update(){

    image(fall_sprites[int(random(0, sprite_number))], width/2, height/2);
  }
}
