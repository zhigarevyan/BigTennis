package entity.dbEntity;

import entity.StringResult;

import javax.persistence.*;


@NamedQueries({
        @NamedQuery(name = "Result.byParams",
                query = "From ResultEntity result " +
                        "WHERE score = :score " +
                        "and set1 = :set1 " +
                        "and set2 = :set2 " +
                        "and set3 = :set3 " +
                        "and set4 = :set4 " +
                        "and set5 = :set5 ")
})



@Entity
@Table(name = "Results", schema = "bigtennis", catalog = "")
public class ResultEntity {
    private int id;
    private String score;
    private String set1;
    private String set2;
    private String set3;
    private String set4;
    private String set5;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "score", nullable = false, length = 3)
    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Basic
    @Column(name = "set1", nullable = false, length = 5)
    public String getSet1() {
        return set1;
    }

    public void setSet1(String set1) {
        this.set1 = set1;
    }

    @Basic
    @Column(name = "set2", nullable = false, length = 5)
    public String getSet2() {
        return set2;
    }

    public void setSet2(String set2) {
        this.set2 = set2;
    }

    @Basic
    @Column(name = "set3", nullable = true, length = 5)
    public String getSet3() {
        return set3;
    }

    public void setSet3(String set3) {
        this.set3 = set3;
    }

    @Basic
    @Column(name = "set4", nullable = true, length = 5)
    public String getSet4() {
        return set4;
    }

    public void setSet4(String set4) {
        this.set4 = set4;
    }

    @Basic
    @Column(name = "set5", nullable = true, length = 5)
    public String getSet5() {
        return set5;
    }

    public void setSet5(String set5) {
        this.set5 = set5;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultEntity that = (ResultEntity) o;

        if (id != that.id) return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;
        if (set1 != null ? !set1.equals(that.set1) : that.set1 != null) return false;
        if (set2 != null ? !set2.equals(that.set2) : that.set2 != null) return false;
        if (set3 != null ? !set3.equals(that.set3) : that.set3 != null) return false;
        if (set4 != null ? !set4.equals(that.set4) : that.set4 != null) return false;
        if (set5 != null ? !set5.equals(that.set5) : that.set5 != null) return false;

        return true;
    }

    public String[] setsToArrString() {
        return new String[]{set1,set2,set3,set4,set5};
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (set1 != null ? set1.hashCode() : 0);
        result = 31 * result + (set2 != null ? set2.hashCode() : 0);
        result = 31 * result + (set3 != null ? set3.hashCode() : 0);
        result = 31 * result + (set4 != null ? set4.hashCode() : 0);
        result = 31 * result + (set5 != null ? set5.hashCode() : 0);
        return result;
    }

    public void setAll(StringResult params) {
        String score = params.getScore();

        String set1 = params.getSet1();
        String set2 = params.getSet2();
        String set3 = params.getSet3();
        String set4 = params.getSet4();
        String set5 = params.getSet5();


        setScore(score);
        setSet1(set1);
        setSet2(set2);
        setSet3(set3);
        setSet4(set4);
        setSet5(set5);

    }

}
