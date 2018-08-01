package com.game.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private static final int ROW_COUNT = 3;

    private RecyclerView recyclerView;
    private TextView tvGameState;
    private ImageButton ibClose;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview_cell);
        tvGameState = findViewById(R.id.tv_game_state);
        ibClose = findViewById(R.id.ib_close);

        ibClose.setOnClickListener(this);

        this.makeActivityAppearOnLockScreen();
        this.initRecyclerView();
    }


    private void initRecyclerView()
    {
        CellGridViewAdapter adapter = new CellGridViewAdapter(ROW_COUNT);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, ROW_COUNT));
        recyclerView.setAdapter(adapter);

        adapter.SetOnItemClickListener(new CellGridViewAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position)
            {

            }
        });

        adapter.SetOnGameFinishListener(new CellGridViewAdapter.OnGameFinishListener() {

            @Override
            public void onFinish(int winner)
            {
                if(winner == TicTacToe.Game.PLAYER_SMILE.getValue())
                {
                    String text = getEmojiByUnicode(TicTacToe.Game.PLAYER_SMILE_UNICODE.getValue()) + "  Rocks";
                    tvGameState.setText(text);
                }

                else if(winner == TicTacToe.Game.PLAYER_WINK.getValue())
                {
                    String text = getEmojiByUnicode(TicTacToe.Game.PLAYER_WINK_UNICODE.getValue()) + "  Rocks";
                    tvGameState.setText(text);
                }

                else
                {
                    String text = getEmojiByUnicode(TicTacToe.Game.PLAYER_SMILE_UNICODE.getValue()) + "  Tie  "
                            + getEmojiByUnicode(TicTacToe.Game.PLAYER_WINK_UNICODE.getValue());
                    tvGameState.setText(text);
                }

                tvGameState.setVisibility(View.VISIBLE);
            }
        });
    }

    private void makeActivityAppearOnLockScreen()
    {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                        | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                        | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
                        | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                        | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.tv_new_game:

                tvGameState.setVisibility(View.GONE);
                initRecyclerView();
                break;

            case R.id.ib_close:

                finish();
                break;
        }
    }


    public String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }
}