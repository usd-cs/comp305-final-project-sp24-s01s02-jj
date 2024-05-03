package edu.sandiego.comp305.sp24.schoolSim.enums;

public enum Grade{
    FRESHMAN(1),
    SOPHOMORE(2),
    JUNIOR(3),
    SENIOR(4),
    GRADUATE(5),
    DOCTORAL(6);

    private final int id;

    Grade(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Grade fromId(int id) {
        for (Grade grade : Grade.values()) {
            if (grade.getId() == id) {
                return grade;
            }
        }
        throw new IllegalArgumentException("Invalid Grade id: " + id);
    }
}