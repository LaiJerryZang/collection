package tw.com.collection.basic.db;

public class SQL_CreateLine {

    private String TABLE_CREATE_001 = "CREATE TABLE `User` (" +
            " `userId` INTEGER NOT NULL PRIMARY KEY," +
            " `userName` VARCHAR NOT NULL," +
            " `sex` smallint DEFAULT NULL," +
            " `beginDate` datetime NOT NULL," +
            " `loginTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP" +
            ");";

    private final String[] TABLE_CREATE=new String[]{TABLE_CREATE_001};

    public static final int MAX_TABLE_LINE = 1;

    public String[] LineString() {
        return TABLE_CREATE;
    }
}
