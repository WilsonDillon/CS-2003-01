//import javax.lang.model.util.ElementScanner14;

public class StudentGPA implements Comparable<StudentGPA> {
 
    protected int id;
    protected String name;
    protected double GPA;

  public StudentGPA(int id, String name, double gpa) {
    this.GPA = gpa;
    this.name = name;
    this.id = id;
  }  // end constructor

  public String toString() {
      return "("+ id + "," + name + ","+GPA+")";
  } // end toString

  public int compareTo(StudentGPA stu){
	  if(GPA < stu.GPA){
      return -1;}
    else if (GPA == stu.GPA)
    {return 0;}
    else 
    {return 1;}
  }


}