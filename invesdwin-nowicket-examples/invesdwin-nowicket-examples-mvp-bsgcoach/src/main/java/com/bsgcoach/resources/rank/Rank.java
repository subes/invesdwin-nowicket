package com.bsgcoach.resources.rank;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

@NotThreadSafe
public enum Rank {
    _1("1"),
    _2("2"),
    _3("3"),
    _4("4"),
    _5("5"),
    _6("6"),
    _7("7"),
    _8("8"),
    _9("9"),
    _10("10");

    private String title;

    Rank(final String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

    public ResourceReference getIcon() {
        return new PackageResourceReference(getClass(), "Rank_" + title + ".png");
    }

    public static Rank valueOfIndex(final int index) {
        final Rank newS = Rank.values()[index];
        return newS;
    }

}
