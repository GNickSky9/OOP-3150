import javax.swing.ImageIcon;
import java.awt.*;
import javax.swing.*;
import java.io.FileNotFoundException;

public class Element
{
	public ImageIcon rep;
	private Rectangle ele;
	public char letter;
	
	private String choose(char c)
	{
		String str = new String();
		str = c + ".png";
		return str;
	}
	
	public void setW(int w){ele.width = w;}
	public void setH(int h){ele.height = h;}
	public void setX(int x){ele.x = x;}
	public void setY(int y){ele.y = y;}
	public int getW(){return ele.width;}
	public int getH(){return ele.height;}
	public int getX(){return ele.x;}
	public int getY(){return ele.y;}
	public char getLetter(){return letter;}
		
	public Element(char c)
	{
		rep = new ImageIcon(choose(c));
		letter = c;
		ele = new Rectangle();
	}
	
	
	public static void main(String[] args) throws FileNotFoundException
	{

	}		
}
