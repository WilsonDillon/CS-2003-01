public class SetTest
{ // Simple main function to test ADT Set
    public static void main(String[] args)
    {
      Integer[] M ={2,3,5,7,11,13,17,19,23,29};
      Integer[] N = {1,3,6,8,9,11,12,17,19,23,24,25,3};
	
      SetListBased<Integer> A = new SetListBased<Integer>(); 
      A.add(10);
      A.addAll(M);
      System.out.println("\nSet A Follows:");
      A.display();

      SetListBased<Integer> B = new SetListBased<Integer>(); 
      B.add(11);
      B.addAll(N);
      System.out.println("\nSet B Follows:");
      B.display();

      SetListBased<Integer> C = (SetListBased<Integer>) A.union(B);
      SetListBased<Integer> D = (SetListBased<Integer>) A.intersection(B);
      System.out.println("\nSet C which is (A Union B) Follows:");
      C.display();
      System.out.println("\nSet D which is (A intersect B) Follows:");
      D.display();

      SetListBased<Integer> E = (SetListBased<Integer>) A.difference(B);
      SetListBased<Integer> F = (SetListBased<Integer>) B.difference(A);
      System.out.println("\nSet E which is (A - B) Follows:");
      E.display();
      System.out.println("\nSet F which is (B - A) Follows:");
      F.display();
      System.out.println("\nElement 2 in Set E:");
      System.out.println(E.contains(2));
      System.out.println("\nElement 2 in set F:");
      System.out.println(F.contains(2));
   } // end main
} 