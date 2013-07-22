package com.deviceinfoapp.controller;

import android.content.Context;
import android.location.Address;
import android.os.Bundle;

import com.deviceinfoapp.element.Location;
import com.deviceinfoapp.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jake on 7/18/13.
 */
public class LocationController extends ActiveElementController implements Location.Callbacks {

    public interface Callbacks extends ActiveElementController.Callbacks {
        /** Corresponds to LocationListener.onLocationChanged() */
        void onLocationChanged(android.location.Location location);
        /** Corresponds to LocationListener.onProviderDisabled() */
        void onProviderDisabled(String provider);
        /** Corresponds to LocationListener.onProviderEnabled() */
        void onProviderEnabled(String provider);
        /** Corresponds to LocationListener.onStatusChanged() */
        void onStatusChanged(String provider, int status, Bundle extras);
        /** Custom callback that is called after the Geocoder updates the closest address */
        void onAddressChanged(Address address, android.location.Location location);

        /** Corresponds to GpsStatus.Listener.onGpsStatusChanged() */
        void onGpsStatusChanged(int event);
        /** Corresponds to GpsStatus.NmeaListener.onNmeaReceived() */
        void onNmeaReceived(long timestamp, String nmea);
    }

    public LocationController(Context context, LocationController.Callbacks callbacks) {
        super(context, callbacks);
        mElement = new Location(context, this);
    }

    @Override
    protected void update(int action) {

    }

