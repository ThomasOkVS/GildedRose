package com.gildedrose.itemupdater;

import com.gildedrose.Item;
import com.gildedrose.QualityConstants;

final class TimeBasedQualityBoostUpdater implements ItemUpdater {
    private static final int FIRST_THRESHOLD_DAYS = 10;
    private static final int SECOND_THRESHOLD_DAYS = 5;

    @Override
    public void update(Item item) {
        item.sellIn--;

        boolean isExpired = item.sellIn < 0;
        if (isExpired) {
            item.quality = QualityConstants.MIN_QUALITY;
        } else {
            int qualityIncrease = 1;
            if (item.sellIn < FIRST_THRESHOLD_DAYS) {
                qualityIncrease++;
            }
            if (item.sellIn < SECOND_THRESHOLD_DAYS) {
                qualityIncrease++;
            }
            increaseQuality(item, qualityIncrease);
        }
    }

    private void increaseQuality(Item item, int amount) {
        item.quality = Math.min(QualityConstants.MAX_QUALITY, item.quality + amount);
    }
}
