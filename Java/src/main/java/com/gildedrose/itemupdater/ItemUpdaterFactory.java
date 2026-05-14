package com.gildedrose.itemupdater;

import com.gildedrose.Item;

public final class ItemUpdaterFactory {

    // region items
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String CONJURED = "Conjured";
    //endregion

    // region updaters
    private static final IncreasingQualityUpdater INCREASING_QUALITY_UPDATER = new IncreasingQualityUpdater();
    private static final TimeBasedQualityBoostUpdater TIME_BASED_QUALITY_BOOST_UPDATER = new TimeBasedQualityBoostUpdater();
    private static final FixedQualityUpdater FIXED_QUALITY_UPDATER = new FixedQualityUpdater();
    private static final DecreasingQualityUpdater DECREASING_QUALITY_UPDATER = new DecreasingQualityUpdater();
    private static final DoubleDecreasingQualityUpdater DOUBLE_DECREASING_QUALITY_UPDATER = new DoubleDecreasingQualityUpdater();
    // endregion

    private ItemUpdaterFactory() {}

    public static ItemUpdater getUpdater(Item item) {
        if (item.name.startsWith(CONJURED)) {
            return DOUBLE_DECREASING_QUALITY_UPDATER;
        }

        return switch (item.name) {
            case AGED_BRIE -> INCREASING_QUALITY_UPDATER;
            case BACKSTAGE_PASS -> TIME_BASED_QUALITY_BOOST_UPDATER;
            case SULFURAS -> FIXED_QUALITY_UPDATER;
            default -> DECREASING_QUALITY_UPDATER;
        };
    }
}


