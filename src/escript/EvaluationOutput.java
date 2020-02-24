package escript;

import escript.ast.nodes.function.payload.FunctionDefinitionPayload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents all of the output from the Evaluator.
 */
public class EvaluationOutput {

    private List<String> output = new ArrayList<>();
    private HashMap<String, Object> symbolTable = new HashMap<>();
    private HashMap<String, FunctionDefinitionPayload> functionTable = new HashMap<>();

    // TODO: Improve comments
    public EvaluationOutput() {

    }

    public EvaluationOutput(List<String> output, HashMap<String, Object> symbolTable, HashMap<String, Object> functionTable) {
        this.output = output;
        this.symbolTable = (HashMap<String, Object>) symbolTable.clone();
        this.functionTable = (HashMap<String, FunctionDefinitionPayload>) functionTable.clone();
    }

    public void log(String message) {
        output.add(message);
    }

    public List<String> getOutput() {
        return output;
    }

    public HashMap<String, Object> getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(HashMap<String, Object> variableMap) {
        this.symbolTable = (HashMap) variableMap.clone();
    }

    public void setFunctionTable(HashMap<String, FunctionDefinitionPayload> functionTable) {
        this.functionTable = (HashMap) functionTable.clone();
    }

    @Override
    public String toString() {
        String variables = symbolTable.entrySet().stream().map(e -> {
            String object = e.getValue().toString();
            if (e.getValue() instanceof String) object = String.format("\"%s\"", object);
            return String.format("%s: %s", e.getKey(), object);
        }).collect(Collectors.joining(", "));

        String functions = functionTable.entrySet().stream().map(e -> {
            String object = e.getValue().toString();
            return e.getKey();
        }).collect(Collectors.joining(", "));

        return String.format("{ variables: { %s }, functions: { %s } }", variables, functions);
    }
}
