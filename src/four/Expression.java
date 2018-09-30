package four;

import java.util.Random;

public class Expression {
    
    public String[] createFormula(int r){
        Random random = new Random();
        String[] operator = {"＋","－","×","÷","＝"};

        //运算符 && 操作数 && 式子
        String[] totalOperator = new String[1 + random.nextInt(3)];
        String[] totalFraction = new String[totalOperator.length+1];
        String formula = new String();
        //是否包含分数
        Boolean hasFraction = false;

        //随机产生 操作数：
        for (int i=0;i<totalFraction.length;i++) {
            int fractionOrNot = random.nextInt(2);
            if (fractionOrNot == 0) {
                int integralPart = random.nextInt(r+1);
                totalFraction[i] = String.valueOf(integralPart);
            } else {
                int denominator = 1+random.nextInt(r);
                int molecule = random.nextInt(denominator);
                int integralPart = random.nextInt(r+1);
                if (molecule!=0) {
                    int commonFactor = commonFactor(denominator, molecule);
                    denominator /= commonFactor;
                    molecule /= commonFactor;
                }
                if (integralPart == 0 && molecule > 0) {
                    totalFraction[i] = molecule + "/" + denominator;
                    hasFraction = true;
                }
                else if (molecule == 0)
                    totalFraction[i] = String.valueOf(integralPart);
                else {
                    totalFraction[i] = integralPart + "'" + molecule + "/" + denominator;
                    hasFraction = true;
                }
            }
        }
        for (int i=0;i < totalOperator.length;i++) {
            if (hasFraction)
                totalOperator[i] = operator[random.nextInt(2)];
            else
                totalOperator[i] = operator[random.nextInt(4)];
        }
        int choose = totalFraction.length;
        if (totalFraction.length != 2 )
            choose = random.nextInt(totalFraction.length);
        for (int i=0;i<totalFraction.length;i++) {
            if (i == choose && choose<totalOperator.length) {
                formula = formula + "(" + totalFraction[i] + totalOperator[i] ;
            } else if (i == totalFraction.length - 1 && i == choose+1 && choose<totalOperator.length) {
                formula = formula + totalFraction[i] + ")" + "=";
            } else if (i == choose+1 && choose<totalOperator.length) {
                formula = formula + totalFraction[i] + ")" + totalOperator[i];
            } else if (i == totalFraction.length - 1) {
                formula = formula + totalFraction[i] + "=";
            } else {
                formula = formula + totalFraction[i] + totalOperator[i];
            }
        }

        //检查运算结果
        Calculate checkAns = new Calculate();
        String[] ansFormula = checkAns.checkout(formula,3*totalOperator.length+2+1);//2*totalOperator.length+3+1

        //System.out.println("ansFormula："+Arrays.toString(ansFormula));
        if (ansFormula!=null)
            return ansFormula;
        return null;
    }

   
    public int commonFactor(int x,int y) {
        while(true)
        {
            if(x%y==0)return y;
            int temp=y;
            y=x%y;
            x=temp;
        }
    }

}