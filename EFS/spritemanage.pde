class Sprites{
  PImage[] sprt_img;
  int qtt;
  Sprites(String file_name, int temp_qtt){
    qtt = temp_qtt;
    sprt_img = new PImage[qtt];
    for (int i = 0; i < qtt; i++){
      sprt_img[i] = loadImage(file_name + "_" + i + ".png");
    }
  }
}
