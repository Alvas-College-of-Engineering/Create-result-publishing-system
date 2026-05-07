public class ResultService {

    public void calculate(Student s) {
        int sum = 0;

        for (int m : s.marks) {
            sum += m;
        }

        s.total = sum;
        s.percentage = sum / (float) s.marks.length;

        s.pass = true;
        for (int m : s.marks) {
            if (m < 35) {
                s.pass = false;
                break;
            }
        }

        if (s.percentage >= 90) s.grade = 'A';
        else if (s.percentage >= 75) s.grade = 'B';
        else if (s.percentage >= 60) s.grade = 'C';
        else if (s.percentage >= 50) s.grade = 'D';
        else s.grade = 'F';
    }

    public void publish(Student s) {
        System.out.println("\n===== RESULT CARD =====");
        System.out.println("Name       : " + s.name);

        System.out.print("Marks      : ");
        for (int m : s.marks) {
            System.out.print(m + " ");
        }

        System.out.println("\nTotal      : " + s.total);
        System.out.println("Percentage : " + s.percentage + "%");
        System.out.println("Grade      : " + s.grade);
        System.out.println("Status     : " + (s.pass ? "PASS" : "FAIL"));
        System.out.println("========================");
    }
}