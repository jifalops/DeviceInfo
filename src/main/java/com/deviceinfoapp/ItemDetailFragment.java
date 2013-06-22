package com.deviceinfoapp;

import android.app.ListFragment;
import android.os.Bundle;

import com.deviceinfoapp.adapter.ElementAdapter;
import com.deviceinfoapp.element.Audio;
import com.deviceinfoapp.element.Element;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends ListFragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    private static final int PROCESSORS  = 0;
    private static final int RAM 		 = 1;
    private static final int STORAGE 	 = 2;
    private static final int DISPLAY 	 = 3;
    private static final int CAMERAS 	 = 4;
    private static final int BATTERY 	 = 5;
    private static final int SENSORS 	 = 6;
    private static final int AUDIO 		 = 7;
    private static final int GRAPHICS 	 = 8;
    private static final int LOCATION 	 = 9;
    private static final int NETWORK     = 10;
    private static final int CELLULAR 	 = 11;
    private static final int WIFI 		 = 12;
    private static final int BLUETOOTH 	 = 13;
    private static final int UPTIME 	 = 14;
    private static final int PLATFORM 	 = 15;
    private static final int IDENTIFIERS = 16;
    private static final int FEATURES    = 17;
    private static final int PROPERTIES  = 18;
    private static final int KEYS 		 = 19;

    /**
     * The dummy content this fragment is presenting.
     */
    private int mItem;

    private Element mElement;
    private ElementAdapter mAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = getArguments().getInt(ARG_ITEM_ID);

            String[] items = getResources().getStringArray(R.array.main_items);
            getActivity().setTitle(items[mItem]);

            switch (mItem) {
                case PROCESSORS:

                    break;
                case RAM:

                    break;
                case STORAGE:

                    break;
                case DISPLAY:

                    break;
                case CAMERAS:

                    break;
                case BATTERY:

                    break;
                case SENSORS:

                    break;
                case AUDIO:
                    mElement = new Audio(getActivity());
                    mAdapter = new ElementAdapter(getActivity(), mElement);
                    setListAdapter(mAdapter);
                    break;
                case GRAPHICS:

                    break;
                case LOCATION:

                    break;
                case NETWORK:

                    break;
                case CELLULAR:

                    break;
                case WIFI:

                    break;
                case BLUETOOTH:

                    break;
                case UPTIME:

                    break;
                case PLATFORM:

                    break;
                case IDENTIFIERS:

                    break;
                case FEATURES:

                    break;
                case PROPERTIES:

                    break;
                case KEYS:

                    break;
            }
        }
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//            Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);
//
//        // Show the dummy content as text in a TextView.
//        if (mItem >= 0) {
//            ((TextView) rootView.findViewById(R.id.item_detail)).setText(String.valueOf(mItem));
//        }
//
//        return rootView;
//    }
}
