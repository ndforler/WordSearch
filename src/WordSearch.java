import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class WordSearch {
    private char[][] Board;

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

        //checking the rows and columns to see if the program ran properly
        System.out.println(row + " is the number of rows");
        System.out.println(col + " is the number of cols");
        Board = new char[row][col];

        fillBoard(row, col);
        getWords(col);
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
                getGrid(section);
            }
        }catch(InputMismatchException e){//checks if the input is acceptable(integers)
            System.out.println("Unable to read input, please try again.");
            getGrid(section);
        }
        return 0;
    }

    /**
     * fills the board with random letters
     * @param row numbers of rows the user entered
     * @param col number of columns the user entered
     */
    public void fillBoard(int row, int col){
        Random num = new Random();
        for(int x = 0; x < row; x++){
            for(int y = 0; y < col; y++){
                char letter = (char)(num.nextInt(26)+ 'A');
                Board[x][y] = letter;
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
        for(int x = 0; x < Board.length; x++){
            for(int y = 0; y < Board[0].length; y++){
                words += Board[x][y] + " ";
            }
            words += "\n";
        }
        return words;
    }

    /**
     * gets the words to enter into the table
     * @param col is the number of columns the user entered
     * @return the array of words
     */
    private String[] getWords(int col){
        Scanner input = new Scanner(System.in);

        //Making word String and an array to hold the words typed out
        String[] words = new String[Board.length];
        String word;
        for(int x = 0; x < Board.length;){
            System.out.print("Enter a word: ");
            word = input.next();
            if(word.length() <= col) {   //checks if word is longer than column
                words[x] = word.trim().toUpperCase();
                x++;
            }
        }
        return words;
    }
}
