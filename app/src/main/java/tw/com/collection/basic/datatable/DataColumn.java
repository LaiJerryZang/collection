package tw.com.collection.basic.datatable;

/**
 * Created by Howard on 2015/12/23.
 */
public class DataColumn {
    /*
        * DataColumn所屬的DataTable
        */
    private DataTable table;
    /*
        * DataColumn的欄位名稱
        */
    public String ColumnName; // 欄名，當做DataRow的key

    /*
        * DataColumn被建立時，一定要指定欄名
        * @param columnName 欄名
        */
    public DataColumn(String columnName) {
        this.ColumnName = columnName.toLowerCase();
    }

    /*
        * 給DataColumnCollection加入DataColumn時設定所屬的DataTable的方法，同一個package才用到
        * @param table
        */
    void setTable(DataTable table)
    {
        this.table = table;
    }

    /*
        * 取得DataColumn所屬的DataTable，唯讀
        * @return DataTable
        */
    public DataTable getTable()
    {
        return this.table;
    }

    /*
        * DataColumn物件的toString()，會回傳自己的欄名
        * @return
        */
    @Override
    public String toString(){
        return this.ColumnName;
    }
}
