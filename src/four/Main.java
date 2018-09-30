package four;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	while(true) {
    		System.out.println("请输入生成题目数-n");
    		Scanner inputn=new Scanner(System.in);
    		int n=inputn.nextInt();
    		System.out.println("请输入生成题目参数范围-r");
    		Scanner inputr=new Scanner(System.in);
    		int r=inputr.nextInt();
            Function makefile = new Function();
                makefile.createProblemSet(n,r);
        }
    }
}
