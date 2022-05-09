package top.lucian.dictionary.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.lucian.dictionary.entity.Vocabulary;

import java.util.List;

@Mapper
public interface VocabularyMapper {

    /**
     * 查询未背诵过的单词
     * @param bookid
     * @param userid
     * @return
     */
    List<Vocabulary> selectNoReviewWords(Integer bookid,Integer userid);

    /**
     * 查询已经背诵过的单词
     * @param bookid
     * @param userid
     * @return
     */
    List<Vocabulary> selectReviewWords(Integer bookid,Integer userid);

    /**
     * 分页查询
     * @param bookid
     * @param userid
     * @param limit
     * @return
     */
    List<Vocabulary> getNoReviewedWordByLimit(Integer bookid,Integer userid,Integer limit);

    /**
     * 添加单词到单词本中
     * @param vocabulary
     * @return
     */
    boolean addVocabulary(Vocabulary vocabulary);

    /**
     * 判断单词是否加入到单词本
     * @param userid
     * @param name
     * @return
     */
    Integer isExist(Integer userid,String name);

    /**
     * 复习单词
     * @param vocid
     * @param isReview
     * @param type
     * @return
     */
    boolean review(Integer vocid,Integer isReview,Integer type);

    /**
     * 批量删除
     * @param words
     * @return
     */
    boolean deleteAllWords(@Param("words") String[] words);

    boolean deleteWord(Integer vocid);
}
