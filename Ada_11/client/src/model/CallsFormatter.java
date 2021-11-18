package model;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CallsFormatter {
    public String generateRequest(String serviceName, int varsNumber, Iterator<String> variables) {
        JSONObject json = new JSONObject();

        try {
            json.put("servicio", serviceName);
            json.put("variables", varsNumber);

            for (int i = 1; i <= varsNumber; i++) {
                json.put("variable" + i, variables.next());

                String next = variables.next();
                try {
                    json.put("valor" + i, Integer.parseInt(next));
                } catch (Exception e) {
                    json.put("valor" + i, next);
                }
            }
        } catch (Exception e) {
            json = new JSONObject();
        }

        return json.toJSONString();
    }

    public String generateResponse(String serviceName, int varsNumber, Iterator<String> variables) {
        JSONObject json = new JSONObject();

        try {
            json.put("servicio", serviceName);
            json.put("respuestas", varsNumber);

            for (int i = 1; i <= varsNumber; i++) {
                json.put("respuesta" + i, variables.next());

                String next = variables.next();
                try {
                    json.put("valor" + i, Integer.parseInt(next));
                } catch (Exception e) {
                    json.put("valor" + i, next);
                }
            }
        } catch (Exception e) {
            json = new JSONObject();
        }

        return json.toJSONString();
    }

    public Iterator<String[]> recoverAnswers(String message) {
        ArrayList<String[]> answers = new ArrayList<>();

        try {
            JSONObject json = (JSONObject) new JSONParser().parse(message);
            int answerAmount = ((Long) json.get("respuestas")).intValue();

            for(int i = 2; i <= answerAmount; i++) {
                Object secondOption = json.get("valor" + i);
                answers.add(
                        new String[]{(String) json.get("respuesta" + i),
                        secondOption instanceof String ? (String) secondOption : "" + ((Long) secondOption).intValue()});

                System.out.println(answers.get(i-2)[0] + "," + answers.get(i-2)[1]);
            }
        } catch (Exception e) {
            answers.clear();
        }

        return answers.iterator();
    }
}
