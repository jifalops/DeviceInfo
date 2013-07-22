package com.deviceinfoapp.controller;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;

import com.deviceinfoapp.element.Wifi;
import com.deviceinfoapp.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jake on 7/18/13.
 */
public class WifiController extends ActiveElementController implements Wifi.Callbacks {

    public interface Callbacks extends ActiveElementController.Callbacks {
        void onScanCompleted(List<ScanResult> results);
        void onNetworkIdsChanged(List<WifiConfiguration> configurations);
        void onNetworkStateChanged(NetworkInfo networkInfo, String bssid, WifiInfo wifiInfo);
        void onRssiChanged(int rssi);
        void onSupplicantConnectionChanged(boolean connected);
        void onSupplicantStateChanged(SupplicantState state, int error);
    }

    public WifiController(Context context, WifiController.Callbacks callbacks) {
        super(context, callbacks);
        mElement = new Wifi(context, this);
    }

    @Override
    protected void update(int action) {

    }

    @Override
    public List<Item> getData() {
        Wifi e = (Wifi) mElement;
//        int[] actions = new int[] {Battery.ACTION_BATTERY};

        List<Item> data = new ArrayList<Item>();

//        @Override
//        public LinkedHashMap<String, String> getContents() {
//            LinkedHashMap<String, String> contents = super.getContents();
//
//            // Wifi
//            contents.put("State", getWifiState(mWifiManager.getWifiState()));
//
//            // WifiManager
//            contents.put("IsEnabled", String.valueOf(mWifiManager.isWifiEnabled()));
//
////		Requires CHANGE_WIFI_STATE permission
////		contents.put("Can Ping Supplicant", String.valueOf(mWifiManager.pingSupplicant()));
//
//            // WifiInfo
//            WifiInfo winfo = mWifiManager.getConnectionInfo();
//            if (winfo != null) {
//                contents.put("BSSID", winfo.getBSSID());
//                contents.put("SSID", winfo.getSSID());
//                contents.put("Is SSID Hidden", String.valueOf(winfo.getHiddenSSID()));
//                contents.put("IP", Convert.Ip4.fromInt(winfo.getIpAddress()));
//                contents.put("Link Speed (Mbps)", String.valueOf(winfo.getLinkSpeed()));
//                contents.put("MAC Address", winfo.getMacAddress());
//                contents.put("Network ID", String.valueOf(winfo.getNetworkId()));
//                contents.put("RSSI (asu)", String.valueOf(winfo.getRssi()));
//                contents.put("Supplicant State", getSupplicantState(winfo.getSupplicantState()));
//                contents.put("Detailed State", mNetwork.getStateString(
//                        WifiInfo.getDetailedStateOf(winfo.getSupplicantState())));
//            }
//            else contents.put("WifiInfo", null);
//
//            // DhcpInfo
//            DhcpInfo dinfo = mWifiManager.getDhcpInfo();
//            if (dinfo != null) {
//                contents.put("DHCP DNS1", Convert.Ip4.fromInt(dinfo.dns1));
//                contents.put("DHCP DNS2", Convert.Ip4.fromInt(dinfo.dns2));
//                contents.put("DHCP Gateway", Convert.Ip4.fromInt(dinfo.gateway));
//                contents.put("DHCP IP", Convert.Ip4.fromInt(dinfo.ipAddress));
//                contents.put("DHCP Lease Duration (s?)", String.valueOf(dinfo.leaseDuration));
//                contents.put("DHCP Netmask", Convert.Ip4.fromInt(dinfo.netmask));
//                contents.put("DHCP Server", Convert.Ip4.fromInt(dinfo.serverAddress));
//            }
//            else contents.put("DhcpInfo", null);
//
//            // ScanResults
//            List<ScanResult> scans = mWifiManager.getScanResults();
//            if (scans != null) {
//                for (int i = 0; i < scans.size(); ++i) {
//                    contents.put("ScanResult " + i + " BSSID", scans.get(i).BSSID);
//                    contents.put("ScanResult " + i + " SSID", scans.get(i).SSID);
//                    contents.put("ScanResult " + i + " capabilities", scans.get(i).capabilities);
//                    contents.put("ScanResult " + i + " frequency (MHz)", String.valueOf(scans.get(i).frequency));
//                    contents.put("ScanResult " + i + " level (dBm)", String.valueOf(scans.get(i).level));
//                }
//            }
//            else contents.put("ScanResults", null);
//
//            // WifiConfigurations
//            List<WifiConfiguration> wifis = mWifiManager.getConfiguredNetworks();
//            String[] strings;
//            if (wifis != null) {
//                for (int i = 0; i < wifis.size(); ++i) {
//                    contents.put("WifiConfiguration " + i + " BSSID", wifis.get(i).BSSID);
//                    contents.put("WifiConfiguration " + i + " SSID", wifis.get(i).SSID);
//                    contents.put("WifiConfiguration " + i + " Is Hidden SSID", String.valueOf(wifis.get(i).hiddenSSID));
//                    contents.put("WifiConfiguration " + i + " Network ID", String.valueOf(wifis.get(i).networkId));
//                    contents.put("WifiConfiguration " + i + " Has PreSharedKey",
//                            String.valueOf(hasPreSharedKey(wifis.get(i).preSharedKey)));
//                    contents.put("WifiConfiguration " + i + " Priority", String.valueOf(wifis.get(i).priority));
//                    contents.put("WifiConfiguration " + i + " Status", getStatus(wifis.get(i).status));
//                    contents.put("WifiConfiguration " + i + " Num WEP Keys", String.valueOf(getNumWepKeys(wifis.get(i).wepKeys)));
//                    contents.put("WifiConfiguration " + i + " Default WEP Key index", String.valueOf(wifis.get(i).wepTxKeyIndex));
//
//                    strings = getAuthAlgorithms(wifis.get(i).allowedAuthAlgorithms);
//                    if (strings != null) contents.put("WifiConfiguration " + i + " Allowed AuthAlgorithms",
//                            TextUtils.join(", ", strings));
//                    else contents.put("WifiConfiguration " + i + " Allowed AuthAlgorithms", null);
//
//                    strings = getGroupCiphers(wifis.get(i).allowedGroupCiphers);
//                    if (strings != null) contents.put("WifiConfiguration " + i + " Allowed GroupCiphers",
//                            TextUtils.join(", ", strings));
//                    else contents.put("WifiConfiguration " + i + " Allowed GroupCiphers", null);
//
//                    strings = getKeyManagements(wifis.get(i).allowedKeyManagement);
//                    if (strings != null) contents.put("WifiConfiguration " + i + " Allowed KeyManagement",
//                            TextUtils.join(", ", strings));
//                    else contents.put("WifiConfiguration " + i + " Allowed KeyManagement", null);
//
//                    strings = getPairwiseCiphers(wifis.get(i).allowedPairwiseCiphers);
//                    if (strings != null) contents.put("WifiConfiguration " + i + " Allowed PairwiseCiphers",
//                            TextUtils.join(", ", strings));
//                    else contents.put("WifiConfiguration " + i + " Allowed PairwiseCiphers", null);
//
//                    strings = getProtocols(wifis.get(i).allowedProtocols);
//                    if (strings != null) contents.put("WifiConfiguration " + i + " Allowed Protocols",
//                            TextUtils.join(", ", strings));
//                    else contents.put("WifiConfiguration " + i + " Allowed Protocols", null);
//                }
//            }
//            else contents.put("WifiConfigurations", null);
//
//            return contents;
//        }

        return data;
    }

    @Override
    public void start() {
        ((Wifi) mElement).start();
    }

    @Override
    public void stop() {
        ((Wifi) mElement).stop();
    }


    @Override
    public void onScanCompleted(List<ScanResult> results) {
        ((Callbacks) mCallbacks).onScanCompleted(results);
    }

    @Override
    public void onNetworkIdsChanged(List<WifiConfiguration> configurations) {
        ((Callbacks) mCallbacks).onNetworkIdsChanged(configurations);
    }

    @Override
    public void onNetworkStateChanged(NetworkInfo networkInfo, String bssid, WifiInfo wifiInfo) {
        ((Callbacks) mCallbacks).onNetworkStateChanged(networkInfo, bssid, wifiInfo);
    }

    @Override
    public void onRssiChanged(int rssi) {
        ((Callbacks) mCallbacks).onRssiChanged(rssi);
    }

    @Override
    public void onSupplicantConnectionChanged(boolean connected) {
        ((Callbacks) mCallbacks).onSupplicantConnectionChanged(connected);
    }

    @Override
    public void onSupplicantStateChanged(SupplicantState state, int error) {
        ((Callbacks) mCallbacks).onSupplicantStateChanged(state, error);
    }
}
