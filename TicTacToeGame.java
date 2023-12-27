import java.util.Scanner;
import java.util.Random;

class TicTacToe{
    static char[][] board;
    public TicTacToe(){
       board = new char[3][3];
       initialiseBoard();
    }
    void initialiseBoard(){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j<board[i].length;j++){
                board[i][j] = ' ';
            }
        }
    }
    static void displayBoard(){
        System.out.println("-------------");
        for(int i = 0; i < board.length; i++){
            System.out.print("| ");
            for(int j = 0; j<board[i].length;j++){
               System.out.print(board[i][j] + " | ");
            }
          System.out.println();
          System.out.println("-------------");
        }
    }

    static void placeMark(int row, int column, char mark){
        if(row >=0 && row<=2 && column >=0 && column<=2){
            board[row][column] = mark;
        }
        else{
            System.out.println("Invalid input");
        }
    }

    static boolean columnWin(){
        for(int j = 0;j<board.length;j++){
            if(board[0][j]!= ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]){
                return true;
            }
        }
        return false;
    }
    static boolean rowWin(){
        for(int i = 0;i<board.length;i++){
            if(board[i][0]!= ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]){
                return true;
            }
        }
        return false;
    }
    static boolean diagonalWin(){
        if(board[0][0]!= ' ' && board[0][0]==board[1][1]&&board[2][2]==board[1][1]){
            return true;
        }
        else if(board[0][2]!= ' ' && board[0][2]==board[1][1]&&board[2][0]==board[1][1]){
            return true;
        }
        else{
            return false;
        }
    }
    static boolean checkDraw(){
        for(int i = 0; i<=2;i++){
            for(int j =0;j<=2;j++){
               if(board[i][j] == ' '){
                return false;
               }
            }
        }
        return true;
    }
}

abstract class Play{
    String name;
    char mark;  
    abstract void markTerritory();
    boolean moveValidityCheck(int row, int column){
        if(row >=0 && row<=2 && column >=0 && column<=2){
            if(TicTacToe.board[row][column] == ' '){
                return true;
            }
        }
        else{System.out.println("Invalid territory marked!");}
        return false;
    }
}
class PlayWithFriends extends Play{
    PlayWithFriends(String name, char mark){
        this.name = name;
        this.mark = mark;
    }
    void markTerritory(){
        Scanner scan = new Scanner(System.in);
        int row,column;
        do{
        System.out.println("Enter row and column you want to mark.");
        row = scan.nextInt();
        column = scan.nextInt();}while(!moveValidityCheck(row, column));
        TicTacToe.placeMark(row, column, mark);
    }
}
class PlayWithAI extends Play{
    PlayWithAI(String name, char mark){
        this.name = name;
        this.mark = mark;
    }
    void markTerritory(){
        int row,column;
        do
        {
            Random r = new Random();
            row = r.nextInt(3);
            column = r.nextInt(3);
        }
        while(!moveValidityCheck(row, column));
        TicTacToe.placeMark(row, column, mark);
    }
}
public class TicTacToeGame {
    public static void main(String[] args) {
        System.out.println("Lets start the game!!");
        Scanner sc = new Scanner(System.in);
        System.out.println("If you have a friend to play with press 2 and if you want to play with our AI press 1.");
        
        int value = sc.nextInt();
        System.out.println("Enter name of Players.");
        String nameOfPlayer1,nameOfPlayer2;
        nameOfPlayer1 = sc.next();
        nameOfPlayer2 = sc.next();
        TicTacToe s = new TicTacToe();
        PlayWithFriends player1 = new PlayWithFriends(nameOfPlayer1, 'X');
        Play player2 = null;
        if(value == 2){
             player2 = new PlayWithFriends(nameOfPlayer2, 'O');
        }
        else
        { player2 = new PlayWithAI(nameOfPlayer2, 'O');
    }

        Play currentPlayer;
        currentPlayer = player1;
        while(true){
            System.out.println(currentPlayer.name + ", its your turn!");
            currentPlayer.markTerritory();
            TicTacToe.displayBoard();
            if(TicTacToe.columnWin() || TicTacToe.rowWin() || TicTacToe.diagonalWin())
            {
             System.out.println(currentPlayer.name + " has won.");
             break;
            }
            else if(TicTacToe.checkDraw()){
              System.out.println("It's a Draw!!");
               break;
            }
            else
            {
                if(currentPlayer == player1){
                currentPlayer = player2;
                }
                else
                {
                  currentPlayer = player1;
                }
            }
        }

    }
}
