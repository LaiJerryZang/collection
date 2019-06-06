package tw.com.collection.basic.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.SystemClock;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import tw.com.collection.basic.datatable.DataRow;
import tw.com.collection.basic.datatable.DataTable;


public class ConnectionDB extends SQLiteOpenHelper {
    private static final String DB_NAME = "super_db";
    private static final int DB_VERSION = 36; //資料庫版本，增加後跑到 onUpgrade
    private static ConnectionDB connectionDB = null;

    public static void initInstance(Context context) {
        if (connectionDB == null) {
            connectionDB = new ConnectionDB(context);
        }
    }

    public static ConnectionDB getInstance() {
        if (connectionDB == null) {
            throw new NullPointerException("not init");
        }
        return connectionDB;
    }

    private ConnectionDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        SQL_CreateLine SQLine = new SQL_CreateLine();
        String[] cSQL = SQLine.LineString();

        for (int i = 0; i < SQL_CreateLine.MAX_TABLE_LINE; i++) {
            try {
                if (cSQL[i].length() > 0) {
                    db.execSQL(cSQL[i]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        SQL_UpdateLine SQLine = new SQL_UpdateLine();
        String[] cSQL = SQLine.LineString();

        for (int i = 0; i < SQL_UpdateLine.MAX_TABLE_LINE; i++) {
            try {
                if (cSQL[i].length() > 0) {
                    db.execSQL(cSQL[i]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String ExecSQL(String sql) {
        SQLiteDatabase db = getWritableDatabase();
        String response = "";
        boolean error = false;
        //1
        try {
            db.execSQL(sql);
            response = "OK";
        } catch (Exception e) {
            response = e.getMessage();
            SystemClock.sleep(10);
            error = true;
            Log.i("ExecSQL(1)：", response + sql);

        }
        //2
        if (error) {
            try {
                db.execSQL(sql);
                response = "OK";
                error = false;
            } catch (Exception e) {
                response = e.getMessage();
                SystemClock.sleep(10);
                error = true;
                Log.i("ExecSQL(2)：", response + sql);
            }
        }
        //3
        if (error) {
            try {
                db.execSQL(sql);
                response = "OK";
            } catch (Exception e) {
                response = e.getMessage();
                Log.i("ExecSQL(3)：", response + sql);
            }
        }

        return response;
    }

    public DataTable Select(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        DataTable dt = new DataTable();
        boolean error = false;
        Cursor cursor = null;

        //1
        try {
            cursor = db.rawQuery(sql, null);//SQL 撈資料
        } catch (Exception e) {
            SystemClock.sleep(10);
            error = true;
            Log.i("Select(1)：", e.getMessage() + sql);

        }
        //2
        if (error) {
            try {
                cursor = db.rawQuery(sql, null);//SQL 撈資料
                error = false;
            } catch (Exception e) {
                SystemClock.sleep(10);
                error = true;
                Log.i("Select(2)：", e.getMessage() + sql);
            }
        }
        //3
        if (error) {
            try {
                cursor = db.rawQuery(sql, null);//SQL 撈資料
                error = false;
            } catch (Exception e) {
                Log.i("Select(3)：", e.getMessage() + sql);
            }
        }

        if (error) {
            return dt;
        }

        try {
            //塞資料
            while (cursor.moveToNext()) {
                DataRow dr = dt.NewRow();
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    //Log.i("XYZ--cursor--", cursor.getColumnName(i) + "***" + cursor.getType(i));
                    dt.Columns.Add(cursor.getColumnName(i));//放入欄位名稱
                    switch (cursor.getType(i)) {//型別判斷
                        case 0: //FIELD_TYPE_NULL
                            dr.setValue(i, null);
                            break;
                        case 1: //FIELD_TYPE_INTEGER
                            String cV = cursor.getString(i);
                            long iV = Long.parseLong(cV);

                            if (iV > 2147483647 || iV < -2147483648) {
                                dr.setValue(i, cursor.getLong(i)); //bigint
                            } else {
                                dr.setValue(i, cursor.getInt(i));
                            }
                            //dr.setValue(i, cursor.getInt(i));
                            break;
                        case 2: //FIELD_TYPE_FLOAT

                            dr.setValue(i, cursor.getFloat(i));
                            break;
                        case 3: //FIELD_TYPE_STRING
                            dr.setValue(i, cursor.getString(i));
                            break;
                        case 4: //FIELD_TYPE_BLOB
                            dr.setValue(i, cursor.getBlob(i));
                            break;
                        default:
                            dr.setValue(i, cursor.getString(i));
                    }
                }
                dt.Rows.add(dr);//加入至DataRowCollection
            }
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
            if (!cursor.isClosed()) cursor.close();
        }

        return dt;
    }

    public JSONArray jSelect(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        JSONArray jsonArray = new JSONArray();
        boolean error = false;
        Cursor cursor = null;
        for (int i = 0; i < 3; i++) {
            try {
                cursor = db.rawQuery(sql, null);
                error = false;
            } catch (Exception e) {
                Log.i("Select(" + i + ")：", e.getMessage() + sql);
                error = true;
            }
            if (!error) {
                break;
            }
        }

        if (error) {
            return jsonArray;
        }

        try {
            //塞資料
            while (cursor.moveToNext()) {
                JSONObject jsonObject = new JSONObject();
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    //Log.i("XYZ--cursor--", cursor.getColumnName(i) + "***" + cursor.getType(i));
                    String value;
                    switch (cursor.getType(i)) {//型別判斷
                        case 0: //FIELD_TYPE_NULL
                            value = "";
                            break;
                        case 1: //FIELD_TYPE_INTEGER
                            String cV = cursor.getString(i);
                            long iV = Long.parseLong(cV);

                            if (iV > 2147483647 || iV < -2147483648) {
                                value = String.valueOf(cursor.getLong(i));
                            } else {
                                value = String.valueOf(cursor.getInt(i));
                            }
                            break;
                        case 2: //FIELD_TYPE_FLOAT
                            value = String.valueOf(cursor.getFloat(i));
                            break;
                        case 3: //FIELD_TYPE_STRING
                            value = cursor.getString(i);
                            break;
                        case 4: //FIELD_TYPE_BLOB
                            value = new String(cursor.getBlob(i));
                            break;
                        default:
                            value = cursor.getString(i);
                    }
                    jsonObject.put(cursor.getColumnName(i), value);
                }
                jsonArray.put(jsonObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!cursor.isClosed()) cursor.close();
        return jsonArray;
    }

    @Override
    public synchronized void close() {
        super.close();
        connectionDB = null;
    }
}
