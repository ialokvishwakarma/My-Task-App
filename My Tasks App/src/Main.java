import java.io.File;
import java.util.Scanner;
import java.io.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static final File file = new File("tasks.txt");
    static final String FILE_NAME = file.getName();



    public static void main(String[] args) throws IOException {
        boolean exit = false;


        System.out.println("← Welcome to MyTask App →");
        while(true) {
            menu();
            System.out.print("→ Enter your choice : ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1-> seeTasks();
                case 2-> addTask();
                case 3-> markTaskDone();
                case 4 -> deleteTask();
                case 5-> {
                    System.out.println("Thanks for using our app. Have a Good Day!");
                    return;

                }

                default -> System.out.println("Invalid Choice.");


            }
        }


    }



    static void menu(){
        System.out.println("-------------------------------------\n1. See Your Tasks\n2. Add Task to your schedule\n3. Mark Task Done\n4. Delete the task.\n5. Exit\n-------------------------------------");
    }

    static void seeTasks() throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            int count = 0;
            System.out.println("- Your Tasks -");
            while((line= br.readLine())!=null){
                System.out.println("Task "+(count+1)+" → "+line);
                count++;
            }
            if(count==0){
                System.out.println("No tasks in Your Task List");
            }
//            if(count == 1){
//                System.out.println("No tasks in  Your Task List.");
//            }
        }catch (FileNotFoundException e){
            System.out.println("File not found!");
        } catch (IOException e){
            System.out.println("Error reading file!");
        }
    }

     static void addTask(){

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file,true))){
            System.out.print("Enter the number of tasks to be added : ");
            int tasknum = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < tasknum; i++) {


                System.out.print("→ Enter your task : ");

                String task = scanner.nextLine();
                bw.write(task);
                bw.newLine();
            }
                System.out.println("- Tasks added successfully. -");
        } catch (FileNotFoundException e) {
            System.out.println("File not found!.");
        }catch (IOException e){
            System.out.println("Error reading File!");
        }
    }

    static void markTaskDone() {
        File inputfile = new File(FILE_NAME);
        File tempfile = new File("temp.txt");

        boolean found = false;
        boolean isDone = false;
        try (
                BufferedReader input = new BufferedReader(new FileReader(inputfile));
                BufferedWriter tempwriter = new BufferedWriter(new FileWriter(tempfile))
        ) {
            String currline;
            int currTask = 1;
            seeTasks();
            System.out.print("Enter task number to mark as done : ");
            int tasknumber = scanner.nextInt();
            scanner.nextLine();
            while ((currline = input.readLine()) != null) {
                if (currTask == tasknumber) {
                    if (!currline.endsWith("✅")) {
                        currline = currline + " ✅";
                        found = true;
                    } else if (currline.endsWith("✅")) {
                        isDone = true;
                    }


                }
                tempwriter.write(currline);
                tempwriter.newLine();
                currTask++;

            }
            tempwriter.flush();

        } catch (FileNotFoundException e) {
            System.out.println("File not found!.");
        }catch (IOException e){
            System.out.println("Error reading File!");
        }

        if (inputfile.delete()) {
            if (tempfile.renameTo(inputfile)) {
                if (found) {
                    System.out.println("Task marked as done.");
                }else if(isDone){
                    System.out.println("Already marked as done.");
                }
                else{
                    System.out.println("Task not found.");
                }
            }
        }
    }

    static void deleteTask() {
        File inputfile = new File(FILE_NAME);
        File tempfile = new File("temp.txt");
        boolean found = false;


        try (
                BufferedReader input = new BufferedReader(new FileReader(inputfile));
                BufferedWriter tempwriter = new BufferedWriter(new FileWriter(tempfile))
        ) {
            String currline;

            int currTask = 1;
            seeTasks();
            System.out.print("Enter task number to delete : ");
            int tasknumber = scanner.nextInt();
            scanner.nextLine();
            while ((currline = input.readLine()) != null) {
                if (currTask == tasknumber) {
                    found = true;
                    currTask++;
                }
                else{
                tempwriter.write(currline);
                tempwriter.newLine();
                currTask++;
                }

            }
            tempwriter.flush();

        } catch (FileNotFoundException e) {
            System.out.println("File not found!.");
        }catch (IOException e){
            System.out.println("Error reading File!");
        }

        if (inputfile.delete()) {
            if (tempfile.renameTo(inputfile)) {
                if (found) {
                    System.out.println("Task Deleted.");
                }
                else{
                    System.out.println("Task not found.");
                }
            }
        }
    }

}
