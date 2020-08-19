package com.vany.excleuploadsqlite.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class DBConstants {
    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public static final String USER_TABLE = "customers";
    public static final String CUSTOMER_ID = "customer_id";
    public static final String CUSTOMER_NAME = "customer_name";
    public static final String CUSTOMER_CONTACT_NO = "customer_contact_no";
    public static final String CUSTOMER_EMAIL = "customer_email";
    public static final String CUSTOMER_ADDRESS = "customer_address";
    public static final String CUSTOMER_FEEDBACK = "customer_feedback";

    public DBConstants(Context context) {
        this.context = context;
    }

    public DBConstants open() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + USER_TABLE + " ("
            + CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + CUSTOMER_NAME + " TEXT,"
            + CUSTOMER_CONTACT_NO + " TEXT,"
            + CUSTOMER_EMAIL + " TEXT,"
            + CUSTOMER_ADDRESS + " TEXT,"
            + CUSTOMER_FEEDBACK + " TEXT)";

    static final String SELECT_CUSTOMER_QUERY = "SELECT * FROM " + USER_TABLE;

    public void insertData(String name, String contactNumber, String email, String address, String feedback) {
        String insertQuery = "INSERT INTO " + USER_TABLE +
                "(" + CUSTOMER_NAME + "," + CUSTOMER_CONTACT_NO + "," + CUSTOMER_EMAIL + "," + CUSTOMER_ADDRESS + "," + CUSTOMER_FEEDBACK +
                ")Values ('" + name + "','" + contactNumber + "','" + email + "','" + address + "', '" + feedback + "');";
        System.out.println("Query : " + insertQuery);
        this.database.execSQL(insertQuery);
    }

    public Cursor selectData() {
        Cursor rowData = this.database.rawQuery(SELECT_CUSTOMER_QUERY, null);
        return rowData;
    }
}
