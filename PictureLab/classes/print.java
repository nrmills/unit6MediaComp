

  public void mirrorHorizontal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int width = pixels.length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        topPixel = pixels[row][col];
        bottomPixel = pixels[row][width - 1 - col];
        bottomPixel.setColor(topPixel.getColor());
      }
    } 
  }