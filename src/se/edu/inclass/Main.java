package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.util.ArrayList;
import java.util.Locale;

import static java.util.stream.Collectors.toList;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        System.out.println("Printing deadlines");
        //printDeadlines(tasksData);
        printDeadlinesWithStream(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        printSortedDeadlinesWithStream(tasksData);

        ArrayList<Task> filteredList = filterTasksByString(tasksData, "11");
        System.out.println("\nPrinting filtered list of data");
        printData(filteredList);
    }

    private static ArrayList<Task> filterTasksByString(ArrayList<Task> tasksData, String s) {
        ArrayList<Task> filteredList = (ArrayList<Task>) tasksData.stream()
                .filter((t) -> t.getDescription().contains(s))
                .collect(toList());

        return filteredList;
    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    private static int countDeadlinesWithStream(ArrayList<Task> tasks) {
        int count = 0;

        count = (int) tasks.stream()
                .filter((t) -> t instanceof Deadline)
                .count();

        return count;
    }

    public static void printData(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDataWithStream(ArrayList<Task> tasks) {
        System.out.println("\nPrint tasks using streams");
        tasks.stream()       // convert task data to a stream
                .forEach(System.out::println);   //terminal operation
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printSortedDeadlinesWithStream(ArrayList<Task> tasks) {
        System.out.println("\nPrinting deadline with streams (sorted)");
        tasks.stream()
                .filter((t) -> t instanceof Deadline)
                .sorted((a, b) -> a.getDescription().toLowerCase().compareTo(b.getDescription().toLowerCase()));
    }

    public static void printDeadlinesWithStream(ArrayList<Task> tasks) {
        System.out.println("\nPrint deadlines using streams");
        tasks.stream()
                .filter((t) -> t instanceof Deadline)
                .forEach(System.out::println);
    }
}
