public class GameState {
    private char lastPlayed;
    private char[] board;

    public GameState(){
        lastPlayed = ' ';
        board = new char[]{'-', '-', '-', '-', '-', '-', '-', '-', '-'};
    }

    public boolean update(char value, int position){
        if(value != lastPlayed && board[position] == '-'){
            lastPlayed = value;
            board[position] = value;
            return true;
        }
        return false;
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
