class Person {
    protected String name;
    protected int age;
    protected String email;
    
    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
    
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Email: " + email);
    }
    
    public void contact() {
        System.out.println("Contacting " + name + " at " + email);
    }
}

class Teacher extends Person {
    private String subject;
    private String department;
    private int yearsOfExperience;
    
    public Teacher(String name, int age, String email, String subject, String department, int yearsOfExperience) {
        super(name, age, email);
        this.subject = subject;
        this.department = department;
        this.yearsOfExperience = yearsOfExperience;
    }
    
    public void displayRole() {
        System.out.println("Role: Teacher");
    }
    
    public void teachClass() {
        System.out.println(name + " is teaching " + subject + " class");
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        displayRole();
        System.out.println("Subject: " + subject);
        System.out.println("Department: " + department);
        System.out.println("Years of Experience: " + yearsOfExperience);
    }
    
    public void gradeAssignment(String studentName, String assignment, String grade) {
        System.out.println(name + " graded " + studentName + "'s " + assignment + ": " + grade);
    }
}

class Student extends Person {
    private int gradeLevel;
    private double gpa;
    private String studentId;
    
    public Student(String name, int age, String email, int gradeLevel, double gpa, String studentId) {
        super(name, age, email);
        this.gradeLevel = gradeLevel;
        this.gpa = gpa;
        this.studentId = studentId;
    }
    
    public void displayRole() {
        System.out.println("Role: Student");
    }
    
    public void attendClass(String className) {
        System.out.println(name + " is attending " + className + " class");
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        displayRole();
        System.out.println("Student ID: " + studentId);
        System.out.println("Grade Level: " + gradeLevel);
        System.out.println("GPA: " + gpa);
    }
    
    public void submitAssignment(String assignmentName) {
        System.out.println(name + " submitted assignment: " + assignmentName);
    }
}

class Staff extends Person {
    private String position;
    private String department;
    private double salary;
    
    public Staff(String name, int age, String email, String position, String department, double salary) {
        super(name, age, email);
        this.position = position;
        this.department = department;
        this.salary = salary;
    }
    
    public void displayRole() {
        System.out.println("Role: Staff Member");
    }
    
    public void performDuty() {
        System.out.println(name + " is performing " + position + " duties");
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        displayRole();
        System.out.println("Position: " + position);
        System.out.println("Department: " + department);
        System.out.println("Salary: $" + salary);
    }
    
    public void manageSchoolOperation(String operation) {
        System.out.println(name + " is managing school operation: " + operation);
    }
}

public class SchoolSystem {
    public static void main(String[] args) {
        Teacher mathTeacher = new Teacher("Dr. Sarah Wilson", 45, "sarah.wilson@school.edu", 
                                         "Mathematics", "Science Department", 15);
        Student highSchoolStudent = new Student("John Davis", 16, "john.davis@student.school.edu", 
                                              11, 3.8, "STU2024001");
        Staff administrator = new Staff("Mr. Robert Brown", 52, "robert.brown@school.edu", 
                                       "Administrator", "Administration", 65000);
        
        System.out.println("=== TEACHER INFORMATION ===");
        mathTeacher.displayInfo();
        mathTeacher.teachClass();
        mathTeacher.gradeAssignment("John Davis", "Algebra Test", "A");
        System.out.println();
        
        System.out.println("=== STUDENT INFORMATION ===");
        highSchoolStudent.displayInfo();
        highSchoolStudent.attendClass("Mathematics");
        highSchoolStudent.submitAssignment("Algebra Homework");
        System.out.println();
        
        System.out.println("=== STAFF INFORMATION ===");
        administrator.displayInfo();
        administrator.performDuty();
        administrator.manageSchoolOperation("Student Registration");
        System.out.println();
        
        System.out.println("=== SCHOOL COMMUNITY CONTACT ===");
        Person[] schoolCommunity = {mathTeacher, highSchoolStudent, administrator};
        
        for (Person person : schoolCommunity) {
            person.contact();
            if (person instanceof Teacher) {
                ((Teacher) person).displayRole();
            } else if (person instanceof Student) {
                ((Student) person).displayRole();
            } else if (person instanceof Staff) {
                ((Staff) person).displayRole();
            }
            System.out.println("---");
        }
        
        System.out.println("=== DAILY SCHOOL ACTIVITIES ===");
        mathTeacher.teachClass();
        highSchoolStudent.attendClass("Mathematics");
        administrator.manageSchoolOperation("Lunch Schedule");
        highSchoolStudent.submitAssignment("Math Project");
        mathTeacher.gradeAssignment("John Davis", "Math Project", "A+");
    }
}