// The "Picture" class.
import java.awt.*;
import java.awt.image.*;
import hsa.Console;

public class Picture2 implements ImageObserver
{
    Console c;           // The output console
    public static Image Pic;
    int x,y;

    public Picture2(String name, Console c, Image Pic, int x, int y)
    {
	this.c = c;
	Toolkit tk = Toolkit.getDefaultToolkit ();
	Pic = tk.getImage (name);
	this.Pic = Pic;
	this.x = x;
	this.y = y;
	tk.prepareImage (Pic, -1, -1, this);


    }


    public boolean imageUpdate (Image img, int infoflags, int x2, int y2, int width, int height)
    {

	c.drawImage (Pic, x, y, null);

	return true;
    }
} // Picture class
