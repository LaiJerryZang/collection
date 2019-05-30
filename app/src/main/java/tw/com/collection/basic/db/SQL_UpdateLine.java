package tw.com.collection.basic.db;

/**
 * Created by Howard on 2016/3/22.
 */
public class SQL_UpdateLine {
    private static String TABLE_UPDATE_001 = "ALTER TABLE `mail` ADD COLUMN `BroadcastMode` varchar(1) NOT NULL DEFAULT '';";
    private static String TABLE_UPDATE_002 = "ALTER TABLE `JobOnOff` ADD COLUMN `EmployeeFormationID` integer NOT NULL DEFAULT 0;";
    private static String TABLE_UPDATE_003 = "CREATE TABLE `EmployeeFormation` (" +
            "  `id` integer NOT NULL PRIMARY KEY," +
            "  `position` varchar(20) NOT NULL," +
            "  `JobOnTime` varchar(10) NOT NULL DEFAULT ''," +
            "  `JobOffTime` varchar(10) NOT NULL DEFAULT ''," +
            "  `DelMark` tinyint NOT NULL DEFAULT 0," +
            "  `SaveDate` datetime DEFAULT NULL" +
            ");";
    private static String TABLE_UPDATE_004 = "ALTER TABLE `mail` ADD COLUMN `OnKey` integer NOT NULL DEFAULT 0;";
    private static String TABLE_UPDATE_005 = "ALTER TABLE `system_path` ADD COLUMN `MailWarning` integer NOT NULL DEFAULT 3;";
    private static String TABLE_UPDATE_006 = "ALTER TABLE `system_path` ADD COLUMN `GuestWarning` integer NOT NULL DEFAULT 3;";
    private static String TABLE_UPDATE_007 = "ALTER TABLE `house_data` ADD COLUMN `buy_point2` integer NOT NULL DEFAULT 0;";

