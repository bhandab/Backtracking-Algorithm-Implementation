/**
   Main method to test the methods of the Three-in-a-Row class.    
*/ 
public class PuzzleTest  
{
   public static void main(String[] args) 
   {
      int matching;
      
      try {
         ThreeInaRow puzzle0 = new ThreeInaRow("puzzle0.txt", 4);
         //System.out.println(puzzle0.printBoard());
         //ThreeInaRow puzzle1 = new ThreeInaRow("puzzle1.txt", 6);
         //System.out.println(puzzle1.printBoard());
         //ThreeInaRow puzzle3 = new ThreeInaRow("puzzle3.txt", 8);
         //System.out.println(puzzle3.printBoard());
         

         

         
         System.out.println("SOLUTION:\n\n" + puzzle0);
         matching = countMatches(puzzle0.solution(), "|BWWB|BBWW|WBBW|WWBB|"); 
         if (matching == 16)
            System.out.println("Problem 0 solved!!");
         else       
            System.out.println("Problem 0: " + matching + " matching characters");
      
         ThreeInaRow puzzle1 = new ThreeInaRow("puzzle1.txt", 6);
         System.out.println("SOLUTION:\n\n" + puzzle1);
         matching = countMatches(puzzle1.solution(), "|BWWBBW|WWBWBB|BBWBWW|WBBWWB|BWBWBW|WBWBWB|"); 
         if (matching == 36)
            System.out.println("Problem 1 solved!!");
         else       
            System.out.println("Problem 1: " + matching + " matching characters");
         
         ThreeInaRow puzzle2 = new ThreeInaRow("puzzle3.txt", 8);
         System.out.println("SOLUTION:\n\n" + puzzle2);
         matching = countMatches(puzzle2.solution(), 
            "|BWWBWBWB|WWBWBWBB|BBWBWBWW|WBBWBWBW|BWBWBWWB|BWWBWBBW|WBWBBWBW|WBBWWBWB|");
         if (matching == 64)
            System.out.println("Problem 2 solved!!");
         else       
            System.out.println("Problem 2: " + matching + " matching characters");
      }
      catch (Exception e) {
         System.out.println("EXCEPTION " + e);
      }
   }
 
 /** Counts the number of matching characters (other than '|') between two strings.
     @param str1 one of the strings to be compared
     @param str2 the second string to be compared
     @returns the number of matching characters, excluding '|'
 */  
   public static int countMatches(String str1, String str2)
   {
      int count = 0;
      int limit = Math.min(str1.length(), str2.length());
      for (int i = 0; i < limit; ++i)
         if (str1.charAt(i) == str2.charAt(i) && str1.charAt(i) != '|')
            count++;
      return count;
   }
}
