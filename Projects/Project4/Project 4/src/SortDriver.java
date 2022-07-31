import java.util.Random;
import java.io.*;

public class SortDriver extends Sorting{
    public static void main(String[] args) throws FileNotFoundException{

        int arraySizes[] = {100,1000,10000};
		int nIterations = 10;
		Random rand = new Random();

        Long arrayOut [][][]= new Long[3][6][10];
        Long arrayAvg [][]= new Long[3][6];
        Long arrayStDev [][]= new Long[3][6];
        String arrayNames []= new String[]{"Selection", "Bubble", "Insertion", "Merge", "Quick", "Radix"};
        Double arrayQ [][][] = new Double[3][5][5];
        String arrayD [][][] = new String[3][5][5];

		for(int i = 0; i <arraySizes.length; i++) {
			int iSize=arraySizes[i];
			Integer arrayOrig[] []= new Integer[10][iSize];
			for(int k = 0; k < nIterations; k++) {

				for(int idx = 0; idx < iSize; idx++) {
					arrayOrig[k][idx] = rand.nextInt(1000);
				}

				Integer arrayCopyss[] = new Integer[iSize];
				Integer arrayCopybs[] = new Integer[iSize];
                Integer arrayCopyis[] = new Integer[iSize];
                Integer arrayCopyms[] = new Integer[iSize];
                Integer arrayCopyqs[] = new Integer[iSize];
                Integer arrayCopyrs[] = new Integer[iSize];

				for(int idx = 0; idx < iSize; idx++) {
					arrayCopyss[idx] = arrayOrig[k][idx];
					arrayCopybs[idx] = arrayOrig[k][idx];
                    arrayCopyis[idx] = arrayOrig[k][idx];
                    arrayCopyms[idx] = arrayOrig[k][idx];
                    arrayCopyqs[idx] = arrayOrig[k][idx];
                    arrayCopyrs[idx] = arrayOrig[k][idx];
				}
				
				long startTimess = System.nanoTime();
				selectionSort(arrayCopyss, arrayCopyss.length);
				long timess = System.nanoTime() - startTimess;

                long startTimebs = System.nanoTime();
				bubbleSort(arrayCopybs, arrayCopybs.length);
				long timebs = System.nanoTime() - startTimebs;

                long startTimeis = System.nanoTime();
				insertionSort(arrayCopyis, arrayCopyis.length);
				long timeis = System.nanoTime() - startTimeis;

                long startTimems = System.nanoTime();
				mergeSort(arrayCopyms, 0, arrayCopyms.length-1);
				long timems = System.nanoTime() - startTimems;

                long startTimeqs = System.nanoTime();
				quickSort(arrayCopyqs, 0, arrayCopyqs.length-1);
				long timeqs = System.nanoTime() - startTimeqs;

                long startTimers = System.nanoTime();
				radixSort(arrayCopyrs, 10);
				long timers = System.nanoTime() - startTimers;

                arrayOut[i][0][k] = timess;
                arrayOut[i][1][k] = timebs;
                arrayOut[i][2][k] = timeis;
                arrayOut[i][3][k] = timems;
                arrayOut[i][4][k] = timeqs;
                arrayOut[i][5][k] = timers;

			}
		}
        
        for (int i = 0; i < arrayAvg.length; i++) {
            for (int j = 0; j < arrayAvg[i].length; j++) {
                arrayAvg[i][j] = getAvg(arrayOut[i][j]);
                arrayStDev[i][j] = getStDev(arrayOut[i][j]);
            }
        }

        for (int i = 0; i < arrayAvg.length; i++) {
            double MSWG = getMSWG(arrayOut[i]);
            for (int j = 0; j < arrayAvg[i].length-1; j++) {
                for (int k = j; k < 5; k++) {
                    Long avg1 = arrayAvg[i][j];
                    Long avg2 = arrayAvg[i][k+1];
                    double q = (Math.abs(avg1-avg2)) / (Math.sqrt(MSWG/10.0));
                    arrayQ[i][j][k] = q;
                    if (avg1 < avg2) {
                        arrayD[i][j][k] = arrayNames[j];
                    }
                    if (avg1 > avg2) {
                        arrayD[i][j][k] = arrayNames[k+1];
                    }
                }
            }
        }

        for (int i = 0; i < arrayQ.length; i++) {
            for (int j = 0; j < arrayQ[i].length; j++) {
                for (int k = 0; k < arrayQ[i][j].length; k++) {
                    if (arrayQ[i][j][k] == null) {
                        arrayQ[i][j][k] = 0.0;
                    }
                    if (arrayQ[i][j][k] < 4.178) {
                        arrayD[i][j][k] = "-";
                    }
                }            
            }
        }

        PrintStream x = new PrintStream(new File("timings.dat"));
        System.setOut(x);
        for (int i = 0; i < arrayOut.length; i++) {
            System.out.printf("%s", "          Runtimes in nanoseconds on data set of size " + arraySizes[i] + " -- F: ");
            System.out.printf("%.2f\n", getF(arrayOut[i]));
            for (int j = 0; j < arrayOut[i].length; j++) {
                System.out.printf("%10s", arrayNames[j]);
                for (int k = 0; k < arrayOut[i][j].length; k++) {
                    System.out.printf("%11d", arrayOut[i][j][k]);
                }
                System.out.printf("%22s", "<" + arrayAvg[i][j] + ", " + arrayStDev[i][j] + ">");
                System.out.println();
            }
            System.out.println();

            System.out.printf("%s\n", "          (Q,D) value pairs for data set of size " + arraySizes[i]);
            for (int j = 0; j < arrayQ[i].length; j++) {
                System.out.printf("%20s", arrayNames[j+1]);
            }
            System.out.println();
            for (int j = 0; j < arrayQ[i].length; j++) {
                System.out.printf("%10s", arrayNames[j]);
                for (int k = 0; k < arrayQ[i][j].length; k++) {
                    System.out.printf("%8.2f", arrayQ[i][j][k]);
                    System.out.printf("%-11s", ", " + arrayD[i][j][k]);
                }
                System.out.println();
            }
            System.out.println();
        
        }
    }

    public static long getAvg(Long [] array) {
        long avg = 0;
        long sum = 0;
        for (long x : array) {
            sum += x;
        }
        avg = sum / array.length;
        return avg;
    }

    public static long getStDev(Long [] array) {
        long sum = 0;
        long avg = getAvg(array);
        for (long x : array) {
            sum += Math.pow((x-avg), 2);
        }
        double frac = (1.0/(array.length-1));
        long  stdev = (long) Math.sqrt(frac*sum);
        return stdev;
    }

    public static double getMSBG(Long [][] array) {
        double sum = 0;
        for (Long [] x : array) {
            double avg = getAvg(x);
            sum += Math.pow((avg-getMuBar(array)), 2);
        }
        double frac = 10.0/(array.length-1);
        double MSBG = frac * sum;
        return MSBG;
    }

    public static long getMuBar(Long [][] array) {
        long sum = 0;
        for (Long [] x : array) {
            sum += getAvg(x);
        }
        double frac = (1.0/(6));
        long  muBar = (long) (frac * sum);
        return muBar;
    }

    public static double getMSWG(Long [][] array) {
        double sum = 0;
        for (Long [] x : array) {
            sum += Math.pow((getStDev(x)), 2);
        }
        double frac = 1.0/(array.length);
        double MSWG = frac * sum;
        return MSWG;
    }

    public static double getF(Long [][] array) {
        double MSBG = getMSBG(array);
        double MSWG = getMSWG(array);
        double F = MSBG / MSWG;
        return F;
    }

}
