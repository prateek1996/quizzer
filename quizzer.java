// A simple Swing application.
import javax.swing.*;
import java.awt.*; //Included to use FlowLayout
import java.awt.event.*;
import java.io.*;


class window extends JFrame{
	
	String[] q = new String [100];
	String[] ans = new String [100];
	int score = 0, n = 0;
	String input;
	JLabel question;
	
	window()
	{
		int i = 0;
		try 
		{
				FileInputStream file = new FileInputStream("questions.txt");
				BufferedReader br = new BufferedReader(new InputStreamReader(file));
				
				while(br.read()!=-1)
				{
				q[i] = br.readLine();
				ans[i] = br.readLine();
				i++;
				}
				draw();
		}
		
		catch(Exception e)
		{
			System.out.println("Couldn't open file");
		}
		
	}
	
	public void draw() 
	{

		setTitle("Quizzer!");
			
		setLayout(new FlowLayout());
		/*The FlowLayout class puts components in a row, sized at their preferred size. If the horizontal space in the container is too small to put all the 
		components in one row, the FlowLayout class uses multiple rows. If the container is wider than necessary for a row of components, the row is, by default, 
		centered horizontally within the container. To specify that the row is to aligned either to the left or right, use a FlowLayout constructor that takes an 
		alignment argument. Another constructor of the FlowLayout class specifies how much vertical or horizontal padding is put around the components. */
				
		// Give the frame an initial size.
		setSize(500, 500);
	
		// Terminate the program when the user closes the application.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Keeps window at centre
		setLocationRelativeTo(null);
		
		JButton next = new JButton("Next");
		add(next);
		
		JButton submit = new JButton("Submit");
		add(submit);
		
		
		question = new JLabel(q[n]);
		add(question);
		
		// Create a text-based label.
		JTextField text = new JTextField(42);
		add(text);
		
		
		
		
		// Display the frame.
		setVisible(true);
		
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
			
			input = text.getText();
			if(input.equalsIgnoreCase(ans[n]))
				{
						score+=10;
						setTitle("CORRECT! Current Score : "+score);
				}
			
			else if(!input.equals(""))
				{
					score-=10;
					setTitle("WRONG! Current Score : "+score);
				}
			revalidate();
			}
		});
		
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent pq) {
			n++;
			
			setVisible(false);
			
			remove(question);
			remove(text);
			question=new JLabel(q[n]);
			revalidate();
			
			if(q[n].equals("END"))
			{
				question=new JLabel("That's all");
				add(question);
				remove(submit);
				remove(next);
			}
			
			else
			{
			add(question);
			add(text);
			}
			
			setVisible(true);
			}
		});
		
	}
	
}

class quizzer
{
		public static void main(String args[]) {
	
		// Create the frame on the event dispatching thread.
		
		SwingUtilities.invokeLater(new Runnable() 
			{
				public void run() {
		
					window obj = new window();

				}
			});
			
			
		}

}	