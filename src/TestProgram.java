import expressionscript.*;
import expressionscript.ast.ASTBuilder;
import expressionscript.ast.nodes.ASTNode;
import expressionscript.ast.nodes.statement.EExpressionOperator;
import expressionscript.ast.nodes.statement.ExpressionNode;
import expressionscript.ast.nodes.values.BooleanNode;
import expressionscript.ast.nodes.values.IntegerNode;
import expressionscript.ast.nodes.values.VariableNode;
import expressionscript.ast.nodes.condition.BranchNode;
import expressionscript.ast.nodes.condition.ConditionNode;
import expressionscript.ast.nodes.condition.EComparisonOperator;
import expressionscript.ast.nodes.statement.IfNode;
import expressionscript.ast.nodes.values.StringNode;
import expressionscript.ast.nodes.statement.AssignmentNode;
import expressionscript.exceptions.TypeException;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.Tree;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class TestProgram {

    private static escriptParser createParser(String source) {
        CharStream sourceCharStream = CharStreams.fromString(source);
        escriptLexer lexer = new escriptLexer(sourceCharStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
        escriptParser parser = new escriptParser(commonTokenStream);
        return parser;
    }

    private static ASTNode createAST(String source) {
        Object result = new ASTBuilder().visit(createParser(source).start());
        return (ASTNode) result;
    }

    private static void createVisualTree(Tree tree, String title) {
        // Random GUI stuff, TODO: Cleanup
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TreeViewer treeViewer = new TreeViewer(Arrays.asList(""), tree);
        frame.setMinimumSize(new Dimension(300, 200));
        frame.getContentPane().add(treeViewer, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void createConcreteTreeVisual(escriptParser parser, String title) {
        // Random GUI stuff, TODO: Cleanup
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TreeViewer treeViewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), parser.start());
        frame.setMinimumSize(new Dimension(300, 200));
        frame.getContentPane().add(treeViewer, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main3(String[] args) {
        ASTNode tree = new BranchNode(
                new IfNode(
                        new ConditionNode(
                                new VariableNode("money"),
                                EComparisonOperator.EQUALS,
                                new VariableNode("b")
                        ),
                        Arrays.asList(
                                new AssignmentNode(
                                        new VariableNode("msg"),
                                        new StringNode("Empty Account")
                                )
                        )
                ),
                Arrays.asList(
                        new IfNode(
                                new ConditionNode(
                                        new VariableNode("money"),
                                        EComparisonOperator.GREATER_THEN_OR_EQUALS,
                                        new IntegerNode(100)
                                ),
                                Arrays.asList(
                                        new AssignmentNode(
                                                new VariableNode("money"),
                                                new StringNode("£MAX")
                                        ),
                                        new AssignmentNode(
                                                new VariableNode("is_rich"),
                                                new BooleanNode(true)
                                        )
                                )
                        )
                ),
                Arrays.asList(
                        new AssignmentNode(
                                new VariableNode("msg"),
                                new ExpressionNode(
                                        new StringNode("£"),
                                        EExpressionOperator.ADD,
                                        new VariableNode("money")
                                )
                        ),
                        new AssignmentNode(
                                new VariableNode("is_bankrupt"),
                                new BooleanNode(false)
                        ),
                        new AssignmentNode(
                                new VariableNode("is_bankrupt"),
                                new ExpressionNode(new ConditionNode(
                                        new VariableNode("apple"),
                                        EComparisonOperator.OR,
                                        new VariableNode("microsoft")
                                ),
                                        EExpressionOperator.MULTIPLY,
                                        new IntegerNode(200))
                        )
                )
        );

        createVisualTree(tree, "From Nodes We Springth");
        System.out.println(tree.toStringTree());
//        System.out.println(new PrettyPrintVisitor().visit(tree));
    }

    public static void main(String[] args) {
        try {
//        String sourceCode = "name=\"lewis\";\n" +
//                "age = 2020 - 1997;\n" +
//                "money = age * 356;\n" +
//                "\n" +
//                "is_bankrupt = money <= 0;\n" +
//                "\n" +
//                "if (is_bankrupt) msg = \"Empty Account\";\n" +
//                "else if (money > 10000) {\n" +
//                "\tmsg = \"£MAX\";\n" +
//                "}\n" +
//                "else msg = \"£\" + (money / 1.5);";

//        String sourceCode = "name = \"lh16674\";age = 22;\n" +
//                "    money = 22 * 1000 - 9250;\n" +
//                "is_bankrupt = money       >\n" +
//                " 0;\n" +
//                "\n" +
//                " if (is_bankrupt == true)\n" +
//                "    age = age + 50;\n" +
//                "    else if (100 > 50){\n" +
//                "        money = money * 10;\n" +
//                "        money = money + 1;}\n" +
//                "  else\n" +
//                "    money = 9999;";

//        String sourceCode = "if (a == b)\n" +
//                "\tmsg = \"Empty Account\";\n" +
//                "else if (money >= 100) {\n" +
//                "\tmsg = \"£MAX\";\n" +
//                "}\n" +
//                "else\n" +
//                "{\n" +
//                "\tmsg = \"£\" + money;\n" +
//                "\tis_bankrupt = !false;\n" +
//                "\tmath = 3*(2-1)/4.3;\n" +
//                "}";

//            String sourceCode = "name=-(3+5); test=false;";

            String sourceCode = "n = 12;previous = 0;current = 1;index = 1;while (index <= n){current;temp = current;current = current + previous;previous = temp;index = index + 1;}";

            System.out.println("----------------------------------------------------------");
            System.out.println("INPUT");
            System.out.println("----------------------------------------------------------");
            System.out.println(sourceCode);
            System.out.println("----------------------------------------------------------");
            System.out.println("OUTPUT");
            System.out.println("----------------------------------------------------------");

            ASTNode sourceAST = createAST(sourceCode);
            String formattedSourceCode = new PrettyPrintVisitor().visit(sourceAST);
            System.out.println(formattedSourceCode);
            ASTNode formattedSourceAST = createAST(formattedSourceCode);


            createConcreteTreeVisual(createParser(sourceCode), "Antlr Concrete");
            createVisualTree(sourceAST, "AST From Source");
            createVisualTree(formattedSourceAST, "AST From Formatted Source");

            System.out.println("----------------------------------------------------------");
            System.out.println("TREES");
            System.out.println("SOURCE TREE   : " + sourceAST.toStringTree());
            System.out.println("FORMATTED TREE: " + formattedSourceAST.toStringTree());
            System.out.println("EQUIVALENT    : " + sourceAST.toStringTree().equals(formattedSourceAST.toStringTree()));
            System.out.println("----------------------------------------------------------");

            System.out.println("----------------------------------------------------------");
            System.out.println("EVAL");
            System.out.println("----------------------------------------------------------");
            System.out.println(new EvaluatorVisitor().visit(sourceAST));
//            System.out.println(new PythonCompileVisitor().visit(sourceAST));
        } catch (TypeException err) {
            err.printStackTrace();
        }
    }

//    public static void main(String[] args) {
////        String sourceCode = "a=4-2;foo=\"bar  \"+3;apple=a * 3 / -(2+1);banana=\"hello world\";z=a>2;";
////        String sourceCode = "a=3>2;b=a+2;c=b*a;a>b;";
////        String sourceCode = "a=3>2;if (a > 5) {mstg=2;} else if (b == c) {if (b == b) name=a;lARR=\"ha\";} else foo=true;";
//        String sourceCode = "name = \"lh16674\";age = 22;\n" +
//                "    money = 22 * 1000 - 9250;\n" +
//                "is_bankrupt = money       <\n" +
//                " 0;\n" +
//                "\n" +
//                " if (is_bankrupt == true)\n" +
//                "    age = age + 50;\n" +
//                "    else if (100 > 50){\n" +
//                "        money = money * 10;\n" +
//                "        money = money + 1;}\n" +
//                "  else\n" +
//                "    money = 9999;";
////        String sourceCode = "result=4+5-7;";
//
//        System.out.println("INPUT : " + sourceCode);
////        ASTNode assignmentStatement = createAST(sourceCode);
//
////        String output = new PrettyPrintVisitor().visit((ASTNode) assignmentStatement);
//        System.out.println("OUTPUT: " + output);
//
//        // The output we produce should allow use to recreate the output using itself as input
////        System.out.println(
////                assignmentStatement.toStringTree().equals(
////                        createAST(new PrettyPrintASTVisitor().visit(assignmentStatement)).toStringTree())
////        );
//
//
////        System.out.println("ORIGINAL TREE: " + assignmentStatement.toStringTree());
////        System.out.println("REMADE TREE  : " + createAST(output).toStringTree());
////        System.out.println("TREE MATCHES : " + assignmentStatement.toStringTree().equals(createAST(output).toStringTree()));
////
////        createVisualTree(assignmentStatement);
////        createVisualTree(createAST(output));
//    }
}
