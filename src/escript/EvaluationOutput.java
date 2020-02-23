package escript;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents all of the output from the Evaluator.
 */
public class EvaluationOutput {

    private List<String> output = new ArrayList<>();
    private HashMap<String, Object> variableMap = new HashMap<>();

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
}
