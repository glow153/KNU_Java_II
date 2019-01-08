package problem02;

import java.util.Random;

import problem02.callback.OnReceiveListener;

public class Recipient extends Thread {
	private DonationCenter dc;
	private OnReceiveListener listener;
	private int timeDiff;

	public Recipient(DonationCenter dc) {
		this.dc = dc;
		timeDiff = (new Random().nextInt(10) + 1) * 100;
		System.out.println("recipient time diff: " + timeDiff);
	};
	
	public void setOnReceiveListener(OnReceiveListener listener) {
		this.listener = listener;
	}

	@Override
	public void run() {
		while(true) {
			if(dc.getGoal() >= dc.getDonation()) {
				// TODO Auto-generated method stub
				int amount = (new Random().nextInt(300) + 1) * 1000;
				dc.withdraw(amount);
				listener.onReceive(amount);
				
				try {
					sleep(timeDiff);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				break;	
			}
		}
	}
}
