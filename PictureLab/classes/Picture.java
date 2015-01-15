import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
    ///////////////////// constructors //////////////////////////////////

    /**
     * Constructor that takes no arguments 
     */
    public Picture ()
    {
        /* not needed but use it to show students the implicit call to super()
         * child constructors always call a parent constructor 
         */
        super();  
    }

    /**
     * Constructor that takes a file name and creates the picture 
     * @param fileName the name of the file to create the picture from
     */
    public Picture(String fileName)
    {
        // let the parent class handle this fileName
        super(fileName);
    }

    /**
     * Constructor that takes the width and height
     * @param height the height of the desired picture
     * @param width the width of the desired picture
     */
    public Picture(int height, int width)
    {
        // let the parent class handle this width and height
        super(width,height);
    }

    /**
     * Constructor that takes a picture and creates a 
     * copy of that picture
     * @param copyPicture the picture to copy
     */
    public Picture(Picture copyPicture)
    {
        // let the parent class do the copy
        super(copyPicture);
    }

    /**
     * Constructor that takes a buffered image
     * @param image the buffered image to use
     */
    public Picture(BufferedImage image)
    {
        super(image);
    }

    ////////////////////// methods ///////////////////////////////////////

    /**
     * Method to return a string with information about this picture.
     * @return a string with information about the picture such as fileName,
     * height and width.
     */
    public String toString()
    {
        String output = "Picture, filename " + getFileName() + 
            " height " + getHeight() 
            + " width " + getWidth();
        return output;

    }

    // copies the region defined by rows 10-100 and columns 20-200 of picture1 into picture2
    // such that the upper-left corner of the copied picture starts at row 30 and column 40
    //(and, therefore, extends to row 120 and column 220).
    //picture2.copy( picture1, 10, 100, 20, 200, 30, 40 );
    public void copy( Picture initSourcePicture, int startSourceRow, int endSourceRow,
    int startSourceCol, int endSourceCol, int startDestRow, int startDestCol )
    {
        //Initialize Pictures
        Pixel[][] canvasPicture = this.getPixels2D();
        Pixel[][] sourcePicture = initSourcePicture.getPixels2D();
        int sourceRow = startSourceRow;
        int sourceCol = startSourceCol;

        //Copy sourcePicture onto canvasPicture
        for( int row = startDestRow; row < startDestRow + ( endSourceRow - startSourceRow ); row++ )
        {
            for( int col = startDestCol; col < startDestCol + ( endSourceCol - startSourceCol ); col++ )
            {
                canvasPicture[row][col].setColor( sourcePicture[sourceRow][sourceCol].getColor() );
                sourceCol++;
            }
            sourceCol = startSourceCol;
            sourceRow++;
        }
    }

    public void crop( Picture initSourcePicture, int startSourceRow, int endSourceRow,
    int startSourceCol, int endSourceCol, int startDestRow, int startDestCol )
    {
        //Initialize Pictures
        Pixel[][] canvasPicture = this.getPixels2D();
        Pixel[][] sourcePicture = initSourcePicture.getPixels2D();
        Pixel[][] croppedPicture = new Pixel[sourcePicture.length/2][sourcePicture[0].length];
        //Initialize variables to hold averages of red, green, blue values
        int redVal = 0;
        int greenVal = 0;
        int blueVal = 0;

        //crop sourcePicture
        for( int row = 0; row < sourcePicture.length; row += 2 )
        {
            for( int col = 0; col < sourcePicture[0].length; col += 2 )
            {
                redVal = 0;
                greenVal = 0;
                blueVal = 0;
                for( int microRow = row; microRow < 2; microRow++ )
                {
                    for( int microCol = col; microCol < 2; microCol++ )
                    {
                        redVal += sourcePicture[microRow][microCol].getRed();
                        greenVal += sourcePicture[microRow][microCol].getGreen();
                        blueVal += sourcePicture[microRow][microCol].getBlue();
                    }
                }
                //assign average color values of 4 pixel grid
                croppedPicture[row/2][col/2].setRed(redVal/4);
                croppedPicture[row/2][col/2].setBlue(blueVal/4);
                croppedPicture[row/2][col/2].setRed(greenVal/4);
            }
        }
    }

    public void grayScale()
    {
        Pixel[][] pixels = this.getPixels2D();
        for( Pixel[] rowArray : pixels )
        {
            for( Pixel pixelObj : rowArray )
            {
                int avg = (pixelObj.getRed()+pixelObj.getGreen()+
                        pixelObj.getBlue())/3;
                pixelObj.setRed(avg);
                pixelObj.setGreen(avg);
                pixelObj.setBlue(avg);
            }
        }
    }

    public void negate()
    {
        Pixel[][] pixels = this.getPixels2D();
        for( Pixel[] rowArray : pixels )
        {
            for( Pixel pixelObj : rowArray )
            {
                pixelObj.setRed(255-pixelObj.getRed());
                pixelObj.setGreen(255-pixelObj.getGreen());
                pixelObj.setBlue(255-pixelObj.getBlue());
            }
        }
    }

    public void fixUnderwater()
    {
        Pixel[][] pixels = this.getPixels2D();
        for( Pixel[] rowArray : pixels )
        {
            for( Pixel pixelObj : rowArray )
            {
                pixelObj.setRed( ( (pixelObj.getGreen() + pixelObj.getBlue() )/2) );
                //pixelObj.setGreen( (pixelObj.getGreen()/2) );
                //pixelObj.setBlue( (pixelObj.getBlue()/2) );
            }
        }
    }

    /** Method to set the blue to 0 */
    public void zeroBlue()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setBlue(0);
            }
        }
    }

    public void keepOnlyBlue()
    {
        Pixel[][] pixels = this.getPixels2D();
        for( Pixel[] rowArray : pixels )
        {
            for( Pixel pixelObj : rowArray )
            {
                pixelObj.setRed(0);
                pixelObj.setGreen(0);
            }
        }
    }

    public void mirrorVerticalRightToLeft() 
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for( int row = 0; row < pixels.length; row++ )
        {
            for( int col = 0; col < width / 2; col++ )
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                leftPixel.setColor(rightPixel.getColor());
            }
        }
    }

    /** Method that mirrors the picture around a 
     * vertical mirror in the center of the picture
     * from left to right */
    public void mirrorVertical()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < width / 2; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                rightPixel.setColor(leftPixel.getColor());
            }
        } 
    }

    public void mirrorHorizontal()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        int width = pixels.length;
        int height = pixels[0].length;
        for( int col = 0; col < height; col++ )
        {
            for( int row = 0; row < pixels.length; row++ )
            {
                topPixel = pixels[row][col];
                bottomPixel = pixels[width - 1 - row][col];
                bottomPixel.setColor(topPixel.getColor());
            }
        }
    }

    public void mirrorHorizontalBotToTop()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels.length;
        System.out.println(pixels.length);
        System.out.println(pixels[0].length);
        for (int row = pixels.length-1; row > width / 2; row-- )
        {
            for (int col = pixels[0].length-1; col > 0; col--)
            {
                rightPixel = pixels[row][col];
                leftPixel = pixels[-row+pixels.length][-col+pixels[0].length];
                leftPixel.setColor(rightPixel.getColor());
            }
        } 
    }

    /** Mirror just part of a picture of a temple */
    public void mirrorTemple()
    {
        int mirrorPoint = 276;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int count = 0;
        Pixel[][] pixels = this.getPixels2D();

        // loop through the rows
        for (int row = 27; row < 97; row++)
        {
            // loop from 13 to just before the mirror point
            for (int col = 13; col < mirrorPoint; col++)
            {

                leftPixel = pixels[row][col];      
                rightPixel = pixels[row]                       
                [mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
                count++;
            }
        }
        System.out.println(count);
    }

    public void mirrorArms()
    {
        //row start: 163
        //row end: 202
        //col start: 105
        //col end: 169
        int mirrorPoint = 169;
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        Pixel[][] pixels = this.getPixels2D();

        for( int row = 163; row < 205; row++ )
        {
            for( int col = 105; col < mirrorPoint; col++ )
            {
                topPixel = pixels[row][col];
                bottomPixel = pixels[180+(205-row)][col];
                bottomPixel.setColor(topPixel.getColor());
            }
        }
    }

    /** copy from the passed fromPic to the
     * specified startRow and startCol in the
     * current picture
     * @param fromPic the picture to copy from
     * @param startRow the start row to copy to
     * @param startCol the start col to copy to
     */
    public void copy(Picture fromPic, 
    int startRow, int startCol)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = 0, toRow = startRow; 
        fromRow < fromPixels.length &&
        toRow < toPixels.length; 
        fromRow++, toRow++)
        {
            for (int fromCol = 0, toCol = startCol; 
            fromCol < fromPixels[0].length &&
            toCol < toPixels[0].length;  
            fromCol++, toCol++)
            {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
            }
        }   
    }

    /** Method to create a collage of several pictures */
    public void createCollage()
    {
        Picture flower1 = new Picture("flower1.jpg");
        Picture flower2 = new Picture("flower2.jpg");
        this.copy(flower1,0,0);
        this.copy(flower2,100,0);
        this.copy(flower1,200,0);
        Picture flowerNoBlue = new Picture(flower2);
        flowerNoBlue.zeroBlue();
        this.copy(flowerNoBlue,300,0);
        this.copy(flower1,400,0);
        this.copy(flower2,500,0);
        this.mirrorVertical();
        this.write("collage.jpg");
    }

    /** Method to show large changes in color 
     * @param edgeDist the distance for finding edges
     */
    public void edgeDetection(int edgeDist)
    {
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        Color rightColor = null;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; 
            col < pixels[0].length-1; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][col+1];
                rightColor = rightPixel.getColor();
                if (leftPixel.colorDistance(rightColor) > 
                edgeDist)
                    leftPixel.setColor(Color.BLACK);
                else
                    leftPixel.setColor(Color.WHITE);
            }
        }
    }

    /* Main method for testing - each class in Java can have a main 
     * method 
     */
    public static void main(String[] args) 
    {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.zeroBlue();
        beach.explore();
    }

} // this } is the end of class Picture, put all new methods before this
