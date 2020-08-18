package com.vany.excleuploadsqlite.db;

public class DBConstants {
    static final String USER_TABLE = "customers";
    static final String CUSTOMER_ID = "customer_id";
    static final String CUSTOMER_NAME = "customer_name";
    static final String CUSTOMER_CONTACT_NO = "customer_contact_no";
    static final String CUSTOMER_EMAIL = "customer_email";
    static final String CUSTOMER_ADDRESS = "customer_address";
    static final String CUSTOMER_FEEDBACK = "customer_feedback";

    static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + USER_TABLE + " ("
            + CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + CUSTOMER_NAME + " TEXT,"
            + CUSTOMER_CONTACT_NO + " TEXT,"
            + CUSTOMER_EMAIL + " TEXT,"
            + CUSTOMER_ADDRESS + " TEXT,"
            + CUSTOMER_FEEDBACK + " TEXT)";

    static final String SELECT_CUSTOMER_QUERY = "SELECT * FROM " + USER_TABLE;
}
