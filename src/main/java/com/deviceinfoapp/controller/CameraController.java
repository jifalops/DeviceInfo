package com.deviceinfoapp.controller;

import android.content.Context;

import com.deviceinfoapp.element.Camera;
import com.deviceinfoapp.item.Item;
import com.deviceinfoapp.item.Item2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Jake on 7/18/13.
 */
public class CameraController extends AbsElementController {

    public CameraController(Context context) {
        super(context);
        mElement = new Camera(context);
    }

    @Override
    public List<Item> getData() {
        Camera cam = (Camera) mElement;

        List<Item> data = new ArrayList<Item>();
        data.add(new Item2("Number of Cameras", String.valueOf(cam.getNumCameras())));

        List<Camera.CameraWrapper> cameras = cam.getCameras();
        LinkedHashMap<String, String> params;
        int paramIndex = 0;
        for (int i = 0; i < cameras.size(); ++i) {
            if (API >= 9) {
                data.add(new Item2("Camera " + i + " Direction", cameras.get(i).getCameraDirection()));
                data.add(new Item2("Camera " + i + " Orientation (Degrees)", String.valueOf(cameras.get(i).getCameraOrientation())));
            }

            params = cameras.get(i).getCameraParametersMap();
            if (params != null) {
                for (String key : params.keySet()) {
                    data.add(new Item2("Camera " + i + " Parameter " + paramIndex, key + " = " + params.get(key)));
                    ++paramIndex;
                }
            }
        }
        return data;
    }
}
