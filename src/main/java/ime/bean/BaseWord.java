package ime.bean;



import ime.trie.SimpleTrie;
import ime.utils.ConvertUtil;

import java.util.Objects;
import java.util.Set;

public abstract class BaseWord implements SimpleTrie.TrieNodeable, Comparable<BaseWord> {
    public String originalStr;
    public String[] pinyin;
    public int length;
    public double DF = 1000;

    public Set<String> numSet;

    /**
     * 根据用户的环境，自行解析数据
     * @param bw
     */
    public abstract void parseBaseWord(BaseWord bw);

    public BaseWord() {
        parseBaseWord(this);
        init();
    }


    private void init() {
        numSet = ConvertUtil.covertHanziWithPinyinIntoNum(this.pinyin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseWord)) return false;
        BaseWord baseWord = (BaseWord) o;
        return Objects.equals(originalStr, baseWord.originalStr) &&
                Objects.equals(pinyin, baseWord.pinyin);
    }

    @Override
    public int hashCode() {

        return Objects.hash(originalStr, pinyin);
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
        if (this.DF > o.DF) {
            return -1;
        } else if (this.DF < o.DF) {
            return 1;
        } else {
            return 0;
        }
    }


}
