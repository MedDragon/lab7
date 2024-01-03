package sumdu.edu.ua.mavenproject2;

public class Content {
    int id;
    int stud_id;
    String title;
    String mark_let;
    int mark_num;
    
    public Content(){}
    
    public Content(int id, int stud_id, String title, String mark_let, int mark_num) {
        this.id = id;
        this.stud_id = stud_id;
        this.title = title;
        this.mark_let = mark_let;
        this.mark_num = mark_num;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMark_let() {
        return mark_let;
    }

    public int getMark_num() {
        return mark_num;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStud_id(int stud_id) {
        this.stud_id = stud_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMark_let(String mark_let) {
        this.mark_let = mark_let;
    }

    public void setMark_num(int mark_num) {
        this.mark_num = mark_num;
    }

    public int getStud_id() {
        return stud_id;
    }
    
    
}
