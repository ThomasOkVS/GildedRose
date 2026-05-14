package com.gildedrose.itemupdater;

import com.gildedrose.Item;
import com.gildedrose.QualityConstants;

final class DecreasingQualityUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        item.sellIn--;

        boolean isExpired = item.sellIn < 0;
        int qualityDecrease = isExpired ? 2 : 1;
        decreaseQuality(item, qualityDecrease);
    }

    private void decreaseQuality(Item item, int amount) {
        item.quality = Math.max(QualityConstants.MIN_QUALITY, item.quality - amount);
    }
}

