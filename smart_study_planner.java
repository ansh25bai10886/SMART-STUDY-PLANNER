 import java.util.ArrayList;
import java.util.Scanner;

// Represents a study task
class StudyTask {
    private String subject;
    private String task;
    private String priority;
    private boolean completed;

    public StudyTask(String subject, String task, String priority) {
        this.subject = subject;
        this.task = task;
        this.priority = priority;
        this.completed = false;
    }

    public String getSubject() {
        return subject;
    }

    public String getTask() {
        return task;
    }

    public String getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        completed = true;
    }

    public void displayTask(int number) {
        System.out.println("\nTask " + number);
        System.out.println("Subject  : " + subject);
        System.out.println("Task     : " + task);
        System.out.println("Priority : " + priority);
        System.out.println("Status   : " +
                (completed ? "Completed" : "Pending"));
    }
}

// Main application
public class Main {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<String> subjects = new ArrayList<>();
    static ArrayList<StudyTask> tasks = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("      SMART STUDY PLANNER");
        System.out.println("=================================");

        int choice;

        do {
            showMenu();
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addSubject();
                        break;

                    case 2:
                        addTask();
                        break;

                    case 3:
                        viewSubjects();
                        break;

                    case 4:
                        viewStudyPlan();
                        break;

                    case 5:
                        markTaskCompleted();
                        break;

                    case 6:
                        showProgress();
                        break;

                    case 7:
                        System.out.println("\nThank you for using Smart Study Planner!");
                        System.out.println("Keep studying and stay consistent. 📚");
                        break;

                    default:
                        System.out.println("\nPlease enter a number between 1 and 7.");
                }

            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input. Please enter a number.");
                choice = 0;
            }

        } while (choice != 7);

        scanner.close();
    }

    // Displays the main menu
    static void showMenu() {
        System.out.println("\n----------- MENU -----------");
        System.out.println("1. Add Subject");
        System.out.println("2. Add Study Task");
        System.out.println("3. View Subjects");
        System.out.println("4. View Study Plan");
        System.out.println("5. Mark Task as Completed");
        System.out.println("6. View Progress");
        System.out.println("7. Exit");
        System.out.println("----------------------------");
    }

    // Adds a new subject
    static void addSubject() {

        System.out.print("\nEnter subject name: ");
        String subject = scanner.nextLine().trim();

        if (subject.isEmpty()) {
            System.out.println("Subject name cannot be empty.");
            return;
        }

        if (subjects.contains(subject)) {
            System.out.println("This subject already exists.");
        } else {
            subjects.add(subject);
            System.out.println("Subject added successfully!");
        }
    }

    // Adds a new study task
    static void addTask() {

        if (subjects.isEmpty()) {
            System.out.println("\nPlease add a subject first.");
            return;
        }

        System.out.println("\nAvailable Subjects:");

        for (int i = 0; i < subjects.size(); i++) {
            System.out.println((i + 1) + ". " + subjects.get(i));
        }

        System.out.print("Choose subject number: ");

        try {
            int subjectNumber = Integer.parseInt(scanner.nextLine());

            if (subjectNumber < 1 || subjectNumber > subjects.size()) {
                System.out.println("Invalid subject number.");
                return;
            }

            String selectedSubject = subjects.get(subjectNumber - 1);

            System.out.print("Enter study task: ");
            String task = scanner.nextLine();

            if (task.trim().isEmpty()) {
                System.out.println("Task cannot be empty.");
                return;
            }

            System.out.print("Enter priority (High/Medium/Low): ");
            String priority = scanner.nextLine();

            if (!isValidPriority(priority)) {
                System.out.println("Invalid priority. Use High, Medium or Low.");
                return;
            }

            StudyTask newTask =
                    new StudyTask(selectedSubject, task, priority);

            tasks.add(newTask);

            System.out.println("Study task added successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    // Checks priority
    static boolean isValidPriority(String priority) {

        return priority.equalsIgnoreCase("High")
                || priority.equalsIgnoreCase("Medium")
                || priority.equalsIgnoreCase("Low");
    }

    // Displays all subjects
    static void viewSubjects() {

        if (subjects.isEmpty()) {
            System.out.println("\nNo subjects added yet.");
            return;
        }

        System.out.println("\n======= YOUR SUBJECTS =======");

        for (int i = 0; i < subjects.size(); i++) {
            System.out.println((i + 1) + ". " + subjects.get(i));
        }
    }

    // Displays all study tasks
    static void viewStudyPlan() {

        if (tasks.isEmpty()) {
            System.out.println("\nYour study plan is empty.");
            return;
        }

        System.out.println("\n======= YOUR STUDY PLAN =======");

        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).displayTask(i + 1);
        }
    }

    // Marks a task as completed
    static void markTaskCompleted() {

        if (tasks.isEmpty()) {
            System.out.println("\nNo study tasks available.");
            return;
        }

        viewStudyPlan();

        System.out.print("\nEnter task number to mark as completed: ");

        try {
            int taskNumber = Integer.parseInt(scanner.nextLine());

            if (taskNumber < 1 || taskNumber > tasks.size()) {
                System.out.println("Invalid task number.");
                return;
            }

            StudyTask selectedTask = tasks.get(taskNumber - 1);

            if (selectedTask.isCompleted()) {
                System.out.println("This task is already completed.");
            } else {
                selectedTask.markCompleted();
                System.out.println("Great! Task marked as completed.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    // Shows overall progress
    static void showProgress() {

        if (tasks.isEmpty()) {
            System.out.println("\nNo tasks available to calculate progress.");
            return;
        }

        int completed = 0;

        for (StudyTask task : tasks) {
            if (task.isCompleted()) {
                completed++;
            }
        }

        int total = tasks.size();
        double percentage = (completed * 100.0) / total;

        System.out.println("\n======= STUDY PROGRESS =======");
        System.out.println("Total Tasks     : " + total);
        System.out.println("Completed Tasks : " + completed);
        System.out.println("Pending Tasks   : " + (total - completed));
        System.out.printf("Progress        : %.1f%%\n", percentage);

        if (percentage == 100) {
            System.out.println("Excellent! All tasks are completed! 🎉");
        } else if (percentage >= 50) {
            System.out.println("Good progress! Keep going. 👍");
        } else {
            System.out.println("Keep working on your study plan. 💪");
        }
    }
}