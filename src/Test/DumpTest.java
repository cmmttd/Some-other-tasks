package Test;

import java.io.*;
import java.util.ArrayList;

public class DumpTest
{
    public static void main(final String args[]) throws Exception
    {
        ArrayList<String> al = new ArrayList<>(){{add("first"); add("sec"); add("thrd");}};
        ArrayList<String> al2 = new ArrayList<>(){{add("cube"); add("sphere"); add("pyramide");}};
        ArrayList<String> res = new ArrayList<>();
        System.out.println(al);

        int i = 0;
        al.forEach(v -> pr(v, i));
        System.out.println(res);
    }

    static void pr(String s, int nn){
        System.out.println(s);
        System.out.println(nn);
    }
}