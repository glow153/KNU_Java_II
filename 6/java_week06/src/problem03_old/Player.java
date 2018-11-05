package problem03_old;

import java.util.Random;

public class Player {
	protected Random r;
	protected int life;
	protected int lastAction;
	
	public Player(boolean bot) {
		if(bot)
			r = new Random();
		life = 3;
		lastAction = -1;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getLastAction() {
		return lastAction;
	}

	public void setLastAction(int lastAction) {
		this.lastAction = lastAction;
	}
	
	public void setLastAction(String lastActionString) {
		for(int i=0;i<DragonBallFrame.actions.length;i++) {
			if(lastActionString.equals(DragonBallFrame.actions[i])) {
				lastAction = i;
				break;
			}
		}
	}
	
	public void selectAction() {
		while(true) {
			int selectedActionId = r.nextInt(3);
			if(selectedActionId == 2) {
				
			}
		}
	}
}
