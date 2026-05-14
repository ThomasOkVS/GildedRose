package com.gildedrose.itemupdater;

import com.gildedrose.Item;
import com.gildedrose.QualityConstants;

final class IncreasingQualityUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        item.sellIn--;

        boolean isExpired = item.sellIn < 0;
        if (isExpired) {
            increaseQuality(item, 2);
        } else {
            increaseQuality(item, 1);
        }
    }

    private void increaseQuality(Item item, int amount) {
        item.quality = Math.min(QualityConstants.MAX_QUALITY, item.quality + amount);
    }
}
