package top.lucian.dictionary.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lucian.dictionary.dao.VocabularyMapper;
import top.lucian.dictionary.entity.Vocabulary;

import java.util.List;

@Service
public class VocabularyService {
    @Autowired
    private VocabularyMapper vocabularyMapper;

    public List<Vocabulary> selectNoReviewWords(Integer bookid, Integer userid) {
        return vocabularyMapper.selectNoReviewWords(bookid, userid);
    }

    public List<Vocabulary> getNoReviewedWordByLimit(Integer bookid,Integer userid,Integer limit){
        return vocabularyMapper.getNoReviewedWordByLimit(bookid, userid, limit);
    }

    public List<Vocabulary> selectReviewWords(Integer bookid, Integer userid) {
        return vocabularyMapper.selectReviewWords(bookid, userid);
    }

    public boolean addVocabulary(Vocabulary vocabulary) {
        return vocabularyMapper.addVocabulary(vocabulary);
    }

    public Integer isExist(Integer userid, String name) {
        return vocabularyMapper.isExist(userid, name);
    }

    public boolean review(Integer vocid,Integer isReview,Integer type){
        return vocabularyMapper.review(vocid, isReview, type);
    }

    public boolean deleteAllWords(String[] words){
        return vocabularyMapper.deleteAllWords(words);
    }

    public boolean deleteWord(Integer vocid){
        return vocabularyMapper.deleteWord(vocid);
    }
}
