import ime.bean.BaseWord;
import ime.table.MultiWordTable;
import ime.trie.SimpleTrie;
import ime.utils.ConvertUtil;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

import java.io.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Guan
 * @date Created on 2018/7/26
 */
public class Test {

    public static void main(String[] args) throws Exception {

        File file2 = new File("src\\main\\resources\\file\\splStr");

        File outputFile = new File("src\\main\\resources\\file\\splNum");

        BufferedReader bf = null;
        BufferedWriter out = null;
        try {
            //获取assets资源管理器

            //通过管理器打开文件并读取
            bf = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
            out = new BufferedWriter(new FileWriter(outputFile, true));

            String line;
            HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
            format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            Set<String> set = new LinkedHashSet<>();

            while ((line = bf.readLine()) != null) {
                String numStr = ConvertUtil.convertPinyinIntoNum(line);
                set.add(numStr);
            }
            for (String s : set) {
                out.write(s + "\n");
            }

//            System.out.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bf != null) {
                bf.close();
            }
            if (out != null) {
                out.write("\n}");
                out.flush();
                out.close();
            }

        }

    }

}
