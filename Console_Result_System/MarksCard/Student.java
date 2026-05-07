package MarksCard;

public class Student{
    String name;
    int[] marks;
    int totalmarks;
    float percentage;
    char grade;
    boolean pass;
   
    public Student(String name,int[] marks){
        this.name=name;
        this.marks=marks;
        this.totalmarks=0;
        this.percentage=0;
        this.grade='n';
        this.pass= false;
    }
   
    public void CalculateResult(){
        int sum = 0;
        for (int mark : marks){
            sum += mark;
        }
        this.totalmarks=sum;
        this.percentage= (float)sum/(marks.length);
       
        if (percentage >=40){
            this.pass=true;
            if (percentage <=100 && percentage >=90){
                for (int mark : marks){
                    if (mark <40){
                        this.pass=false;
                    }
                    else
                        this.grade = 'A';
                }
            }
            else if (percentage <=89 && percentage >=80){
                for (int mark : marks){
                    if (mark <40){
                        this.pass=false;
                    }
                    else
                        this.grade = 'B';
                }
            }
            else if (percentage <=79 && percentage >=60){
                for (int mark : marks){
                    if (mark <40){
                        this.pass=false;
                    }
                    else
                        this.grade = 'C';
                }
            }
            else if (percentage <=59 && percentage >=40) {
                for (int mark : marks){
                    if (mark <40){
                        this.pass=false;
                    }
                    else
                        this.grade = 'D';
                }
            }
        }
        else{
            this.pass=false;
            this.grade='F';
        }
    }
   
    public void printMarks(){
        for (int i=0;i<marks.length;i++){
            System.out.println("Mark in Subject "+(i+1)+" is : "+marks[i]);
            if (marks[i] < 40){
                System.out.println("Mark in Subject "+(i+1)+" is less than the required marks");
            }
        }
    }
   
    public void printResults() {
        System.out.println("----------------------------------------------------");
        System.out.println("                     Student Result");
        System.out.println("----------------------------------------------------");
        System.out.println("Student: " + name);
        printMarks();
        System.out.println("Total Marks: " + totalmarks);
        System.out.println("Percentage: " + percentage + "%");
        System.out.println("Grade: " + grade);
        System.out.println("Pass: " + (pass ? "Pass" : "Fail"));
    }
   
    public static void main(String[] args) {
        String student1Name = "Alice Smith";
        int[] student1Marks = {85, 92, 78, 98, 50};
        String student2Name = "Bob Johnson";
        int[] student2Marks = {55, 85, 39, 50, 80};
        Student student1 = new Student(student1Name, student1Marks);
        student1.CalculateResult();
        student1.printResults();
        Student student2 = new Student(student2Name, student2Marks);
        student2.CalculateResult();
        student2.printResults();
    }
}