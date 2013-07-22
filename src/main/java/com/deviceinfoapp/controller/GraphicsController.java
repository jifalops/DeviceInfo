package com.deviceinfoapp.controller;

import android.content.Context;

import com.deviceinfoapp.element.Graphics;
import com.deviceinfoapp.viewable.Item;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Jake on 7/18/13.
 */
public class GraphicsController extends ActiveElementController implements Graphics.Callbacks {

    public interface Callbacks extends ActiveElementController.Callbacks {
        /** Corresponds to GLSurfaceView.Renderer.onSurfaceCreated(); */
        void onSurfaceCreated(GL10 gl, EGLConfig config);
        /** Corresponds to GLSurfaceView.Renderer.onSurfaceChanged(); */
        void onSurfaceChanged(GL10 gl, int width, int height);
        /** Corresponds to GLSurfaceView.Renderer.onDrawFrame(); */
        void onDrawFrame(GL10 gl);
    }

    public GraphicsController(Context context, GraphicsController.Callbacks callbacks) {
        super(context, callbacks);
        mElement = new Graphics(context, this);
    }

    @Override
    protected void update(int action) {

    }

    @Override
    public List<Item> getData() {
        List<Item> data = new ArrayList<Item>();

        Graphics e = (Graphics) mElement;



        // Main
//        @Override
//        public LinkedHashMap<String, String> getContents() {
//            LinkedHashMap<String, String> contents = super.getContents();
//            contents.put("OpenGL ES Version", String.valueOf(getOpenGlesVersion()));
//            if (mOpenGles != null) contents.putAll(mOpenGles.getContents());
//            return contents;
//        }


        // OpenGles
//        @Override
//        public LinkedHashMap<String, String> getContents() {
//            LinkedHashMap<String, String> contents = new LinkedHashMap<String, String>();
//
//            contents.put("Version", String.valueOf(getOpenGlesVersion()));
//            contents.put("Renderer", getRenderer());
//            contents.put("Version", getVersion());
//            contents.put("Vendor", getVendor());
//            contents.put("MaxTextureSize", String.valueOf(getMaxTextureSize()));
//            String[] extensions = getExtensions();
//            if (extensions != null) {
//                for (int i = 0; i < extensions.length; ++i) {
//                    contents.put("Extension " + i, extensions[i]);
//                }
//            }
//            return contents;
//        }

        // OpenGles10
//        @Override
//        public LinkedHashMap<String, String> getContents() {
//            LinkedHashMap<String, String> contents = super.getContents();
//            contents.put("MaxTextureUnits", String.valueOf(getMaxTextureUnits()));
//            contents.put("MaxTextureStackDepth", String.valueOf(getMaxTextureStackDepth()));
//            return contents;
//        }

        // OpenGles20
//        @Override
//        public LinkedHashMap<String, String> getContents() {
//            LinkedHashMap<String, String> contents = super.getContents();
//            contents.put("MaxTextureImageUnits", String.valueOf(getMaxTextureImageUnits()));
//            contents.put("MaxRenderBufferSize", String.valueOf(getMaxRenderBufferSize()));
//            return contents;
//        }


        return data;
    }

    @Override
    public void start() {
        ((Graphics) mElement).start();
    }

    @Override
    public void stop() {
        ((Graphics) mElement).stop();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        ((Callbacks) mCallbacks).onSurfaceCreated(gl, config);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        ((Callbacks) mCallbacks).onSurfaceChanged(gl, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        ((Callbacks) mCallbacks).onDrawFrame(gl);
    }
}
