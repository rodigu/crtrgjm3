
int collide(float x1, float y1, float sx1, float sy1,
            float x2, float y2, float sx2, float sy2){
  if ((x1 >= x2 && y1 >= y2 && x1 <= x2 + sx2 && y1 <= y2 +sy2) ||
      (x1 + sx1 >= x2 && y1 >= y2 && x1 + sx1 <= x2 + sx2 && y1 <= y2 + sy2) ||
      (x1 + sx1 >= x2 && y1 + sy1 >= y2 && x1 + sx1 <= x2 + sx2 && y1 + sy1 <= y2 +sy2) ||
      (x1 >= x2 && y1 + sy1 >= y2 && x1 <= x2 + sx2 && y1 + sy1 <= y2 +sy2))
    return 1;
  
  return 0;
}
