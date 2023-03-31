package org.god.alien.whackamole_final.threads;

import org.god.alien.whackamole_final.component.GameFragment;

import java.util.Random;

/**
 * Created by dell on 2016/7/20.
 */
public class PopMoleRunnable implements Runnable{
    private final GameFragment gameFragment;
    private final long pop_duration;
    private volatile boolean isStop;
    private final int numOfButtons;

    public PopMoleRunnable(GameFragment gameFragment,
                    long pop_duration,
                    int numOfButtons){
        this.gameFragment = gameFragment;
        this.pop_duration = pop_duration;
        isStop = false;
        this.numOfButtons = numOfButtons;
    }

    public void setStop(boolean isStop){
        //UI-thread call here to stop the thread
        this.isStop = isStop;
    }

    @Override
    public void run() {
        while(!isStop){
            try {
                Thread.sleep(pop_duration);
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }
            gameFragment.getActivity().runOnUiThread(
                    new Runnable() {
                        @Override
                        public void run() {
                            Random random = new Random();
                            gameFragment.pop_Mole(random.nextInt(numOfButtons - 1) + 1);
                        }
                    }
            );
        }
    }
}
