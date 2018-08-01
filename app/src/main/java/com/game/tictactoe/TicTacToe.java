package com.game.tictactoe;


public class TicTacToe
{
    private int array[][];
    private boolean isFinished;
    private int cell;

    private int winner = Game.PLAYER_NONE.getValue();


    public enum Game
    {
        PLAYER_NONE(-1),
        PLAYER_WINK(0),
        PLAYER_SMILE(1),

        PLAYER_WINK_UNICODE(0x1F609),
        PLAYER_SMILE_UNICODE(0x1F601);

        private final int value;

        Game(int value)
        {
            this.value = value;
        }

        public int getValue()
        {
            return value;
        }
    }


    TicTacToe(int cell)
    {
        this.cell = cell;
        this.array = new int[cell][cell];
    }

    /**
     * Initialize cell with -1 (Empty Cell)
     */
    public void initGame()
    {
        for(int x_index=0; x_index<array.length; x_index++)
        {
            for(int y_index=0; y_index< array.length; y_index++)
            {
                array[x_index][y_index]= -1;
            }
        }
    }


    /**
     * @param x_index - clicked x index coordinate
     * @param y_index - clicked y index coordinate
     * @param value - value (0 or 1)
     * @return
     */
    public boolean put(int x_index, int y_index, int value)
    {
        if(array[x_index][y_index] == -1)
        {
            array[x_index][y_index] = value;
            return true;
        }

        return false;
    }


    /**
     * check if there is any empty cell
     */
    public void isEmpty()
    {
        /**
         * if game is finished return
         */
        if(this.isFinished)
        {
            return;
        }

        /**
         * make game finished
         */
        this.isFinished = true;

        /**
         * check if any cell left (ex. value -1)
         * re-initialize isFinished to false
         */
        for(int[] arr: array)
        {
            for(int value: arr)
            {
                if(value == -1)
                {
                    this.isFinished = false;
                    break;
                }
            }
        }
    }


    /**
     * check for match
     * @param x_axis - clicked x index coordinate
     * @param y_axis - clicked y index coordinate
     */
    public void match(int x_axis, int y_axis, int player)
    {
        int x_count = 1, y_count = 1, r_count = 1, l_count = 1;

        int length = array.length-1;

        for(int index=0; index<array.length-1; index++)
        {
            /**
             * check coordinate horizontally
             * increment x_counter by 1 if match found
             */
            if(array[x_axis][index] == array[x_axis][index+1])
            {
                x_count++;
            }

            /**
             * check coordinate vertically
             * increment y_counter by 1 if match found
             */
            if(array[index][y_axis] == array[index+1][y_axis])
            {
                y_count++;
            }

            /**
             * check coordinate right diagonally
             * ex. start from (0, 0) and check (1, 1) is not empty and (0, 0) == (1, 1)
             * increment r_count by 1 if match found
             */
            if(array[index+1][index+1] != -1 && array[index][index] == array[index+1][index+1])
            {
                r_count++;
            }

            /**
             * check coordinate left diagonally
             * ex. start from (2, 2) and check (2, 2) is not empty and (2, 2) == (1, 1)
             * increment l_count by 1 if match found
             */
            if(array[index+1][length-1] != -1 && array[index][length] == array[index+1][length-1])
            {
                l_count++;
            }

            /**
             * if any count is equal to cell game over
             */
            if(x_count == cell || y_count == cell || r_count == cell || l_count == cell)
            {
                this.winner = player;
                this.isFinished = true;
                break;
            }

            length--;
        }
    }


    public int getCell()
    {
        return this.cell;
    }

    public void setCell(int cell)
    {
        this.cell = cell;
    }

    public void setFinished(boolean isFinished)
    {
        this.isFinished = isFinished;
    }

    public boolean isFinished()
    {
        return this.isFinished;
    }

    public int getWinner()
    {
        return this.winner;
    }

    public void setWinner(int winner)
    {
        this.winner = winner;
    }
}