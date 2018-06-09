package com.lifegame;

import java.util.Random;

public class Matrix {
	private static int height;
	private static int width;
	private static int [][] matrix;
	
	public Matrix() {
		
	}
	
	public Matrix(int height,int width) {
		// TODO Auto-generated constructor stub
		this.height=height;
		this.width=width;
	}
	//随机初始化矩阵
	public static int [][] getMatrix(){
		matrix =new int[height][width];
		for(int i=0;i<matrix.length;i++) {
			for(int j=0;j<matrix[i].length;j++) {
				if(new Random().nextInt(10)>8)
					matrix[i][j]=1;
				else
					matrix[i][j]=0;
			}
		}
		return matrix;
	}
	public void transform(int[][]matrix){
		int[][] nextMatrix=new int[height][width];
		for (int y = 0; y < matrix.length; y++) {
			for (int x = 0; x < matrix[0].length; x++) {
				nextMatrix[y][x]=0;
				int nearNum= findLifedNum(y,x);
				//等于3，则下一状态总是活
				if(nearNum==3){
					nextMatrix[y][x]=1;
				}
				//等于2，则与上一状态一样
				else if(nearNum==2){
					nextMatrix[y][x]=matrix[y][x];
				}
			}
		}
		matrix=nextMatrix;
	}
	public int findLifedNum(int y, int x){
		int num=0;
		//左边
		if(x!=0){
			num+=matrix[y][x-1];
		}
		//左上角
		if(x!=0&&y!=0){
			num+=matrix[y-1][x-1];
		}
		//上边
		if(y!=0){
			num+=matrix[y-1][x];
		}
		//右上
		if(x!=width-1&&y!=0){
			num+=matrix[y-1][x+1];
		}
		//右边
		if(x!=width-1){
			num+=matrix[y][x+1];
		}
		//右下
		if(x!=width-1&&y!=height-1){
			num+=matrix[y+1][x+1];
		}
		//下边
		if(y!=height-1){
			num+=matrix[y+1][x];
		}
		//左下
		if(x!=0&&y!=height-1){
			num+=matrix[y+1][x-1];
		}
		return num;
	}
}
