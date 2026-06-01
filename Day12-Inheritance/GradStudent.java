public class GradStudent extends Student {

    private String thesisTitle;

    public GradStudent(String name,int age,int studentId,double gpa,String thesisTitle) {
        super(name, age, studentId, gpa);
        this.thesisTitle = thesisTitle;
    }

    public String getThesisTitle() { return thesisTitle; }

    public void setThesisTitle(String thesisTitle) {
        this.thesisTitle = thesisTitle;
    }

    @Override
    public String toString() {
        return "GradStudent{name='" + getName() + "', age=" + getAge() +
                ", studentId=" + getStudentId() +
                ", gpa=" + getGpa() +
                ", thesisTitle='" + thesisTitle + "'}";
    }
}