import java.util.*;

abstract class Patient {
    private String patientId;
    private String name;
    private int age;
    private String diagnosis;

    public Patient(String patientId, String name, int age, String diagnosis) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.diagnosis = diagnosis;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    protected String getDiagnosis() {
        return diagnosis;
    }

    public String getPatientDetails() {
        return "ID: " + patientId + ", Name: " + name + ", Age: " + age + ", Diagnosis: " + diagnosis;
    }

    public abstract double calculateBill();
}

interface MedicalRecord {
    void addRecord(String record);
    List<String> viewRecords();
}

class InPatient extends Patient implements MedicalRecord {
    private double roomChargePerDay;
    private int days;
    private List<String> records;

    public InPatient(String patientId, String name, int age, String diagnosis, double roomChargePerDay, int days) {
        super(patientId, name, age, diagnosis);
        this.roomChargePerDay = roomChargePerDay;
        this.days = days;
        this.records = new ArrayList<>();
    }

    public double calculateBill() {
        return roomChargePerDay * days;
    }

    public void addRecord(String record) {
        records.add(record);
    }

    public List<String> viewRecords() {
        return records;
    }
}

class OutPatient extends Patient implements MedicalRecord {
    private double consultationFee;
    private List<String> records;

    public OutPatient(String patientId, String name, int age, String diagnosis, double consultationFee) {
        super(patientId, name, age, diagnosis);
        this.consultationFee = consultationFee;
        this.records = new ArrayList<>();
    }

    public double calculateBill() {
        return consultationFee;
    }

    public void addRecord(String record) {
        records.add(record);
    }

    public List<String> viewRecords() {
        return records;
    }
}

public class HospitalPatientManagement {
    public static void main(String[] args) {
        List<Patient> patients = new ArrayList<>();

        InPatient p1 = new InPatient("P101", "Alice", 30, "Fever", 2000, 5);
        p1.addRecord("Admitted for high fever");
        p1.addRecord("Prescribed antibiotics");

        OutPatient p2 = new OutPatient("P202", "Bob", 25, "Cough", 500);
        p2.addRecord("Consulted for cough");
        p2.addRecord("Given cough syrup");

        patients.add(p1);
        patients.add(p2);

        for (Patient patient : patients) {
            System.out.println(patient.getPatientDetails());
            System.out.println("Bill Amount: " + patient.calculateBill());
            if (patient instanceof MedicalRecord) {
                MedicalRecord mr = (MedicalRecord) patient;
                System.out.println("Medical Records: " + mr.viewRecords());
            }
            System.out.println();
        }
    }
}
