package com.example.votecast.utils.filters;


import com.example.votecast.R;

import java.util.ArrayList;
import java.util.List;

public class FilterUtils {

    private FilterUtils() {
    }

    public static List<FilterRoot> getFilters() {
        List<FilterRoot> filterRoots = new ArrayList<>();
        filterRoots.add(new FilterRoot(0, "None"));
        filterRoots.add(new FilterRoot(R.raw.livegif1, "livegif1"));
        filterRoots.add(new FilterRoot(R.raw.livegif2, "livegif2"));
        filterRoots.add(new FilterRoot(R.raw.livegif3, "livegif3"));
        filterRoots.add(new FilterRoot(R.raw.livegif4, "livegif4"));
        filterRoots.add(new FilterRoot(R.raw.livegif6, "livegif6"));
        filterRoots.add(new FilterRoot(R.raw.livegif7, "livegif7"));
        return filterRoots;
    }

    public static List<FilterRoot> getFilter2() {
        List<FilterRoot> filterRoots = new ArrayList<>();
        filterRoots.add(new FilterRoot(0, "None"));
        filterRoots.add(new FilterRoot(R.raw.bubble, "livegif1"));
        filterRoots.add(new FilterRoot(R.raw.fires, "livegif2"));
        filterRoots.add(new FilterRoot(R.raw.heartsfilter, "livegif3"));

        return filterRoots;
    }


}
