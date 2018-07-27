package ime.table;




import ime.bean.BaseWord;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class MultiWordTable {

    public static final String defaultRegex = " ";


    public static List<BaseWord> loadWordBankList(InputStream inputStream) {
        return loadWordBankList(inputStream, defaultRegex);
    }

    public static List<BaseWord> loadWordBankList(InputStream inputStream, String regex) {
        List<BaseWord> list = new LinkedList<>();
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bf.readLine()) != null) {
                //TODO 这里读取一行
                BaseWord baseWord = new BaseWord(line);
                list.add(baseWord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


}
