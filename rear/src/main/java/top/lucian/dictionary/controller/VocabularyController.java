package top.lucian.dictionary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.lucian.dictionary.entity.Vocabulary;
import top.lucian.dictionary.service.VocabularyService;
import top.lucian.dictionary.util.Result;

import java.util.List;

@RestController
@RequestMapping("/vocabulary")
@CrossOrigin
public class VocabularyController {

    @Autowired
    private VocabularyService vocabularyService;

    @ResponseBody
    @RequestMapping("/add")
    public Result AddVocabulary(Vocabulary vocabulary) {
        boolean b = vocabularyService.addVocabulary(vocabulary);
        if (b) {
            return new Result(200, "添加成功");
        } else {
            return new Result(405, "添加失败");
        }
    }

    @ResponseBody
    @RequestMapping("/queryIsExist")
    public int queryIsExist(Integer userid, String name) {
        Integer exist = vocabularyService.isExist(userid, name);
        if (exist != null) {
            return exist;
        } else {
            return 0;
        }
    }

    @ResponseBody
    @RequestMapping("/getNoReviewedWord")
    public Result getNoReviewedWord(Integer bookid, Integer userid) {
        List<Vocabulary> vocabularies = vocabularyService.selectNoReviewWords(bookid, userid);
        return new Result(200, "success", vocabularies);
    }

    @ResponseBody
    @RequestMapping("/getReviewedWord")
    public Result getReviewedWord(Integer bookid, Integer userid) {
        List<Vocabulary> vocabularies = vocabularyService.selectReviewWords(bookid, userid);
        return new Result(200, "success", vocabularies);
    }

    @ResponseBody
    @RequestMapping("/getNoReviewedWordByLimit")
    public Result getNoReviewedWordByLimit(Integer bookid, Integer userid, Integer limit) {
        System.out.println(limit);
        List<Vocabulary> vocabularies = vocabularyService.getNoReviewedWordByLimit(bookid, userid, limit);
        return new Result(200, "success", vocabularies);
    }

    @ResponseBody
    @RequestMapping("/review")
    public Result review(Integer vocid, Integer isReview, Integer type) {
        boolean review = vocabularyService.review(vocid, isReview, type);
        if (review) {
            return new Result(200, "success");
        } else {
            return new Result(200, "error");
        }
    }

    @ResponseBody
    @RequestMapping("/deleteAllWords")
    public Result deleteAllWords(String[] itemArr) {
        boolean b = vocabularyService.deleteAllWords(itemArr);
        if (b){
            return new Result(200,"success");
        }else{
            return new Result(500,"error");
        }
    }

    @ResponseBody
    @RequestMapping("/deleteWord")
    public Result deleteWord(Integer vocid) {
        System.out.println(vocid);
        boolean b = vocabularyService.deleteWord(vocid);
        if (b){
            return new Result(200,"success");
        }else{
            return new Result(500,"error");
        }
    }


}
