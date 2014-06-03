package com.nikohapa.icountyke.constant;

/**
 * Created by james on 3/06/14.
 */
public class Constants {

    //DB consts
    public static final String DB_NAME = "icountyke_db.sqlite";
    public static final int DB_VERSION = 1;
    public static final String DB_DIR = "/data/data/com.nikohapa.icountyke/databases/";
    public static final String FORCE_FOREIGN_KEY_CHECKS = "PRAGMA foreign_keys = ON";

    //splash timer
    public static final long SPLASH_TIMER = 1000;

    //TABS
    public static final String[] TABS_TITLE = {"Public", "InHouse Chat", "Profile"};

    //message types
    public static final String[] MSG_TYPES = {"SMS", "TWITTER", "FACEBOOK", "WHATSAPP"};

}
