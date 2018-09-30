package four;

import java.util.Stack;
import java.util.HashMap;

public class Calculate {
   
    public String[] checkout(String formula,int length){
        Stack<String> stackNumber = new Stack<>();
        Stack<String> stackOperator = new Stack<>();
        String[] reversePolishNotation = new String[length];
        HashMap<String, Integer> hashmap = new HashMap<>();
        hashmap.put("(", 0);
        hashmap.put("＋", 1);
        hashmap.put("－", 1);
        hashmap.put("×", 2);
        hashmap.put("÷", 2);
        for (int i=0,j=0; i < formula.length();) {
            StringBuilder digit = new StringBuilder();
            char c = formula.charAt(i);
            while (Character.isDigit(c)||c=='/'||c=='\'') {
                digit.append(c);
                i++;
                c = formula.charAt(i);
            }
            if (digit.length() == 0){
                switch (c) {
                    case '(': {
                        stackOperator.push(String.valueOf(c));
                        break;
                    }
                    case ')': {
                        String operator = stackOperator.pop();
                        while (!stackOperator.isEmpty() && !operator.equals("(")) {
                            String a = stackNumber.pop();
                            String b = stackNumber.pop();
                            reversePolishNotation[j++] = a;
                            reversePolishNotation[j++] = b;
                            reversePolishNotation[j++] = operator;
                            String ansString = calculate(b, a, operator);
                            if(ansString == null)
                                return  null;
                            stackNumber.push(ansString);
                            operator = stackOperator.pop();
                        }
                        break;
                    }
                    case '=': {
                        String operator;
                        while (!stackOperator.isEmpty()) {
                            operator = stackOperator.pop();
                            String a = stackNumber.pop();
                            String b = stackNumber.pop();
                            reversePolishNotation[j++] = a;
                            reversePolishNotation[j++] = b;
                            reversePolishNotation[j++] = operator;
                            String ansString = calculate(b, a, operator);
                            if(ansString == null)
                                return null;
                            stackNumber.push(ansString);
                        }
                        break;
                    }
                    default: {
                        String operator;
                        while (!stackOperator.isEmpty()) {
                            operator = stackOperator.pop();
                            if (hashmap.get(operator) >= hashmap.get(String.valueOf(c))) {
                                String a = stackNumber.pop();
                                String b = stackNumber.pop();
                                reversePolishNotation[j++] = a;
                                reversePolishNotation[j++] = b;
                                reversePolishNotation[j++] = operator;
                                String ansString =calculate(b, a, operator);
                                if(ansString == null)
                                    return  null;
                                stackNumber.push(ansString);
                            }
                            else {
                                stackOperator.push(operator);
                                break;
                            }

                        }
                        stackOperator.push(String.valueOf(c));  
                        break;
                    }
                }
            }
            else {
                stackNumber.push(digit.toString());
                continue; 
            }
            i++;
        }
        reversePolishNotation[length-3] = "=";
        reversePolishNotation[length-2] = stackNumber.peek();
        reversePolishNotation[length-1] = formula;
        return reversePolishNotation;
    }

   
    private String calculate(String m,String n,String operator) {
        String ansFormula = null;
        char op = operator.charAt(0);
        int[] indexFraction = {m.indexOf('\''), m.indexOf('/'), n.indexOf('\''), n.indexOf('/')};
        if (indexFraction[1] > 0 || indexFraction[3] > 0) {
            int[] denominator = new int[3];
            int[] molecule = new int[3];
            int[] integralPart = new int[3];
            if (indexFraction[1] > 0) {
                for (int i = 0; i < m.length(); i++) {
                    if (i < indexFraction[0]) {
                        integralPart[0] = Integer.parseInt(integralPart[0] + String.valueOf(m.charAt(i) - '0'));
                    } else if (i > indexFraction[0] && i < indexFraction[1]) {
                        molecule[0] = Integer.parseInt(molecule[0] + String.valueOf(m.charAt(i) - '0'));
                    } else if (i > indexFraction[1]) {
                        denominator[0] = Integer.parseInt(denominator[0] + String.valueOf(m.charAt(i) - '0'));
                    }
                }
            } else {
                integralPart[0] = Integer.parseInt(m);
                denominator[0] = 1;
                molecule[0] = 0;
            }

            if (indexFraction[3] > 0) {
                for (int i = 0; i < n.length(); i++) {
                    if (i < indexFraction[2]) {
                        integralPart[1] = Integer.parseInt(integralPart[1] + String.valueOf(n.charAt(i) - '0'));
                    } else if (i > indexFraction[2] && i < indexFraction[3]) {
                        molecule[1] = Integer.parseInt(molecule[1] + String.valueOf(n.charAt(i) - '0'));
                    } else if (i > indexFraction[3]) {
                        denominator[1] = denominator[1] + n.charAt(i) - '0';
                    }
                }
            } else {
                integralPart[1] = Integer.parseInt(n);
                denominator[1] = 1;
                molecule[1] = 0;
            }
            switch (op) {
                case '＋': {
                    denominator[2] = denominator[0] * denominator[1];
                    molecule[2] = integralPart[0] * denominator[2] + molecule[0] * denominator[1]
                            + integralPart[1] * denominator[2] + molecule[1] * denominator[0];
                    break;
                }
                case '－': {
                    denominator[2] = denominator[0] * denominator[1];
                    molecule[2] = integralPart[0] * denominator[2] + molecule[0] * denominator[1]
                            - integralPart[1] * denominator[2] - molecule[1] * denominator[0];
                    break;
                }
                default:
                    return null;
            }
            if (molecule[2] >= denominator[2] && molecule[2]>0) {
                integralPart[2] = molecule[2] / denominator[2];
                molecule[2] = Math.abs(molecule[2] % denominator[2]);
            } else if (molecule[2]<0) {
                return null;
            }
            if (molecule[2] != 0) {
                ansFormula = greatFraction(integralPart[2],molecule[2],denominator[2]);
            } else ansFormula = String.valueOf(integralPart[2]);

        } else {
            int a = Integer.parseInt(m);
            int b = Integer.parseInt(n);
            switch (op) {
                case '＋': {
                    ansFormula = String.valueOf(a + b);
                    break;
                }
                case '－': {
                    if (a - b >= 0)
                        ansFormula = String.valueOf(a - b);
                    else
                        return null;
                    break;
                }
                case '×': {
                    ansFormula = String.valueOf(a * b);
                    break;
                }
                case '÷': {
                    if (b == 0) {
                        return null;
                    } else if (a % b != 0) {
                        ansFormula = a % b + "/" + b;
                        if (a / b > 0) ansFormula = a / b + "'" + ansFormula;
                    } else
                        ansFormula = String.valueOf(a / b);
                    break;
                }
            }
        }
        return ansFormula;
    }

    
    private String greatFraction (int integralPart,int molecule,int denominator) {
        String ansFormula;
        int commonFactor = 1;

        //求最大公约数
        Expression create = new Expression();
        commonFactor = create.commonFactor(denominator,molecule);

        //化简分数
        denominator /= commonFactor;
        molecule /= commonFactor;

        //带分数 表示
        if (integralPart == 0 && molecule > 0) {
            ansFormula = String.valueOf(molecule) + '/' + String.valueOf(denominator);
        } else if (molecule == 0)
            ansFormula = String.valueOf(integralPart);
        else {
            ansFormula = String.valueOf(integralPart) + "'" + String.valueOf(molecule) + '/' + String.valueOf(denominator);
        }

        //返回最简分数
        return ansFormula;
    }
}