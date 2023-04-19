package project.todolist.todolist.entity;

public enum Status {
    NA("ไม่ระบุ", "Not Applicable"),
    TODO("ยังไม่เริ่ม", "To Do"),
    IN_PROGRESS("กำลังทำ", "In Progress"),
    LATER("เลื่อนไปทำใหม่", "Later"),
    DONE("เสร็จแล้ว", "Done");
    String thaiName;
    String engName;

    Status(String thaiName, String engName) {
        this.thaiName = thaiName;
        this.engName = engName;
    }

    public String getThaiName() {
        return thaiName;
    }

    public String getEngName() {
        return engName;
    }
}
