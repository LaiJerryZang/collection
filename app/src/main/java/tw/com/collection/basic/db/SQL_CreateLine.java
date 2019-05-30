package tw.com.collection.basic.db;

/*
 * Created by Howard on 2015/12/23.
*/
public class SQL_CreateLine {
    private static String TABLE_CREATE_001 = "CREATE TABLE building_sn (" +
            " sort INTEGER NOT NULL," +
            " full_name VARCHAR(20) PRIMARY KEY DEFAULT ''" +
            ");";
    private static String TABLE_CREATE_002 = "CREATE TABLE `bulletin` (`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " `pic_name` VARCHAR(12) NOT NULL," +
            " `who_receive` VARCHAR(30) NOT NULL," +
            " `subject` VARCHAR(30) NOT NULL," +
            " `content` VARCHAR(100) NOT NULL," +
            " `start_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00'," +
            " `end_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00'," +
            " `time_in` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP" +
            ");";
    private static String TABLE_CREATE_003 = "CREATE TABLE `card_data` (" +
            " `card_no` varchar(20) NOT NULL PRIMARY KEY," +
            " `card_sn` varchar(20) NOT NULL" +
            ");";
    private static String TABLE_CREATE_004 = "CREATE TABLE `community_members` (" +
            " `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " `card_no` varchar(12) NOT NULL," +
            " `nick_name` varchar(20) NOT NULL" +
            ");";
    private static String TABLE_CREATE_005 = "CREATE TABLE `Employee` (" +
            "  `EmployeeID` INTEGER NOT NULL PRIMARY KEY," +
            "  `EmployeeNum` varchar(20) NOT NULL," +
            "  `Card_sn` varchar(20) DEFAULT NULL," +
            "  `EmployeeName` varchar(50) DEFAULT NULL," +
            "  `BuildDate` datetime DEFAULT NULL," +
            "  `BranchOfNum` varchar(1024) DEFAULT NULL," +
            "  `Postion` varchar(50) DEFAULT NULL," +
            "  `Sex` smallint DEFAULT NULL," +
            "  `BeginDate` datetime DEFAULT NULL," +
            "  `EndDate` datetime DEFAULT NULL," +
            "  `SaveDate` datetime DEFAULT NULL," +
            "  `Disabled` smallint DEFAULT NULL," +
            "  `DelMark` smallint DEFAULT NULL," +
            "  `ONOFF` smallint DEFAULT NULL " +
            ");";
    private static String TABLE_CREATE_006 = "";
    private static String TABLE_CREATE_007 = "";
        /*
    private static String TABLE_CREATE_006 = "CREATE TABLE `facility` (" +
            "  `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "  `card_no` varchar(12) NOT NULL," +
            "  `pic_name` varchar(12) NOT NULL," +
            "  `status` varchar(1) NOT NULL," +
            "  `time_in` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP," +
            "  `time_out` datetime NOT NULL DEFAULT '0000-00-00 00:00:00'," +
            "  `category` varchar(20) NOT NULL," +
            "  `item` varchar(50) NOT NULL," +
            "  `save_dir` varchar(500) NOT NULL," +
            "  `cash` INTEGER NOT NULL," +
            "  `content` varchar(100) NOT NULL," +
            "  `ReserveHour` INTEGER DEFAULT 0" +
            ");";
    private static String TABLE_CREATE_007 = "CREATE TABLE `facility_list` (" +
            "  `id` integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "  `catelog` integer NOT NULL," +
            "  `name` varchar(10) NOT NULL," +
            "  `point` integer NOT NULL DEFAULT 0," +
            "  `ImageNo` smallint NOT NULL DEFAULT 0," +
            "  `NotDouble` smallint DEFAULT 0" +
            ");";
        */
    private static String TABLE_CREATE_008 = "CREATE TABLE `fail_send` (" +
            "  `id` integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "  `msg` varchar(300) NOT NULL," +
            "  `type` varchar(20) NOT NULL," +
            "  `DelMark` smallint NOT NULL DEFAULT 0," +
            "  `SaveDate` datetime DEFAULT CURRENT_TIMESTAMP," +
            "  `SendDate` datetime DEFAULT NULL " +
            ");";
    private static String TABLE_CREATE_009 = "CREATE TABLE `floor_sn` (" +
            " sort INTEGER NOT NULL," +
            " full_name VARCHAR(20) PRIMARY KEY DEFAULT ''" +
            ");";
    private static String TABLE_CREATE_010 = "CREATE TABLE `guest` (" +
            "  `id` integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "  `card_no` varchar(12) NOT NULL," +
            "  `pic_name` varchar(12) NOT NULL," +
            "  `subject` varchar(20) NOT NULL," +
            "  `visit_home` varchar(12) NOT NULL," +
            "  `guest_name` varchar(12) NOT NULL," +
            "  `status` varchar(1) NOT NULL," +
            "  `card` varchar(10) NOT NULL," +
            "  `tel` varchar(15) NOT NULL," +
            "  `car_set` varchar(10) NOT NULL," +
            "  `people` integer NOT NULL," +
            "  `time_in` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP," +
            "  `time_out` datetime NOT NULL DEFAULT '0000-00-00 00:00:00'," +
            "  `buckle` varchar(10) NOT NULL," +
            "  `save_dir` varchar(500) NOT NULL," +
            "  `car_set2` varchar(10) DEFAULT NULL," +
            "  `car_set3` varchar(10) DEFAULT NULL," +
            "  `car_set4` varchar(10) DEFAULT NULL," +
            "  `car_set5` varchar(10) DEFAULT NULL," +
            "  `carDescription` varchar(100) DEFAULT NULL," +
            "  `BuckleDescription` varchar(100) DEFAULT NULL," +
            "  `SignFileName` varchar(20) DEFAULT NULL," +
            "  `CertificateNo` varchar(10) DEFAULT NULL," +
            "  `UpdateOK` varchar(1) DEFAULT NULL," +
            "  `OrderIndex` integer NOT NULL DEFAULT 0" +
            ");";
    private static String TABLE_CREATE_011 = "CREATE TABLE `handover` (" +
            "  `id` integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "  `card_no` varchar(12) NOT NULL," +
            "  `pic_name` varchar(12) NOT NULL," +
            "  `status` varchar(1) NOT NULL," +
            "  `time_in` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP," +
            "  `time_out` datetime NOT NULL DEFAULT '0000-00-00 00:00:00'," +
            "  `time_hint` datetime NOT NULL DEFAULT '0000-00-00 00:00:00'," +
            "  `subject` varchar(20) NOT NULL," +
            "  `save_dir` varchar(500) NOT NULL," +
            "  `cash` integer NOT NULL," +
            "  `content` varchar(100) NOT NULL," +
            "  `SignFileName` varchar(20) DEFAULT NULL," +
            "  `collar_name` varchar(12) DEFAULT NULL," +
            "  `UpdateOK` varchar(1) DEFAULT NULL," +
            "  `OrderIndex` integer NOT NULL DEFAULT 0" +
            ");";
    private static String TABLE_CREATE_012 = "CREATE TABLE `handover_list` (" +
            "  `id` integer NOT NULL PRIMARY KEY, " +
            "  `list` varchar(20) NOT NULL" +
            ");";
    private static String TABLE_CREATE_013 = "CREATE TABLE `house_data` (" +
            "  `id` integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "  `building_sn` varchar(20) NOT NULL," +
            "  `floor_sn` varchar(20) NOT NULL," +
            "  `section_sn` varchar(20) NOT NULL," +
            "  `size` integer NOT NULL DEFAULT 0," +
            "  `money` integer NOT NULL DEFAULT 0," +
            "  `full_name` varchar(50) NOT NULL," +
            "  `point` integer NOT NULL DEFAULT 0," +
            "  `handsel_point` integer NOT NULL DEFAULT 0," +
            "  `buy_point` integer NOT NULL DEFAULT 0," +
            "  `buy_point2` integer NOT NULL DEFAULT 0," +
            "  `starthandsel_point` integer NOT NULL DEFAULT 0," +
            "  `DefPoint` integer NOT NULL DEFAULT 0," +
            "  `ManagementFeePoint` integer NOT NULL DEFAULT 0," +
            "  `send` integer NOT NULL DEFAULT 0," +
            "  `SynchronizeDate` datetime," +
            "  `ispoint` integer NOT NULL DEFAULT 0, " +
            "  `mTimestamp` integer NOT NULL DEFAULT 0 " +
            ");";
    private static String TABLE_CREATE_014 = "CREATE TABLE `JobOnOff` (" +
            "  `id` integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "  `EmployeeID` integer NOT NULL," +
            "  `EmployeeFormationID` integer NOT NULL DEFAULT 0," +
            "  `punchmode` smallint NOT NULL," +
            "  `punchdate` datetime NOT NULL," +
            "  `UpdateDate` datetime DEFAULT NULL , " +
            "  `save_dir` VARCHAR(500) NOT NULL DEFAULT ''"+
            ");";
    private static String TABLE_CREATE_015 = "CREATE TABLE `Language` (" +
            "  `Lid` integer NOT NULL PRIMARY KEY," +
            "  `Bsn` varchar(12) NOT NULL," +
            "  `Fsn` varchar(12) NOT NULL," +
            "  `Ssn` varchar(12) NOT NULL," +
            "  `Luser` varchar(12) NOT NULL," +
            "  `LSsn` varchar(12) NOT NULL" +
            ");";
    private static String TABLE_CREATE_016 = "CREATE TABLE `mail` (" +
            "  `id` integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "  `card_no` varchar(12) NOT NULL," +
            "  `mail_type` varchar(20) NOT NULL," +
            "  `logistic` varchar(20) NOT NULL," +
            "  `barcode` varchar(40) NOT NULL," +
            "  `quantity1` integer NOT NULL DEFAULT 0," +
            "  `quantity2` integer NOT NULL DEFAULT 0," +
            "  `quantity3` integer NOT NULL DEFAULT 0," +
            "  `quantity4` integer NOT NULL DEFAULT 0," +
            "  `read_flag` varchar(1) NOT NULL," +
            "  `status` varchar(1) NOT NULL," +
            "  `taker_name` varchar(12) NOT NULL," +
            "  `pic_name` varchar(12) NOT NULL," +
            "  `time_in` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP," +
            "  `time_out` datetime NOT NULL DEFAULT '0000-00-00 00:00:00'," +
            "  `mail_order` integer NOT NULL," +
            "  `SignMode` integer DEFAULT NULL," +
            "  `SignUser` varchar(30) DEFAULT NULL," +
            "  `SignFileName` varchar(20) DEFAULT NULL," +
            "  `UpdateOK` varchar(1) DEFAULT NULL," +
            "  `DescriptionS` varchar(20) DEFAULT NULL," +
            "  `SendID` integer DEFAULT NULL," +
            "  `Read_Time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00'," +
            "  `collar_name` varchar(12) DEFAULT NULL," +
            "  `OrderFormID` bigint(20) NOT NULL DEFAULT '0'," +
            "  `OrderFormUpdateDate` datetime DEFAULT NULL," +
            "  `BroadcastMode` varchar(1) DEFAULT '', " +
            "  `OnKey` integer NOT NULL DEFAULT 0, " +
            "  `save_dir` varchar(500) NOT NULL DEFAULT ''"+
            ");";
    private static String TABLE_CREATE_017 = "CREATE TABLE `mail_order` (" +
            "  `sn` integer NOT NULL PRIMARY KEY," +
            "  `address` varchar(50) NOT NULL " +
            ");";
    private static String TABLE_CREATE_018 = "CREATE TABLE `manager` (" +
            "  `card_no` varchar(12) NOT NULL PRIMARY KEY, " +
            "  `card_sn` varchar(12) NOT NULL DEFAULT '', " +
            "  `name` varchar(20) NOT NULL DEFAULT '', " +
            "  `rank` varchar(10) NOT NULL DEFAULT '', " +
            "  `pass` varchar(12) NOT NULL DEFAULT '', " +
            "  `ONOFF` smallint DEFAULT NULL" +
            ");";
    private static String TABLE_CREATE_019 = "CREATE TABLE `resident` (" +
            "  `id` integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "  `card_no` varchar(12) NOT NULL," +
            "  `card_sn` varchar(12) NOT NULL DEFAULT ''," +
            "  `name` varchar(20) NOT NULL DEFAULT ''," +
            "  `birth_year` varchar(12) NOT NULL DEFAULT ''," +
            "  `home_phone_num` varchar(16) NOT NULL DEFAULT ''," +
            "  `cell_phone_num` varchar(16) NOT NULL DEFAULT ''," +
            "  `email` varchar(40) NOT NULL DEFAULT ''," +
            "  `gender` varchar(1) NOT NULL DEFAULT ''," +
            "  `identity` varchar(3) NOT NULL DEFAULT ''," +
            "  `notation` varchar(5) NOT NULL DEFAULT ''," +
            "  `building_sn` varchar(20) NOT NULL DEFAULT ''," +
            "  `floor_sn` varchar(20) NOT NULL DEFAULT ''," +
            "  `section_sn` varchar(20) NOT NULL DEFAULT ''," +
            "  `pic_name` varchar(12) NOT NULL DEFAULT ''," +
            "  `input_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP," +
            "  `PosDownLoad` datetime DEFAULT NULL," +
            "  `AppLogin` datetime NULL DEFAULT NULL," +
            "  `SynchronizeDate` datetime" +
            ");";
    private static String TABLE_CREATE_020 = "CREATE TABLE `section_sn` (" +
            "  `sort` integer NOT NULL," +
            "  `full_name` varchar(20) NOT NULL PRIMARY KEY" +
            ");";
    private static String TABLE_CREATE_021 = "CREATE TABLE `ServiceCard` (" +
            "  `Card_No` varchar(12) NOT NULL PRIMARY KEY," +
            "  `Suppliers_ID` integer NOT NULL," +
            "  `MifareID` varchar(20) NOT NULL," +
            "  `UserName` varchar(30) NOT NULL DEFAULT ''," +
            "  `Worker` varchar(20) DEFAULT NULL," +
            "  `SaveDate` datetime DEFAULT NULL," +
            "  `Disabled` smallint NOT NULL DEFAULT 0," +
            "  `DelMark` smallint NOT NULL DEFAULT 0" +
            ");";
    private static String TABLE_CREATE_022 = "CREATE TABLE `ServiceCommunity` (" +
            "  `CommunityServiceID` varchar(20) NOT NULL," +
            "  `Community_ID` integer NOT NULL," +
            "  `Suppliers_ID` varchar(200) NOT NULL," +
            "  `ServiceMode` varchar(20) NOT NULL DEFAULT ''," +
            "  `ServiceItem` varchar(20) NOT NULL DEFAULT ''," +
            "  `BuildDate` datetime NOT NULL DEFAULT '0000-00-00 00:00:00'," +
            "  `BuildCardNo` varchar(20) NOT NULL DEFAULT ''," +
            "  `Description` varchar(1024) DEFAULT NULL," +
            "  `UserName` varchar(20) DEFAULT NULL," +
            "  `UserTel` varchar(20) DEFAULT NULL," +
            "  `UserCash` decimal(10,4) DEFAULT NULL," +
            "  `Rental` smallint DEFAULT NULL," +
            "  `UserCell` varchar(20) DEFAULT NULL," +
            "  `Other1` varchar(50) DEFAULT NULL," +
            "  `Other2` varchar(50) DEFAULT NULL," +
            "  `Other3` varchar(50) DEFAULT NULL," +
            "  `Other4` varchar(50) DEFAULT NULL," +
            "  `Other5` varchar(50) DEFAULT NULL," +
            "  `Other6` varchar(50) DEFAULT NULL," +
            "  `Other7` varchar(50) DEFAULT NULL," +
            "  `Other8` varchar(50) DEFAULT NULL," +
            "  `Other9` varchar(50) DEFAULT NULL," +
            "  `Other10` varchar(50) DEFAULT NULL," +
            "  `Other11` varchar(50) DEFAULT NULL," +
            "  `Other12` varchar(50) DEFAULT NULL," +
            "  `Other13` varchar(50) DEFAULT NULL," +
            "  `Other14` varchar(50) DEFAULT NULL," +
            "  `Other15` varchar(50) DEFAULT NULL," +
            "  `Other16` varchar(50) DEFAULT NULL," +
            "  `Other17` varchar(50) DEFAULT NULL," +
            "  `Other18` varchar(50) DEFAULT NULL," +
            "  `Other19` varchar(50) DEFAULT NULL," +
            "  `Other20` varchar(50) DEFAULT NULL," +
            "  `Photo1` mediumblob," +
            "  `Photo2` mediumblob," +
            "  `Photo3` mediumblob," +
            "  `Photo4` mediumblob," +
            "  `Photo5` mediumblob," +
            "  `Suppliers_ID_1` integer NOT NULL DEFAULT 0," +
            "  `Suppliers_ID_2` integer NOT NULL DEFAULT 0," +
            "  `Suppliers_ID_3` integer NOT NULL DEFAULT 0," +
            "  `Suppliers_ID_4` integer NOT NULL DEFAULT 0," +
            "  `Suppliers_ID_5` integer NOT NULL DEFAULT 0," +
            "  `Suppliers_ID_6` integer NOT NULL DEFAULT 0," +
            "  `Suppliers_ID_7` integer NOT NULL DEFAULT 0," +
            "  `Suppliers_ID_8` integer NOT NULL DEFAULT 0," +
            "  `Suppliers_ID_9` integer NOT NULL DEFAULT 0," +
            "  `PresentDate1` datetime NOT NULL DEFAULT '0000-00-00 00:00:00'," +
            "  `PresentCardNo1` varchar(20) NOT NULL DEFAULT ''," +
            "  `PresentDate2` datetime NOT NULL DEFAULT '0000-00-00 00:00:00'," +
            "  `PresentCardNo2` varchar(20) NOT NULL DEFAULT ''," +
            "  `PresentDate3` datetime NOT NULL DEFAULT '0000-00-00 00:00:00'," +
            "  `PresentCardNo3` varchar(20) NOT NULL DEFAULT ''," +
            "  `PresentDate4` datetime NOT NULL DEFAULT '0000-00-00 00:00:00'," +
            "  `PresentCardNo4` varchar(20) NOT NULL DEFAULT ''," +
            "  `PresentDate5` datetime NOT NULL DEFAULT '0000-00-00 00:00:00'," +
            "  `PresentCardNo5` varchar(20) NOT NULL DEFAULT ''," +
            "  `PresentDate6` datetime NOT NULL DEFAULT '0000-00-00 00:00:00'," +
            "  `PresentCardNo6` varchar(20) NOT NULL DEFAULT ''," +
            "  `PresentDate7` datetime NOT NULL DEFAULT '0000-00-00 00:00:00'," +
            "  `PresentCardNo7` varchar(20) NOT NULL DEFAULT ''," +
            "  `PresentDate8` datetime NOT NULL DEFAULT '0000-00-00 00:00:00'," +
            "  `PresentCardNo8` varchar(20) NOT NULL DEFAULT ''," +
            "  `PresentDate9` datetime NOT NULL DEFAULT '0000-00-00 00:00:00'," +
            "  `PresentCardNo9` varchar(20) NOT NULL DEFAULT ''," +
            "  `ForecastDate` datetime NOT NULL DEFAULT '0000-00-00 00:00:00'," +
            "  `ForecastCardNo` varchar(20) NOT NULL DEFAULT ''," +
            "  `PresentDate` datetime NOT NULL DEFAULT '0000-00-00 00:00:00'," +
            "  `PresentCardNo` varchar(20) NOT NULL DEFAULT ''," +
            "  `InsertTime` datetime DEFAULT NULL," +
            "  `ReturnTime` datetime DEFAULT NULL," +
            "  `EndingTime` datetime DEFAULT NULL," +
            "  `Worker` varchar(20) DEFAULT NULL," +
            "  `SaveDate` datetime DEFAULT NULL," +
            "  `Disabled` smallint NOT NULL DEFAULT 0," +
            "  `DelMark` smallint NOT NULL DEFAULT 0," +
            "  `LicensePlate` varchar(50) NOT NULL DEFAULT ''," +
            "  constraint pk_t2 primary key (`CommunityServiceID`, `Community_ID`)" +
            ");";
    private static String TABLE_CREATE_023 = "CREATE TABLE `ServiceSuppliers` (" +
            "  `Suppliers_ID` integer NOT NULL PRIMARY KEY," +
            "  `ByCommunity` varchar(200) NOT NULL DEFAULT ''," +
            "  `SupplierName` varchar(30) NOT NULL DEFAULT ''," +
            "  `ServiceMode` varchar(20) NOT NULL DEFAULT ''," +
            "  `Service_Items` varchar(50) NOT NULL DEFAULT ''," +
            "  `OpenFiles` varchar(50) NOT NULL DEFAULT ''," +
            "  `SupplierTel` varchar(20) DEFAULT NULL," +
            "  `SupplierFax` varchar(20) DEFAULT NULL," +
            "  `SupplierMail` varchar(50) DEFAULT NULL," +
            "  `SupplierAddess` varchar(50) DEFAULT NULL," +
            "  `ServicePersonnel` varchar(50) NOT NULL DEFAULT ''," +
            "  `SupplierCell` varchar(20) NOT NULL DEFAULT ''," +
            "  `SupplierLoginID` varchar(20) NOT NULL DEFAULT ''," +
            "  `SupplierPassword` varchar(20) NOT NULL DEFAULT ''," +
            "  `DM1` mediumblob," +
            "  `DM2` mediumblob," +
            "  `DM1Type` varchar(20) DEFAULT NULL," +
            "  `DM2Type` varchar(20) DEFAULT NULL," +
            "  `Explanation1` varchar(100) NOT NULL DEFAULT ''," +
            "  `Explanation2` varchar(100) NOT NULL DEFAULT ''," +
            "  `Worker` varchar(20) DEFAULT NULL," +
            "  `SaveDate` datetime DEFAULT NULL," +
            "  `Disabled` smallint NOT NULL DEFAULT 0," +
            "  `DelMark` smallint NOT NULL DEFAULT 0" +
            ");";
    private static String TABLE_CREATE_024 = "CREATE TABLE `system_path` (" +
            "  `build_id` integer NOT NULL PRIMARY KEY," +
            "  `reader_type` integer NOT NULL," +
            "  `account` varchar(50) NOT NULL DEFAULT ''," +
            "  `pass` varchar(20) NOT NULL DEFAULT ''," +
            "  `enc` varchar(64) DEFAULT NULL," +
            "  `licensed` varchar(10) DEFAULT NULL," +
            "  `build_name` varchar(20) DEFAULT NULL," +
            "  `IsTable` varchar(1) DEFAULT NULL," +
            "  `LoadServer` varchar(20) DEFAULT NULL," +
            "  `AppUpdateDate` datetime NULL DEFAULT NULL," +
            "  `JobOnOff_Open` varchar(1) DEFAULT NULL," +
            "  `Language` integer DEFAULT NULL," +
            "  `Mcompany_ID` integer DEFAULT NULL," +
            "  `OtherSetup` varchar(20) NOT NULL DEFAULT ''," +
            "  `ServiceOpen` varchar(1) NOT NULL DEFAULT '0'," +
            "  `ServiceFunction` varchar(400) NOT NULL DEFAULT ''," +
            "  `ServiceFunctionJump` varchar(20) NOT NULL DEFAULT '', " +
            "  `MailWarning` integer NOT NULL DEFAULT 3, " +
            "  `GuestWarning` integer NOT NULL DEFAULT 3, " +
            "  `HandselPointZero` integer NOT NULL DEFAULT 0, " +
            "  `NoCardOpen` varchar(1) NOT NULL DEFAULT '0', "+
            "  `PadFunction` varchar(3) NOT NULL DEFAULT '', " +
            "  `PointWay` integer NOT NULL DEFAULT 1 " +
            ");";
    private static String TABLE_CREATE_025 = "CREATE TABLE `transaction_log` (" +
            "  `id` integer NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "  `house_id` integer NOT NULL," +
            "  `facility` varchar(12) NOT NULL," +
            "  `deduction` integer NOT NULL," +
            "  `point` integer NOT NULL," +
            "  `time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP," +
            "  `card_no` varchar(20) NOT NULL DEFAULT ''" +
            ");";
    private static String TABLE_CREATE_026 = "CREATE TABLE `mail_type_list` (" +
            "  `id` integer NOT NULL PRIMARY KEY, " +
            "  `list` varchar(20) NOT NULL" +
            ");";
    private static String TABLE_CREATE_027 = "CREATE TABLE `mail_mode_list` (" +
            "  `id` integer NOT NULL PRIMARY KEY, " +
            "  `list` varchar(20) NOT NULL" +
            ");";
    private static String TABLE_CREATE_028 = "CREATE TABLE `mail_logistic_list` (" +
            "  `id` integer NOT NULL PRIMARY KEY, " +
            "  `list` varchar(20) NOT NULL" +
            ");";
    private static String TABLE_CREATE_029 = "INSERT INTO `Language` (Lid, Bsn, Fsn, Ssn, Luser, LSsn) VALUES (1, '公司', '部門', '支部', '員工', '支部門別 ：');";
    private static String TABLE_CREATE_030 = "INSERT INTO `Language` (Lid, Bsn, Fsn, Ssn, Luser, LSsn) VALUES (2, '公司', '部門', '職稱', '員工', '職　　稱 ：');";
    private static String TABLE_CREATE_031 = "INSERT INTO `manager` VALUES ('00000000','00000000','網管人員','111','0000',NULL);";
    //private static String TABLE_CREATE_032 = "INSERT INTO `manager` VALUES ('HB001989','B45479AF','測試1','1111','1234',NULL);";
    //private static String TABLE_CREATE_033 = "INSERT INTO `manager` VALUES ('HB001979','678BCF93','測試2','1111','1234',NULL);";
    private static String TABLE_CREATE_032 = "CREATE TABLE `EmployeeFormation` (" +
            "  `id` integer NOT NULL PRIMARY KEY," +
            "  `position` varchar(20) NOT NULL," +
            "  `JobOnTime` varchar(10) NOT NULL DEFAULT ''," +
            "  `JobOffTime` varchar(10) NOT NULL DEFAULT ''," +
            "  `DelMark` tinyint NOT NULL DEFAULT 0," +
            "  `SaveDate` datetime DEFAULT NULL" +
            ");";
    private static String TABLE_CREATE_033 = "CREATE TABLE `PublicPlace` (" + //-----2016/4/22
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
            "  `DelMark` tinyint NOT NULL DEFAULT 0 ," +
            "  `familyONOFF` integer NOT NULL DEFAULT 0 ," +
            "  `Use_Hours` integer NOT NULL DEFAULT 1 ," +
            "  `AppBooking` integer NOT NULL DEFAULT 0 "+
            ");";
    private static String TABLE_CREATE_034 = "CREATE TABLE `PublicApply` (" +
            "  `PublicApplyID` bigint NOT NULL PRIMARY KEY," +
            "  `Card_No` varchar(20) NOT NULL," +
            "  `Status` tinyint NOT NULL DEFAULT 0," +
            "  `PublicPlace` varchar(20) NOT NULL," +
            "  `PublicPlaceName` varchar(50) NOT NULL DEFAULT ''," +
            "  `ApplyDate` datetime NOT NULL," +
            "  `ForecastReturnDate` datetime NOT NULL," +
            "  `ReturnDate` datetime," +
            "  `ApplyHours` integer NOT NULL DEFAULT 0," +
            "  `Description` varchar(500) NOT NULL DEFAULT ''," +
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
            "  `SignUser` varchar(30) NOT NULL DEFAULT ''," +
            "  `SignFileName` varchar(20) NOT NULL DEFAULT ''," +
            "  `SynchronizeDate` datetime," +
            "  `Worker` varchar(20) NOT NULL DEFAULT ''," +
            "  `ConfromWorker` varchar(20) NOT NULL DEFAULT ''," +
            "  `CloseWorker` varchar(20) NOT NULL DEFAULT ''," +
            "  `SaveDate` datetime DEFAULT NULL," +
            "  `Disabled` tinyint NOT NULL DEFAULT 0," +
            "  `DelMark` tinyint NOT NULL DEFAULT 0 " +
            ");";
    private static String TABLE_CREATE_035 = "CREATE TABLE `PointAlert` (" +
            "  `PointAlertID` bigint NOT NULL PRIMARY KEY," +
            "  `building_sn` varchar(20) NOT NULL," +
            "  `floor_sn` varchar(20) NOT NULL," +
            "  `section_sn` varchar(20) NOT NULL," +
            "  `Card_No` varchar(20) NOT NULL," +
            "  `Status` tinyint NOT NULL DEFAULT 0," +
            "  `AlertPoint` integer NOT NULL DEFAULT 0," +
            "  `PublicPlaceName` varchar(50) NOT NULL DEFAULT ''," +
            "  `OriginalStarthandsel_point` integer NOT NULL DEFAULT 0," +
            "  `OriginalHandsel_point` integer NOT NULL DEFAULT 0," +
            "  `OriginalBuy_point` integer NOT NULL DEFAULT 0," +
            "  `OriginalBuy_point2` integer NOT NULL DEFAULT 0," +
            "  `DelStarthandsel_point` integer NOT NULL DEFAULT 0," +
            "  `DelHandsel_point` integer NOT NULL DEFAULT 0," +
            "  `DelBuy_point` integer NOT NULL DEFAULT 0," +
            "  `DelBuy_point2` integer NOT NULL DEFAULT 0," +
            "  `SynchronizeDate` datetime," +
            "  `Worker` varchar(20) NOT NULL DEFAULT ''," +
            "  `SaveDate` datetime DEFAULT NULL," +
            "  `Disabled` tinyint NOT NULL DEFAULT 0," +
            "  `DelMark` tinyint NOT NULL DEFAULT 0 " +
            ");";
    private static String TABLE_CREATE_036 = "CREATE TABLE `Marquee_data` (" +
            "  `id` integer NOT NULL PRIMARY KEY," +
            "  `MarqueeTxt` varchar(30) NOT NULL," +
            "  `EndDate` datetime DEFAULT NULL," +
            "   `SaveDate` datetime DEFAULT NULL" +
            ");";

