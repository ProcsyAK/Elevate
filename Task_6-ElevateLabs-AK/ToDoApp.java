import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

class Task {
    String text;
    boolean completed;
    String dueDate;

    Task(String text, boolean completed, String dueDate) {
        this.text = text;
        this.completed = completed;
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return (completed ? "[X] " : "[ ] ") + text + " | Due: " + dueDate;
    }
}

public class ToDoApp extends JFrame {

    private DefaultListModel<Task> taskListModel;
    private JList<Task> taskList;
    private JTextField taskField;
    private JButton addButton, deleteButton, toggleButton;
    private static final String FILE_NAME = "tasks.txt";

    public ToDoApp() {
        setTitle("To-Do List");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setCellRenderer(new TaskRenderer());
        JScrollPane scrollPane = new JScrollPane(taskList);

        taskField = new JTextField();
        taskField.setFont(new Font("SansSerif", Font.PLAIN, 16)); 
        taskField.setPreferredSize(new Dimension(200, 35));      
        taskField.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8)); 

        addButton = new JButton("Add Task");
        deleteButton = new JButton("Delete Task");
        toggleButton = new JButton("Mark Done/Undone");

        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.add(taskField, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(toggleButton);

        inputPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        loadTasksFromFile();

        addButton.addActionListener(e -> addTask());
        deleteButton.addActionListener(e -> deleteTask());
        toggleButton.addActionListener(e -> toggleTask());

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                saveTasksToFile();
            }
        });
    }

    private void addTask() {
        String text = taskField.getText().trim();
        if (!text.isEmpty()) {
            String[] options = {"Today", "Tomorrow", "Next Week", "Custom"};
            String choice = (String) JOptionPane.showInputDialog(
                    this,
                    "Select due date:",
                    "Due Date",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    "Today"
            );

            if (choice != null) {
                String dueDate = "";
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Calendar cal = Calendar.getInstance();

                switch (choice) {
                    case "Today":
                        dueDate = sdf.format(new Date());
                        break;
                    case "Tomorrow":
                        cal.add(Calendar.DATE, 1);
                        dueDate = sdf.format(cal.getTime());
                        break;
                    case "Next Week":
                        cal.add(Calendar.DATE, 7);
                        dueDate = sdf.format(cal.getTime());
                        break;
                    case "Custom":
                        dueDate = JOptionPane.showInputDialog(this,
                                "Enter custom due date (e.g. 2025-10-01 14:00):",
                                sdf.format(new Date()));
                        if (dueDate == null || dueDate.trim().isEmpty()) {
                            dueDate = sdf.format(new Date());
                        }
                        break;
                }

                taskListModel.addElement(new Task(text, false, dueDate));
                taskField.setText("");
                saveTasksToFile();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a task!");
        }
    }

    private void deleteTask() {
        int index = taskList.getSelectedIndex();
        if (index != -1) {
            taskListModel.remove(index);
            saveTasksToFile();
        } else {
            JOptionPane.showMessageDialog(this, "Select a task to delete!");
        }
    }

    private void toggleTask() {
        int index = taskList.getSelectedIndex();
        if (index != -1) {
            Task task = taskListModel.get(index);
            task.completed = !task.completed;
            taskList.repaint();
            saveTasksToFile();
        } else {
            JOptionPane.showMessageDialog(this, "Select a task to mark done/undone!");
        }
    }

    private void loadTasksFromFile() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.length() < 4) continue;

                    boolean completed = line.startsWith("[X]");
                    String content = line.substring(4);

                    String[] parts = content.split("\\| Due:");
                    if (parts.length == 2) {
                        String text = parts[0].trim();
                        String dueDate = parts[1].trim();
                        taskListModel.addElement(new Task(text, completed, dueDate));
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error loading tasks: " + e.getMessage());
            }
        }
    }

    private void saveTasksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (int i = 0; i < taskListModel.size(); i++) {
                writer.write(taskListModel.get(i).toString());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving tasks: " + e.getMessage());
        }
    }

    static class TaskRenderer extends JCheckBox implements ListCellRenderer<Task> {
        @Override
        public Component getListCellRendererComponent(JList<? extends Task> list, Task value,
                                                      int index, boolean isSelected, boolean cellHasFocus) {
            setText(value.text + "  (Due: " + value.dueDate + ")");
            setSelected(value.completed);

            if (value.completed) {
                setFont(getFont().deriveFont(Font.ITALIC));
            } else {
                setFont(getFont().deriveFont(Font.PLAIN));
            }

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            setEnabled(list.isEnabled());
            setOpaque(true);
            return this;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ToDoApp().setVisible(true));
    }
}
