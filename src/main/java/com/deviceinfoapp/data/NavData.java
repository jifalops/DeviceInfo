package com.deviceinfoapp.data;

import com.deviceinfoapp.viewable.DrawerHeader;
import com.deviceinfoapp.viewable.DrawerItem;
import com.deviceinfoapp.viewable.Item;

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
    public static final int[] DRAWER_TO_ELEMENT;

    static {
        NUM_DRAWER_ITEMS = NUM_ELEMENTS + 4;

        DRAWER_ITEMS = new ArrayList<Item>(NUM_DRAWER_ITEMS);      DRAWER_TO_ELEMENT = new int[NUM_DRAWER_ITEMS];
        DRAWER_ITEMS.add(new DrawerItem(NAMES[OVERVIEW]));           DRAWER_TO_ELEMENT[0]  = OVERVIEW ;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[PROCESSORS]));         DRAWER_TO_ELEMENT[1]  = PROCESSORS;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[BATTERY]));            DRAWER_TO_ELEMENT[2]  = BATTERY;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[SENSORS]));            DRAWER_TO_ELEMENT[3]  = SENSORS;

        DRAWER_ITEMS.add(new DrawerHeader("Memory"));              DRAWER_TO_ELEMENT[4]  = OVERVIEW;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[RAM]));                DRAWER_TO_ELEMENT[5]  = RAM;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[STORAGE]));            DRAWER_TO_ELEMENT[6]  = STORAGE;

        DRAWER_ITEMS.add(new DrawerHeader("Audio Visual"));        DRAWER_TO_ELEMENT[7]  = OVERVIEW;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[DISPLAY]));            DRAWER_TO_ELEMENT[8]  = DISPLAY;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[GRAPHICS]));           DRAWER_TO_ELEMENT[9]  = GRAPHICS;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[CAMERAS]));            DRAWER_TO_ELEMENT[10] = CAMERAS;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[AUDIO]));              DRAWER_TO_ELEMENT[11] = AUDIO;

        DRAWER_ITEMS.add(new DrawerHeader("Connections"));         DRAWER_TO_ELEMENT[12] = OVERVIEW;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[LOCATION]));           DRAWER_TO_ELEMENT[13] = LOCATION;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[CELLULAR]));           DRAWER_TO_ELEMENT[14] = CELLULAR;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[WIFI]));               DRAWER_TO_ELEMENT[15] = WIFI;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[NETWORK]));            DRAWER_TO_ELEMENT[16] = NETWORK;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[BLUETOOTH]));          DRAWER_TO_ELEMENT[17] = BLUETOOTH;


        DRAWER_ITEMS.add(new DrawerHeader("System"));              DRAWER_TO_ELEMENT[18] = OVERVIEW;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[UPTIME]));             DRAWER_TO_ELEMENT[19] = UPTIME;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[PLATFORM]));           DRAWER_TO_ELEMENT[20] = PLATFORM;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[IDENTIFIERS]));        DRAWER_TO_ELEMENT[21] = IDENTIFIERS;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[FEATURES]));           DRAWER_TO_ELEMENT[22] = FEATURES;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[PROPERTIES]));         DRAWER_TO_ELEMENT[23] = PROPERTIES;
        DRAWER_ITEMS.add(new DrawerItem(NAMES[KEYS]));               DRAWER_TO_ELEMENT[24] = KEYS;
    }
}