    @Override
    public List<Item> getData() {
        List<Item> data = new ArrayList<Item>();

        Location e = (Location) mElement;



        // Main

//        @Override
//        public LinkedHashMap<String, String> getContents() {
//            LinkedHashMap<String, String> contents = new LinkedHashMap<String, String>();
//            LinkedHashMap<String, String> subcontents;
//
//            contents.put("Is Listening (GpsStatus)", String.valueOf(isActive()));
//            contents.put("Best Provider index", String.valueOf(mProviders.indexOf(getBestProvider())));
//            contents.put("Last GPS Status Timestamp", String.valueOf(getLastGpsStatusTimestamp()));
//            contents.put("Last GPS Status Event", getLastGpsStatusEvent());
//            contents.put("Last NMEA Timestamp", String.valueOf(getLastNmeaTimestamp()));
//            contents.put("Last NMEA", getNmea());
//            contents.put("GPS Status update frequency (ms)", String.valueOf(getGpsStatusUpdateFrequency()));
//
//            if (mGpsStatus != null) {
//                // GpsStatus values
//                contents.put("Max Satellites", String.valueOf(mGpsStatus.getMaxSatellites()));
//                contents.put("Time to First Fix", String.valueOf(mGpsStatus.getTimeToFirstFix()));
//
//                // GpsSatellite values
//                int i = 0;
//                Iterable<GpsSatellite> sats = mGpsStatus.getSatellites();
//                if (sats != null) {
//                    for (GpsSatellite s : sats) {
//                        contents.put("Satellite " + i + " Azimuth (°)", String.valueOf(s.getAzimuth()));
//                        contents.put("Satellite " + i + " Elevation (°)", String.valueOf(s.getElevation()));
//                        contents.put("Satellite " + i + " PRN", String.valueOf(s.getPrn()));
//                        contents.put("Satellite " + i + " SNR", String.valueOf(s.getSnr()));
//                        contents.put("Satellite " + i + " hasAlmanac", String.valueOf(s.hasAlmanac()));
//                        contents.put("Satellite " + i + " hasEphemeris", String.valueOf(s.hasEphemeris()));
//                        contents.put("Satellite " + i + " usedInFix", String.valueOf(s.usedInFix()));
//                        ++i;
//                    }
//                }
//                else contents.put("Satellites", null);
//            }
//            else contents.put("GpsStatus", null);
//
//            for (int i = 0; i < mProviders.size(); ++i) {
//                subcontents = mProviders.get(i).getContents();
//                for (Map.Entry<String, String> e : subcontents.entrySet()) {
//                    contents.put("Provider " + i + " " + e.getKey(), e.getValue());
//                }
//            }
//
//            return contents;
//        }



        // ProviderWrapper
//        @Override
//        public LinkedHashMap<String, String> getContents() {
//            LinkedHashMap<String, String> contents = new LinkedHashMap<String, String>();
//
//            // ProviderWrapper values
//            contents.put("Type", getProviderString());
//            contents.put("Accuracy", getAccuracyString());
//            contents.put("Power", getPowerRequirementString());
//            contents.put("Min Time (ms)", String.valueOf(getMinTime()));
//            contents.put("Min Distance (m)", String.valueOf(getMinDistance()));
//            contents.put("Last Location Timestamp (ms)", String.valueOf(getLastLocationTimestamp()));
//            contents.put("Last Address Timestamp (ms)", String.valueOf(getLastAddressTimestamp()));
//            contents.put("Address Update Frequency (ms)", String.valueOf(getAddressUpdateFrequency()));
//            contents.put("Is Enabled", String.valueOf(isEnabled()));
//            contents.put("Is Listening", String.valueOf(isListening()));
//            contents.put("Last Status", getLastStatusString());
//            if (getLastExtras() != null) {
//                for (String s : getLastExtras().keySet()) {
//                    contents.put("Last Extras " + s, getLastExtras().getString(s));
//                }
//            }
//            else contents.put("Last Extras", null);
//
//            // LocationProvider values
//            contents.put("HasMonetaryCost", String.valueOf(mProvider.hasMonetaryCost()));
//            contents.put("Requires Cellular", String.valueOf(mProvider.requiresCell()));
//            contents.put("Requires Network", String.valueOf(mProvider.requiresNetwork()));
//            contents.put("Requires Satellite", String.valueOf(mProvider.requiresSatellite()));
//            contents.put("Supports Altitude", String.valueOf(mProvider.supportsAltitude()));
//            contents.put("Supports Bearing", String.valueOf(mProvider.supportsBearing()));
//            contents.put("Supports Speed", String.valueOf(mProvider.supportsSpeed()));
//
//
//            // Location values
//            if (mLocation != null) {
//                contents.put("Last Location Accuracy (m)", String.valueOf(mLocation.getAccuracy()));
//                contents.put("Last Location Altitude (m?)", String.valueOf(mLocation.getAltitude()));
//                contents.put("Last Location Bearing (°)", String.valueOf(mLocation.getBearing()));
//                if (mLocation.getExtras() != null) {
//                    for (String s : mLocation.getExtras().keySet()) {
//                        contents.put("Last Location Extras " + s, mLocation.getExtras().getString(s));
//                    }
//                }
//                else contents.put("Last Location Extras", null);
//                contents.put("Last Location Latitude", String.valueOf(mLocation.getLatitude()));
//                contents.put("Last Location Longitude", String.valueOf(mLocation.getLongitude()));
//                contents.put("Last Location Provider", mLocation.getProvider());
//                contents.put("Last Location Speed", String.valueOf(mLocation.getSpeed()));
//                contents.put("Last Location Timestamp", String.valueOf(mLocation.getTime()));
//                contents.put("Last Location hasAccuracy", String.valueOf(mLocation.hasAccuracy()));
//                contents.put("Last Location hasAltitude", String.valueOf(mLocation.hasAltitude()));
//                contents.put("Last Location hasBearing", String.valueOf(mLocation.hasBearing()));
//                contents.put("Last Location hasSpeed", String.valueOf(mLocation.hasSpeed()));
//            }
//            else contents.put("Last Location", null);
//
//            if (mAddress != null) {
//                // Address values
//                int len = mAddress.getMaxAddressLineIndex() + 1;
//                for (int i = 0; i < len; ++i) {
//                    contents.put("Last Address Line " + i, mAddress.getAddressLine(i));
//                }
//                contents.put("Last Address AdminArea", mAddress.getAdminArea());
//                contents.put("Last Address CountryCode", mAddress.getCountryCode());
//                contents.put("Last Address CountryName", mAddress.getCountryName());
//                if (mAddress.getExtras() != null) {
//                    for (String s : mAddress.getExtras().keySet()) {
//                        contents.put("Last Address Extras " + s, mAddress.getExtras().getString(s));
//                    }
//                }
//                else contents.put("Last Address Extras", null);
//                contents.put("Last Address FeatureName", mAddress.getFeatureName());
//                contents.put("Last Address Latitude", String.valueOf(mAddress.getLatitude()));
//                contents.put("Last Address Longitude", String.valueOf(mAddress.getLongitude()));
//                contents.put("Last Address Phone", mAddress.getPhone());
//                contents.put("Last Address PostalCode", mAddress.getPostalCode());
//                contents.put("Last Address Premises", mAddress.getPremises());
//                contents.put("Last Address SubAdminArea", mAddress.getSubAdminArea());
//                contents.put("Last Address SubLocality", mAddress.getSubLocality());
//                contents.put("Last Address SubThoroughfare", mAddress.getSubThoroughfare());
//                contents.put("Last Address Thoroughfare", mAddress.getThoroughfare());
//            }
//            else contents.put("Last Address", null);
//
//            return contents;
//        }

        return data;
    }

    @Override
    public void start() {
        ((Location) mElement).start();
    }

    @Override
    public void stop() {
        ((Location) mElement).stop();
    }

    @Override
    public void onLocationChanged(android.location.Location location) {
        ((Callbacks) mCallbacks).onLocationChanged(location);
    }

    @Override
    public void onProviderDisabled(String provider) {
        ((Callbacks) mCallbacks).onProviderDisabled(provider);
    }

    @Override
    public void onProviderEnabled(String provider) {
        ((Callbacks) mCallbacks).onProviderEnabled(provider);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        ((Callbacks) mCallbacks).onStatusChanged(provider, status, extras);
    }

    @Override
    public void onAddressChanged(Address address, android.location.Location location) {
        ((Callbacks) mCallbacks).onAddressChanged(address, location);
    }

    @Override
    public void onGpsStatusChanged(int event) {
        ((Callbacks) mCallbacks).onGpsStatusChanged(event);
    }

    @Override
    public void onNmeaReceived(long timestamp, String nmea) {
        ((Callbacks) mCallbacks).onNmeaReceived(timestamp, nmea);
    }
}
