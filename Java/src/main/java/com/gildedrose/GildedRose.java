package com.gildedrose;

import com.gildedrose.itemupdater.ItemUpdater;
import com.gildedrose.itemupdater.ItemUpdaterFactory;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

class GildedRose {
    private static final Logger logger = getLogger(GildedRose.class.getName());

    final Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        logger.fine(() -> "Starting quality update for %d items".formatted(items.length));

        for (Item item : items) {
            logger.finer(() -> "Updating item: %s, sellIn: %d, quality: %d".formatted(item.name, item.sellIn, item.quality));
            ItemUpdater updater = ItemUpdaterFactory.getUpdater(item);
            updater.update(item);
            logger.finer(() -> "Updated item: %s, sellIn: %d, quality: %d".formatted(item.name, item.sellIn, item.quality));
        }

        logger.fine("Quality update completed");
    }
}
