class Screen{
  //load sprites
  PImage[] player_sprites;
  int n_player_sprites;
  float reference;
  Screen(float refsize, int tempn){
    playe_sprites = new PImage[tempn];
    reference = refsize;
    n_player_sprites = tempn;
    for (int i = 0; i < tempn; i++){
      player_sprites[i] = loadImage("player_" + i + ".png");
      player_sprites[i].resize(reference, reference);
    }
  }

  void update(){

  }
  void display(){

  }
}
