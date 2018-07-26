package ime.table;


import ime.bean.BaseWord;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class MultiWordTable {

    public static final String WORD_BANK = "THUOCL_it.txt";
    public static final String defaultRegex = " ";


    public static List<BaseWord> loadWordBankList(File file) {
        return loadWordBankList(file, defaultRegex);
    }

    public static List<BaseWord> loadWordBankList(File file, String regex) {
        List<BaseWord> list = new LinkedList<>();
        try {
            //获取assets资源管理器

            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            while ((line = bf.readLine()) != null) {
                //TODO 这里读取一行
                String[] ss = line.split(regex);
                BaseWord baseWord = new BaseWord(ss[0]);
                list.add(baseWord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
