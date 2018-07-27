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

    /**
     * 将 不带拼音的 中文字符串 转换为数字字符串
     *
     * @param originalStr
     * @return
     */
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
                if (pinyins == null) {
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


    /**
     * 将 带拼音的 中文字符串 转换为数字字符串
     * <p>
     * "带拼音的 中文字符串" 格式为：'da'sha'te'sha 大杀特杀
     * 这种情况，不需要考虑多音字
     *
     * @param pinyin
     * @return
     */
    public static Set<String> covertHanziWithPinyinIntoNum(String pinyin) {
        if (StringUtils.isEmpty(pinyin)) {
            return new HashSet<>();
        }

        try {
            String[] sentencePinyin = pinyin.split("\'");
            List<String> list = new ArrayList<>(sentencePinyin.length);

            for (String s : sentencePinyin) {
                list.add(convertPinyinIntoNum(s));
            }
            return combination(list);


        } catch (Exception e) {
            e.printStackTrace();
            return combination(null);
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
        if (list.size() == 0) {
            return;
        }
        if (startPosition >= list.size()) {

            //将 全拼 加入set中
            resultSet.add(prefix.replaceAll(" ", ""));

            //将 首字母 加入set中
            String[] ss = prefix.split(" ");
            StringBuilder sb = new StringBuilder(ss.length);
            for (String s : ss) {
                if (s.length() == 0) {
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

    /**
     * 按照 全拼和首字母 两种情况，组合成对应的 字符串
     * TODO 后期应该考虑 全拼和首字母 混合的情况
     *
     * @param list
     */
    private static Set<String> combination(List<String> list) {
        if (list == null || list.size() == 0) {
            return new HashSet<>();
        }
        Set<String> resultSet = new HashSet<>(list.size());
        //存储 全拼
        StringBuilder sb1 = new StringBuilder();
        //存储 首字母
        StringBuilder sb2 = new StringBuilder();

        for (String s : list) {
            if (StringUtils.isEmpty(s)) {
                continue;
            }
            sb1.append(s);
            sb2.append(s.charAt(0));
        }
        resultSet.add(sb1.toString());
        resultSet.add(sb2.toString());

        return resultSet;

    }

    public static void main(String[] args) {

        covertHanziWithPinyinIntoNum("'da'mo'quan'wang");

    }

}
