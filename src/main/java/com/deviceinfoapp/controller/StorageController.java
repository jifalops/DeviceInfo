package com.deviceinfoapp.controller;

import android.content.Context;

import com.deviceinfoapp.element.Storage;
import com.deviceinfoapp.viewable.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jake on 7/18/13.
 */
public class StorageController extends AbsElementController {

    public StorageController(Context context) {
        super(context);
        mElement = new Storage(context);
    }

    @Override
    public List<Item> getData() {
        Storage e = (Storage) mElement;

        List<Item> data = new ArrayList<Item>();

        // Main
        // TODO ui facing strings
//        @Override
//        public LinkedHashMap<String, String> getContents() {
//            LinkedHashMap<String, String> contents = new LinkedHashMap<String, String>();
//
//            // Interesting mounts
//            contents.put("System Mount index", String.valueOf(mMounts.indexOf(getSystemMount())));
//            contents.put("Data Mount index", String.valueOf(mMounts.indexOf(getDataMount())));
//            contents.put("Cache Mount index", String.valueOf(mMounts.indexOf(getCacheMount())));
//            contents.put("Root Mount index", String.valueOf(mMounts.indexOf(getRootMount())));
//            List<Mount> sdMounts = getSdcardMounts();
//            for (int i = 0; i < sdMounts.size(); ++i) {
//                contents.put("SD card Mount " + i + " index",
//                        String.valueOf(mMounts.indexOf(sdMounts.get(i))));
//            }
//
//            // All mounts
//            LinkedHashMap<String, String> subcontents;
//            for (int i = 0, len = mMounts.size(); i < len; ++i) {
//                subcontents = mMounts.get(i).getContents();
//                for (String s : subcontents.keySet()) {
//                    contents.put("Mount " + i + " " + s, subcontents.get(s));
//                }
//            }
//
//            // Interesting partitions
//            contents.put("Boot Partition index", String.valueOf(mPartitions.indexOf(getBootPartition())));
//            contents.put("Recovery Partition index", String.valueOf(mPartitions.indexOf(getRecoveryPartition())));
//
//            // All partitions
//            for (int i = 0, len = mPartitions.size(); i < len; ++i) {
//                subcontents = mPartitions.get(i).getContents();
//                for (String s : subcontents.keySet()) {
//                    contents.put("Partition " + i + " " + s, subcontents.get(s));
//                }
//            }
//
//            // Aliased partitions
//            List<Partition> aliased = getAliasedPartitions();
//            for (int i = 0, len = aliased.size(); i < len; ++i) {
//                contents.put("Aliased Partition " + i + " index",
//                        String.valueOf(mPartitions.indexOf(aliased.get(i)))
//                                + " (" + aliased.get(i).getAlias() + ")");
//            }
//
//            return contents;
//        }

        // Partition
//        @Override
//        public LinkedHashMap<String, String> getContents() {
//            LinkedHashMap<String, String> contents = new LinkedHashMap<String, String>();
//
//            contents.put("Alias", getAlias());
//            contents.put("Name", getName());
//            contents.put("Num Blocks", String.valueOf(getNumBlocks()));
//            contents.put("Block Size", String.valueOf(getBlockSize()));
//            contents.put("Total Size", String.valueOf(getTotalSize()));
//            contents.put("Device", getDevice());
//            contents.put("Device Major", String.valueOf(getDeviceMajor()));
//            contents.put("Device Minor", String.valueOf(getDeviceMinor()));
//
//            return contents;
//        }

        // Mount
//        @Override
//        public LinkedHashMap<String, String> getContents() {
//            LinkedHashMap<String, String> contents = new LinkedHashMap<String, String>();
//
//            contents.put("Device", getDevice());
//            contents.put("MountPoint", getMountPoint());
//            contents.put("FileSystem", getFileSystem());
//            contents.put("Attributes", getAttributesString());
//            contents.put("BlockSize", String.valueOf(getBlockSize()));
//            contents.put("BlockCount", String.valueOf(getBlockCount()));
//            contents.put("TotalSize", String.valueOf(getTotalSize()));
//            contents.put("FreeSpace", String.valueOf(getFreeSpace()));
//            contents.put("AvailableSpace", String.valueOf(getAvailableSpace()));
//
//            return contents;
//        }

        return data;
    }
}
