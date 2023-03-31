package org.god.alien.whackamole_final.component;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import org.god.alien.whackamole_final.Game;
import org.god.alien.whackamole_final.threads.KillMoleRunnable;
import org.god.alien.whackamole_final.threads.PopMoleRunnable;
import org.god.alien.whackamole_final.R;

import java.util.ArrayDeque;
import java.util.Queue;

/*
* Caution!
* Use putLong() to put kill_duration and pop_duration
* */
public class GameFragment extends Fragment {
    private Queue<ImageButton> imageButtonsQueue;

    //parameters
    private static final String KILL_DURATION = "kill_duration";
    private static final String POP_DURATION = "pop_duration";

    private long kill_duration;
    private long pop_duration;

    //threads
    private KillMoleRunnable killMoleRunnable;
    private PopMoleRunnable popMoleRunnable;

    private ImageButton[] myImagebuttons;
    private Bitmap moleBitmap;
    private Bitmap holeBitmap;

    private int score;
    private int poptimes;

    public GameFragment() {
        // Required empty public constructor
    }

    private void dec_score(){
        //decrement the score
        --score;
    }

    private void add_score(){
        //increment the score
        ++score;
    }

    public int getScore(){
        return score;
    }

    public int getPoptimes(){
        return poptimes;
    }

    private void isGameOver(){
        if(poptimes == 40){
            if(score < 20)
                ((Game)getActivity()).game_over(false);
            else
                ((Game)getActivity()).game_over(true);
            stop_whack_a_mole();
        }
    }

    public void kill_topMole(){
        //remove the head of the queue
        if(!imageButtonsQueue.isEmpty()) {
            remove_fromSet(imageButtonsQueue.peek());
            dec_score();
        }
        isGameOver();//call gameOver
    }

    public void pop_Mole(int i){
        ImageButton tmpButton = myImagebuttons[i];
        if(! is_inSet(tmpButton)) {
            add_toSet(tmpButton);
            ++poptimes;
        }
        isGameOver();//call gameOver
    }

    public boolean is_inSet(ImageButton imageButton){
        return imageButtonsQueue.contains(imageButton);
    }

    public void remove_fromSet(ImageButton imageButton){
        //show animation & add score
        setHoleBackGround(imageButton);
        boolean success = imageButtonsQueue.remove(imageButton);
        if(success) {
            add_score();
            isGameOver();//call gameOver
        }
    }

    public void add_toSet(ImageButton imageButton){
        //show animation
        setMoleBackGround(imageButton);
        imageButtonsQueue.add(imageButton);
    }

    public static GameFragment newInstance(Context context,
            String mode, String moleImagePath){
        if(mode.equals("easy")){
            return newInstance(context, 2000, 400, moleImagePath);
        }
        else if(mode.equals("normal")){
            return newInstance(context, 1000, 300, moleImagePath);
        }
        else if(mode.equals("hard")){
            return newInstance(context, 700, 200, moleImagePath);
        }
        else{
            //Mode HELL
            return newInstance(context, 400, 130, moleImagePath);
        }
    }

    public static GameFragment newInstance(Context context,
                                           long kill_duration, long pop_duration,
                                           String moleImagePath) {
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        args.putLong(KILL_DURATION, kill_duration);
        args.putLong(POP_DURATION, pop_duration);
        fragment.setArguments(args);

        Drawable dr = context.getResources().getDrawable(R.drawable.molehill);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        fragment.holeBitmap = Bitmap.createScaledBitmap(bitmap, 150, 90, true);

        Log.i("=====", "Mole " + moleImagePath);
        if(moleImagePath!=null){
            bitmap = BitmapFactory.decodeFile(moleImagePath);
        }
        else{
            dr = context.getResources().getDrawable(R.drawable.mole);
            bitmap = ((BitmapDrawable) dr).getBitmap();
        }

        fragment.moleBitmap = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
        fragment.score = 0;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            kill_duration = getArguments().getLong(KILL_DURATION);
            pop_duration = getArguments().getLong(POP_DURATION);
        }
    }


    @Override
    public void onSaveInstanceState(Bundle state){
        state.putLong(KILL_DURATION, kill_duration);
        state.putLong(POP_DURATION, pop_duration);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        stop_whack_a_mole();
    }

    @Override
    public void onPause(){
        super.onPause();
        stop_whack_a_mole();
    }

    @Override
    public void onResume(){
        super.onResume();
        start_whack_a_mole();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state){
        super.onActivityCreated(state);
        //TODO

        //TODO change hammer image location
        imageButtonsQueue = new ArrayDeque<>();
        //myImagebuttons[0] is not used
        myImagebuttons = new ImageButton[10];
        myImagebuttons[1] = (ImageButton)getActivity().findViewById(R.id.imageButton);
        myImagebuttons[2] = (ImageButton)getActivity().findViewById(R.id.imageButton2);
        myImagebuttons[3] = (ImageButton)getActivity().findViewById(R.id.imageButton3);
        myImagebuttons[4] = (ImageButton)getActivity().findViewById(R.id.imageButton4);
        myImagebuttons[5] = (ImageButton)getActivity().findViewById(R.id.imageButton5);
        myImagebuttons[6] = (ImageButton)getActivity().findViewById(R.id.imageButton6);
        myImagebuttons[7] = (ImageButton)getActivity().findViewById(R.id.imageButton7);
        myImagebuttons[8] = (ImageButton)getActivity().findViewById(R.id.imageButton8);
        myImagebuttons[9] = (ImageButton)getActivity().findViewById(R.id.imageButton9);

        for(int i=1;i<10;++i)
            myImagebuttons[i].setOnClickListener(
                    new MyMoleButtonListener(myImagebuttons[i])
            );
        for (int i = 1; i < 10; ++i) {
            myImagebuttons[i].setBackgroundColor(Color.TRANSPARENT);
            setHoleBackGround(myImagebuttons[i]);
        }

        //Start the working thread
        killMoleRunnable = new KillMoleRunnable(this, kill_duration, 9);
        popMoleRunnable = new PopMoleRunnable(this, pop_duration, 9);
        start_whack_a_mole();
    }

    private void setMoleBackGround(ImageButton imageButton){
        imageButton.setImageBitmap(moleBitmap);
    }

    private void setHoleBackGround(ImageButton imageButton){
        imageButton.setImageBitmap(holeBitmap);
    }

    public void start_whack_a_mole(){
        /*
        * Start the thread
        * */
        killMoleRunnable.setStop(false);
        popMoleRunnable.setStop(false);
        new Thread(killMoleRunnable).start();
        new Thread(popMoleRunnable).start();
    }

    public void stop_whack_a_mole(){
        //stop the threads
        killMoleRunnable.setStop(true);
        popMoleRunnable.setStop(true);
    }

    public class MyMoleButtonListener implements View.OnClickListener{
        private ImageButton myImageButton;
        public MyMoleButtonListener(ImageButton imageButton){
            myImageButton = imageButton;
        }

        @Override
        public void onClick(View v) {
            /*
            * If in set, means the mole is popped up, need to remove it
            * */
            remove_fromSet(myImageButton);
        }
    }
}
