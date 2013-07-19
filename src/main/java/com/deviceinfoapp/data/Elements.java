package com.deviceinfoapp.data;

/**
 * Created by Jake on 7/18/13.
 */
public class Elements {
    private Elements(){}

    public static final int
            OVERVIEW = 0,
            PROCESSORS = 1,
            BATTERY = 2,
            SENSORS = 3,
            RAM = 4,
            STORAGE = 5,
            DISPLAY = 6,
            GRAPHICS = 7,
            CAMERAS = 8,
            AUDIO = 9,
            LOCATION = 10,
            CELLULAR = 11,
            WIFI = 12,
            NETWORK = 13,
            BLUETOOTH = 14,
            UPTIME = 15,
            PLATFORM = 16,
            IDENTIFIERS = 17,
            FEATURES = 18,
            PROPERTIES = 19,
            KEYS = 20,

            // Count of above elements
            NUM_ELEMENTS = 21;


    public static final String[] NAMES;
    
    static {
        NAMES = new String[NUM_ELEMENTS];

        NAMES[OVERVIEW] = "Overview";
        NAMES[PROCESSORS] = "Processors";
        NAMES[BATTERY] = "Battery";
        NAMES[SENSORS] = "Sensors";
        NAMES[RAM] = "RAM";
        NAMES[STORAGE] = "Storage";
        NAMES[DISPLAY] = "Display";
        NAMES[GRAPHICS] = "Graphics";
        NAMES[CAMERAS] = "Cameras";
        NAMES[AUDIO] = "Audio";
        NAMES[LOCATION] = "Location";
        NAMES[CELLULAR] = "Cellular";
        NAMES[WIFI] = "WiFi";
        NAMES[NETWORK] = "Network";
        NAMES[BLUETOOTH] = "Bluetooth";
        NAMES[UPTIME] = "Uptime";
        NAMES[PLATFORM] = "Platform";
        NAMES[IDENTIFIERS] = "Identifiers";
        NAMES[FEATURES] = "Features";
        NAMES[PROPERTIES] = "Properties";
        NAMES[KEYS] = "Keys";
    }
}
