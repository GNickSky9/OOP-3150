import java.awt.Color;

public class Robot
{
	public Rectangle body; // Coordinates, Width, And Height Of Robot
	public int moves;	   // Moves Robot Has Made This Sequence
	public int nextPos;	   // Next Position Robot Has To Move To
	Color color;		   // Color Of Robot
	
	// Constructor
	public Robot()
	{
		body = new Rectangle();
		moves = 0;
		nextPos = 0;
	}
	
	// Set The Color
	public void setColor(Color c)
	{
		color = c;
	}
	
	// Move Robot Left On Screen
	public void moveLeft()
	{
		if(body.x == 0)
			return;
		body.x -= 10;
		++moves;
	}
	
	// Move Robot Right On Screen
	public void moveRight()
	{
		if(body.x == 1100)
			return;
		body.x += 10;
		++moves;
	}	
	
	// Robot Stays And Waits
	public void stay()
	{
		++moves;
	}
	
	// Robot Is Clear To Push The Button He Is At
	public void push()
	{
		++moves;
	}
}
