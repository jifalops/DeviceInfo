package com.deviceinfoapp.data;

import com.deviceinfoapp.model.DrawerHeader;
import com.deviceinfoapp.model.DrawerItem;
import com.deviceinfoapp.model.Item;

import java.util.ArrayList;
import java.util.List;
import static com.deviceinfoapp.data.Elements.*;

/**
 * Created by Jake on 7/18/13.
 */
public class NavData {
    private NavData() {}

    public static final int NUM_DRAWER_ITEMS;

    public static final List<Item> DRAWER_ITEMS;
    public static final int[] ITEM_MAP;

    static {
        NUM_DRAWER_ITEMS = NUM_ELEMENTS + 4;

        DRAWER_ITEMS = new ArrayList<Item>(NUM_DRAWER_ITEMS);      ITEM_MAP = new int[NUM_DRAWER_ITEMS];
        DRAWER_ITEMS.add(new DrawerItem(NAMES[OVERVIEW]));           ITEM_MAP[0]  = OVERVIEW ;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[PROCESSORS]));         ITEM_MAP[1]  = PROCESSORS;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[BATTERY]));            ITEM_MAP[2]  = BATTERY;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[SENSORS]));            ITEM_MAP[3]  = SENSORS;

        DRAWER_ITEMS.add(new DrawerHeader("Memory"));              ITEM_MAP[4]  = OVERVIEW;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[RAM]));                ITEM_MAP[5]  = RAM;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[STORAGE]));            ITEM_MAP[6]  = STORAGE;

        DRAWER_ITEMS.add(new DrawerHeader("Audio Visual"));        ITEM_MAP[7]  = OVERVIEW;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[DISPLAY]));            ITEM_MAP[8]  = DISPLAY;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[GRAPHICS]));           ITEM_MAP[9]  = GRAPHICS;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[CAMERAS]));            ITEM_MAP[10] = CAMERAS;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[AUDIO]));              ITEM_MAP[11] = AUDIO;

        DRAWER_ITEMS.add(new DrawerHeader("Connections"));         ITEM_MAP[12] = OVERVIEW;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[LOCATION]));           ITEM_MAP[13] = LOCATION;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[CELLULAR]));           ITEM_MAP[14] = CELLULAR;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[WIFI]));               ITEM_MAP[15] = WIFI;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[NETWORK]));            ITEM_MAP[16] = NETWORK;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[BLUETOOTH]));          ITEM_MAP[17] = BLUETOOTH;


        DRAWER_ITEMS.add(new DrawerHeader("System"));              ITEM_MAP[18] = OVERVIEW;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[UPTIME]));             ITEM_MAP[19] = UPTIME;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[PLATFORM]));           ITEM_MAP[20] = PLATFORM;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[IDENTIFIERS]));        ITEM_MAP[21] = IDENTIFIERS;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[FEATURES]));           ITEM_MAP[22] = FEATURES;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[PROPERTIES]));         ITEM_MAP[23] = PROPERTIES;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[KEYS]));               ITEM_MAP[24] = KEYS;
    }
}
