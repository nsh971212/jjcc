package four;

import java.util.ArrayList;

public class Check {
    private ArrayList<String> returnList = new ArrayList<>();
    private ArrayList<String> txtList = new ArrayList<>();
    private ArrayList<String> ansList = new ArrayList<>();
    private ArrayList<String[]> ansFoList = new ArrayList<>();

   
    public ArrayList generate(int n,int r) {
        Expression create = new Expression();
        //����n�����ظ���ʽ��
        for(int i=0;i<n;){
            //�������ʽ��
            String[] ansFormula = create.createFormula(r);
            //�ж����ɵ�ʽ���Ƿ��ظ�
            if (ansFormula!=null)
                if (!ifRepeat(ansFormula)) i++;
        }

        //��ʽ�Ӽ���������ӵ�returnList
        for (int i =0; i<2*n;i++) {
            if(i<n) {
                returnList.add(txtList.get(i));
            } else {
                returnList.add(ansList.get(i - n));
            }
        }
        return returnList;
    }

   
    private boolean ifRepeat(String[] ansFormula) {
        String formula = ansFormula[ansFormula.length-1];
        String[] rPNotation = new String[ansFormula.length-1];
        System.arraycopy(ansFormula, 0, rPNotation, 0, ansFormula.length-1);
        boolean ifRepeat = false;
        for (String[] ansFo: ansFoList) {
            if (ansFo == rPNotation) ifRepeat =true;
            else if (ansFo.length == rPNotation.length && ansFo[rPNotation.length-1].equals(rPNotation[rPNotation.length-1])){
                for (int j=0;j<rPNotation.length;j=j+3) {
                    if (ansFo[j].equals(rPNotation[j+1]) && ansFo[j+1].equals(rPNotation[j]) && ansFo[j+2].equals(rPNotation[j+2]))
                        ifRepeat =true;
                }
            }
        }
        if (!ifRepeat) {
            this.txtList.add(formula);
            this.ansList.add(rPNotation[rPNotation.length-1]);
            this.ansFoList.add(rPNotation);
        }
        return ifRepeat;
    }
}