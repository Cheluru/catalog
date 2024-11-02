import org.json.JSONArray;
import org.json.JSONObject;
import java.util.*;

public class ShamirSecretSharing {
    public static void main(String[] args) {
        String testCase = "{\"k\":3,\"points\":[{\"base\":\"10\",\"value\":\"4\"},{\"base\":\"2\",\"value\":\"111\"},{\"base\":\"10\",\"value\":\"12\"},{\"base\":\"4\",\"value\":\"211\"}]}";
        System.out.println(findConstantTerm(testCase));
    }

    public static long findConstantTerm(String testCase) {
        JSONObject jsonObject = new JSONObject(testCase);
        int degree = jsonObject.getInt("k");
        JSONArray points = jsonObject.getJSONArray("points");

        long constantTerm = 1;
        for (int i = 0; i < points.length(); i++) {
            JSONObject point = points.getJSONObject(i);
            int base = Integer.parseInt(point.getString("base"));
            String value = point.getString("value");

            long decimalValue = decimalConversion(value, base);
            constantTerm *= decimalValue;
        }
        constantTerm *= (long) Math.pow(-1, degree);

        return constantTerm;
    }

    public static long decimalConversion(String value, int base) {
        long decimal = 0;
        int power = 0;
        for (int i = value.length() - 1; i >= 0; i--) {
            char digit = value.charAt(i);
            int digitValue = Character.isDigit(digit) ? digit - '0' : digit - 'a' + 10;
            decimal += digitValue * Math.pow(base, power);
            power++;
        }
        return decimal;
    }
}
