import java.util.Iterator;

import org.json.simple.JSONObject;

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
}
