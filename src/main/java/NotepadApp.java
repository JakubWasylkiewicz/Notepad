import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import  java.util.List;

public class NotepadApp {
    private static List<Note> notes = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Notepad");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton btnAddNote = new JButton("Add Note");
        JButton btnNotes = new JButton("Notes");
        JButton btnEdit = new JButton("Edit");
        JButton btnArchives = new JButton("Archives");
        JButton btnBin = new JButton("Bin");

        panel.add(btnAddNote);
        panel.add(btnNotes);
        panel.add(btnEdit);
        panel.add(btnArchives);
        panel.add(btnBin);

        btnNotes.addActionListener(e -> showNotes(frame));
        btnAddNote.addActionListener(e -> openNoteEditor());

        frame.add(panel, BorderLayout.WEST);
        frame.setVisible(true);

    }

    private static void openNoteEditor() {

        JFrame noteFrame = new JFrame("New note");
        noteFrame.setSize(300, 200);

        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        noteFrame.add(new JScrollPane(textArea), BorderLayout.CENTER);

        noteFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                int choice = JOptionPane.showConfirmDialog(noteFrame, "Should I save that note?", "Save Note",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (choice == JOptionPane.YES_OPTION) {
                    String fileName = JOptionPane.showInputDialog(noteFrame, "File name: ");

                    if (fileName != null && !fileName.trim().isEmpty()) {
                        saveNoteToFile(fileName, textArea.getText());
                        Note newNote = new Note(fileName);
                        notes.add(newNote);
                    }
                }
                noteFrame.dispose();
            }

        });
        noteFrame.setVisible(true);
    }

    private static void saveNoteToFile(String fileName, String content) {
        try (FileWriter writer = new FileWriter(fileName + ".txt")) {
            writer.write(content);
            JOptionPane.showConfirmDialog(null, "Note saved as " + fileName + ".txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error, file not saved.");
            e.printStackTrace();
        }
    }

    //wyświetlanie listy

    public static void showNotes(JFrame frame) {
        JPanel notesPanel = new JPanel();
        notesPanel.setLayout(new BoxLayout(notesPanel, BoxLayout.Y_AXIS));

        for (Note note : notes) {
            String text = note.getName() + " - Made: " + note.getCreationDate() + "- Last modified: " + note.getLastModifiedDate();
            notesPanel.add(new JLabel(text));
        }

        JFrame notesFrame = new JFrame("Notes List");
        notesFrame.setSize(400, 300);
        notesFrame.add(new JScrollPane(notesPanel), BorderLayout.CENTER);
        notesFrame.setVisible(true);

    }


}
