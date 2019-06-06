package tw.com.collection.basic.db;

/**
 * Created by Howard on 2016/3/22.
 */
public class SQL_UpdateLine {
    private static String TABLE_UPDATE_001 = "ALTER TABLE `User` ADD COLUMN `status` tinyint NOT NULL DEFAULT 0;";

    public static final int MAX_TABLE_LINE = 1;

    public static final String[] TABLE_UPDATE=new String[]{TABLE_UPDATE_001};

    public String[] LineString() {
        return TABLE_UPDATE;
    }
}
