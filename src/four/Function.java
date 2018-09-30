package four;

import java.io.*;
import java.util.ArrayList;

public class Function {
    
    public void createProblemSet(int n,int r){
        Check temporarySet = new Check();
        ArrayList returnList = temporarySet.generate(n,r);
        ArrayList<String> txtList = new ArrayList<>();
        ArrayList<String> ansList = new ArrayList<>();
        for (int i =0;i<2*n;i++) {
            if(i<n) txtList.add(returnList.get(i).toString());
            else ansList.add(returnList.get(i).toString());
        }
        createEXEFile(txtList);
        createAnsFile(ansList);
    }

    
    private void createEXEFile(ArrayList txtList){
        try{
            File exTXT = new File("../Exercises.txt");

            //����ļ��Ѵ��ڣ���ɾ���ļ�
            if (exTXT.exists()) {
                exTXT.delete();
            }
            //�����ļ��ɹ�����
            if(exTXT.createNewFile()){
                System.out.println("����Exercises.txt:");
                FileOutputStream txtFile = new FileOutputStream(exTXT);
                PrintStream q = new PrintStream(exTXT);

                for(int i=0;i<txtList.size();i++){
                    System.out.print(">");
                    q.println(i+1 + ". " +txtList.get(i));
                    System.out.println(i+1 + ". " +txtList.get(i));
                }

                txtFile.close();
                q.close();
                System.out.println("�Ѵ���Exercises.txt");
            }
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    
    private void createAnsFile(ArrayList ansList){
        try{
            File ansTXT = new File("../Answer.txt");

            //����ļ��Ѵ��ڣ���ɾ���ļ�
            if (ansTXT.exists()) {
                ansTXT.delete();
            }
            //�����ļ��ɹ�����
            if(ansTXT.createNewFile()){
                System.out.print("����Answer.txt:");
                FileOutputStream ansFile = new FileOutputStream(ansTXT);
                PrintStream p = new PrintStream(ansTXT);
                p.println("�𰸣�\n");

                for(int i=0;i<ansList.size();i++){//��������
                //for(int i=0;i<ansList.size()+1;i++){//���Դ��븲����
                    System.out.print(">");
                    p.println(i+1 + ". " +ansList.get(i));
                }
                ansFile.close();
                p.close();
                System.out.println("�����ɹ���");
            }
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    

}