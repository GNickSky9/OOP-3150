import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class JMagicka extends JPanel implements ActionListener
{
    private PrintWriter pw;			// - For Outputting To File.
    private Scanner in;				// - For Reading From File.
    public Vector<String> combo;	// - First Two Characters = 2 Base Elements, 3rd Character = What The Latter Form.
    public Vector<String> oppose;	// - Two Elements That Oppose Each Other.
    public String spell;			// - n-length String Of Elements We Are To Invoke.
    public Vector<Character> eleList;	// - List Of Our Elements.
    
    // Default Constructor.
    public JMagicka()
    {
    	setBackground(Color.WHITE);
    	
    	combo = new Vector<String>();
    	oppose = new Vector<String>();
    	spell = new String();
    	eleList = new Vector<Character>();
    }
    
    // Reset Data Containers For Each Case From File.
    public void reset()
    {
    	combo.clear();
    	oppose.clear();
    	eleList.clear();
    	spell = "";
    }
    
    // Load Important Files At Start.
    public void loadFiles()
    {
    	try 
    	{
    		File file = new File("small.txt");
			in = new Scanner(file);
		}
    	catch(FileNotFoundException e)
    	{
			System.out.println("ERROR OPENING FILE!");
		}
    	try 
    	{
			pw = new PrintWriter(new FileWriter("output.txt"));
		}
    	catch(IOException e)
    	{
    		System.out.println("ERROR, CAN'T CREATE OUTPUT.TXT");
		}
    }
    
    // Close Files When Done.
    public void closeFiles()
    {
    	pw.flush();
        pw.close();
        in.close();
    }
    
    // Check If Last 2 Elements In Our Invoke List Combine To Form A New Element.
    // If They Do We Remove The 2 Elements And Append The New One.
    // Return True If The Above Occurred, Else Return False.
    private boolean isCombine(final Vector<Character> lis)
    {
        String temp = new String();
        char first, second, fuse;
        int size;
        
        for(int i = 0; i < combo.size(); ++i)
        {
            temp = combo.get(i);
            first = temp.charAt(0);
            second = temp.charAt(1);
            fuse = temp.charAt(2);
            size = lis.size();
            if(lis.size() >= 2 && lis.get(size-1).equals(first))
            {
                if(lis.get(size-2).equals(second))
                {
                    lis.remove(lis.size()-1);
                    lis.remove(lis.size()-1);
                    lis.add(fuse);
                    return true;
                }
            }
            else if(lis.size() >= 2 && lis.get(size-1).equals(second))
            {
                if(lis.get(size-2).equals(first))
                {
                    lis.remove(lis.size()-1);
                    lis.remove(lis.size()-1);
                    lis.add(fuse);
                    return true;
                }
            }
        }
        return false;
    }    
    
    // Check If Any 2 Elements Oppose Eachother In Our Invoke List.
    // If They Do, Clear The List And Return True.
    // Otherwise Do Nothing And Return False. 
    private boolean isOpposed(final Vector<Character> lis)
    {
        String temp = new String();
        char first, second;
        boolean stat1, stat2;
        
        for(int j = 0; j < oppose.size(); ++j)
        {
            temp = oppose.get(j);
            first = temp.charAt(0);
            second = temp.charAt(1);
            stat1 = false;
            stat2 = false;
            for(int i = 0; i < lis.size(); ++i)
            {
                if(lis.get(i) == first)
                    stat1 = true;
                if(lis.get(i) == second)
                    stat2 = true;
                if(stat1 && stat2)
                {
                    lis.clear();
                    return true;
                }
            }
        }
        return false;
    }   
    
    // Here We Parse Our List Of Elements We Are To Invoke.
    private void determine()
    {
    	eleList.add(spell.charAt(0));
        
        for(int i = 1; i < spell.length(); ++i)
        {
        	eleList.add(spell.charAt(i));
            if(!isCombine(eleList))
                isOpposed(eleList);
        }
        print_(eleList);
    } 
    
    // Handle All Input And Solve The Code Jam Problem.
    public void solve()
    {
        int d = 0, n = 0, c = 0, cases = 0, cnt = 1;
        
        cases = in.nextInt();
        in.nextLine();
        while(cnt != cases+1)
        {
            pw.print("Case #" + cnt + ": ");
            c = in.nextInt();
            if(c!=0)
            {
                for(int i = 0; i < c; ++i)
                {
                    String temp;
                    temp = in.next();
                    combo.add(temp);
                }
            }
                
            d = in.nextInt();
            if(d!=0)
            {
                for(int i = 0; i < d; ++i)
                {
                    String temp;
                    temp = in.next();
                    oppose.add(temp);
                }
            }
                
            n = in.nextInt();
            if(n!=0)
            {
                String temp;
                temp = in.next();
                spell = temp;
            }
            
            if(c == 0 && d == 0)
                print_(spell);
            else
                determine();

            reset();
            pw.println();
            in.nextLine();
            ++cnt;               
        }
        closeFiles();
    }
    
    public static void main(String[] args) throws Exception
    {
    	JFrame mf = new JFrame();
    	mf.setTitle("Magicka Simulation");
    	mf.setLayout(new BorderLayout());
    	mf.setSize(1100, 600);
    	mf.setLocationRelativeTo(null);
    	mf.setResizable(false);
    	mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	JMagicka solver1 = new JMagicka();
    	mf.add(solver1, BorderLayout.CENTER);
    	
    	mf.setVisible(true);
    	solver1.loadFiles();
    	solver1.solve();
    	
    }
    
    // Outputs String Nicely
    private void print_(String str)
    {
        pw.print("[");
        for(int i = 0; i < str.length(); ++i){
            if(i == str.length()-1)
                pw.print(str.charAt(i));
            else
                pw.print(str.charAt(i) + ", ");
        }
        pw.print("]");
    }
    // Outputs Vector Of Char Nicely
    private void print_(Vector<Character> vec)
    {
        pw.print("[");
        for(int i = 0; i < vec.size(); ++i)
        {
            if(i == vec.size()-1)
                pw.print(vec.get(i));
            else
                pw.print(vec.get(i) + ", ");
        }
        pw.print("]");
    }

	@Override
	public void actionPerformed(ActionEvent e)
	{
		
	}
}
