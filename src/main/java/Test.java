import ime.bean.BaseWord;
import ime.table.MultiWordTable;
import ime.trie.SimpleTrie;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Guan
 * @date Created on 2018/7/26
 */
public class Test {

    public static void main(String[] args) throws Exception {

        File file2 = new File("src\\main\\resources\\file\\commonwords.txt");

        File outputFile = new File("src\\main\\resources\\file\\output");

        try {
            //获取assets资源管理器

            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
            BufferedWriter out = new BufferedWriter(new FileWriter(outputFile, true));

            String line;
            HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
            format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            while ((line = bf.readLine()) != null) {
                String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(line.toCharArray()[0], format);
                Set<String> set = new HashSet<>();
                for (String s : pinyins) {
                    set.add(s);
                }
                for(String s:set){
                    out.write("'" + s + " " + line + "\n");
                }


            }
            out.write("\n}");
            out.flush();
            out.close();
//            System.out.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
