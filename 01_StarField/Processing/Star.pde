class Star {
  float x;
  float y;
  float z;
  
  float pz;
  
  Star() {
    x = random(-width, width);
    y = random(-height, height);
    z = random(width);
    pz = z;
  }
  
  void update() {
    z = z - speed;
    if (z < 1) {
      z = width;
      x = random(-width/2, width/2);
      y = random(-height/2, height/2);
      pz = z;
    }
  }
  
  void show() {
    fill(255);
    noStroke();
    
    float sx = map(x / z, 0, 1, 0, width);
    float sy = map(y / z, 0, 1, 0, height);
    
    float r = map(z, 0, width, 8, 0);
    ellipse(sx, sy, r , r);
    
    float px = map(x / pz, 0, 1, 0, width);
    float py = map(y / pz, 0, 1, 0, height);
    
    pz = z;
    
    stroke(255);
    fill(255);
    //line(px, py, sx, sy); //Just the simple line
    //line(px, py, sx, sy + r / 2);
    //line(px, py, sx, sy - r / 2);
    triangle(px, py, sx, sy + r / 2, sx, sy - r / 2);
   
  }
 
}