    public static final int iMaxTableLine = 36;

    public static final String[] TABLE_CREATE=new String[] {TABLE_CREATE_001,TABLE_CREATE_002,TABLE_CREATE_003,TABLE_CREATE_004,TABLE_CREATE_005,
            TABLE_CREATE_006,TABLE_CREATE_007,TABLE_CREATE_008,TABLE_CREATE_009,TABLE_CREATE_010,
            TABLE_CREATE_011,TABLE_CREATE_012,TABLE_CREATE_013,TABLE_CREATE_014,TABLE_CREATE_015,
            TABLE_CREATE_016,TABLE_CREATE_017,TABLE_CREATE_018,TABLE_CREATE_019,TABLE_CREATE_020,
            TABLE_CREATE_021,TABLE_CREATE_022,TABLE_CREATE_023,TABLE_CREATE_024,TABLE_CREATE_025,
            TABLE_CREATE_026, TABLE_CREATE_027, TABLE_CREATE_028, TABLE_CREATE_029, TABLE_CREATE_030,
            TABLE_CREATE_031, TABLE_CREATE_032, TABLE_CREATE_033, TABLE_CREATE_034, TABLE_CREATE_035 ,TABLE_CREATE_036};

    /*
    public static final String TABLE_CREATE_L = TABLE_CREATE_001 + TABLE_CREATE_002 + TABLE_CREATE_003 + TABLE_CREATE_004 + TABLE_CREATE_005 +
            TABLE_CREATE_006 + TABLE_CREATE_007 + TABLE_CREATE_008 + TABLE_CREATE_009 + TABLE_CREATE_010 +
            TABLE_CREATE_011 + TABLE_CREATE_012 + TABLE_CREATE_013 + TABLE_CREATE_014 + TABLE_CREATE_015 +
            TABLE_CREATE_016 + TABLE_CREATE_017 + TABLE_CREATE_018 + TABLE_CREATE_019 + TABLE_CREATE_020 +
            TABLE_CREATE_021 + TABLE_CREATE_022 + TABLE_CREATE_023 + TABLE_CREATE_024 + TABLE_CREATE_025 +
            TABLE_CREATE_026 + TABLE_CREATE_027 + TABLE_CREATE_028 + TABLE_CREATE_029 + TABLE_CREATE_030;
    */

    public String[] LineString() {
        return TABLE_CREATE;
    }

    public int MaxLine() {
        return iMaxTableLine;
    }
}
