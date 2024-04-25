package edu.sandiego.comp305.sp24.schoolSim;

public class Config {
    private Config() {}
    private String databaseName;
    private String databaseUsername;
    private String databasePassword;
    private String databaseHost;
    private void readFromFile(String fileName) {

    }
    public Config getInstance() {
        return null;
    }
    public String getDatabaseName() {
        return databaseName;
    }
    public String getDatabaseUsername() {
        return databaseUsername;
    }
    public String getDatabasePassword() {
        return databasePassword;
    }
    public String getDatabaseHost() {
        return databaseHost;
    }
}
