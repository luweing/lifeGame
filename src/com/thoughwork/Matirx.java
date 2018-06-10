package com.thoughwork;

public class Matirx {
	private int maxRow;
	private int maxCol;
	
	private int[][]matrix;
	
	public Matirx(int maxRow,int maxCol) {
		this.maxRow=maxRow;
		this.maxCol=maxCol;
		
		matrix=new int[maxRow+2][maxCol+2];
		for(int row=0;row<=maxRow+1;row++) {
			for(int col=0;col<=maxCol+1;col++) {
				matrix[row][col]=0;
			}
		}
	}
	
	public void update() {
		int[][]newMatrix=new int[maxRow+2][maxCol+2];
		for(int row=1;row<=maxRow;row++) {
			for(int col=1;col<=maxCol;col++) {
				switch(getNeighborCount(row,col)) {
				case 2:
					newMatrix[row][col]=matrix[row][col];
					break;
				case 3:
					newMatrix[row][col]=1;
					break;
				default:
					newMatrix[row][col]=0;
				}
			}
		}
		for(int row=1;row<=maxRow;row++) {
			for(int col=1;col<=maxCol;col++) {
				matrix[row][col]=newMatrix[row][col];
			}
		}
	}

	private int getNeighborCount(int row, int col) {
		// TODO Auto-generated method stub
		int count=0;
		for(int i=row-1;i<=row+1;i++) {
			for(int j=col-1;j<=col+1;j++) {
				count+=matrix[i][j];
			}
		}
		return count-matrix[row][col];
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

}
