package com.deviceinfoapp.controller;

import android.content.Context;
import android.content.Intent;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;

import com.deviceinfoapp.element.ActiveElement;
import com.deviceinfoapp.element.Battery;
import com.deviceinfoapp.element.Cellular;
import com.deviceinfoapp.viewable.Item;
import com.deviceinfoapp.viewable.Item2;

import java.util.List;

/**
 * Created by Jake on 7/18/13.
 */
public class CellularController extends ActiveElementController implements Cellular.Callbacks {

    /** Methods correspond to PhoneStateListener methods */
    public interface Callbacks extends ActiveElementController.Callbacks {
        void onCallForwardingIndicatorChanged(boolean cfi);
        void onCallStateChanged(int state, String incomingNumber);
        void onCellLocationChanged(CellLocation location);
        void onDataActivity(int direction);
        void onDataConnectionStateChanged(int state, int networkType);
        void onMessageWaitingIndicatorChanged(boolean mwi);
        void onServiceStateChanged(ServiceState serviceState);
        void onSignalStrengthsChanged(SignalStrength signalStrength);
    }

    public CellularController(Context context, CellularController.Callbacks callbacks) {
        super(context, callbacks);
        mElement = new Cellular(context, this);
    }

    @Override
    public List<Item> getData() {
        Cellular e = (Cellular) mElement;
        int[] actions = new int[] {Cellular.ACTION_CELL_LOCATION};
        mData.clear();

        // Cellular info
        mData.add(new Item2("MCC", String.valueOf(e.getMcc())));
        mData.add(new Item2("MNC", String.valueOf(e.getMnc())));
        mData.add(new Item2("Radio Version", e.getRadioVersion()));
        mData.add(new Item2("Baseband", e.getBaseband()));
        mData.add(new Item2("RIL Version", e.getRilVersion()));
        mData.add(new Item2("RIL Barcode", e.getRilBarcode()));

        // TelephonyManager info
        mData.add(new Item2("Call State", e.getCallState()));
        mData.add(new Item2("Data Activity", e.getDataActivity()));
        mData.add(new Item2("Data State", e.getDataState()));
        mData.add(new Item2("Device ID", e.getTelephonyManager().getDeviceId()));
        mData.add(new Item2("Device Software Version", e.getTelephonyManager().getDeviceSoftwareVersion()));
        mData.add(new Item2("Line 1 Number", e.getTelephonyManager().getLine1Number()));
        mData.add(new Item2("Network Country ISO", e.getTelephonyManager().getNetworkCountryIso()));
        mData.add(new Item2("Network Operator", e.getTelephonyManager().getNetworkOperator()));
        mData.add(new Item2("Network Operator Name", e.getTelephonyManager().getNetworkOperatorName()));
        mData.add(new Item2("Network Type", e.getNetworkType()));
        mData.add(new Item2("Phone Type", e.getPhoneType()));
        mData.add(new Item2("SIM Country ISO", e.getTelephonyManager().getSimCountryIso()));
        mData.add(new Item2("SIM Operator", e.getTelephonyManager().getSimOperator()));
        mData.add(new Item2("SIM Operator Name", e.getTelephonyManager().getSimOperatorName()));
        mData.add(new Item2("SIM Serial Number", e.getTelephonyManager().getSimSerialNumber()));
        mData.add(new Item2("SIM State", e.getSimState()));
        mData.add(new Item2("Subscriber ID", e.getTelephonyManager().getSubscriberId()));
        mData.add(new Item2("Voice Mail Alpha Tag", e.getTelephonyManager().getVoiceMailAlphaTag()));
        mData.add(new Item2("Voice Mail Number", e.getTelephonyManager().getVoiceMailNumber()));
        mData.add(new Item2("Has ICC Card", String.valueOf(e.getTelephonyManager().hasIccCard())));
        mData.add(new Item2("Is Network Roaming", String.valueOf(e.getTelephonyManager().isNetworkRoaming())));

        // CellLocation info
        if (e.getCellLocation() != null) {
            if (e.getTelephonyManager().getPhoneType() == TelephonyManager.PHONE_TYPE_CDMA) {
                CdmaCellLocation loc = (CdmaCellLocation) e.getCellLocation();
                mData.add(new Item2("Base Station ID", String.valueOf(loc.getBaseStationId())));
                mData.add(new Item2("Base Station Latitude", String.valueOf(loc.getBaseStationLatitude())));
                mData.add(new Item2("Base Station Longitude", String.valueOf(loc.getBaseStationLongitude())));
                mData.add(new Item2("Network ID", String.valueOf(loc.getNetworkId())));
                mData.add(new Item2("System ID", String.valueOf(loc.getSystemId())));
            }
            else if (e.getTelephonyManager().getPhoneType() == TelephonyManager.PHONE_TYPE_GSM) {
                GsmCellLocation loc = (GsmCellLocation) e.getTelephonyManager().getCellLocation();
                mData.add(new Item2("Cell ID", String.valueOf(loc.getCid())));
                mData.add(new Item2("Location Area Code", String.valueOf(loc.getLac())));
                mData.add(new Item2("Primary Scrambling Code", String.valueOf(loc.getPsc())));
            }
        }
        else mData.add(new Item2("CellLocation", null));

        // NeighboringCellInfo info
        List<NeighboringCellInfo> cells = e.getTelephonyManager().getNeighboringCellInfo();
        if (cells != null) {
            int i = 0;
            for (NeighboringCellInfo info : cells) {
                mData.add(new Item2("NeighboringCellInfo " + i + " Cell ID", String.valueOf(info.getCid())));
                mData.add(new Item2("NeighboringCellInfo " + i + " Location Area Code", String.valueOf(info.getLac())));
                mData.add(new Item2("NeighboringCellInfo " + i + " Network Type", String.valueOf(info.getNetworkType())));
                mData.add(new Item2("NeighboringCellInfo " + i + " Primary Scrambling Code", String.valueOf(info.getPsc())));
                mData.add(new Item2("NeighboringCellInfo " + i + " Received Signal Strength Indication", String.valueOf(info.getRssi())));
                ++i;
            }
        }
        else mData.add(new Item2("NeighboringCellInfo", null));

        // ServiceState info
        mData.add(new Item2("Is Manual Selection", String.valueOf(e.getServiceState().getIsManualSelection())));
        mData.add(new Item2("Operator Alpha Long", e.getServiceState().getOperatorAlphaLong()));
        mData.add(new Item2("Operator Alpha Short", e.getServiceState().getOperatorAlphaShort()));
        mData.add(new Item2("Operator Numeric", e.getServiceState().getOperatorNumeric()));
        mData.add(new Item2("Is Roaming", String.valueOf(e.getServiceState().getRoaming())));
        mData.add(new Item2("Service State", e.getServiceStateString()));

        // SignalStrength info
        if (e.getSignalStrength() != null) {
            mData.add(new Item2("CDMA dBm", String.valueOf(e.getSignalStrength().getCdmaDbm())));
            mData.add(new Item2("CDMA Ec/Io", String.valueOf(e.getSignalStrength().getCdmaEcio())));
            mData.add(new Item2("EVDO dBm", String.valueOf(e.getSignalStrength().getEvdoDbm())));
            mData.add(new Item2("EVDO Ec/Io", String.valueOf(e.getSignalStrength().getEvdoEcio())));
            mData.add(new Item2("EVDO Signal to Noise Ratio", String.valueOf(e.getSignalStrength().getEvdoSnr())));
            mData.add(new Item2("GSM Bit Error Rate", String.valueOf(e.getSignalStrength().getGsmBitErrorRate())));
            mData.add(new Item2("GSM Signal Strength", String.valueOf(e.getSignalStrength().getGsmSignalStrength())));
            mData.add(new Item2("Is GSM", String.valueOf(e.getSignalStrength().isGsm())));
        }
        else mData.add(new Item2("SignalStrength", null));

        return mData;
    }

    @Override
    public void onCallForwardingIndicatorChanged(boolean cfi) {
        ((Callbacks) mCallbacks).onCallForwardingIndicatorChanged(cfi);
    }

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        ((Callbacks) mCallbacks).onCallStateChanged(state, incomingNumber);
    }

    @Override
    public void onCellLocationChanged(CellLocation location) {
        ((Callbacks) mCallbacks).onCellLocationChanged(location);
    }

    @Override
    public void onDataActivity(int direction) {
        ((Callbacks) mCallbacks).onDataActivity(direction);
    }

    @Override
    public void onDataConnectionStateChanged(int state, int networkType) {
        ((Callbacks) mCallbacks).onDataConnectionStateChanged(state, networkType);
    }

    @Override
    public void onMessageWaitingIndicatorChanged(boolean mwi) {
        ((Callbacks) mCallbacks).onMessageWaitingIndicatorChanged(mwi);
    }

    @Override
    public void onServiceStateChanged(ServiceState serviceState) {
        ((Callbacks) mCallbacks).onServiceStateChanged(serviceState);
    }

    @Override
    public void onSignalStrengthsChanged(SignalStrength signalStrength) {
        ((Callbacks) mCallbacks).onSignalStrengthsChanged(signalStrength);
    }
}
