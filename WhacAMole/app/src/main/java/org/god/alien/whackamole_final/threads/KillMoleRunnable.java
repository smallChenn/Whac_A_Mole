package org.god.alien.whackamole_final.threads;

import org.god.alien.whackamole_final.component.GameFragment;

/**
 * Created by dell on 2016/7/20.
 */
public class KillMoleRunnable implements Runnable{
    private final GameFragment gameFragment;
    private final long kill_duration;
    private volatile boolean isStop;
    private final int numOfButtons;

    public KillMoleRunnable(GameFragment gameFragment,
                            long kill_duration,
                            int numOfButtons){
        isStop = false;
        this.gameFragment = gameFragment;
        this.kill_duration = kill_duration;
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
                Thread.sleep(kill_duration);
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }
            gameFragment.getActivity().runOnUiThread(
                    new Runnable() {
                        @Override
                        public void run() {
                            gameFragment.kill_topMole();
                        }
                    }
            );
        }
    }
}
