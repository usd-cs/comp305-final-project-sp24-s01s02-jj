package edu.sandiego.comp305.sp24.schoolSim.enums;

public enum DegreeType {
    BACHELOR(1),
    MASTERS(2),
    DOCTORAL(3);

    private final int id;

    DegreeType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static DegreeType fromId(int id) {
        for (DegreeType degreeType : DegreeType.values()) {
            if (degreeType.getId() == id) {
                return degreeType;
            }
        }
        throw new IllegalArgumentException("Invalid DegreeType id: " + id);
    }
}
