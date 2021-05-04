import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class WordSearch {
    private char[][] board;

    private String[] words;
    /**
     * constructor
     * gets the number of rows and columns
     * sets the number of rows and columns to the double array
     */
    public WordSearch(){
        int row, col;

        //calling getGrid method which sets the rows and columns
        row = getGrid("rows");
        col = getGrid("cols");

        board = new char[row][col];

        words = new String[board.length];

        fillBoard();
        getWords();
        makeBoard();
    }

    /**
     * method is used to get the number of rows and cols
     * @return number which is the length of the row/col
     */
    public int getGrid(String section){
        Scanner input = new Scanner(System.in); //makes a scanner

        try {
            System.out.printf("Enter the number of %s (2-15): ", section);//prompt user to enter a number
            int number = input.nextInt(); //gets input from the user

            if (number >= 2 && number <= 15) {
                if(section.equals("rows")){
                    return number;
                }else if(section.equals("cols")){
                    return number;
                }
            }else{//if number is not between the range, the program will rerun
                System.out.println("The number needs to be in the range of (2-15).");
            }
        }catch(InputMismatchException e){//checks if the input is acceptable(integers)
            System.out.println("Unable to read input, please try again.");
        }
        return getGrid(section);
    }

    /**
     * fills the board with random letters
     */
    public void fillBoard(){
        Random num = new Random();
        for(int x = 0; x < board.length; x++){
            for(int y = 0; y < board[0].length; y++){
                char letter = (char)(num.nextInt(26)+ 'A');
                board[x][y] = letter;
            }
        }
    }

    /**
     * prints out the table
     * @return a String which is viewed as a table
     */
    @Override
    public String toString() {
        String words = "";
        for(int x = 0; x < board.length; x++){
            for(int y = 0; y < board[0].length; y++){
                words += board[x][y] + " ";
            }
            words += "\n";
        }
        return words;
    }

    /**
     * gets the words to enter into the table
     * @return the array of words
     */
    private void getWords(){
        Scanner input = new Scanner(System.in);

        //Making word String and an array to hold the words typed out
        String word;
        for(int x = 0; x < board.length;){
            System.out.print("Enter a word: ");
            word = input.next();
            if(word.length() <= board[0].length) {   //checks if word is longer than column
                words[x] = word.trim().toUpperCase();
                x++;
            }else{
                System.out.println("Please enter a word with " + board[0].length + " characters.");
            }
        }
    }
    public void makeBoard(){
        int range;
        for(int x = 0; x < board.length; x++) {
            range = board[0].length - words[x].length();
            range = (int) (Math.random() * range);
            for (int y = 0; y < words[x].length(); y++) {
                board[x][y + range] = words[x].charAt(y);
            }
        }
    }
}