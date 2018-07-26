package ime.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConvertUtil {

    private static HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();

    static {
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    }

    public static Set<String> covertHanziIntoNum(String originalStr) {
        if (StringUtils.isEmpty(originalStr)) {
            return new HashSet<>();
        }

        try {
            char[] words = originalStr.toCharArray();
            List<Set<String>> list = new ArrayList<>(words.length);

            int initialCapacity = 1;
            for (char word : words) {
                String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(word, format);
                if (pinyins==null){
                    continue;
                }
                Set<String> numSet = convertPinyinIntoNum(pinyins);
                list.add(numSet);
                initialCapacity *= numSet.size();
            }
            initialCapacity = initialCapacity == 0 ? 16 : initialCapacity;
            Set<String> set = new HashSet<>(initialCapacity);

            generate(list, 0, "", set);
            return set;
        } catch (Exception e) {
            e.printStackTrace();
            return new HashSet<>();
        }
    }

    public static Set<String> convertPinyinIntoNum(String[] pinyins) {
        Set<String> set = new HashSet<>(pinyins.length);
        for (String pinyin : pinyins) {
            set.add(convertPinyinIntoNum(pinyin));
        }
        return set;
    }

    public static String convertPinyinIntoNum(String pinyin) {
        StringBuilder sb = new StringBuilder(pinyin.length());
        char[] pyChars = pinyin.toCharArray();
        for (char c : pyChars) {
            sb.append(convertLetterIntoNum(c));
        }
        return sb.toString();
    }


    //=====================================================================
    //
    //=====================================================================

    private static String convertLetterIntoNum(char c) {
        String result = "";
        if (c < 'a') {
            //大写字母变为小写字母
            c += 32;
        }
        if (c >= 'a' && c <= 'o') {
            result = ((c - 'a') / 3 + 2) + "";
        } else if (c >= 'p' && c <= 's') {
            result = "7";
        } else if (c >= 't' && c <= 'v') {
            result = "8";
        } else if (c >= 'w' && c <= 'z') {
            result = "9";
        }
        return result;
    }


    /**
     * 对 list 中的元素之间，进行全排列。结果存入set中
     *
     * @param list
     * @param startPosition
     * @param resultSet
     */
    private static void generate(List<Set<String>> list, int startPosition, String prefix, Set<String> resultSet) {
        if (list.size()==0){
            return;
        }
        if (startPosition >= list.size()) {

            //将 全拼 加入set中
            resultSet.add(prefix.replaceAll(" ",""));

            //将 首字母 加入set中
            String[] ss=prefix.split(" ");
            StringBuilder sb=new StringBuilder(ss.length);
            for(String s:ss){
                if (s.length()==0){
                    System.out.println();
                }
                sb.append(s.charAt(0));
            }
            resultSet.add(sb.toString());
            return;
        }
        Set<String> item = list.get(startPosition);

        for (String s : item) {
            generate(list, startPosition + 1, prefix + s + " ", resultSet);
        }


    }

    public static void main(String[] args){

        covertHanziIntoNum("行");

    }

}
