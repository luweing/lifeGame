package com.lifegame;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class UI extends JFrame{
	private static int height=100;
	private static int width=100;
	private Button but1=new Button("开始");
	private Button but2=new Button("暂停");
	private JPanel panel1=new JPanel(new GridLayout(1, 2));
	private JPanel panel2=new JPanel(new GridLayout(height, width));
	private JTextField[][]Jmatrix;
	private int[][]initMatrix;
	private boolean isPuase=false;
	
	public UI (){
		
		but1.setFont(new Font("黑体",1,50));
		but2.setFont(new Font("黑体",1,50));

		panel1.add(but1);
		panel1.add(but2);
		
		initPanel();
	
		getContentPane().add("North",panel1);
		getContentPane().add("Center",panel2);
	    this.setSize(1000, 1200);
		this.setVisible(true);
		this.setLocationRelativeTo(null);//居中
		
		but1.addActionListener(new startGame());
		but2.addActionListener(new pauseGame());	
		
		panel2.updateUI();
		
	}
	//初始化界面
	private void initPanel() {
		// TODO Auto-generated method stub
		Jmatrix=new JTextField[height][width];
		new Matrix(height, width);
		initMatrix=Matrix.getMatrix();
		
		for(int i=0;i<Jmatrix.length;i++) {
			for(int j=0;j<Jmatrix[i].length;j++) {
				JTextField text=new JTextField();
				Jmatrix[i][j]=text;
				if(initMatrix[i][j]==1)
					 Jmatrix[i][j].setBackground(Color.black);
				else
					Jmatrix[i][j].setBackground(Color.white);
				panel2.add(text);
			}
		}
		
	}
	//开始游戏
	public class startGame implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		/*	changePanel(); 
			panel2.updateUI();*/
			 new Thread(new GameControlTask()).start();
			 panel2.updateUI();
			
		}
	}
	 private class GameControlTask implements Runnable {

	        @Override
	        public void run() {
	            while (true) {
	            	initMatrix=new Matrix().transform(initMatrix);
	            	changePanel();
	            	panel2.updateUI();
	            }
	        }
	    }

	//面板改变
	private void changePanel() {
		// TODO Auto-generated method stub
		for(int i=0;i<Jmatrix.length;i++) {
			for(int j=0;j<Jmatrix[i].length;j++) {
				/*JTextField text=new JTextField();
				Jmatrix[i][j]=text;*/
				if(initMatrix[i][j]==1)
					 Jmatrix[i][j].setBackground(Color.black);
				else
					Jmatrix[i][j].setBackground(Color.white);
//				panel2.add(text);
			}
		}
		panel2.updateUI();
	}
	//暂停
	public class pauseGame implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
            
		}

	}
	 
}
