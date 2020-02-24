package escript;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents all of the output from the Evaluator.
 */
public class EvaluationOutput {

    private List<String> output = new ArrayList<>();
    private HashMap<String, Object> variableMap = new HashMap<>();

    // TODO: Improve comments
    public EvaluationOutput() {

    }

    public EvaluationOutput(List<String> output, HashMap<String, Object> variableMap) {
        this.output = output;
        this.variableMap = (HashMap<String, Object>) variableMap.clone();
    }

    public void log(String message) {
        output.add(message);
    }

    public List<String> getOutput() {
        return output;
    }

    public HashMap<String, Object> getVariableMap() {
        return variableMap;
    }

    public void setVariableMap(HashMap<String, Object> variableMap) {
        this.variableMap = (HashMap) variableMap.clone();
    }

    @Override
    public String toString() {
        String variables = variableMap.entrySet().stream().map(e -> {
            String object = e.getValue().toString();
            if (e.getValue() instanceof String) object = String.format("\"%s\"", object);
            return String.format("%s: %s", e.getKey(), object);
        }).collect(Collectors.joining(", "));

        return String.format("{ %s }", variables);
    }
}
