package com.gildedrose.itemupdater;

import com.gildedrose.Item;
import com.gildedrose.QualityConstants;

final class FixedQualityUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        item.quality = QualityConstants.LEGENDARY_FIXED_QUALITY;
    }
}


