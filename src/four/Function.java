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

            //如果文件已存在，则删除文件
            if (exTXT.exists()) {
                exTXT.delete();
            }
            //创建文件成功？？
            if(exTXT.createNewFile()){
                System.out.println("创建Exercises.txt:");
                FileOutputStream txtFile = new FileOutputStream(exTXT);
                PrintStream q = new PrintStream(exTXT);

                for(int i=0;i<txtList.size();i++){
                    System.out.print(">");
                    q.println(i+1 + ". " +txtList.get(i));
                    System.out.println(i+1 + ". " +txtList.get(i));
                }

                txtFile.close();
                q.close();
                System.out.println("已创建Exercises.txt");
            }
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    
    private void createAnsFile(ArrayList ansList){
        try{
            File ansTXT = new File("../Answer.txt");

            //如果文件已存在，则删除文件
            if (ansTXT.exists()) {
                ansTXT.delete();
            }
            //创建文件成功？？
            if(ansTXT.createNewFile()){
                System.out.print("创建Answer.txt:");
                FileOutputStream ansFile = new FileOutputStream(ansTXT);
                PrintStream p = new PrintStream(ansTXT);
                p.println("答案：\n");

                for(int i=0;i<ansList.size();i++){//正常运行
                //for(int i=0;i<ansList.size()+1;i++){//测试代码覆盖率
                    System.out.print(">");
                    p.println(i+1 + ". " +ansList.get(i));
                }
                ansFile.close();
                p.close();
                System.out.println("创建成功！");
            }
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    

}