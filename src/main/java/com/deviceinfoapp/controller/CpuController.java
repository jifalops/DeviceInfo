package com.deviceinfoapp.controller;

import android.content.Context;

import com.deviceinfoapp.element.Cpu;
import com.deviceinfoapp.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jake on 7/18/13.
 */
public class CpuController extends ActiveElementController implements Cpu.Callbacks {

    public interface Callbacks extends ActiveElementController.Callbacks {
        void onCpuUpdated(int numCpuStatsUpdated);
    }

    public CpuController(Context context, CpuController.Callbacks callbacks) {
        super(context, callbacks);
        mElement = new Cpu(context, this);
    }

    @Override
    protected void update(int action) {

    }

    @Override
    public List<Item> getData() {
        List<Item> data = new ArrayList<Item>();

        Cpu e = (Cpu) mElement;



        // Main TODO started to fix main, but want to use the setup from old app (elementview package)
//        for (int i = 0; i < e.getCpuinfo().size(); ++i) {
//            mData.add(new Item2("CPU Info " + i, e.getCpuinfo().get(i)));
//        }
//
//        subcontents = mCpuStat.getContents();
//        for (String s : subcontents.keySet()) {
//            mData.add(new Item2("Overall CpuStat " + s, subcontents.get(s)));
//        }
//
//        int i = 0;
//        for (LogicalCpu logicalCpu : mLogicalCpus) {
//            subcontents = logicalCpu.getContents();
//            for (String s : subcontents.keySet()) {
//                mData.add(new Item2("Logical CPU " + i + " " + s, subcontents.get(s)));
//            }
//            ++i;
//        }


        // LogicalCpu
//        @Override
//        public LinkedHashMap<String, String> getContents() {
//            LinkedHashMap<String, String> contents = new LinkedHashMap<String, String>();
//            LinkedHashMap<String, String> subcontents;
//
//            contents.put("ID", String.valueOf(getId()));
//            contents.put("Root", getRoot().getAbsolutePath());
//            contents.put("Frequency (MHz)", String.valueOf(getFrequency()));
//            contents.put("MinFrequency (MHz)", String.valueOf(getMinFrequency()));
//            contents.put("MaxFrequency (MHz)", String.valueOf(getMaxFrequency()));
//
//            int[] freqs = getAvailableFrequencies();
//            if (freqs != null) {
//                for (int i = 0; i < freqs.length; ++i) {
//                    contents.put("AvailableFrequency " + i + " (MHz)",
//                            String.valueOf(freqs[i]));
//                }
//            }
//
//            String[] govs = getAvailableGovernors();
//            if (govs != null) {
//                for (int i = 0; i < govs.length; ++i) {
//                    contents.put("AvailableGovernor " + i, govs[i]);
//                }
//            }
//
//            contents.put("Governor", getGovernor());
//            contents.put("Driver", getDriver());
//            contents.put("TransitionLatency (ns)", String.valueOf(getTransitionLatency()));
//            contents.put("TotalTransitions", String.valueOf(getTotalTransitions()));
//            contents.put("TimeInTransitions (s)", String.valueOf(getTimeInTransitions()));
//            contents.put("TimeInTransitions (s)", String.valueOf(getTimeInTransitions()));
//
//            int[][] times = getTimeInFrequency();
//            if (times != null) {
//                for (int i = 0; i < times.length; ++i) {
//                    contents.put("TimeInFrequency " + times[i][0] + "MHz (Jiffies (10ms))",
//                            String.valueOf(times[i][1]));
//                }
//            }
//
//            Map<Integer, Float> percents = getPercentInFrequency();
//            if (percents != null && percents.size() > 0) {
//                for (int freq : percents.keySet()) {
//                    contents.put("PercentInFrequency " + freq + "MHz",
//                            String.valueOf(percents.get(freq)));
//                }
//            }
//
//            subcontents = getCpuStat().getContents();
//            for (Map.Entry<String, String> e : subcontents.entrySet()) {
//                contents.put("CpuStat " + e.getKey(), e.getValue());
//            }
//
//            return contents;
//        }


        // CpuStat
//        @Override
//        public LinkedHashMap<String, String> getContents() {
//            LinkedHashMap<String, String> contents = new LinkedHashMap<String, String>();
//
//            contents.put("ID", String.valueOf(getId()));
//            contents.put("Timestamp", String.valueOf(getTimestamp()));
//            contents.put("TimestampPrevious", String.valueOf(getTimestampPrevious()));
//            contents.put("TimestampDifference", String.valueOf(getTimestampDifference()));
//            contents.put("User", String.valueOf(getUser()));
//            contents.put("UserPrevious", String.valueOf(getUserPrevious()));
//            contents.put("UserDifference", String.valueOf(getUserDifference()));
//            contents.put("UserPercent", String.valueOf(getUserPercent()));
//            contents.put("Nice", String.valueOf(getNice()));
//            contents.put("NicePrevious", String.valueOf(getNicePrevious()));
//            contents.put("NiceDifference", String.valueOf(getNiceDifference()));
//            contents.put("NicePercent", String.valueOf(getNicePercent()));
//            contents.put("System", String.valueOf(getSystem()));
//            contents.put("SystemPrevious", String.valueOf(getSystemPrevious()));
//            contents.put("SystemDifference", String.valueOf(getSystemDifference()));
//            contents.put("SystemPercent", String.valueOf(getSystemPercent()));
//            contents.put("Idle", String.valueOf(getIdle()));
//            contents.put("IdlePrevious", String.valueOf(getIdlePrevious()));
//            contents.put("IdleDifference", String.valueOf(getIdleDifference()));
//            contents.put("IdlePercent", String.valueOf(getIdlePercent()));
//            contents.put("IoWait", String.valueOf(getIoWait()));
//            contents.put("IoWaitPrevious", String.valueOf(getIoWaitPrevious()));
//            contents.put("IoWaitDifference", String.valueOf(getIoWaitDifference()));
//            contents.put("IoWaitPercent", String.valueOf(getIoWaitPercent()));
//            contents.put("Intr", String.valueOf(getIntr()));
//            contents.put("IntrPrevious", String.valueOf(getIntrPrevious()));
//            contents.put("IntrDifference", String.valueOf(getIntrDifference()));
//            contents.put("IntrPercent", String.valueOf(getIntrPercent()));
//            contents.put("SoftIrq", String.valueOf(getSoftIrq()));
//            contents.put("SoftIrqPrevious", String.valueOf(getSoftIrqPrevious()));
//            contents.put("SoftIrqDifference", String.valueOf(getSoftIrqDifference()));
//            contents.put("SoftIrqPercent", String.valueOf(getSoftIrqPercent()));
//            contents.put("UserTotal", String.valueOf(getUserTotal()));
//            contents.put("UserTotalPrevious", String.valueOf(getUserTotalPrevious()));
//            contents.put("UserTotalDifference", String.valueOf(getUserTotalDifference()));
//            contents.put("UserTotalPercent", String.valueOf(getUserTotalPercent()));
//            contents.put("SystemTotal", String.valueOf(getSystemTotal()));
//            contents.put("SystemTotalPrevious", String.valueOf(getSystemTotalPrevious()));
//            contents.put("SystemTotalDifference", String.valueOf(getSystemTotalDifference()));
//            contents.put("SystemTotalPercent", String.valueOf(getSystemTotalPercent()));
//            contents.put("IdleTotal", String.valueOf(getIdleTotal()));
//            contents.put("IdleTotalPrevious", String.valueOf(getIdleTotalPrevious()));
//            contents.put("IdleTotalDifference", String.valueOf(getIdleTotalDifference()));
//            contents.put("IdleTotalPercent", String.valueOf(getIdleTotalPercent()));
//            contents.put("Total", String.valueOf(getTotal()));
//            contents.put("TotalPrevious", String.valueOf(getTotalPrevious()));
//            contents.put("TotalDifference", String.valueOf(getTotalDifference()));
//            contents.put("TotalPercent", String.valueOf(getTotalPercent()));
//
//            return contents;
//        }

        return data;
    }

    @Override
    public void start() {
        ((Cpu) mElement).start();
    }

    @Override
    public void stop() {
        ((Cpu) mElement).stop();
    }

    @Override
    public void onCpuUpdated(int numCpuStatsUpdated) {
        ((Callbacks) mCallbacks).onCpuUpdated(numCpuStatsUpdated);
    }
}
