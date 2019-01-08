package problem02;

import java.util.Random;

import problem02.callback.DonationCenterCallback;

public class DonationCenter {
	private DonationCenterCallback callback;
	protected int goal, balance;
	
	public DonationCenter(DonationCenterCallback callback) {
		this.callback = callback;
		goal = (new Random().nextInt(50) + 1) * 100000;
		balance = 0;
	}
	
	public synchronized void donate(int amount) {
		balance += amount;
		refreshLabel();
		notify();
	}
	
	public synchronized void withdraw(int amount) {
		while (balance < amount) {
			try {
				callback.noMoney();
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		balance -= amount;
		refreshLabel();
	}
	
	public void refreshLabel() {
		if(goal > balance)
			callback.onRefresh("목표 기부금 : " + goal + ", 현재 기부금 : " + balance);
		else
			callback.onRefresh("목표 기부금 : " + goal + ", 현재 기부금 : " + balance + "  - 목표 달성!!");
	}

	public synchronized int getGoal() {
		return goal;
	}

	public synchronized int getDonation() {
		return balance;
	}
}
