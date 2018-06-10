package com.thoughwork;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UI extends JFrame implements ActionListener{
	private JPanel buttonPanel;
	private JPanel gridPanel;
	private JPanel maPanel;
	private JButton bt_ok,bt_start,bt_pause,bt_next;
	private JLabel lbRow,lbCol ;
	private JComboBox rowList,colList;
	private boolean[][]isSelected;
	private JButton[][]btMatrix;
	private Matirx matrix;
	
	private int maxRow,maxCol;

	private Thread thread;

	private boolean isDead;
	private boolean isRunning;
	
	public UI(String name) {
		super(name);
		initGUI();
	}
	
	
	public void initGUI() {
		if(maxRow==0) {
			maxRow=100;
		}
		if(maxCol==0) {
			maxCol=100;
		}
		
		matrix=new Matirx(maxRow,maxCol);
		
		maPanel=new JPanel(new BorderLayout());
		buttonPanel=new JPanel();
		gridPanel=new JPanel(new GridLayout(maxRow, maxCol));
		
		rowList=new JComboBox();
		for(int i=10;i<=100;i++) {
			rowList.addItem(String.valueOf(i));
		}
		colList=new JComboBox();
		for(int i=10;i<=100;i++) {
			colList.addItem(String.valueOf(i));
		}
		rowList.setSelectedIndex(maxRow-10);
		colList.setSelectedIndex(maxCol-10);
		
		bt_ok=new JButton("确定");
		bt_start=new JButton("开始");
		bt_pause=new JButton("暂停");
		bt_next=new JButton("下一代");
		
		isSelected=new boolean[maxRow][maxCol];
		lbRow=new JLabel("行数：");
		lbCol=new JLabel("列数：");
		
		this.setContentPane(maPanel);
		maPanel.add(gridPanel,"Center");
		maPanel.add(buttonPanel,"South");
		
		btMatrix=new JButton[maxRow][maxCol];
		for(int i=0;i<maxRow;i++) {
			for(int j=0;j<maxCol;j++) {
				btMatrix[i][j]=new JButton("");
				btMatrix[i][j].setBackground(Color.WHITE);
				gridPanel.add(btMatrix[i][j]);
			}
		}
		
		buttonPanel.add(lbRow);
		buttonPanel.add(rowList);
		buttonPanel.add(lbCol);
		buttonPanel.add(colList);
		buttonPanel.add(bt_ok);
		buttonPanel.add(bt_start);
		buttonPanel.add(bt_next);
		buttonPanel.add(bt_pause);
		
		this.setSize(960, 720);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		bt_ok.addActionListener(this);
		bt_start.addActionListener(this);
		bt_next.addActionListener(this);
		bt_pause.addActionListener(this);
		for(int i=0;i<maxRow;i++) {
			for(int j=0;j<maxCol;j++) {
				btMatrix[i][j].addActionListener(this);
			}
		}
	}
	
	public int getMaxRow() {
		return maxRow;
	}

	public void setMaxRow(int maxRow) {
		this.maxRow = maxRow;
	}

	public int getMaxCol() {
		return maxCol;
	}

	public void setMaxCol(int maxCol) {
		this.maxCol = maxCol;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==bt_ok) {
			this.setMaxRow(rowList.getSelectedIndex()+10);
			this.setMaxCol(colList.getSelectedIndex()+10);
			initGUI();
			matrix=new Matirx(getMaxRow(), getMaxCol());
		}else if(e.getSource()==bt_next) {
			getNextGeneration();
		}else if(e.getSource()==bt_pause) {
			isRunning = false;
			thread=null;
		}else if(e.getSource()==bt_start) {
			isRunning=true;
			thread=new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					while(isRunning) {
						getNextGeneration();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						isDead=true;
						for(int row=1;row<=maxRow;row++) {
							for(int col=1;col<=maxCol;col++) {
								if(matrix.getMatrix()[row][col]!=0) {
									isDead=false;
									break;
								}
							}
							if(!isDead) {
								break;
							}
						}
						if(isDead) {
							isRunning=false;
							thread=null;
						}
					}
					
				}
			});
			thread.start();
		}else {
			int[][]grid=matrix.getMatrix();
			for(int i=0;i<maxRow;i++) {
				for(int j=0;j<maxCol;j++) {
					if(e.getSource()==btMatrix[i][j]) {
						isSelected[i][j]=!isSelected[i][j];
						if(isSelected[i][j]) {
							btMatrix[i][j].setBackground(Color.BLACK);
							grid[i+1][j+1]=1;
						}else {
							btMatrix[i][j].setBackground(Color.white);
							grid[i+1][j+1]=0;
						}
						break;
					}
				}
			}
		}
		
	}
	
	public void getNextGeneration() {
		matrix.update();
		int[][]grid=matrix.getMatrix();
		for(int i=0;i<maxRow;i++) {
			for(int j=0;j<maxCol;j++) {
				if(grid[i+1][j+1]==1) {
					btMatrix[i][j].setBackground(Color.BLACK);
				}else {
					btMatrix[i][j].setBackground(Color.WHITE);
				}
			}
		}
	}

	

}
