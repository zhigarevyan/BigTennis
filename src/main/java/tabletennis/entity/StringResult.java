package tabletennis.entity;

public class StringResult {

    private String score;

    private String set1 = "null";
    private String set2 = "null";
    private String set3 = "null";
    private String set4 = "null";
    private String set5 = "null";
    private String set6 = "null";
    private String set7 = "null";

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getSet1() {
        return set1;
    }

    public void setSet1(String set1) {
        this.set1 = set1;
    }

    public String getSet2() {
        return set2;
    }

    public void setSet2(String set2) {
        this.set2 = set2;
    }

    public String getSet3() {
        return set3;
    }

    public void setSet3(String set3) {
        this.set3 = set3;
    }

    public String getSet4() {
        return set4;
    }

    public void setSet4(String set4) {
        this.set4 = set4;
    }

    public String getSet5() {
        return set5;
    }

    public void setSet5(String set5) {
        this.set5 = set5;
    }

    public String getSet6() {
        return set6;
    }

    public void setSet6(String set6) {
        this.set6 = set6;
    }

    public String getSet7() {
        return set7;
    }

    public void setSet7(String set7) {
        this.set7 = set7;
    }

    public void setSet(int setNum, String result) {
        switch (setNum){
            case 1: this.set1 = result; break;
            case 2: this.set2 = result; break;
            case 3: this.set3 = result; break;
            case 4: this.set4 = result; break;
            case 5: this.set5 = result; break;
            case 6: this.set6 = result; break;
            case 7: this.set7 = result; break;
        }
    }

    @Override
    public String toString() {
        return "StringResult{" +
                "score='" + score + '\'' +
                ", set1='" + set1 + '\'' +
                ", set2='" + set2 + '\'' +
                ", set3='" + set3 + '\'' +
                ", set4='" + set4 + '\'' +
                ", set5='" + set5 + '\'' +
                ", set6='" + set6 + '\'' +
                ", set7='" + set7 + '\'' +
                '}';
    }
}
