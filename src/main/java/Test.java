import ime.bean.BaseWord;
import ime.table.MultiWordTable;
import ime.trie.SimpleTrie;

import java.io.File;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Guan
 * @date Created on 2018/7/26
 */
public class Test {

    public static void main(String[] args) {

        File file = new File("src\\main\\resources\\file\\THUOCL_it.txt");
        File file2 = new File("src\\main\\resources\\file\\commonwords.txt");

        System.out.println(file.getAbsolutePath());

        SimpleTrie<BaseWord> simpleTrie = new SimpleTrie<>();
        for (BaseWord baseWord : MultiWordTable.loadWordBankList(file, " \t ")) {
            simpleTrie.insert(baseWord);
        }
        for (BaseWord baseWord : MultiWordTable.loadWordBankList(file2)) {
            simpleTrie.insert(baseWord);
        }
        System.out.println("trie.size="+simpleTrie.countPrefix(""));
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String input = in.nextLine();
            if (!"exit".equals(input)) {
                long start = System.currentTimeMillis();
                Set<BaseWord> set = simpleTrie.getObject(input);
                long runningTime = System.currentTimeMillis() - start;
                for (BaseWord word : set) {
                    System.out.print(word.originalStr + " ");
                }
                System.out.println("\n查找时间：" + runningTime+"ms");
            }
        }
//
//        simpleTrie.preTraverse(simpleTrie.getRoot());

    }

}
