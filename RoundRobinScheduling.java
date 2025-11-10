import java.util.*;

class Process {
    int pid;
    int burstTime;
    int priority;
    int remainingTime;
    Process next;

    Process(int pid, int burstTime, int priority) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
        this.next = null;
    }
}

class RoundRobin {
    Process head = null;

    void addProcess(int pid, int burstTime, int priority) {
        Process newProcess = new Process(pid, burstTime, priority);
        if (head == null) {
            head = newProcess;
            head.next = head;
        } else {
            Process temp = head;
            while (temp.next != head)
                temp = temp.next;
            temp.next = newProcess;
            newProcess.next = head;
        }
    }

    void removeProcess(int pid) {
        if (head == null) return;
        Process curr = head, prev = null;
        do {
            if (curr.pid == pid) {
                if (prev == null) {
                    if (head.next == head) head = null;
                    else {
                        Process temp = head;
                        while (temp.next != head) temp = temp.next;
                        head = head.next;
                        temp.next = head;
                    }
                } else {
                    prev.next = curr.next;
                    if (curr == head) head = curr.next;
                }
                return;
            }
            prev = curr;
            curr = curr.next;
        } while (curr != head);
    }

    void displayProcesses() {
        if (head == null) {
            System.out.println("No processes remaining.");
            return;
        }
        Process temp = head;
        do {
            System.out.print("P" + temp.pid + "(BT:" + temp.remainingTime + ") -> ");
            temp = temp.next;
        } while (temp != head);
        System.out.println("HEAD");
    }

    void simulate(int timeQuantum) {
        if (head == null) {
            System.out.println("No processes to schedule.");
            return;
        }

        int totalWaitingTime = 0, totalTurnaroundTime = 0, time = 0, count = 0;
        Process curr = head;
        List<Integer> waitingTime = new ArrayList<>();
        List<Integer> turnaroundTime = new ArrayList<>();

        while (head != null) {
            if (curr.remainingTime > 0) {
                int execTime = Math.min(curr.remainingTime, timeQuantum);
                curr.remainingTime -= execTime;
                time += execTime;
                System.out.println("Executing Process P" + curr.pid + " for " + execTime + " units.");

                if (curr.remainingTime == 0) {
                    totalTurnaroundTime += time;
                    totalWaitingTime += (time - curr.burstTime);
                    turnaroundTime.add(time);
                    waitingTime.add(time - curr.burstTime);
                    System.out.println("Process P" + curr.pid + " completed.");
                    removeProcess(curr.pid);
                }

                displayProcesses();

                if (head == null) break;
                curr = curr.next;
            } else curr = curr.next;
        }

        for (int i = 0; i < waitingTime.size(); i++) count++;
        System.out.println("\nAverage Waiting Time: " + (float) totalWaitingTime / count);
        System.out.println("Average Turnaround Time: " + (float) totalTurnaroundTime / count);
    }
}

public class RoundRobinScheduling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RoundRobin rr = new RoundRobin();

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter Process ID: ");
            int pid = sc.nextInt();
            System.out.print("Enter Burst Time: ");
            int bt = sc.nextInt();
            System.out.print("Enter Priority: ");
            int pr = sc.nextInt();
            rr.addProcess(pid, bt, pr);
        }

        System.out.print("Enter Time Quantum: ");
        int tq = sc.nextInt();

        rr.simulate(tq);
    }
}
