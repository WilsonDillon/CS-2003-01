import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// import Lab4.MinMaxObj;

public class Lab4 {
	
	public static void main(String[] args) {
		ArrayList<Double> numbers = new ArrayList<Double>();
		try {
			File data = new File("lab4.dat");
			Scanner scan = new Scanner(data);
			
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				numbers.add(Double.parseDouble(line));
			}
		} catch (FileNotFoundException e){
			System.err.println(e.getMessage());
		}
		Lab4 lab = new Lab4();
		MinMaxObj mmo = findMinMax(numbers);
		System.out.println("Max\t:" + mmo.getMax() +
				"\nMaxPos\t:" + mmo.getMaxPos() +
				"\nMin\t:" + mmo.getMin() +
				"\nMinPos\t:" + mmo.getMinPos());
	}
	
	/**
	 * A method for finding the minimum and maximum values and indices of an arraylist.
	 * @param list
	 * @return MinMaxObj containing the min, max, and their indices
	 */
	public static MinMaxObj findMinMax(ArrayList<Double> list) {
		MinMaxObj mmo = new MinMaxObj(list.get(0), list.get(0), 0, 0);
		return findMinMax(mmo, 1, list);
	}
	
	/**
	 * The private method for finding the minimum and maximum values and indices of an arraylist.
	 * @param mmo 	- A MinMaxObj that will hold the min and maximum values and indices of the list
	 * @param index - An integer specifying which index to compare (0 <= index <= list.size())
	 * @param list	- An ArrayList of Doubles containing the list to find the min and max of.
	 * @return A MinMaxObj containing the min and max values and indices from list
	 */
	private static MinMaxObj findMinMax(MinMaxObj mmo, int index, ArrayList<Double> list) {
		// TODO: Complete the recursive findMinMax method
		if (index > list.size()-1) {
            return mmo;
        }
        else {
            if (list.get(index) < mmo.getMin()) {
                mmo.setMin(list.get(index));
                mmo.setMinPos(index);
                return findMinMax(mmo, index+1, list);
            }
            else if (list.get(index) > mmo.getMax()) {
            	mmo.setMax(list.get(index));
                mmo.setMaxPos(index);
                return findMinMax(mmo, index+1, list);
            }
            else {
                return findMinMax(mmo, index+1, list);
            }
        }
	}
	
	/**
	 * A class for storing the maximum, minimum, maximum position, and minimum position
	 */
	public static class MinMaxObj{
		/*
		 * Needs the following private variables:
		 * 	double max
		 * 	double min
		 * 	int maxPos
		 * 	int minPos
		 * 
		 * as well as getters and setters for each of them
		 * and a constructor taking an initial value for each 
		 * private variable
		 * 
		 *
		 */
		
		// TODO: Complete the MinMaxObj inner class
		 private double max;
         private double min;
         private int maxPos;
         private int minPos;

		public MinMaxObj(double mx, double mn, int mxP, int mnP) {
			max = mx;
            min = mn;
            maxPos = mxP;
            minPos = mnP;
		}

        public double getMax() {
            return max;
        }

        public double getMin() {
            return min;
        }

        public int getMaxPos() {
            return maxPos;
        }

        public int getMinPos() {
            return minPos;
        }

        public void setMax(double newMax) {
            max = newMax;
        }

        public void setMin(double newMin) {
            min = newMin;
        }

        public void setMaxPos(int newMaxPos) {
            maxPos = newMaxPos;
        }

        public void setMinPos(int newMinPos) {
            minPos = newMinPos;
        }
	}
}