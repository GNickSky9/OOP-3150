import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.Timer;

import java.util.*;

public class Simulation extends JPanel implements ActionListener
{
	public Robot OJ; // Orange Robot
	public Robot BL; // Blue Robot
	public Vector<Integer> ojPositions; // Moves Orange Has To Make
	public Vector<Integer> blPositions; // Moves Blue Has To Make
	public String sequence;	// The Sequence The Robots Have To Move
	public Hallway buttons; // Buttons For Orange And Blue
	private Timer tm;		// Timer For Driving The Events
	
	public JButton test1 = new JButton("Start");
	public boolean test1Bool = false;
	
	// Constructor
	public Simulation()
	{
		setBackground(Color.BLACK);
		OJ = new Robot();
		BL = new Robot();
		ojPositions = new Vector<Integer>();
		blPositions = new Vector<Integer>();
		sequence = new String();
		tm = new Timer(4000/30,this);
		buttons = new Hallway();
		fill();
		
		this.setLayout(null);
		test1.setBounds(700, 515, 100, 20);
		this.add(test1);
		this.setLayout(new BorderLayout());
	}
	
	// Set The Sequence For This Case
	public void setSeq(String str)
	{
		sequence = str;
		parseSeq();
	}
	
	// Parse The String And Store The Positions The Robots Have To Move To
	public void parseSeq()
	{
		char [] cArr = sequence.toCharArray();
		for(int i = 0; i < cArr.length-2; ++i)
		{
			if(cArr[i] == 'O')
				ojPositions.add(Character.getNumericValue(cArr[i+2]));
			else if(cArr[i] == 'B')
				blPositions.add(Character.getNumericValue(cArr[i+2]));
		}
	}
	
	// Printer Vector For Testing
	public <E> void printVec(Vector<E> vec)
	{
		for(int i = 0; i < vec.size(); ++i)
			System.out.print(vec.get(i) + " ");
		System.out.println();
	}
	
	// Initialize The Robots Positions/etc.
	public void fill()
	{
		OJ.body.x = 22;
		OJ.body.y = 220;
		OJ.body.width = 10;
		OJ.body.height = 10;
		OJ.setColor(Color.ORANGE);
		
		BL.body.x = 22;
		BL.body.y = 380;
		BL.body.width = 10;
		BL.body.height = 10;
		BL.setColor(Color.BLUE);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.setColor(Color.RED);
		g.setFont(new Font("Serif", Font.BOLD, 34));
		g.drawString("Bot Trust Simulation!", 420, 50);
		g.drawLine(420, 52, 735, 52);
		g.setFont(new Font("Serif", Font.PLAIN, 12));
		
		buttons.drawHallway(g);
		g.setColor(OJ.color);		
		g.fillOval(OJ.body.x, OJ.body.y, OJ.body.width, OJ.body.height);	
		g.setColor(BL.color);
		g.fillOval(BL.body.x, BL.body.y, BL.body.width, BL.body.height);
				
		g.setColor(Color.white);
		g.drawLine(0, 500, 1165, 500);
		g.setFont(new Font("Serif", Font.BOLD, 24));
		g.drawString("Stats:", 0, 530);
		g.drawLine(0, 532, 55, 532);
		g.setFont(new Font("Serif", Font.BOLD, 16));
		g.drawString("Sequence: " + sequence, 100, 530);
		
		g.drawLine(565,501,565,675);
		g.setFont(new Font("Serif", Font.BOLD, 24));
		g.drawString("Controls:", 570, 530);
		g.drawLine(570, 532, 665, 532);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
			if(e.getSource() == tm)
			{
				repaint();
			}
	}
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Bot Trust Sim");
		frame.setLayout(new BorderLayout());
		frame.setSize(1165,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		Simulation sim = new Simulation();
		frame.add(sim, BorderLayout.CENTER);		
		frame.setVisible(true);
		
		sim.tm.start();
		sim.setSeq("O 2, B 1, B 3, O 4");
	}
}
