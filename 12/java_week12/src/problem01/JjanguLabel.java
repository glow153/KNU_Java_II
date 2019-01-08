package problem01;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class JjanguLabel extends JLabel implements Runnable {
	public static final int FPS = 60; 
	private Random r;
	private int xSpeed, ySpeed;
	private int xSize, ySize;
	private int xPos, yPos;
	private int xMax, yMax;
	private int xDir, yDir;
	
	private OnJjanguMovedListener listener;
	
	public void setOnJjanguMovedListener(OnJjanguMovedListener listener) {
		this.listener = listener;
	}
	
	public JjanguLabel(int xMax, int yMax) {
		this.xMax = xMax;
		this.yMax = yMax;
		r = new Random();

		//1. 아이콘 설정
		ImageIcon ii = new ImageIcon("img\\jjangu.png");
		setIcon(ii);
		//2. 사이즈 설정
		xSize = ii.getIconWidth();
		ySize = ii.getIconHeight();
		setSize(xSize, ySize);
		//3. 위치 설정
		xPos = r.nextInt(xMax - xSize);
		yPos = r.nextInt(yMax - ySize);
		//4. 속도 설정
		xSpeed = r.nextInt(4) + 1;
		ySpeed = r.nextInt(4) + 1;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//random direction
		switch(r.nextInt(4)) {
		case 0:
			xDir = 1;
			yDir = 1;
			break;
		case 1:
			xDir = -1;
			yDir = 1;
			break;
		case 2:
			xDir = 1;
			yDir = -1;
			break;
		case 3:
			xDir = -1;
			yDir = -1;
			break;
		}
		
		while(true) {
			changeDirection();
			xPos += (xDir * xSpeed);
			yPos += (yDir * ySpeed);
			setLocation(xPos, yPos);
			listener.onMoved();
			
			try {
				Thread.sleep(1000 / FPS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void changeDirection() {
		if (xPos > xMax - xSize) {
			xDir = -1;
		} else if (xPos <= 0) {
			xDir = 1;
		}
		
		if (yPos > yMax - ySize) {
			yDir = -1;
		} else if (yPos <= 0) {
			yDir = 1;
		}
	}
}
