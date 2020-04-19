
void DeathMenu(){
  tint(255, 0);
  textSize(ref/2);
  textAlign(CENTER);
  text("play again", (width/2), (height/2));
  if(mousePressed && mouseX >= ref && mouseX <= width - ref &&
     mouseY >= (ref/4) - (height/2) && mouseY <= (ref/4) + (height/2)){
     manage = new Manager();
     state = 1;
   }
}
