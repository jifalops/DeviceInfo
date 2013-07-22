package com.deviceinfoapp.controller;

import android.content.Context;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;

import com.deviceinfoapp.element.Cellular;
import com.deviceinfoapp.item.Item;
import com.deviceinfoapp.item.Item2;

import java.util.ArrayList;
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
    protected void update(int action) {

    }

    @Override
    public List<Item> getData() {
        List<Item> data = new ArrayList<Item>();

        Cellular e = (Cellular) mElement;

        // Cellular info
        data.add(new Item2("MCC", String.valueOf(e.getMcc())));
        data.add(new Item2("MNC", String.valueOf(e.getMnc())));
        data.add(new Item2("Radio Version", e.getRadioVersion()));
        data.add(new Item2("Baseband", e.getBaseband()));
        data.add(new Item2("RIL Version", e.getRilVersion()));
        data.add(new Item2("RIL Barcode", e.getRilBarcode()));

        // TelephonyManager info
        data.add(new Item2("Call State", e.getCallState()));
        data.add(new Item2("Data Activity", e.getDataActivity()));
        data.add(new Item2("Data State", e.getDataState()));
        data.add(new Item2("Device ID", e.getTelephonyManager().getDeviceId()));
        data.add(new Item2("Device Software Version", e.getTelephonyManager().getDeviceSoftwareVersion()));
        data.add(new Item2("Line 1 Number", e.getTelephonyManager().getLine1Number()));
        data.add(new Item2("Network Country ISO", e.getTelephonyManager().getNetworkCountryIso()));
        data.add(new Item2("Network Operator", e.getTelephonyManager().getNetworkOperator()));
        data.add(new Item2("Network Operator Name", e.getTelephonyManager().getNetworkOperatorName()));
        data.add(new Item2("Network Type", e.getNetworkType()));
        data.add(new Item2("Phone Type", e.getPhoneType()));
        data.add(new Item2("SIM Country ISO", e.getTelephonyManager().getSimCountryIso()));
        data.add(new Item2("SIM Operator", e.getTelephonyManager().getSimOperator()));
        data.add(new Item2("SIM Operator Name", e.getTelephonyManager().getSimOperatorName()));
        data.add(new Item2("SIM Serial Number", e.getTelephonyManager().getSimSerialNumber()));
        data.add(new Item2("SIM State", e.getSimState()));
        data.add(new Item2("Subscriber ID", e.getTelephonyManager().getSubscriberId()));
        data.add(new Item2("Voice Mail Alpha Tag", e.getTelephonyManager().getVoiceMailAlphaTag()));
        data.add(new Item2("Voice Mail Number", e.getTelephonyManager().getVoiceMailNumber()));
        data.add(new Item2("Has ICC Card", String.valueOf(e.getTelephonyManager().hasIccCard())));
        data.add(new Item2("Is Network Roaming", String.valueOf(e.getTelephonyManager().isNetworkRoaming())));

        // CellLocation info
        if (e.getCellLocation() != null) {
            if (e.getTelephonyManager().getPhoneType() == TelephonyManager.PHONE_TYPE_CDMA) {
                CdmaCellLocation loc = (CdmaCellLocation) e.getCellLocation();
                data.add(new Item2("Base Station ID", String.valueOf(loc.getBaseStationId())));
                data.add(new Item2("Base Station Latitude", String.valueOf(loc.getBaseStationLatitude())));
                data.add(new Item2("Base Station Longitude", String.valueOf(loc.getBaseStationLongitude())));
                data.add(new Item2("Network ID", String.valueOf(loc.getNetworkId())));
                data.add(new Item2("System ID", String.valueOf(loc.getSystemId())));
            }
            else if (e.getTelephonyManager().getPhoneType() == TelephonyManager.PHONE_TYPE_GSM) {
                GsmCellLocation loc = (GsmCellLocation) e.getTelephonyManager().getCellLocation();
                data.add(new Item2("Cell ID", String.valueOf(loc.getCid())));
                data.add(new Item2("Location Area Code", String.valueOf(loc.getLac())));
                data.add(new Item2("Primary Scrambling Code", String.valueOf(loc.getPsc())));
            }
        }
        else data.add(new Item2("CellLocation", null));

        // NeighboringCellInfo info
        List<NeighboringCellInfo> cells = e.getTelephonyManager().getNeighboringCellInfo();
        if (cells != null) {
            int i = 0;
            for (NeighboringCellInfo info : cells) {
                data.add(new Item2("NeighboringCellInfo " + i + " Cell ID", String.valueOf(info.getCid())));
                data.add(new Item2("NeighboringCellInfo " + i + " Location Area Code", String.valueOf(info.getLac())));
                data.add(new Item2("NeighboringCellInfo " + i + " Network Type", String.valueOf(info.getNetworkType())));
                data.add(new Item2("NeighboringCellInfo " + i + " Primary Scrambling Code", String.valueOf(info.getPsc())));
                data.add(new Item2("NeighboringCellInfo " + i + " Received Signal Strength Indication", String.valueOf(info.getRssi())));
                ++i;
            }
        }
        else data.add(new Item2("NeighboringCellInfo", null));

        // ServiceState info
        data.add(new Item2("Is Manual Selection", String.valueOf(e.getServiceState().getIsManualSelection())));
        data.add(new Item2("Operator Alpha Long", e.getServiceState().getOperatorAlphaLong()));
        data.add(new Item2("Operator Alpha Short", e.getServiceState().getOperatorAlphaShort()));
        data.add(new Item2("Operator Numeric", e.getServiceState().getOperatorNumeric()));
        data.add(new Item2("Is Roaming", String.valueOf(e.getServiceState().getRoaming())));
        data.add(new Item2("Service State", e.getServiceStateString()));

        // SignalStrength info
        if (e.getSignalStrength() != null) {
            data.add(new Item2("CDMA dBm", String.valueOf(e.getSignalStrength().getCdmaDbm())));
            data.add(new Item2("CDMA Ec/Io", String.valueOf(e.getSignalStrength().getCdmaEcio())));
            data.add(new Item2("EVDO dBm", String.valueOf(e.getSignalStrength().getEvdoDbm())));
            data.add(new Item2("EVDO Ec/Io", String.valueOf(e.getSignalStrength().getEvdoEcio())));
            data.add(new Item2("EVDO Signal to Noise Ratio", String.valueOf(e.getSignalStrength().getEvdoSnr())));
            data.add(new Item2("GSM Bit Error Rate", String.valueOf(e.getSignalStrength().getGsmBitErrorRate())));
            data.add(new Item2("GSM Signal Strength", String.valueOf(e.getSignalStrength().getGsmSignalStrength())));
            data.add(new Item2("Is GSM", String.valueOf(e.getSignalStrength().isGsm())));
        }
        else data.add(new Item2("SignalStrength", null));

        return data;
    }

    @Override
    public void start() {
        ((Cellular) mElement).start();
    }

    @Override
    public void stop() {
        ((Cellular) mElement).stop();
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
