package com.deviceinfoapp.controller;

import android.content.Context;

import com.deviceinfoapp.element.Display;
import com.deviceinfoapp.item.Item;
import com.deviceinfoapp.item.ListItem2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jake on 7/18/13.
 */
public class DisplayController extends AbsElementController {

    public DisplayController(Context context) {
        super(context);
        mElement = new Display(context);
    }

    @Override
    public List<Item> getData() {
        Display e = (Display) mElement;

        List<Item> data = new ArrayList<Item>();

        data.add(new ListItem2("Density DPI String", e.getDensityDpiString()));
        data.add(new ListItem2("Density DPI", String.valueOf(e.getDensityDpi())));
        data.add(new ListItem2("X DPI", String.valueOf(e.getXDpi())));
        data.add(new ListItem2("Y DPI", String.valueOf(e.getYDpi())));
        data.add(new ListItem2("Logical Density", String.valueOf(e.getLogicalDensity())));
        data.add(new ListItem2("Scaled Density", String.valueOf(e.getScaledDensity())));
        data.add(new ListItem2("Font Scale", String.valueOf(e.getFontScale(mContext))));
        data.add(new ListItem2("Width (pixel)", String.valueOf(e.getWidth())));
        data.add(new ListItem2("Height (pixel)", String.valueOf(e.getHeight())));
        data.add(new ListItem2("Diagonal (pixel)", String.valueOf(e.getDiagonal())));
        data.add(new ListItem2("Width (inch)", String.valueOf(e.getWidthInches())));
        data.add(new ListItem2("Height (inch)", String.valueOf(e.getHeightInches())));
        data.add(new ListItem2("Diagonal (inch)", String.valueOf(e.getDiagonalInches())));
        data.add(new ListItem2("Refresh Rate", String.valueOf(e.getRefreshRate())));
        data.add(new ListItem2("Rotation (degrees)", String.valueOf(e.getRotationDegrees())));
        data.add(new ListItem2("Pixel Format", e.getPixelFormatString()));
        data.add(new ListItem2("Is Touch Screen", String.valueOf(e.isTouchScreen(mContext))));
        data.add(new ListItem2("Max Simultaneous Touch", String.valueOf(e.getMaxSimultaneousTouch())));
        data.add(new ListItem2("Screen Size String", e.getScreenSizeString(mContext)));
        data.add(new ListItem2("Is Screen Long", String.valueOf(e.isScreenLong(mContext))));
        data.add(new ListItem2("Orientation String", e.getOrientationString(mContext)));
        data.add(new ListItem2("Screen Height DP", String.valueOf(e.getScreenHeightDp(mContext))));
        data.add(new ListItem2("Screen Width DP", String.valueOf(e.getScreenWidthDp(mContext))));
        data.add(new ListItem2("Smallest Screen Width DP", String.valueOf(e.getSmallestScreenWidthDp(mContext))));

        return data;
    }
}
