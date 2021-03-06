import escript.*;
import escript.ast.ASTBuilder;
import escript.ast.exceptions.ASTBuildException;
import escript.ast.exceptions.InvalidIDException;
import escript.ast.exceptions.InvalidOperationException;
import escript.ast.exceptions.UndefinedVariableException;
import escript.ast.nodes.ASTNode;
import org.antlr.v4.gui.TreeViewer;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Standalone IDE for testing EScript.
 */
public class EScriptEditor {

    class ControlsPanel extends JPanel {

        public ControlsPanel() {
            JButton evalButton = new JButton("Evaluate");
            JButton prettyButton = new JButton("Pretty-Print");
            JButton pythonButton = new JButton("Compile to Python");
            JButton clearOutputButton = new JButton("Clear Output");

            evalButton.addActionListener(e -> {
                SwingUtilities.invokeLater(() -> {
                    String text = sourceCodeInput.getText();
                    if (text.trim().equals("")) {
                        output.setText("No input provided");
                        return;
                    }
                    try {
                        ASTNode tree = ASTBuilder.createAST(text);
                        updateAST(tree);
                        EvaluationOutput evaluationOutput = (EvaluationOutput) new EvaluatorVisitor().visit(tree);
                        String log = evaluationOutput.getOutput().stream().collect(Collectors.joining("\n"));
                        output.setText(log + (log.equals("") ? "" : "\n") + evaluationOutput);
                    } catch (UndefinedVariableException | InvalidIDException | InvalidOperationException | ASTBuildException err) {
                        output.setText(err.getMessage());
                    }
                });
            });

            prettyButton.addActionListener(e -> {
                SwingUtilities.invokeLater(() -> {
                    String text = sourceCodeInput.getText();
                    if (text.trim().equals("")) {
                        output.setText("No input provided");
                        return;
                    }
                    try {
                        ASTNode tree = ASTBuilder.createAST(text);
                        updateAST(tree);
                        output.setText(new PrettyPrintVisitor().visit(tree));
                    } catch (UndefinedVariableException | InvalidIDException | InvalidOperationException | ASTBuildException err) {
                        output.setText(err.getMessage());
                    }
                });
            });

            pythonButton.addActionListener(e -> {
                SwingUtilities.invokeLater(() -> {
                    String text = sourceCodeInput.getText();
                    if (text.trim().equals("")) {
                        output.setText("No input provided");
                        return;
                    }
                    try {
                        ASTNode tree = ASTBuilder.createAST(text);
                        updateAST(tree);
                        output.setText(new PythonCompilerVisitor().visit(tree));
                    } catch (UndefinedVariableException | InvalidIDException | InvalidOperationException | ASTBuildException err) {
                        output.setText(err.getMessage());
                    }
                });
            });

            clearOutputButton.addActionListener(e -> {
                SwingUtilities.invokeLater(() -> {
                    output.setText("");
                    astContainer.removeAll();
                    frame.revalidate();
                    frame.repaint();
                });
            });

            add(evalButton);
            add(prettyButton);
            add(pythonButton);
            add(clearOutputButton);
        }
    }

    private JFrame frame;

    private JTextArea sourceCodeInput;
    private JTextArea output;
    private JPanel astContainer;

    private ControlsPanel controlsPanel;

    public EScriptEditor() {
        frame = new JFrame("EScript Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1280, 720));

        sourceCodeInput = new JTextArea();
        sourceCodeInput.setTabSize(4);
        sourceCodeInput.setMargin(new Insets(4, 4, 4, 4));
        sourceCodeInput.setBorder(BorderFactory.createTitledBorder("Input"));

        output = new JTextArea();
        output.setTabSize(4);
        output.setMargin(new Insets(4, 4, 4, 4));
        output.setEditable(false);
        output.setBorder(BorderFactory.createTitledBorder("Output"));

        astContainer = new JPanel();
        astContainer.setBorder(BorderFactory.createTitledBorder("Abstract Syntax Tree"));
        controlsPanel = new ControlsPanel();
        controlsPanel.setBorder(BorderFactory.createTitledBorder("Controls"));

        frame.getContentPane().setLayout(new GridLayout(2, 2));
        frame.getContentPane().add(new JScrollPane(sourceCodeInput));
        frame.getContentPane().add(new JScrollPane(output));
        frame.getContentPane().add(new JScrollPane(astContainer));
        frame.getContentPane().add(new JScrollPane(controlsPanel));

        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    void updateAST(ASTNode tree) {
        astContainer.removeAll();
        TreeViewer treeViewer = new TreeViewer(Arrays.asList(""), tree);
        treeViewer.setScale(1.25f);
        astContainer.add(treeViewer);
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        new EScriptEditor();
    }
}
