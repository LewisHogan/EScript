import expressionscript.PrettyPrintVisitor;
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
import expressionscript.ast.nodes.condition.IfNode;
import expressionscript.ast.nodes.values.StringNode;
import expressionscript.ast.nodes.statement.AssignmentNode;
import expressionscript.escriptLexer;
import expressionscript.escriptParser;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.Tree;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class TestProgram {

    private static ASTNode createAST(String source) {
        CharStream sourceCharStream = CharStreams.fromString(source);
        escriptLexer lexer = new escriptLexer(sourceCharStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
        escriptParser parser = new escriptParser(commonTokenStream);

        ASTBuilder builder = new ASTBuilder();
        Tree concreteTree = parser.start();

        Object result = new ASTBuilder().visit((escriptParser.StartContext) concreteTree);

        return (ASTNode) result;
    }

    private static void createASTVisual(ASTNode tree) {
        // Random GUI stuff, TODO: Cleanup
        JFrame frame = new JFrame("PrettyPrintVisitor Main");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TreeViewer treeViewer = new TreeViewer(Arrays.asList(""), tree);
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

        createASTVisual(tree);
        System.out.println(tree.toStringTree());
        System.out.println(new PrettyPrintVisitor().visit(tree));
    }

    public static void main(String[] args) {
//        String sourceCode = "name=\"lewis\";\n" +
//                "age = 2020 - 1997;\n" +
//                "money = age * 356;\n" +
//                "\n" +
//                "is_bankrupt = money > 0;\n" +
//                "\n" +
//                "if (is_bankrupt == true) msg = \"Empty Account\";\n" +
//                "else if (money > 100) {\n" +
//                "\tmsg = \"£MAX\";\n" +
//                "}\n" +
//                "else msg = \"£\" + money;";

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

        String sourceCode = "if (a == b)\n" +
                "\tmsg = \"Empty Account\";\n" +
                "else if (money >= 100) {\n" +
                "\tmsg = \"£MAX\";\n" +
                "}\n" +
                "else\n" +
                "{\n" +
                "\tmsg = \"£\" + money;\n" +
                "\tis_bankrupt = false;\n" +
                "\tmath = 3*(2-1)/4.3;\n" +
                "}";

        System.out.println("INPUT");
        System.out.println("----------------------------------------------------------");
        System.out.println(sourceCode);
        System.out.println("OUTPUT");
        System.out.println("----------------------------------------------------------");

        ASTNode sourceAST = createAST(sourceCode);
        String formattedSourceCode = new PrettyPrintVisitor().visit(sourceAST);
        System.out.println(formattedSourceCode);
        ASTNode formattedSourceAST = createAST(formattedSourceCode);


        createASTVisual(sourceAST);
        createASTVisual(formattedSourceAST);

        System.out.println("SOURCE TREE   : " + sourceAST.toStringTree());
        System.out.println("FORMATTED TREE: " + formattedSourceAST.toStringTree());
        System.out.println("EQUIVALENT    : " + sourceAST.toStringTree().equals(formattedSourceAST.toStringTree()));
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
////        createASTVisual(assignmentStatement);
////        createASTVisual(createAST(output));
//    }
}
