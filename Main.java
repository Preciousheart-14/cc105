import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Main {

    static ArrayList<String> names = new ArrayList<>();
    static ArrayList<String> phones = new ArrayList<>();

    static Scanner scan = new Scanner(System.in);
    static File file = new File("contacts.txt");

    public static void main(String[] args) {
        loadFile();
        menu();
    }

    static void menu() {
        int choice;

        while (true) {
            System.out.println("CONTACT");
            System.out.println("1. Create Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.print("Choose: ");

            choice = scan.nextInt();
            scan.nextLine();

            if (choice == 1) {
                create();
            } else if (choice == 2) {
                read();
            } else if (choice == 3) {
                update();
            } else if (choice == 4) {
                delete();
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    static void loadFile() {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            Scanner fileScan = new Scanner(file);

            while (fileScan.hasNextLine()) {
                String line = fileScan.nextLine();
                String[] data = line.split(",");

                names.add(data[0]);
                phones.add(data[1]);
            }

            fileScan.close();
        } catch (Exception e) {
            System.out.println("Error loading file.");
        }
    }

    static void saveFile() {
        try {
            FileWriter writer = new FileWriter(file);

            for (int i = 0; i < names.size(); i++) {
                writer.write(names.get(i) + "," + phones.get(i) + " ");
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Error saving file.");
        }
    }

    static void create() {
        System.out.print("Name: ");
        String name = scan.nextLine();

        System.out.print("Phone: ");
        String phone = scan.nextLine();

        names.add(name);
        phones.add(phone);

        saveFile();
        System.out.println("Contact added.");
    }

    static void read() {
        if (names.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }

        int i = 0;
        while (i < names.size()) {
            System.out.println("Name: " + names.get(i));
            System.out.println("Phone: " + phones.get(i));
            i++;
        }
    }

    static void update() {
        System.out.print("Enter name: ");
        String search = scan.nextLine();

        int i = 0;
        while (i < names.size()) {
            if (names.get(i).equals(search)) {
                System.out.print("New phone: ");
                phones.set(i, scan.nextLine());

                saveFile();
                System.out.println("Updated.");
                return;
            }
            i++;
        }

        System.out.println("Contact not found.");
    }

    static void delete() {
        System.out.print("Enter name: ");
        String search = scan.nextLine();

        int i = 0;
        while (i < names.size()) {
            if (names.get(i).equals(search)) {
                names.remove(i);
                phones.remove(i);

                saveFile();
                System.out.println("Deleted.");
                return;
            }
            i++;
        }

        System.out.println("Contact not found.");
    }
}