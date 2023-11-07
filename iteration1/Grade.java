package iteration1;

public class Grade {
    private String letterGrade;
    private double gradeOver4;

    private int gradeOver100;

    public Grade(String letterGrade) {
        this.letterGrade = letterGrade;
        setGradeOver4();
        setGradeOver100();
    }

    private void setGradeOver100() {
        switch (letterGrade) {
            case "AA":
                gradeOver100 = 100;
                break;
            case "BA":
                gradeOver100 = 90;
                break;
            case "BB":
                gradeOver100 = 85;
                break;
            case "CB":
                gradeOver100 = 75;
                break;
            case "CC":
                gradeOver100 = 70;
                break;
            case "DC":
                gradeOver100 = 65;
                break;
            case "DD":
                gradeOver100 = 60;
                break;
            case "FD":
                gradeOver100 = 50;
                break;
            case "FF":
                gradeOver100 = 0;
                break;
        }
    }

    private void setGradeOver4() {
        switch (letterGrade) {
            case "AA":
                gradeOver4 = 4.0;
                break;
            case "BA":
                gradeOver4 = 3.5;
                break;
            case "BB":
                gradeOver4 = 3.0;
                break;
            case "CB":
                gradeOver4 = 2.5;
                break;
            case "CC":
                gradeOver4 = 2.0;
                break;
            case "DC":
                gradeOver4 = 1.5;
                break;
            case "DD":
                gradeOver4 = 1.0;
                break;
            case "FD":
                gradeOver4 = 0.5;
                break;
            case "FF":
                gradeOver4 = 0.0;
                break;
        }
    }

    public String getLetterGrade() {
        return letterGrade;
    }


}
