import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.CREATE;

public class FileMethod {

    static JFileChooser chooser = new JFileChooser();
    static File workingDirectory = new File(System.getProperty("user.dir"));
    static File selectedFile = null;
    public static File fileBrowser(JTextArea textArea, JTextField inputTF, JTextArea resultTA) {
        try {
            chooser.setCurrentDirectory(workingDirectory);
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                textArea.setText("");
                inputTF.setText("");
                resultTA.setText("");
                textArea.append(selectedFile.getName());
                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Please choose a file!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return selectedFile;
    }
    public static File fileBrowser() {
        return selectedFile;
    }

    public static void fileFilter(JTextField inputTF, File selectedFile, JTextArea rightTA) {
        String filter = inputTF.getText();
        try (Stream lines = Files.lines(Paths.get(selectedFile.toString()))) {
            String displayFile = (String) lines.filter(w -> w.toString().contains(filter)).collect(Collectors.joining("\n"));
            rightTA.setText(displayFile);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}