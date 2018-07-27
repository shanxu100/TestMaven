package ime;


import ime.bean.BaseWord;
import ime.table.MultiWordTable;
import ime.trie.SimpleTrie;

import java.io.InputStream;
import java.util.List;
import java.util.Set;


public class SearchManager {
    private static SearchManager mInstance;
    private SimpleTrie<BaseWord> simpleTrie;

    private SearchManager() {
        simpleTrie = new SimpleTrie<>();
    }

    public static SearchManager getInstance() {
        if (mInstance == null) {
            synchronized (SearchManager.class) {
                if (mInstance == null) {
                    mInstance = new SearchManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 加载词库数据
     *
     * @param inputStreams
     * @return
     */
    public boolean loadWordBank(InputStream... inputStreams) {
        if (inputStreams != null && inputStreams.length > 0) {
            for (InputStream is : inputStreams) {
                long t1 = System.currentTimeMillis();
                List<BaseWord> list = MultiWordTable.loadWordBankList(is);
                long t2 = System.currentTimeMillis();
                for (BaseWord baseWord : list) {
                    simpleTrie.insert(baseWord);
                }
                long t3 = System.currentTimeMillis();
                System.out.println("加载词库用时：" + (t2 - t1) + "ms  构建字典树用时：" + (t3 - t2) + "ms");
//                break;
            }
            return true;
        }
        return false;
    }

    /**
     * 开始搜索，返回搜索结果
     *
     * @param searchKey
     * @return
     */
    public Set<BaseWord> search(String searchKey) {
        return simpleTrie.getObject(searchKey);
    }


}
