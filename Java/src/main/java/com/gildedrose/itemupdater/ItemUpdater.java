package com.gildedrose.itemupdater;

import com.gildedrose.Item;

public sealed interface ItemUpdater permits DecreasingQualityUpdater, IncreasingQualityUpdater, TimeBasedQualityBoostUpdater, FixedQualityUpdater, DoubleDecreasingQualityUpdater {
    void update(Item item);
}
