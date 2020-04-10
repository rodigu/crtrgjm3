class Sprites{
  PImage[] sprt_img;
  int qtt;
  int sis;
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
    translate(tx, ty);
    rotate(PI);
    image(sprt_img[n], -sis/2, sis/2);
    //translate(-tx, -ty);
  }
}
