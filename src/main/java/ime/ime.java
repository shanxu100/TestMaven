package ime;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class ime {
    public static final char[] PY_mb_a = "啊阿吖嗄腌锕呵安按爱暗埃".toCharArray();


    public static void main(String[] args) throws Exception {

        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);


        LinkedList<String> list = new LinkedList();
        long l = 10000000;
        try {
//            for (char ch:PY_mb_a){
//                System.out.println("============="+ch+"================");
//
//                String[] pinyinStr = PinyinHelper.toHanyuPinyinStringArray('好', format);
//
//                for (String s:pinyinStr){
//                    System.out.println(s);
//                }
//
//            }
            String s1="426";
            String s2=new String("426");
            Set<String> set=new HashSet<>();
            set.add(s1);
            set.add(s2);

            System.out.println(set.size());


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            System.out.println(list.size());
        }

    }
}
