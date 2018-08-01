package com.game.tictactoe;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.game.tictactoe.TicTacToe.Game;


public class CellGridViewAdapter extends RecyclerView.Adapter<CellGridViewAdapter.ViewHolder>
{

    private boolean flag = true;
    private OnItemClickListener clickListener;
    private OnGameFinishListener finishListener;

    private TicTacToe game;

    private int x_index = -1;
    private int y_index = -1;


    CellGridViewAdapter(int cell)
    {
        this.game = new TicTacToe(cell);
        this.game.initGame();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_cell, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(position % game.getCell() == 0)
        {
            x_index++;
            y_index = 0;
        }

        else
        {
            y_index++;
        }

        holder.ivCell.setTag(x_index + "-" + y_index);
    }

    @Override
    public int getItemCount()
    {
        return (game.getCell() * game.getCell());
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView ivCell;

        ViewHolder(View itemView)
        {
            super(itemView);

            itemView.setOnClickListener(this);
            ivCell = itemView.findViewById(R.id.iv_cell);
        }

        @Override
        public void onClick(View view)
        {
            clickListener.onItemClick(view, getAdapterPosition());

            if(game.isFinished())
            {
                return;
            }

            String tag = (String) ivCell.getTag();

            x_index = Integer.valueOf(tag.split("-")[0]);
            y_index = Integer.valueOf(tag.split("-")[1]);

            if(flag && game.put(x_index, y_index, Game.PLAYER_WINK.getValue()))
            {
                ivCell.setBackgroundResource(R.drawable.wink);
                flag = !flag;

                game.match(x_index, y_index, Game.PLAYER_WINK.getValue());
            }

            else if(game.put(x_index, y_index, Game.PLAYER_SMILE.getValue()))
            {
                ivCell.setBackgroundResource(R.drawable.smile);
                flag = !flag;

                game.match(x_index, y_index, Game.PLAYER_SMILE.getValue());
            }

            game.isEmpty();

            if(game.isFinished())
            {
                finishListener.onFinish(game.getWinner());
            }
        }
    }


    public interface OnItemClickListener
    {
        void onItemClick(View view, int position);
    }

    public interface OnGameFinishListener
    {
        void onFinish(int winner);
    }


    public void SetOnItemClickListener(final OnItemClickListener itemClickListener)
    {
        this.clickListener = itemClickListener;
    }

    public void SetOnGameFinishListener(final OnGameFinishListener finishListener)
    {
        this.finishListener = finishListener;
    }
}