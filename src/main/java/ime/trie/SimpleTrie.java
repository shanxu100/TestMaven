package ime.trie;

import ime.table.SingleWordTable;
import ime.bean.BaseWord;
import ime.table.MultiWordTable;
import ime.table.SingleWordTable;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 数字——字典树
 *
 * @author gzs11641
 */
public class SimpleTrie<T extends SimpleTrie.TrieNodeable> {
    /**
     *
     */
    private static final int SIZE = 10;

    private static final char START_CHAR = '0';
    /**
     * 字典树的根
     */
    private TrieNode root;

    /**
     * 初始化字典树
     */
    public SimpleTrie() {
        root = new TrieNode<T>();
    }

    /**
     * 字典树节点
     */
    private static class TrieNode<S> {
        /**
         * 有多少单词通过这个节点,即由根至该节点组成的字符串模式出现的次数
         */
        private int num;
        /**
         * 所有的子节点
         */
        private TrieNode[] son;
        /**
         * 节点的值 [ 2 - 9 ]
         */
        private char val;

        /**
         * 当前节点挂载的 对象 集合
         */
        private Set<S> mountSet = new HashSet<>();

        public TrieNode() {
            num = 1;
            son = new TrieNode[SIZE];
        }

        public void add(S object) {
            mountSet.add(object);
        }

        public boolean isEnd() {
            return !mountSet.isEmpty();
        }


    }

    //建立字典树

    /**
     * 在字典树中插入一个 对象
     *
     * @param object
     */
    public void insert(T object) {
        if (object == null) {
            return;
        }
        for (String str : object.getString()) {
            if (StringUtils.isEmpty(str)) {
                continue;
            }
            TrieNode node = root;
            node.num++;
            char[] letters = str.toCharArray();
            for (char c : letters) {
                int pos = c - START_CHAR;
                if (node.son[pos] == null) {
                    node.son[pos] = new TrieNode<T>();
                    node.son[pos].val = c;
                } else {
                    node.son[pos].num++;
                }
                node = node.son[pos];
            }
            node.add(object);
        }


    }

    /**
     * 计算单词前缀的数量
     */
    public int countPrefix(String prefix) {
        if (prefix == null) {
            return -1;
        }
        TrieNode node = root;
        char[] letters = prefix.toCharArray();
        for (char c : letters) {
            int pos = c - START_CHAR;
            if (node.son[pos] == null) {
                return 0;
            } else {
                node = node.son[pos];
            }
        }
        return node.num;
    }


    /**
     * 前序遍历此 node 的所有子节点
     * 将该节点和所有子节点上面挂载的对象放入 set 中
     *
     * @param node
     * @param resultSet
     */
    public void preTraverse(TrieNode node, Set<T> resultSet) {
        resultSet.addAll(node.mountSet);
        for (TrieNode child : node.son) {
            if (child != null && resultSet != null) {
                resultSet.addAll(child.mountSet);
                preTraverse(child, resultSet);
            }
        }
        return;
    }


    /**
     * TODO 使用 TreeSet ,使得set中的元素按照一定规则排序
     * 根据指定前缀，获取所有的符合的对象
     *
     * @param prefix
     * @return
     */
    public Set<T> getObject(String prefix) {
        Set<T> resultSet = new HashSet<>();
        TrieNode node = has(prefix);
        if (node != null) {
            preTraverse(node, resultSet);
        }
        return resultSet;
    }


    /**
     * 在字典树中查找一个完全匹配的单词
     */
    public TrieNode has(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        TrieNode node = root;
        char[] letters = str.toCharArray();
        for (int i = 0, len = str.length(); i < len; i++) {
            int pos = letters[i] - START_CHAR;
            if (node.son[pos] != null) {
                node = node.son[pos];
            } else {
                return null;
            }
        }
        return node;
    }

    /**
     * 前序遍历字典树
     */
    public void preTraverse(TrieNode node) {
        if (node != null) {
            System.out.print(node.val + "-");
            for (TrieNode child : node.son) {
                preTraverse(child);
            }
        }
    }

    public TrieNode getRoot() {
        return this.root;
    }

    /**
     *
     */
    public interface TrieNodeable {
        /**
         * @return
         */
        Set<String> getString();
    }


}
