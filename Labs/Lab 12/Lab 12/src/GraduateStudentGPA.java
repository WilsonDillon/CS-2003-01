/* 
 * Class for storing Graduate Student information
 */

public class GraduateStudentGPA extends StudentGPA {

	String advisor;
	
	public GraduateStudentGPA(int id, String name, Double gpa) {
		super(id, name, gpa);
	}

	public GraduateStudentGPA(int id, String name, Double gpa, String ad) {
		super(id, name, gpa);
		setAdvisor(ad);
	}

	public void setAdvisor(String ad){
		advisor = ad;
	}
	public String toString() {
	      return "("+ id + "," + name + ","+GPA+", " + advisor+")";
	  } // end toString

}