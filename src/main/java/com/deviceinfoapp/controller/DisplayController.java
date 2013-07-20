package com.deviceinfoapp.controller;

import android.content.Context;
import android.text.TextUtils;

import com.deviceinfoapp.element.Audio;
import com.deviceinfoapp.element.Display;
import com.deviceinfoapp.viewable.Item;
import com.deviceinfoapp.viewable.Item2;

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

        data.add(new Item2("Density DPI String", e.getDensityDpiString()));
        data.add(new Item2("Density DPI", String.valueOf(e.getDensityDpi())));
        data.add(new Item2("X DPI", String.valueOf(e.getXDpi())));
        data.add(new Item2("Y DPI", String.valueOf(e.getYDpi())));
        data.add(new Item2("Logical Density", String.valueOf(e.getLogicalDensity())));
        data.add(new Item2("Scaled Density", String.valueOf(e.getScaledDensity())));
        data.add(new Item2("Font Scale", String.valueOf(e.getFontScale(e.getContext()))));
        data.add(new Item2("Width (pixel)", String.valueOf(e.getWidth())));
        data.add(new Item2("Height (pixel)", String.valueOf(e.getHeight())));
        data.add(new Item2("Diagonal (pixel)", String.valueOf(e.getDiagonal())));
        data.add(new Item2("Width (inch)", String.valueOf(e.getWidthInches())));
        data.add(new Item2("Height (inch)", String.valueOf(e.getHeightInches())));
        data.add(new Item2("Diagonal (inch)", String.valueOf(e.getDiagonalInches())));
        data.add(new Item2("Refresh Rate", String.valueOf(e.getRefreshRate())));
        data.add(new Item2("Rotation (degrees)", String.valueOf(e.getRotationDegrees())));
        data.add(new Item2("Pixel Format", e.getPixelFormatString()));
        data.add(new Item2("Is Touch Screen", String.valueOf(e.isTouchScreen(e.getContext()))));
        data.add(new Item2("Max Simultaneous Touch", String.valueOf(e.getMaxSimultaneousTouch())));
        data.add(new Item2("Screen Size String", e.getScreenSizeString(e.getContext())));
        data.add(new Item2("Is Screen Long", String.valueOf(e.isScreenLong(e.getContext()))));
        data.add(new Item2("Orientation String", e.getOrientationString(e.getContext())));
        data.add(new Item2("Screen Height DP", String.valueOf(e.getScreenHeightDp(e.getContext()))));
        data.add(new Item2("Screen Width DP", String.valueOf(e.getScreenWidthDp(e.getContext()))));
        data.add(new Item2("Smallest Screen Width DP", String.valueOf(e.getSmallestScreenWidthDp(mContext))));

        return data;
    }
}
