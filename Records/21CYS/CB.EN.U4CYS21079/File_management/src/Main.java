
//submitted code

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

// Abstract File class
abstract class File {
    private String fileName;
    private long fileSize;

    public File(String fileName, long fileSize) {
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public abstract void displayFileDetails();
}

// Document class
class Document extends File {
    private String documentType;

    public Document(String fileName, long fileSize, String documentType) {
        super(fileName, fileSize);
        this.documentType = documentType;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    @Override
    public void displayFileDetails() {
        System.out.println("Document File Name: " + getFileName());
        System.out.println("Document File Size: " + getFileSize() + " bytes");
        System.out.println("Document Type: " + documentType);
        System.out.println("------------------------");
    }
}

// Image class
class Image extends File {
    private String resolution;

    public Image(String fileName, long fileSize, String resolution) {
        super(fileName, fileSize);
        this.resolution = resolution;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    @Override
    public void displayFileDetails() {
        System.out.println("Image File Name: " + getFileName());
        System.out.println("Image File Size: " + getFileSize() + " bytes");
        System.out.println("Resolution: " + resolution);
        System.out.println("------------------------");
    }
}

// Video class
class Video extends File {
    private int duration;

    public Video(String fileName, long fileSize, int duration) {
        super(fileName, fileSize);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public void displayFileDetails() {
        System.out.println("Video File Name: " + getFileName());
        System.out.println("Video File Size: " + getFileSize() + " bytes");
        System.out.println("Duration: " + duration + " minutes");
        System.out.println("------------------------");
    }
}

// FileManager interface
interface FileManager {
    void addFile(File file);
    void deleteFile(String fileName);
    void saveToFile();
    void loadFromFile();
    ArrayList<File> getFiles();
}

// FileManagerImpl class
class FileManagerImpl implements FileManager {
    private ArrayList<File> files;

    public FileManagerImpl() {
        files = new ArrayList<>();
    }

    @Override
    public void addFile(File file) {
        files.add(file);
    }

    @Override
    public void deleteFile(String fileName) {
        for (int i = 0; i < files.size(); i++) {
            if (files.get(i).getFileName().equals(fileName)) {
                files.remove(i);
                break;
            }
        }
    }

    @Override
    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("files.dat"))) {
            oos.writeObject(files);
            System.out.println("File details saved to file.");


        } catch (IOException e) {
            System.out.println("Error occurred while saving file details to file.");
        }
    }
}
    @Override
    public void loadFromFile() {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("files.dat"))) {
            files = (ArrayList<File>) ois.readObject();
            System.out.println("File details loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error occurred while loading file details from file.");
        }
    }

    @Override
    public ArrayList<File> getFiles() {
        return files;
    }


}

// FileManagementSystemUI class
class FileManagementSystemUI {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField fileNameField, fileSizeField, fileTypeField, resolutionField, durationField;

    private FileManager fileManager;

    public FileManagementSystemUI() {
        fileManager = new FileManagerImpl();
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("File Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        JLabel titleLabel = new JLabel("File Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(titleLabel);

        JPanel fileDetailsPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        fileDetailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel fileNameLabel = new JLabel("File Name:");
        JLabel fileSizeLabel = new JLabel("File Size:");
        JLabel fileTypeLabel = new JLabel("File Type:");
        JLabel resolutionLabel = new JLabel("Resolution:");
        JLabel durationLabel = new JLabel("Duration:");

        fileNameField = new JTextField(20);
        fileSizeField = new JTextField(20);
        fileTypeField = new JTextField(20);
        resolutionField = new JTextField(20);
        durationField = new JTextField(20);

        fileDetailsPanel.add(fileNameLabel);
        fileDetailsPanel.add(fileNameField);
        fileDetailsPanel.add(fileSizeLabel);
        fileDetailsPanel.add(fileSizeField);
        fileDetailsPanel.add(fileTypeLabel);
        fileDetailsPanel.add(fileTypeField);
        fileDetailsPanel.add(resolutionLabel);
        fileDetailsPanel.add(resolutionField);
        fileDetailsPanel.add(durationLabel);
        fileDetailsPanel.add(durationField);

        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JButton displayButton = new JButton("Display All");
        JButton saveButton = new JButton("Save to File");
        JButton loadButton = new JButton("Load from File");

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        table = new JTable();
        tableModel = new DefaultTableModel();
        table.setModel(tableModel);
        tableModel.addColumn("File Name");
        tableModel.addColumn("File Size");
        tableModel.addColumn("File Type");

        JScrollPane scrollPane = new JScrollPane(table);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(fileDetailsPanel, BorderLayout.WEST);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFile();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                deleteFile();
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAllFiles();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToFile();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFromFile();
            }
        });

        frame.setVisible(true);
    }

    private void addFile() {
        String fileName = fileNameField.getText();
        long fileSize = Long.parseLong(fileSizeField.getText());
        String fileType = fileTypeField.getText();

        if (fileType.equalsIgnoreCase("document")) {
            String documentType = resolutionField.getText();
            Document document = new Document(fileName, fileSize, documentType);
            fileManager.addFile(document);
        } else if (fileType.equalsIgnoreCase("image")) {
            String resolution = resolutionField.getText();
            Image image = new Image(fileName, fileSize, resolution);
            fileManager.addFile(image);
        } else if (fileType.equalsIgnoreCase("video")) {
            int duration = Integer.parseInt(durationField.getText());
            Video video = new Video(fileName, fileSize, duration);
            fileManager.addFile(video);
        }

        clearFields();
        refreshTable();
    }

    private void deleteFile() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String fileName = tableModel.getValueAt(selectedRow, 0).toString();
            fileManager.deleteFile(fileName);
            refreshTable();
        }
    }

    private void displayAllFiles() {
        ArrayList<File> files = fileManager.getFiles();
        tableModel.setRowCount(0);

        for (File file : files) {
            String fileName = file.getFileName();
            long fileSize = file.getFileSize();
            String fileType = file.getClass().getSimpleName();

            tableModel.addRow(new Object[]{fileName, fileSize, fileType});
        }
    }

    private void saveToFile() {
        fileManager.saveToFile();
    }

    private void loadFromFile() {
        fileManager.loadFromFile();
        refreshTable();
    }

    private void clearFields() {
        fileNameField.setText("");
        fileSizeField.setText("");
        fileTypeField.setText("");
        resolutionField.setText("");
        durationField.setText("");
    }

    private void refreshTable() {
        displayAllFiles();
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FileManagementSystemUI();
            }
        });
    }
}