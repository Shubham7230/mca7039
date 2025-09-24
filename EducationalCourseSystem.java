class Course {
    protected String courseName;
    protected int duration; // in weeks
    protected String instructor;
    
    public Course(String courseName, int duration, String instructor) {
        this.courseName = courseName;
        this.duration = duration;
        this.instructor = instructor;
    }
    
    public void displayCourseInfo() {
        System.out.println("Course Name: " + courseName);
        System.out.println("Duration: " + duration + " weeks");
        System.out.println("Instructor: " + instructor);
    }
    
    public void enrollStudent(String studentName) {
        System.out.println(studentName + " enrolled in " + courseName);
    }
}

class OnlineCourse extends Course {
    protected String platform;
    protected boolean isRecorded;
    protected int maxParticipants;
    
    public OnlineCourse(String courseName, int duration, String instructor,
                       String platform, boolean isRecorded, int maxParticipants) {
        super(courseName, duration, instructor);
        this.platform = platform;
        this.isRecorded = isRecorded;
        this.maxParticipants = maxParticipants;
    }
    
    @Override
    public void displayCourseInfo() {
        super.displayCourseInfo();
        System.out.println("Platform: " + platform);
        System.out.println("Recorded: " + (isRecorded ? "Yes" : "No"));
        System.out.println("Max Participants: " + maxParticipants);
    }
    
    public void joinLiveSession(String studentName) {
        System.out.println(studentName + " joined live session for " + courseName + " on " + platform);
    }
}

class PaidOnlineCourse extends OnlineCourse {
    private double fee;
    private double discount;
    private boolean certificateIncluded;
    
    public PaidOnlineCourse(String courseName, int duration, String instructor,
                           String platform, boolean isRecorded, int maxParticipants,
                           double fee, double discount, boolean certificateIncluded) {
        super(courseName, duration, instructor, platform, isRecorded, maxParticipants);
        this.fee = fee;
        this.discount = discount;
        this.certificateIncluded = certificateIncluded;
    }
    
    @Override
    public void displayCourseInfo() {
        super.displayCourseInfo();
        System.out.println("Course Fee: $" + fee);
        System.out.println("Discount: " + discount + "%");
        System.out.println("Final Price: $" + calculateFinalPrice());
        System.out.println("Certificate Included: " + (certificateIncluded ? "Yes" : "No"));
    }
    
    public double calculateFinalPrice() {
        return fee - (fee * discount / 100);
    }
    
    public void processPayment(String studentName) {
        double finalPrice = calculateFinalPrice();
        System.out.println(studentName + " paid $" + finalPrice + " for " + courseName);
        if (certificateIncluded) {
            System.out.println("Certificate will be provided upon completion");
        }
    }
}

public class EducationalCourseSystem {
    public static void main(String[] args) {
        Course basicCourse = new Course("Introduction to Programming", 8, "Dr. Smith");
        OnlineCourse onlineCourse = new OnlineCourse("Web Development", 12, "Prof. Johnson", 
                                                   "Zoom", true, 50);
        PaidOnlineCourse paidCourse = new PaidOnlineCourse("Data Science Bootcamp", 16, "Dr. Wilson",
                                                          "Microsoft Teams", true, 30,
                                                          499.99, 10.0, true);
        
        System.out.println("=== Basic Course ===");
        basicCourse.displayCourseInfo();
        basicCourse.enrollStudent("Alice");
        System.out.println();
        
        System.out.println("=== Online Course ===");
        onlineCourse.displayCourseInfo();
        onlineCourse.enrollStudent("Bob");
        onlineCourse.joinLiveSession("Bob");
        System.out.println();
        
        System.out.println("=== Paid Online Course ===");
        paidCourse.displayCourseInfo();
        paidCourse.enrollStudent("Charlie");
        paidCourse.joinLiveSession("Charlie");
        paidCourse.processPayment("Charlie");
        System.out.println();
        
        System.out.println("=== Course Enrollment Demo ===");
        Course[] courses = {basicCourse, onlineCourse, paidCourse};
        String[] students = {"David", "Eva", "Frank"};
        
        for (int i = 0; i < courses.length; i++) {
            courses[i].enrollStudent(students[i]);
        }
    }
}