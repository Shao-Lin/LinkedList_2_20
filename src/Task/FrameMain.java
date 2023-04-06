package Task;

import util.ArrayUtils;
import util.SwingUtils;
import util.JTableUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FrameMain extends JFrame {
    private JTable tableInput;
    private JTable tableOutput;
    private JButton buttonLoadInputFromFile;
    private JButton buttonSort;
    private JButton buttonSaveOutputIntoFile;
    private JPanel panelMain;
    private final JFileChooser fileChooserOpen;
    private final JFileChooser fileChooserSave;

    public FrameMain() {
        this.setTitle("Task 2");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(tableInput, 40, false, true, false, true);
        JTableUtils.initJTableForArray(tableOutput, 40, false, true, false, false);
        tableOutput.setEnabled(false);
        tableInput.setRowHeight(25);
        tableOutput.setRowHeight(25);

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");
        JTableUtils.writeArrayToJTable(tableInput, new int[]{142, 3, 3, 10, 134});
        this.pack();


        buttonLoadInputFromFile.addActionListener(actionEvent -> {
            try {
                if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    int[] array = ArrayUtils.readIntArrayFromFile(fileChooserOpen.getSelectedFile().getPath());
                    JTableUtils.writeArrayToJTable(tableInput, array);
                }
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        });

        buttonSaveOutputIntoFile.addActionListener(actionEvent -> {
            try {
                if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    int[] array = JTableUtils.readIntArrayFromJTable(tableOutput);
                    String file = fileChooserSave.getSelectedFile().getPath();
                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }
                    ArrayUtils.writeArrayToFile(file, array, null);
                }
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }

        });
        buttonSort.addActionListener(actionEvent -> {
            try {
                int [] list1 = JTableUtils.readIntArrayFromJTable(tableInput);
                assert list1 != null;
                SimpleLinkedList<Integer> list = SimpleLinkedList.IntegerArrayToLinkedList(list1);
                list.removingNonSimpleElements();
                String[] result = SimpleLinkedList.IntegerToStringArray(list);
                JTableUtils.writeArrayToJTable(tableOutput, result);
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        });
    }
}
