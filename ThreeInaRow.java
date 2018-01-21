import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

/**
  * This class is a program that solves a even squared board puzzle having certain restrictions. Hint for the puzzle 
  * is already given in a file, where program reads the file and continues. Here, it inserts two color either Blue 
  * or White in the puzzle board where no three colors can be consecutive in row or column and number of each color 
  * has to be same on each particular row and column. It makes use of backtracking approach where color is filled 
  * in each cell of board until coloring a certain cell violates rules of the game. If keeping a color in a cell
  * violates rule of the game, it goes back one step and tries next color. This is approached with recursive 
  * approach until whole puzzle board is filled with those colors without violating any constraints.
  * @author Bidhan Bhandari
 */
public class ThreeInaRow {
   
   private String[][] puzzleBoard;
   private int boardSize;
   
   /**
     * Initializes the even squared puzzle board with the given hints 
     * @param fileName Name of file that contains hints for the solution of game
     * @param  N The number of rows and columns of squared puzzle board
    */
   public ThreeInaRow(String fileName, int N)
   {
      puzzleBoard = new String[N][N];//initializes a N*N board
      boardSize  = N;
      try//Catch exception inside this block
      {
         Scanner in = new Scanner(new File(fileName));
         int count = 0;
         while(in.hasNextLine()&&count<N)
         {
            String[] hintArray = in.nextLine().split("");
            System.out.println(hintArray.length);
            
            for (int i = 0; i<N; i++)//Puts hints in puzzle board
            {
               if (hintArray[i]!=null)
               {
                  puzzleBoard[count][i] = hintArray[i];
               }
               else
               {
                  puzzleBoard[count][i] = " ";
               }
            }
            count++;
         }         
      }
      catch (FileNotFoundException e)//Handles if given file is not found
      {
         System.out.println("File Not Found!");
      }
   }
   
   /**
     * Helper method that checks feasibility of particular color in particular cell
     * @param board Puzzle board that is being solved
     * @param row Row of the cell that is being checked for feasibility
     * @param column Column of the cell that is being checked for feasibilty
     * @param color The color that we are currently considering for
     * @return True if the color is feasible in that cell; Otherwise False
    */
   private boolean safeMove(String[][] board, int row, int column, String color)
   {
      if (column>1)//checks left two cells if they have same color already
      {
         if (board[row][column-1].equals(color)  && board[row][column-2].equals(color))
         {
            return false;
         }
      }
      if (column<(board.length)-2)//checks right two cells if they have same color already
      {
         if (board[row][column+1].equals(color)  && board[row][column+2].equals(color))
         {
            return false;
         }
      }
      if (row>1)//checks Down two cells if they have same color already
      {
         if (board[row-1][column].equals(color)  && board[row-2][column].equals(color)){
            return false;
         }
      }
      if (row<(board.length)-2)//checks Up two cells if they have same color already
      {
         if (board[row+1][column].equals(color)  && board[row+2][column].equals(color))
         {
            return false;
         }
      }
   
      if (column>0 && column<(board.length)-1)//Checks one cell to the left and right if they have same color already
      {
         if (board[row][column-1].equals(color)  && board[row][column+1].equals(color))
         {
            return false;
         }
      }
      if (row>0 && row<(board.length)-1)//Checks one cell to the up and down if they have same color already
      {
         if (board[row-1][column].equals(color)  && board[row+1][column].equals(color))
         {
            return false;
         }
      }
      int count1 = 0;
      int count2 = 0;
      for(int i = 0; i<boardSize; i++)
      {
      
         if (board[row][i].equals(color))//Counts number of color into consideration in the given row
         {
            count1++;
         }
         if(board[i][column].equals(color))//Counts number of color into consideration in the given column
         {
            count2++;
         }
      }
      //Checks if number of color into consideration is half the size of number of columns or rows into consideration
      if(count1>=(int)(boardSize/2)||count2>=(int)(boardSize/2))
      {
         return false;
      }
      return true;
   }
   
   /**
     * Helper recursive method that checks if puzzle is solved 
     * @param board The puzzle board that is being solved
     * @param row Row that is being considered into currently
     * @param column Column that is being considered currently
     * @return True if puzzle is solved; otherwise False
    */
    private static int comparisons = 0;
   private boolean completeMove(String[][] board,int row, int column)
   {
      final String[] COLORS = {"B","W"};
      if ((row+1)*(column) >= boardSize * boardSize)//Base case to check if every row and column is filled without vioalting any constraints
      {
         System.out.println(comparisons);
         return true;
      }
      if ( column >= boardSize )//Changes row if column reaches to the end and makes starts form zeroth column again
      {
         return (completeMove(board, row+1, 0));
      }
      
      if (board[row][column].equals(" "))//Solves if given cell is empty
      {
      /*If a chosen color does not lead to a solution, while backtracking this loop deos not allow the same color to be repeated again
        in the particular cell and opts for the next color in that particular spot*/
         for (int i = 0; i< COLORS.length; i++)
         {
            if (safeMove(board, row, column, COLORS[i]))//Checks if blue is feasible in the spot
            {
               comparisons++;
               board[row][column] = COLORS[i];
               if (completeMove(board,row, column+1))//Continues to solve in next column of same row
               {
                  return true;
               }
               board[row][column] = " ";//BackTracks
            }
                 
         }
      }
      else//Continues towards solution by skipping already given hint
      {
         return completeMove(board, row, column+1);
      }
      return false;	
      
      
   }
   
   /**
     * Returns string representation of the puzzle board. It returns original board of puzzle is not solved. For solved puzzle,
     * it return final situation of board.
     * @return String representation of puzzle board
    */
   public String toString()
   {
      completeMove(puzzleBoard,0,0);
      StringBuilder grid = new StringBuilder();
      //Loops through the table and appends into string builder object
      for (int i = 0; i<puzzleBoard.length; i++)
      {
         for(int j = 0; j<puzzleBoard[i].length;j++)
         {
            grid.append(puzzleBoard[i][j]+" ");
         }
         if (i<(puzzleBoard.length)-1)
         {
            grid.append("\n");
         }
      }
      return grid.toString();
   }
   
   /**
     * Checks to see if puzzle is solved and returns different string value accordingly
     * @return String represenation of puzzle if puzzle is solved otherwise NONE
    */
   public String solution()
   {
      if(completeMove(puzzleBoard,0,0))//Proceeds if puzzle is solved 
      {
         StringBuilder grid = new StringBuilder();
         grid.append("|");
         for (int i = 0; i<puzzleBoard.length; i++)//Loops through every cell
         {
            for(int j = 0; j<puzzleBoard[i].length;j++)
            {
               grid.append(puzzleBoard[i][j]);
            }
            grid.append("|");
         }
         return grid.toString();
      }
      else//Proceeds if puzzle is not solved at all
      {
         return "NONE";
      }
   }

}


