import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.Scanner;

public class FileCrawler {

    public static void main(String[] args) {
        System.out.print("Type the word for searching : ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        // search from current path
        Path currentPath = Path.of("").toAbsolutePath();
        String absolutePath = currentPath.toAbsolutePath().toString();

        checkDirectory(absolutePath, input);
        scanner.close();

    }

    // check if it is folder
    public static void checkDirectory(String dir, String input) {
        File folder = new File(dir);
        File[] listOfFiles = folder.listFiles();

        for (File f : listOfFiles) {
            String filesPath = f.getAbsolutePath().toString();
            boolean canRead = f.canRead();
            if (!canRead) {
                System.out.println("Can't read the file ! " + dir);
            }
            if (f.isDirectory()) {
                checkDirectory(filesPath, input);
            } else {
                checkTextInFile(filesPath, input);
            }

        }
    }

    // check if the text exists in the file
    public static void checkTextInFile(String dir, String input) {

        try {
            FileReader fr = new FileReader(dir);
            Scanner sc = new Scanner(fr);

            while (sc.hasNextLine()) {
                if (sc.nextLine().contains(input)) {
                    System.out.println("The text is in : " + dir);
                }
            }
            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error occured : " + dir + e);
            e.printStackTrace();
        }

    }
}
