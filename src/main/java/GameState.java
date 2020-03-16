public class GameState {
    private char lastPlayed;
    private char[] board;
    public static String NEXT_MOVE = "Next Move";
    public static String WINNER = "Winner";
    public static String TIE = "Tie";
    public static String INVALID = "Invalid";
    public GameState(){
        lastPlayed = ' ';
        board = new char[]{'-', '-', '-', '-', '-', '-', '-', '-', '-'};
    }

    public String update(char value, int position){
        if(value == lastPlayed || board[position] != '-'){
            return GameState.INVALID;
        }
        lastPlayed = value;
        board[position] = value;
        //Winner
        if(isSame(0,1,2) ||
                isSame(3,4,5) ||
                isSame(6,7,8) ||
                isSame(0,3,6) ||
                isSame(1,4,7) ||
                isSame(2,5,8) ||
                isSame(0,4,8) ||
                isSame(2,4,6)){
            return GameState.WINNER;
        }
        //Tie
        if(!new String(board).contains("-")){
            return GameState.TIE;
        }
        return GameState.NEXT_MOVE;
    }

    private boolean isSame(int i, int j, int k) {
        return board[i] != '-' && board[i] == board[j] && board[i] == board[k];
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("");
        for(int i=0;i<board.length; i++){
            output.append(board[i] +" ");
            if ((i+1)%3==0) {
                output.append("\n");
            }
        }
        return output.toString();
    }
}
