package ime.bean;

import ime.trie.SimpleTrie;
import ime.utils.ConvertUtil;

import java.util.Objects;
import java.util.Set;

public class BaseWord implements SimpleTrie.TrieNodeable ,Comparable<BaseWord> {
    public String originalStr;
    public int length;
    public int DF = 1000;

    public Set<String> numSet;

    public BaseWord(String originalStr) {
        this.originalStr = originalStr;
        this.length = originalStr.length();
        init();
    }

    public BaseWord(String originalStr, int DF) {
        this.originalStr = originalStr;
        this.length = originalStr.length();
        this.DF = DF;
        init();
    }

    private void init() {
        numSet = ConvertUtil.covertHanziIntoNum(this.originalStr);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaseWord)) {
            return false;
        }
        BaseWord baseWord = (BaseWord) o;
        return Objects.equals(originalStr, baseWord.originalStr);
    }

    @Override
    public int hashCode() {

        return Objects.hash(originalStr);
    }

    @Override
    public Set<String> getString() {
        return numSet;
    }

    @Override
    public int compareTo(BaseWord o) {
        // 该元素保存在红黑树中
        //return -1; //-1表示放在红黑树的左边,即逆序输出
        //return 1;  //1表示放在红黑树的右边，即顺序输出
        //return o;  //表示元素相同，仅存放第一个元素

        return 0;
    }
}
