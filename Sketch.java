import processing.core.PApplet;

public class Sketch extends PApplet {
	
  // Variables
  float[] circleY = new float[15];
  float[] circleX = new float[15];
  boolean[] ballHideStatus = new boolean[25];

  boolean blnUp = false;
  boolean blnDown = false;
  boolean blnRight = false;
  boolean blnLeft = false;

  float blueCircleX = 150;
  float blueCircleY = 150;
  float blueCircleWidth = 25;
  float blueCircleHeight = 25;
  float snowWidth = 30;
  float snowHeight = 30;
  int ballSpeed = 3;
  int gameLives = 3;
  boolean mouseClickSnow = false;

  int intGameStatus = 0;
  
  public void settings() {
	// put your size call here
    size(400, 400);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(0);
    for (int i = 0; i < circleY.length; i++){
      circleY[i] = random(height);
      circleX[i] = random(width);
      ballHideStatus[i] = true;
    }
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {

    // Checks if out of lives and draws whitescreen
    if (intGameStatus != 0){
    background(255);
   }
    else if (intGameStatus == 0){
    background(0);
    blueCircle();
    snowfall();
  }
}

  /**
  * Drawing the snow falling
  */
  public void snowfall() {
    // Snowfall
    for (int i = 0; i < circleY.length; i++){
      if(ballHideStatus[i]){
        fill(255);
        ellipse(circleX[i], circleY[i], snowWidth, snowHeight);
      }

      circleY[i] += ballSpeed;

      if (circleY[i] > height){
        circleY[i] = 0;
      }
      
// Detecting collision between the blue circle and the snow
    if(dist(blueCircleX, blueCircleY, circleX[i], circleY[i]) <= 30 && (ballHideStatus[i])){
      gameLives -= 1;
      ballHideStatus[i] = false;
    }
      
    // Detects if mouse clicks on snowball
    if(mouseClickSnow && dist(mouseX, mouseY, circleX[i], circleY[i]) <= 15){
      ballHideStatus[i] = false;
    }
   } 
  }

  /**
  * draw the lives and the blue circle
  */
  public void blueCircle() {
    // drawing lives and blue circle if there are lives
    for (int i = 1; i <= gameLives; i++){
      fill(255, 0, 0);
      rect(i * 30, 15, 30, 30);
  }
    fill(0, 0, 255);
    ellipse(blueCircleX, blueCircleY, blueCircleWidth, blueCircleHeight);

    if(gameLives == 0){
      intGameStatus = 3;
    }
    // Moving the blue Circle
    if(blnRight){
      blueCircleX += 3;
    }
    if(blnLeft){
      blueCircleX -= 3;
    }
    if(blnDown){
      blueCircleY -= 3;
    }
    if(blnUp){
      blueCircleY += 3;
    }
  }

  /**
  * Mouse clicking on snow
  */
  public void mousePressed(){
    mouseClickSnow = true;
  }

  /**
  * Mouse releasing click on snow
  */
  public void mouseReleased(){
    mouseClickSnow = false;
  }

  /**
  * Changing speed if up and down is pressed and detecting movement keys
  */
  public void keyPressed(){
    // Movement keys of blue circle
    if(key == 'w'){
      blnUp = true;
    }
    if(key == 'a'){
      blnLeft = true;
    }
    if(key == 's'){
      blnDown = true;
    }
    if(key == 'd'){
      blnRight = true;
    }

    // Changing speed
    if(keyCode == UP){
      ballSpeed = 7;
    }
    if(keyCode == DOWN){
      ballSpeed = 1;
    }
  }

  /**
  * reset the speed back to normal and detect movement keys released
  */
  public void keyReleased(){
    ballSpeed = 3;
    if(key == 'w'){
      blnUp = false;
    }
    if(key == 'a'){
      blnLeft = false;
    }
    if(key == 's'){
      blnDown = false;
    }
    if(key == 'd'){
      blnRight = false;
    }
  }
  
}