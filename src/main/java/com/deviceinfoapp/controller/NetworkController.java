package com.deviceinfoapp.controller;

import android.content.Context;

import com.deviceinfoapp.element.Network;
import com.deviceinfoapp.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jake on 7/18/13.
 */
public class NetworkController extends AbsElementController {

    public NetworkController(Context context) {
        super(context);
        mElement = new Network(context);
    }

    @Override
    public List<Item> getData() {
        List<Item> data = new ArrayList<Item>();

        Network e = (Network) mElement;

//        @Override
//        public LinkedHashMap<String, String> getContents() {
//            LinkedHashMap<String, String> contents = new LinkedHashMap<String, String>();
//
//            // Network
//            contents.put("Network Preference", getNetworkPreferenceString());
//            contents.put("Background Data Allowed", String.valueOf(getBackgroundDataSetting()));
//            contents.put(TYPE_WIFI + " Valid", String.valueOf(isNetworkTypeValid(TYPE_WIFI)));
//            contents.put(TYPE_MOBILE + " Valid", String.valueOf(isNetworkTypeValid(TYPE_MOBILE)));
//            contents.put(TYPE_BLUETOOTH + " Valid", String.valueOf(isNetworkTypeValid(TYPE_BLUETOOTH)));
//            contents.put(TYPE_DUMMY + " Valid", String.valueOf(isNetworkTypeValid(TYPE_DUMMY)));
//            contents.put(TYPE_ETHERNET + " Valid", String.valueOf(isNetworkTypeValid(TYPE_ETHERNET)));
//            contents.put(TYPE_MOBILE_DUN + " Valid", String.valueOf(isNetworkTypeValid(TYPE_MOBILE_DUN)));
//            contents.put(TYPE_MOBILE_HIPRI + " Valid", String.valueOf(isNetworkTypeValid(TYPE_MOBILE_HIPRI)));
//            contents.put(TYPE_MOBILE_MMS + " Valid", String.valueOf(isNetworkTypeValid(TYPE_MOBILE_MMS)));
//            contents.put(TYPE_MOBILE_SUPL + " Valid", String.valueOf(isNetworkTypeValid(TYPE_MOBILE_SUPL)));
//            contents.put(TYPE_WIMAX + " Valid", String.valueOf(isNetworkTypeValid(TYPE_WIMAX)));
//
//            // NetworkInfo
//            NetworkInfo[] infos = mConnectivityManager.getAllNetworkInfo();
//            NetworkInfo active = mConnectivityManager.getActiveNetworkInfo();
//            if (active != null) contents.put("Active NetworkInfo Type", active.getTypeName());
//            if (infos != null) {
//                int i = 0;
//                for (NetworkInfo ni : infos) {
//                    contents.put("NetworkInfo " + i + " Type", getNetworkTypeString(ni.getType()));
//                    contents.put("NetworkInfo " + i + " Subtype", getNetworkTypeString(ni.getSubtype()));
//                    contents.put("NetworkInfo " + i + " TypeName", ni.getTypeName());
//                    contents.put("NetworkInfo " + i + " SubtypeName", ni.getSubtypeName());
//                    contents.put("NetworkInfo " + i + " State", getStateString(ni.getState()));
//                    contents.put("NetworkInfo " + i + " DetailedState", getStateString(ni.getDetailedState()));
//                    contents.put("NetworkInfo " + i + " ExtraInfo", ni.getExtraInfo());
//                    contents.put("NetworkInfo " + i + " ReasonFailed", ni.getReason());
//                    contents.put("NetworkInfo " + i + " IsActive", String.valueOf(ni == active));
//                    contents.put("NetworkInfo " + i + " IsAvailable", String.valueOf(ni.isAvailable()));
//                    contents.put("NetworkInfo " + i + " IsConnected", String.valueOf(ni.isConnected()));
//                    contents.put("NetworkInfo " + i + " IsConnectedOrConnecting", String.valueOf(ni.isConnectedOrConnecting()));
//                    contents.put("NetworkInfo " + i + " IsFailover", String.valueOf(ni.isFailover()));
//                    contents.put("NetworkInfo " + i + " IsRoaming", String.valueOf(ni.isRoaming()));
//                    ++i;
//                }
//            }
//            else contents.put("NetworkInfo[]", null);
//
//
//            //TODO belongs elsewhere
//            List<NetworkInterface> networks = new ArrayList<NetworkInterface>();
//            try { networks = Collections.list(NetworkInterface.getNetworkInterfaces());	}
//            catch (SocketException ignored) {}
//            int i = 0;
//            byte[] addr = null;
//            StringBuilder address;
//            int j;
//            InetAddress inetAddress = null;
//            for (NetworkInterface ni : networks) {
//                contents.put("NetworkInterface " + i + " Display Name", ni.getDisplayName());
//                try { addr = ni.getHardwareAddress(); }
//                catch (SocketException ignored) {}
//                if (addr != null) {
//                    address = new StringBuilder();
//                    for (byte b : addr) {
//                        address.append(b & 0xFF).append(':');
//                    }
//                    contents.put("NetworkInterface " + i + " Hardware Address", address.toString());
//                }
//                else contents.put("NetworkInterface " + i + " Hardware Address", null);
//                try { contents.put("NetworkInterface " + i + " MTU", String.valueOf(ni.getMTU())); }
//                catch (SocketException ignored) {}
//                contents.put("NetworkInterface " + i + " Name", String.valueOf(ni.getName()));
//                contents.put("NetworkInterface " + i + " Parent index", String.valueOf(networks.indexOf(ni.getParent())));
//                try { contents.put("NetworkInterface " + i + " Is Loopback", String.valueOf(ni.isLoopback())); }
//                catch (SocketException ignored) {}
//                try { contents.put("NetworkInterface " + i + " Is PointToPoint", String.valueOf(ni.isPointToPoint())); }
//                catch (SocketException ignored) {}
//                try { contents.put("NetworkInterface " + i + " Is Up", String.valueOf(ni.isUp())); }
//                catch (SocketException ignored) {}
//                contents.put("NetworkInterface " + i + " Is Virtual", String.valueOf(ni.isVirtual()));
//                try { contents.put("NetworkInterface " + i + " Supports Multicast", String.valueOf(ni.supportsMulticast())); }
//                catch (SocketException ignored) {}
//
//                List<InterfaceAddress> interfaces = ni.getInterfaceAddresses();
//                if (interfaces != null) {
//                    j = 0;
//                    for (InterfaceAddress ia : ni.getInterfaceAddresses()) {
//                        contents.put("NetworkInterface " + i + " InterfaceAddress " + j + " Network Prefix bits",
//                                String.valueOf(ia.getNetworkPrefixLength()));
//                        inetAddress = ia.getAddress();
//                        addr = inetAddress.getAddress();
//                        if (addr != null) {
//                            address = new StringBuilder();
//                            for (byte b : addr) {
//                                address.append(b & 0xFF).append('.');
//                            }
//                            contents.put("NetworkInterface " + i + " InterfaceAddress " + j + " Address Address",
//                                    address.toString());
//                        }
//                        else contents.put("NetworkInterface " + i + " InterfaceAddress " + j + " Address Address", null);
//                        contents.put("NetworkInterface " + i + " InterfaceAddress " + j + " Address CanonicalHostName",
//                                inetAddress.getCanonicalHostName());
//                        contents.put("NetworkInterface " + i + " InterfaceAddress " + j + " Address HostName",
//                                inetAddress.getHostName());
//                        contents.put("NetworkInterface " + i + " InterfaceAddress " + j + " Address IsAnyLocalAddress",
//                                String.valueOf(inetAddress.isAnyLocalAddress()));
//                        contents.put("NetworkInterface " + i + " InterfaceAddress " + j + " Address IsLinkLocalAddress",
//                                String.valueOf(inetAddress.isLinkLocalAddress()));
//                        contents.put("NetworkInterface " + i + " InterfaceAddress " + j + " Address IsLoopbackAddress",
//                                String.valueOf(inetAddress.isLoopbackAddress()));
//                        contents.put("NetworkInterface " + i + " InterfaceAddress " + j + " Address IsMCGlobal",
//                                String.valueOf(inetAddress.isMCGlobal()));
//                        contents.put("NetworkInterface " + i + " InterfaceAddress " + j + " Address IsMCLinkLocal",
//                                String.valueOf(inetAddress.isMCLinkLocal()));
//                        contents.put("NetworkInterface " + i + " InterfaceAddress " + j + " Address IsMCNodeLocal",
//                                String.valueOf(inetAddress.isMCNodeLocal()));
//                        contents.put("NetworkInterface " + i + " InterfaceAddress " + j + " Address IsMCOrgLocal",
//                                String.valueOf(inetAddress.isMCOrgLocal()));
//                        contents.put("NetworkInterface " + i + " InterfaceAddress " + j + " Address IsMCSiteLocal",
//                                String.valueOf(inetAddress.isMCSiteLocal()));
//                        contents.put("NetworkInterface " + i + " InterfaceAddress " + j + " Address IsMulticast",
//                                String.valueOf(inetAddress.isMulticastAddress()));
//                        // TODO network on ui thread
////					try {
////						contents.put("NetworkInterface " + i + " InterfaceAddress " + j + " Address IsReachable",
////								String.valueOf(inetAddress.isReachable(5000)));
////					} catch (IOException ignored) {}
//                        contents.put("NetworkInterface " + i + " InterfaceAddress " + j + " Address IsSiteLocal",
//                                String.valueOf(inetAddress.isSiteLocalAddress()));
//
//                        inetAddress = ia.getBroadcast();
//                        addr = inetAddress.getAddress();
//                        if (addr != null) {
//                            address = new StringBuilder();
//                            for (byte b : addr) {
//                                address.append(b & 0xFF).append('.');
//                            }
//                            contents.put("NetworkInterface " + i + " InterfaceAddress " + j + " Broadcast Address",
//                                    address.toString());
//                        }
//                        else contents.put("NetworkInterface " + i + " InterfaceAddress " + j + " Broadcast Address", null);
//                        contents.put("NetworkInterface " + i + " InterfaceAddress " + j + " Broadcast CanonicalHostName",
//                                inetAddress.getCanonicalHostName());
//                        contents.put("NetworkInterface " + i + " InterfaceAddress " + j + " Broadcast HostName",
//                                inetAddress.getHostName());
//                        contents.put("NetworkInterface " + i + " InterfaceAddress " + j + " Broadcast IsAnyLocalAddress",
//                                String.valueOf(inetAddress.isAnyLocalAddress()));
//                        contents.put("NetworkInterface " + i + " InterfaceAddress " + j + " Broadcast IsLinkLocalAddress",
//                                String.valueOf(inetAddress.isLinkLocalAddress()));
//                        contents.put("NetworkInterface " + i + " InterfaceAddress " + j + " Broadcast IsLoopbackAddress",
//                                String.valueOf(inetAddress.isLoopbackAddress()));
//                        contents.put("NetworkInterface " + i + " InterfaceAddress " + j + " Broadcast IsMCGlobal",
//                                String.valueOf(inetAddress.isMCGlobal()));
//                        contents.put("NetworkInterface " + i + " InterfaceAddress " + j + " Broadcast IsMCLinkLocal",
//                                String.valueOf(inetAddress.isMCLinkLocal()));
//                        contents.put("NetworkInterface " + i + " InterfaceAddress " + j + " Broadcast IsMCNodeLocal",
//                                String.valueOf(inetAddress.isMCNodeLocal()));
//                        contents.put("NetworkInterface " + i + " InterfaceAddress " + j + " Broadcast IsMCOrgLocal",
//                                String.valueOf(inetAddress.isMCOrgLocal()));
//                        contents.put("NetworkInterface " + i + " InterfaceAddress " + j + " Broadcast IsMCSiteLocal",
//                                String.valueOf(inetAddress.isMCSiteLocal()));
//                        contents.put("NetworkInterface " + i + " InterfaceAddress " + j + " Broadcast IsMulticast",
//                                String.valueOf(inetAddress.isMulticastAddress()));
//                        try {
//                            contents.put("NetworkInterface " + i + " InterfaceAddress " + j + " Broadcast IsReachable",
//                                    String.valueOf(inetAddress.isReachable(5000)));
//                        } catch (IOException ignored) {}
//                        contents.put("NetworkInterface " + i + " InterfaceAddress " + j + " Broadcast IsSiteLocal",
//                                String.valueOf(inetAddress.isSiteLocalAddress()));
//
//                        ++j;
//                    }
//                }
//                else contents.put("NetworkInterface " + i + " InterfaceAddress List", null);
//                ++i;
//            }
//
//            return contents;
//        }

        return data;
    }
}
