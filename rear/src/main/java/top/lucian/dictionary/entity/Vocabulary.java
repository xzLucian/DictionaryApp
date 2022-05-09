package top.lucian.dictionary.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vocabulary {
    private Integer vocabularyid;
    private Integer bookid;
    private Integer userid;
    private String name;
    private String english;
    private String american;
    private String apology;
    private Integer isReview;
    private Integer isAdd;
    private Integer state;
}
