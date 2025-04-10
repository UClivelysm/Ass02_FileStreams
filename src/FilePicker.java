import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilePicker {
    public static File GetFile() {
        File selectedFile = null;
        try {
            // Create a JFileChooser instance
            JFileChooser chooser = new JFileChooser();
            // Open the file chooser dialog; null centers it on the screen
            int result = chooser.showOpenDialog(null);

            // Check if the user selected a file
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
            } else {
                // Optionally, handle the case where the user canceled the operation
                System.out.println("No file was selected.");
            }
        }
        // Catch for security-related exceptions, e.g., when access to file system is denied
        catch (SecurityException se) {
            System.err.println("Security exception encountered: " + se.getMessage());
            se.printStackTrace();
        }
        // General catch-all for any other exceptions that might occur
        catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        return selectedFile;
    }

    public static String SimpleStreamRead(File inputFile) {
        try (Stream<String> lines = Files.lines(inputFile.toPath())) {
            return lines.collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
            return "Error in Reading";
        }
    }

    public static String SearchedStreamRead(File inputFile, String searchTerm) {
        try (Stream<String> lines = Files.lines(inputFile.toPath())) {
            return lines.filter(line -> line.contains(searchTerm))
                    .collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
            return "Error in Reading";
        }
    }

    public static void FileWriter(String input) {
        // Get the current working directory and create a File object for OutputFile.txt
        String workingDir = System.getProperty("user.dir");
        File outputFile = new File(workingDir, "OutputFile.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(input);
            // Pop-up message showing the file was written along with its full path.
            JOptionPane.showMessageDialog(null,
                    "File was written successfully to:\n" + outputFile.getAbsolutePath(),
                    "File Written",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error writing to file: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
