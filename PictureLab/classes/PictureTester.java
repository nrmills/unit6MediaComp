/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{

    public static void testCollage()
    {
        //Initialize canvas and inserted Picture
        Picture canvasPicture = new Picture((850),(875));
        Pixel[][] canvasPixels = canvasPicture.getPixels2D();

        //Black out canvas
        for( int row = 0; row < 850; row++ )
        {
            for( int col = 0; col < 875; col++ )
            {
                canvasPixels[row][col].setRed(0);
                canvasPixels[row][col].setGreen(0);
                canvasPixels[row][col].setBlue(0);
                
            }
        }

        Picture joker = new Picture("Joker.jpg");
        Picture scaledJoker = joker.scale();
        Pixel[][] jokerPixels = scaledJoker.getPixels2D();

        //Initialize shift row and col
        int shiftRow = 0;
        int shiftCol = 0;

        //Insert Picture into canvas
        for( int numRow = 0; numRow < 2; numRow++ )
        {
            shiftRow = 0;
            for( int numCol = 0; numCol < 3; numCol++ )
            {
                //Use copy to set images into the canvas
                canvasPicture.copy( joker,
                    0, 240,
                    0, 300,
                    25+shiftRow,
                    25+shiftCol);
                //Increment shiftRow
                shiftRow += 240;

            }
            //Increment shiftCol
            shiftCol += 500;
        }
        //First Column
        canvasPicture.keepOnlyRed(25,25,265,325);
        canvasPicture.keepOnlyGreen(265,25,505,325);
        canvasPicture.keepOnlyBlue(505,25,745,325);

        //Second Column
        canvasPicture.grayScale(25,525,265,825);
        canvasPicture.negate(265,525,505,825);
        canvasPicture.mirrorVerticalRightToLeft(505,525,745,825);

        //Show Changes
        canvasPicture.explore();
        canvasPicture.write("My Collage.jpg");
    }

    public static void testScale()
    {
        Picture beach = new Picture("Beach.jpg");

        Picture scaledImage = beach.scale();
        scaledImage.explore();
    }

    public static void testCopy()
    {
        Picture water = new Picture("water.jpg");
        Picture beach = new Picture("Beach.jpg");
        beach.copy( water, 10, 100, 20, 200, 30, 40 );
        beach.explore();
    }

    public static void testGrayScale()
    {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.grayScale(0,0,200,200);
        beach.explore();
    }

    public static void testNegate()
    {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.negate(0,0,200,200);
        beach.explore();
    }

    public static void testFixUnderwater()
    {
        Picture water = new Picture("water.jpg");
        water.explore();
        water.fixUnderwater();
        water.explore();
    }

    /** Method to test zeroBlue */
    public static void testZeroBlue()
    {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.zeroBlue(0,0,200,200);
        beach.explore();
    }

    public static void testKeepOnlyBlue()
    {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.keepOnlyBlue(0,0,200,200);
        beach.explore();
    }

    public static void testMirrorVerticalRightToLeft()
    {
        Picture caterpillar = new Picture("caterpillar.jpg");
        caterpillar.explore();
        //caterpillar.mirrorVerticalRightToLeft();
        caterpillar.explore();
    }

    /** Method to test mirrorVertical */
    public static void testMirrorVertical()
    {
        Picture caterpillar = new Picture("caterpillar.jpg");
        caterpillar.explore();
        //caterpillar.mirrorVertical();
        caterpillar.explore();
    }

    public static void testMirrorHorizontalBotToTop()
    {
        Picture caterpillar = new Picture("caterpillar.jpg");
        caterpillar.explore();
        caterpillar.mirrorHorizontalBotToTop();
        caterpillar.explore();
    }

    public static void testMirrorHorizontal()
    {
        Picture caterpillar = new Picture("caterpillar.jpg");
        caterpillar.explore();
        caterpillar.mirrorHorizontalBotToTop();
        caterpillar.explore();
    }

    /** Method to test mirrorTemple */
    public static void testMirrorTemple()
    {
        Picture temple = new Picture("temple.jpg");
        temple.explore();
        temple.mirrorTemple();
        temple.explore();
    }

    public static void testMirrorArms()
    {
        Picture snowman = new Picture("snowman.jpg");
        snowman.explore();
        snowman.mirrorArms();
        snowman.explore();
    }

    /** Method to test edgeDetection */
    public static void testEdgeDetection()
    {
        Picture swan = new Picture("swan.jpg");
        swan.edgeDetection(10);
        swan.explore();
    }

    /** Main method for testing.  Every class can have a main
     * method in Java */
    public static void main(String[] args)
    {
        // uncomment a call here to run a test
        // and comment out the ones you don't want
        // to run
        //testCrop();
        //testZeroBlue();
        //testKeepOnlyBlue();
        //testKeepOnlyRed();
        //testKeepOnlyGreen();
        //testNegate();
        //testGrayScale();
        //testFixUnderwater();
        //testMirrorVertical();
        //testMirrorHorizontal();
        //testMirrorVerticalRightToLeft();
        //testMirrorHorizontalBotToTop();
        //testMirrorTemple();
        //testMirrorArms();
        //testMirrorGull();
        //testMirrorDiagonal();
        //testCollage();
        //testCopy();
        //testEdgeDetection();
        //testEdgeDetection2();
        //testChromakey();
        //testEncodeAndDecode();
        //testGetCountRedOverValue(250);
        //testSetRedToHalfValueInTopHalf();
        //testClearBlueOverValue(200);
        //testGetAverageForColumn(0);
    }
}