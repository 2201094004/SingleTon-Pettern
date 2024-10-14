package com.syaviraindahmaryam.sqlitesingleton;

public class StudentDataSingleton {
    private static StudentDataSingleton instance;
    private String fullName;
    private String studentID;
    private String programStudy;

    // Private constructor to prevent instantiation
    private StudentDataSingleton() {}

    // Static method to get the single instance
    public static synchronized StudentDataSingleton getInstance() {
        if (instance == null) {
            instance = new StudentDataSingleton();
        }
        return instance;
    }

    // Getters and setters for the data
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getProgramStudy() {
        return programStudy;
    }

    public void setProgramStudy(String programStudy) {
        this.programStudy = programStudy;
    }
}