    private static String TABLE_UPDATE_008 = "CREATE TABLE `PublicPlace` (" + //-----2016/4/22
            "  `PublicPlaceNum` varchar(20) NOT NULL PRIMARY KEY," +
            "  `PublicPlaceName` varchar(50) NOT NULL DEFAULT ''," +
            "  `UsePoint` integer NOT NULL DEFAULT 0," +
            "  `People` integer NOT NULL DEFAULT 0," +
            "  `StartTime` varchar(10) NOT NULL DEFAULT ''," +
            "  `EndTime` varchar(10) NOT NULL DEFAULT ''," +
            "  `StartClosed` varchar(20) NOT NULL DEFAULT ''," +
            "  `EndClosed` varchar(20) NOT NULL DEFAULT ''," +
            "  `SynchronizeDate` datetime," +
            "  `Worker` varchar(20) NOT NULL DEFAULT ''," +
            "  `SaveDate` datetime DEFAULT NULL," +
            "  `Disabled` tinyint NOT NULL DEFAULT 0," +
            "  `DelMark` tinyint NOT NULL DEFAULT 0 " +
            ");";
    private static String TABLE_UPDATE_009 = "CREATE TABLE `PublicApply` (" +
            "  `PublicApplyID` integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "  `Card_No` varchar(50) NOT NULL DEFAULT ''," +
            "  `Status` tinyint NOT NULL DEFAULT 0," +
            "  `PublicPlace` varchar(20) NOT NULL," +
            "  `ApplyDate` datetime NOT NULL," +
            "  `ForecastReturnDate` datetime NOT NULL," +
            "  `ReturnDate` datetime," +
            "  `Description` varchar(1024) NOT NULL DEFAULT ''," +
            "  `Deposit` integer NOT NULL DEFAULT 0," +
            "  `ManQty` integer NOT NULL DEFAULT 0," +
            "  `DeductPoint` integer NOT NULL DEFAULT 0," +
            "  `OriginalPoint` integer NOT NULL DEFAULT 0," +
            "  `SurplusPoint` integer NOT NULL DEFAULT 0," +
            "  `OriginalStarthandsel_point` integer NOT NULL DEFAULT 0," +
            "  `OriginalHandsel_point` integer NOT NULL DEFAULT 0," +
            "  `OriginalBuy_point` integer NOT NULL DEFAULT 0," +
            "  `DelStarthandsel_point` integer NOT NULL DEFAULT 0," +
            "  `DelHandsel_point` integer NOT NULL DEFAULT 0," +
            "  `DelBuy_point` integer NOT NULL DEFAULT 0," +
            "  `save_dir` varchar(500) NOT NULL DEFAULT ''," +
            "  `SynchronizeDate` datetime," +
            "  `Worker` varchar(20) NOT NULL DEFAULT ''," +
            "  `SaveDate` datetime DEFAULT NULL," +
            "  `Disabled` tinyint NOT NULL DEFAULT 0," +
            "  `DelMark` tinyint NOT NULL DEFAULT 0 " +
            ");";
    private static String TABLE_UPDATE_010 = "CREATE TABLE `PointAlert` (" +
            "  `PointAlertID` integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "  `Card_No` varchar(50) NOT NULL DEFAULT ''," +
            "  `building_sn` varchar(20) NOT NULL," +
            "  `floor_sn` varchar(20) NOT NULL," +
            "  `section_sn` varchar(20) NOT NULL," +
            "  `Status` tinyint NOT NULL DEFAULT 0," +
            "  `AlertPoint` integer NOT NULL DEFAULT 0," +
            "  `PublicPlaceName` varchar(50) NOT NULL DEFAULT ''," +
            "  `OriginalStarthandsel_point` integer NOT NULL DEFAULT 0," +
            "  `OriginalHandsel_point` integer NOT NULL DEFAULT 0," +
            "  `OriginalBuy_point` integer NOT NULL DEFAULT 0," +
            "  `DelStarthandsel_point` integer NOT NULL DEFAULT 0," +
            "  `DelHandsel_point` integer NOT NULL DEFAULT 0," +
            "  `DelBuy_point` integer NOT NULL DEFAULT 0," +
            "  `SynchronizeDate` datetime," +
            "  `Worker` varchar(20) NOT NULL DEFAULT ''," +
            "  `SaveDate` datetime DEFAULT NULL," +
            "  `Disabled` tinyint NOT NULL DEFAULT 0," +
            "  `DelMark` tinyint NOT NULL DEFAULT 0 " +
            ");";
    private static String TABLE_UPDATE_011 = "ALTER TABLE `house_data` ADD COLUMN `ManagementFeePoint` integer NOT NULL DEFAULT 0;";
    private static String TABLE_UPDATE_012 = "ALTER TABLE `PublicApply` ADD COLUMN `ApplyHours` integer NOT NULL DEFAULT 0;";
    private static String TABLE_UPDATE_013 = "ALTER TABLE `PublicApply` ADD COLUMN PublicApplyID bigint";
    private static String TABLE_UPDATE_014 = "ALTER TABLE `PointAlert` ADD COLUMN PointAlertID bigint";
    private static String TABLE_UPDATE_015 = "ALTER TABLE `PublicApply` ADD COLUMN Card_No varchar(20)";
    private static String TABLE_UPDATE_016 = "ALTER TABLE `PointAlert` ADD COLUMN Card_No varchar(20)";
    private static String TABLE_UPDATE_017 = "ALTER TABLE `PublicApply` ADD COLUMN ConfromWorker varchar(20)  NOT NULL DEFAULT ''";
    private static String TABLE_UPDATE_018 = "ALTER TABLE `PublicApply` ADD COLUMN CloseWorker varchar(20)  NOT NULL DEFAULT ''";
    private static String TABLE_UPDATE_019 = "ALTER TABLE `house_data` ADD COLUMN `buy_point2` integer NOT NULL DEFAULT 0;";
    private static String TABLE_UPDATE_020 = "ALTER TABLE `PointAlert` ADD COLUMN `DelBuy_point2` integer NOT NULL DEFAULT 0;";
    private static String TABLE_UPDATE_021 = "ALTER TABLE `PointAlert` ADD COLUMN `OriginalBuy_point2` integer NOT NULL DEFAULT 0;";
    private static String TABLE_UPDATE_022 = "ALTER TABLE `PublicApply` ADD COLUMN SignUser varchar(30)  NOT NULL DEFAULT ''";
    private static String TABLE_UPDATE_023 = "ALTER TABLE `PublicApply` ADD COLUMN SignFileName varchar(20)  NOT NULL DEFAULT ''";
    private static String TABLE_UPDATE_024 = "UPDATE mail_order SET address='' WHERE sn IN (" +
            "SELECT sn FROM (" +
            "SELECT O.*, M.mail_order FROM mail_order O LEFT JOIN mail M ON O.sn=M.mail_order AND M.status=0 WHERE NOT O.address=''" +
            ") A " +
            "WHERE mail_order IS NULL)";
    private static String TABLE_UPDATE_025 ="ALTER TABLE `system_path` ADD COLUMN `HandselPointZero` tinyint NOT NULL DEFAULT 1;";
    private static String TABLE_UPDATE_026 = "ALTER TABLE `house_data` ADD COLUMN `SynchronizeDate` datetime;";
    private static String TABLE_UPDATE_027 = "ALTER TABLE `PublicApply` ADD COLUMN `PublicPlaceName` varchar(50) NOT NULL DEFAULT'';";
    private static String TABLE_UPDATE_028 = "ALTER TABLE `resident` ADD COLUMN `SynchronizeDate` datetime;";
    private static String TABLE_UPDATE_029 = "UPDATE `resident` SET `SynchronizeDate` = datetime('now', 'localtime');";
    private static String TABLE_UPDATE_030 ="ALTER TABLE `system_path` ADD COLUMN `NoCardOpen` varchar(1) NOT NULL DEFAULT'0'";
    private static String TABLE_UPDATE_031 ="ALTER TABLE `system_path` ADD COLUMN `PadFunction` varchar(3) NOT NULL DEFAULT''";
    private static String TABLE_UPDATE_032 ="DELETE FROM `resident` WHERE `card_no` = 'null' or `card_no` = ''";
    private static String TABLE_UPDATE_033 ="UPDATE `system_path`  SET HandselPointZero = 0";
    private static String TABLE_UPDATE_034 = "ALTER TABLE `mail` ADD COLUMN `save_dir` varchar(500) NOT NULL DEFAULT ''";
    private static String TABLE_UPDATE_035 = "ALTER TABLE `PublicPlace` ADD COLUMN `familyONOFF` integer NOT NULL DEFAULT 0";
    private static String TABLE_UPDATE_036 =  "CREATE TABLE `Marquee_data` (" +
            "  `id` integer NOT NULL PRIMARY KEY," +
            "  `MarqueeTxt` varchar(30) NOT NULL," +
            "  `EndDate` datetime DEFAULT NULL," +
            "   `SaveDate` datetime DEFAULT NULL" +
            ");";
    private static String TABLE_UPDATE_037 = "ALTER TABLE `JobOnOff` ADD `save_dir` VARCHAR(500) NOT NULL DEFAULT ''";
    private static String TABLE_UPDATE_038 = "ALTER TABLE `PublicPlace` ADD COLUMN `Use_Hours` integer NOT NULL DEFAULT 1";
    private static String TABLE_UPDATE_039 = "ALTER TABLE `PublicPlace` ADD COLUMN `AppBooking` integer NOT NULL DEFAULT 0";
    private static String TABLE_UPDATE_040 = "ALTER TABLE `house_data` ADD COLUMN `ispoint` integer NOT NULL DEFAULT 0";
    private static String TABLE_UPDATE_041 = "ALTER TABLE `system_path` ADD COLUMN `PointWay` integer NOT NULL DEFAULT 1";
    private static String TABLE_UPDATE_042 = "ALTER TABLE `house_data` ADD COLUMN `mTimestamp` integer NOT NULL DEFAULT 0";

