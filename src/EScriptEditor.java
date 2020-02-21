import expressionscript.*;
import expressionscript.ast.ASTBuilder;
import expressionscript.ast.nodes.ASTNode;
import expressionscript.exceptions.TypeException;
import javafx.scene.control.TreeView;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class EScriptEditor {

    class ButtonPanel extends JPanel {
        JButton evalButton;
        JButton prettyButton;
        JButton clearButton;

        public ButtonPanel() {
            evalButton = new JButton("Evaluate");
            prettyButton = new JButton("Pretty-Print");
            clearButton = new JButton("Clear Eval");

            evalButton.addActionListener(e -> {
//                try {
////                    EvaluationResults results = (EvaluationResults) evaluator.visit(createAST(sourceCodeInput.getText()));
////                    evalOutputConsole.append(results.lines.stream().collect(Collectors.joining("\n")));
////                    evalVariables.setText(results.variableMap.toString());
//                } catch (TypeException ex) {
//                    ex.printStackTrace();
//                }
                SwingUtilities.invokeLater(() -> {
                    try {
                        ASTNode tree = createAST(sourceCodeInput.getText());
                        EvaluationResults results = (EvaluationResults) evaluator.visit(tree);
                        evalOutputConsole.append(results.lines.stream().collect(Collectors.joining("\n")) + "\n");
                        evalVariables.setText(results.variableMap.toString());
                        setASTPanel(tree);
                    } catch (TypeException err) {
                        err.printStackTrace();
                    }

                });

            });

            prettyButton.addActionListener(e -> {
                try {
                    ASTNode tree = createAST(sourceCodeInput.getText());
                    sourceCodeInput.setText(prettyPrinter.visit(tree));
                    setASTPanel(tree);
                } catch (TypeException ex) {
                    ex.printStackTrace();
                }
            });

            clearButton.addActionListener(e -> {
                evalOutputConsole.setText("");
                evalVariables.setText("");
            });

            add(evalButton);

            add(prettyButton);

            add(clearButton);
        }

        void setASTPanel(ASTNode tree) {
            ASTContainer.removeAll();
            TreeViewer treeViewer = new TreeViewer(Arrays.asList(""), tree);
            treeViewer.setScale(1f);
            ASTContainer.add(
                    treeViewer
            );
            ASTContainer.validate();
        }

    }

    // Button Panel for input, Tree Viewer for AST, TextArea for Source Code, TextArea for output

    private JFrame frame;
    //
    private JTextArea sourceCodeInput;
    private JTextArea evalOutputConsole;
    private JTextArea evalVariables;

    private JSplitPane frameSplitPane;
    private JSplitPane sourceASTPane;
    private JPanel ASTContainer;
    private JSplitPane evalCtrlPane;
    private JSplitPane evalLogMapPane;

    private PrettyPrintVisitor prettyPrinter;
    private EvaluatorVisitor evaluator;

    public EScriptEditor() {
        prettyPrinter = new PrettyPrintVisitor();
        evaluator = new EvaluatorVisitor();

        frame = new JFrame("EScript Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(640, 640));

        frameSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        sourceASTPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

        sourceCodeInput = new JTextArea();
        sourceCodeInput.setTabSize(4);
        sourceCodeInput.setMargin(new Insets(2, 2, 2, 2));

        // TODO: Replace with tree viewer at some point
        ASTContainer = new JPanel();

        sourceASTPane.add(sourceCodeInput);
        sourceASTPane.add(ASTContainer);

        evalCtrlPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

        evalOutputConsole = new JTextArea();
        evalOutputConsole.setTabSize(4);
        evalOutputConsole.setMargin(new Insets(2, 2, 2, 2));
        evalOutputConsole.setEditable(false);

        evalVariables = new JTextArea();
        evalVariables.setTabSize(4);
        evalVariables.setMargin(new Insets(2, 2, 2, 2));
        evalVariables.setEditable(false);

        evalLogMapPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        evalLogMapPane.add(evalOutputConsole);

        evalLogMapPane.add(evalVariables);

        evalCtrlPane.add(evalLogMapPane);
        evalCtrlPane.add(new ButtonPanel());

        frameSplitPane.add(sourceASTPane);
        frameSplitPane.add(evalCtrlPane);

        frame.getContentPane().add(frameSplitPane);
    }

    public void showWindow() {
        frame.pack();

        frameSplitPane.setDividerLocation(307);
        sourceASTPane.setDividerLocation(353);
        evalCtrlPane.setDividerLocation(530);
        evalLogMapPane.setDividerLocation(356);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static escriptParser createParser(String source) {
        CharStream sourceCharStream = CharStreams.fromString(source);
        escriptLexer lexer = new escriptLexer(sourceCharStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
        escriptParser parser = new escriptParser(commonTokenStream);
        return parser;
    }

    private ASTNode createAST(String source) {
        return (ASTNode) new ASTBuilder().visit(createParser(source).start());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EScriptEditor().showWindow());
    }
}
