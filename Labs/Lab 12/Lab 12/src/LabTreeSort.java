import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import BinaryTrees.*;

public class LabTreeSort {
    public static void main(String[] args) {
        BinarySearchTree<StudentGPA> tree = new BinarySearchTree<StudentGPA>();

        try {
			File data = new File("students.in");
			Scanner scan = new Scanner(data);
			
			while (scan.hasNextLine()) {
                String line = scan.nextLine();
                Scanner lineScan = new Scanner(line);
				if (line.contains("BOSS")) {
                    GraduateStudentGPA grad = new GraduateStudentGPA(lineScan.nextInt(), lineScan.next(), lineScan.nextDouble(), lineScan.next());
                    tree.insert(grad);
                } 
                else {
                    StudentGPA student = new StudentGPA(lineScan.nextInt(), lineScan.next(), lineScan.nextDouble());
                    tree.insert(student);
                }
                lineScan.close();
			}
            scan.close();
		} catch (FileNotFoundException e){
			System.err.println(e.getMessage());
		}

        TreeIterator<StudentGPA> treeIt = new TreeIterator<StudentGPA>(tree);
        while (treeIt.hasNext()) {
            System.out.print(treeIt.next());
        }
    }
}
