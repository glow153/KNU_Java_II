package problem02;

import java.util.Random;

import problem02.callback.OnDonateListener;

public class Donator extends Thread {
	private DonationCenter dc;
	private OnDonateListener listener;
	private int timeDiff;

	public Donator(DonationCenter dc) {
		this.dc = dc;
		timeDiff = (new Random().nextInt(10) + 1) * 100;
		System.out.println("donator time diff: " + timeDiff);
	}
	
	public void setOnDonateListener(OnDonateListener listener) {
		this.listener = listener;
	}

	@Override
	public void run() {
		while(true) {
			if (dc.getGoal() >= dc.getDonation()) {
				// TODO Auto-generated method stub
				int amount = (new Random().nextInt(500) + 1) * 1000;
				dc.donate(amount);
				listener.onDonate(amount);
				
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
