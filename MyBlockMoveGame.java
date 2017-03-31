package graphicalInterface;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MyBlockMoveGame extends JFrame{
	final int RC = 3;
	final int N = RC*RC;
	int [] num = new int[N];
	JButton[] button = new JButton[N];// create Button array
	JButton reset = new JButton("Start/Reset");
	JPanel pnlBody = new JPanel();
	JPanel pnlFoot = new JPanel();
	
	
	public MyBlockMoveGame(){
		//frame
		setTitle("simple block moving game");
		setSize(350,350);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//panel
		pnlFoot.add(reset);
		pnlBody.setLayout(new GridLayout(RC,RC));
		getContentPane().setLayout(new BorderLayout() );
		getContentPane().add(pnlBody, BorderLayout.CENTER);
		getContentPane().add(pnlFoot, BorderLayout.SOUTH);
		Font font = new Font("Times New Rome", 0, 24 );
		//pnlBody
		for(int i = 0;i<N;i++){
			num[i]=i+1;
			button[i] = new JButton(i+1+"");
			button[i].setFont(font);
			pnlBody.add(button[i]);
		}
		button[N-1].setVisible(false);
		
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				btn_StartClick();
			}
		});
		
		for(int i = 0;i<N;i++){
			button[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					for(int j = 0;j<N;j++){
						if(e.getSource() == button[j]) btn_Click(j);
					}
				}
			});
		}

		
	}
	void btn_StartClick(){
		System.out.println("start");
		Random random = new Random();
		for(int i = 0;i<100;i++){
			int k = random.nextInt(N);
			int j = random.nextInt(N);
			swap(num,j,k);
		}
		for(int i = 0;i<N;i++){
			button[i].setText(num[i]+"");
			button[i].setVisible(true);
			if(num[i]==N) button[i].setVisible(false);
		}
		
	}
	void swap(int[] num, int j, int k){
		int tmp = num[j];
		num[j] = num[k];
		num[k] = tmp;
	}
	void btn_Click(int j){
		int i = findBlank();
		boolean neighbor = isNeighbor(i,j);
		if(neighbor){
			swap(num,i,j);
			button[i].setText(num[i]+"");
			button[j].setText(num[j]+"");
			button[i].setVisible(true);
			button[j].setVisible(false);
		}
		boolean success = isSuccessful();
		if (success) {
//			System.out.println("success");
			JOptionPane.showMessageDialog(this,
					"You Won!");
		}

		
	}
	int findBlank(){
		for(int i = 0;i<N;i++){
			if(num[i]==N ) return i;
		}
		return -1;
	}
	boolean isNeighbor(int i,int j){
		if((i==j+1||i==j-1)&&(i/RC==j/RC)) return true;
		if(i==j+RC||i==j-RC) return true;
		return false;
	}
	boolean isSuccessful(){
		for (int i = 0;i<N;i++){
			if(num[i]!=i+1) return false; 
		}
		return true;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(()->{
			new MyBlockMoveGame().setVisible(true);
		});
	}

}
// end of file