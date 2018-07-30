package ime.table;


import ime.bean.BaseWord;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

@Deprecated
public class MultiWordTable {

    public static final String defaultRegex = " ";


    public static List<BaseWord> loadWordBankList(InputStream inputStream) {
        return loadWordBankList(inputStream, defaultRegex);
    }

    public static List<BaseWord> loadWordBankList(InputStream inputStream, String regex) {
        List<BaseWord> list = new LinkedList<>();
        return list;
    }


}
