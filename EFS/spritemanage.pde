class Sprites{
  PImage[] sprt_img;
  int qtt, sis, scalex = 1, scaley = 1;
  Sprites(String file_name, int temp_qtt, int ts){
    qtt = temp_qtt;
    sprt_img = new PImage[qtt];
    sis = ts;
    for (int i = 0; i < qtt; i++){
      sprt_img[i] = loadImage(file_name + "_" + i + ".png");
      sprt_img[i].resize(sis, sis);
    }
  }
  void display(int n, float tx, float ty){
    pushMatrix();
    translate(tx + scree_shake, ty + screen_shake);
    scale(scalex, scaley);
    image(sprt_img[n], -sis/2, -sis/2);
    popMatrix();
  }
}
