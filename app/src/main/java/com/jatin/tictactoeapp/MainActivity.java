package com.jatin.tictactoeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Representations
    //0=O
    //1=X
    //2=Null
    boolean gameactive = true;
    int activeplayer = 1; //first play to x
    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};//1d array of whole grid that is null  as initialization
    int[][] winposotions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}//all possible win positions
    };

    public void Playertap(View view) {
        ImageView img = (ImageView) view;//typecasting inside the  view(stores the objects inside the  view)
        int tappedImage = Integer.parseInt(img.getTag().toString());//converting integer view to string
        if (!gameactive) {    //if boolean is false then gamereset
            gamereset(view);
        }
        if (gamestate[tappedImage] == 2 && gameactive) {
            gamestate[tappedImage] = activeplayer;
            img.setTranslationY(-1000f);
            if (activeplayer == 1) {
                img.setImageResource(R.drawable.x);
                activeplayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("O's turn --Tap to Play");
            } else {
                img.setImageResource(R.drawable.o);
                activeplayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("x's turn --Tap to Play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
            //Check if any player has won
            for (int[] winpositions : winposotions)
                if (gamestate[winpositions[0]] == gamestate[winpositions[1]] && gamestate[winpositions[1]] == gamestate[winpositions[2]] && gamestate[winpositions[0]] != 2)
                //somebody has won
                {
                    //find out who won
                    String winnerStr;
                    gameactive = false;
                    if (gamestate [winpositions[0]] == 0) {
                        winnerStr = "O has Won";
                    } else {
                        winnerStr = "X has Won";
                    }
                    //update the status bar for winner announcements
                    TextView status = findViewById(R.id.status);
                    status.setText(winnerStr);
                }
            boolean emptySquare = false;
            for (int squareState : gamestate) {
                if (squareState == 2) {
                    emptySquare = true;
                    break;
                }
            }
            if (!emptySquare && gameactive) {
                // Game is a draw
                gameactive = false;
                String winnerStr;
                winnerStr = "No one won";
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }

}
    public void gamereset(View view) {
    gameactive=true;
    activeplayer=1;
    for(int i=0;i<gamestate.length;i++)
    {gamestate [i]=2;}
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    } }