    public static final int iMaxTableLine = 42;

    public static final String[] TABLE_UPDATE=new String[] {TABLE_UPDATE_001, TABLE_UPDATE_002, TABLE_UPDATE_003, TABLE_UPDATE_004, TABLE_UPDATE_005,
            TABLE_UPDATE_006, TABLE_UPDATE_007, TABLE_UPDATE_008, TABLE_UPDATE_009, TABLE_UPDATE_010,
            TABLE_UPDATE_011, TABLE_UPDATE_012, TABLE_UPDATE_013, TABLE_UPDATE_014, TABLE_UPDATE_015,
            TABLE_UPDATE_016, TABLE_UPDATE_017, TABLE_UPDATE_018, TABLE_UPDATE_019, TABLE_UPDATE_020,
            TABLE_UPDATE_021, TABLE_UPDATE_022, TABLE_UPDATE_023, TABLE_UPDATE_024, TABLE_UPDATE_025,
            TABLE_UPDATE_026, TABLE_UPDATE_027, TABLE_UPDATE_028, TABLE_UPDATE_029, TABLE_UPDATE_030,
            TABLE_UPDATE_031, TABLE_UPDATE_032, TABLE_UPDATE_033, TABLE_UPDATE_034, TABLE_UPDATE_035,
            TABLE_UPDATE_036, TABLE_UPDATE_037, TABLE_UPDATE_038, TABLE_UPDATE_039, TABLE_UPDATE_040,
            TABLE_UPDATE_041, TABLE_UPDATE_042};

    public String[] LineString() {
        return TABLE_UPDATE;
    }

    public int MaxLine() {
        return iMaxTableLine;
    }
}
