import java.io.PrintStream;

import javax.lang.model.util.ElementScanner14;

public class TestSortedList {

    /**
     * Sample tests.
     *
     */
    private static final PrintStream OUT = System.out;

    public static void main(String[] args) {
      // Main function to test SortedList Class  
      SortedList<Integer> L = new SortedList<Integer>(20);
      Integer[] values = {10, 40, 30, 50, 20, 30};
      OUT.print("L = "); L.display();
      for (Integer val : values)
        L.sortedAdd(val);
      OUT.print("L = "); L.display();
      L.sortedRemove(20);
      OUT.print("L -20 = "); L.display();
      L.sortedRemove(30);
      OUT.print("L -30 = "); L.display();
      L.sortedRemove(40);
      OUT.print("L -40 = "); L.display();
      
      SortedList<Shape> M = new SortedList<Shape>();
      M.sortedAdd(new Disk(3));
      M.sortedAdd(new Disk(4));
      M.sortedAdd(new Square(7));
      M.sortedAdd(new Rectangle(10,5));
      M.sortedAdd(new Square(8));
      M.display(); 
      OUT.println();

      OUT.println("Comparing areas.. \n");
      Shape m = M.get(1);
      OUT.println("Item 1 is: \t"+m.toString());
      Shape n = M.get(2);
      OUT.println("Item 2 is: \t"+n.toString());
      OUT.println();

      int comparisonVal = m.compareTo(n);
      if (comparisonVal>0)
        OUT.println("Item 1 has greater area.");
      else if(comparisonVal<0)
        OUT.println("Item 2 has greater area.");
      else 
        OUT.println("Item 1 and Item 2 have equal area.");

    } // end main
  } // end class TestSortedList