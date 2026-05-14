package com.gildedrose;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Nested
    class NormalItemTests {
        @Test
        void decreasesByOneBeforeExpiration() {
            Item[] items = new Item[] { new Item("Normal Item", 5, 10) };
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(9, app.items[0].quality);
            assertEquals(4, app.items[0].sellIn);
        }

        @Test
        void decreasesByTwoAfterExpiration() {
            Item[] items = new Item[] { new Item("Normal Item", 0, 10) };
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(8, app.items[0].quality);
            assertEquals(-1, app.items[0].sellIn);
        }

        @Test
        void qualityNeverNegative() {
            Item[] items = new Item[] { new Item("Normal Item", 0, 0) };
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(0, app.items[0].quality);
        }
    }

    @Nested
    class AgedBrieTests {
        @Test
        void increasesBeforeExpiration() {
            Item[] items = new Item[] { new Item("Aged Brie", 5, 10) };
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(11, app.items[0].quality);
            assertEquals(4, app.items[0].sellIn);
        }

        @Test
        void increasesDoubleAfterExpiration() {
            Item[] items = new Item[] { new Item("Aged Brie", 0, 10) };
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(12, app.items[0].quality);
            assertEquals(-1, app.items[0].sellIn);
        }

        @Test
        void neverExceedsMaxQuality() {
            Item[] items = new Item[] { new Item("Aged Brie", 5, 50) };
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(50, app.items[0].quality);
        }
    }

    @Nested
    class BackstagePassTests {

        @ParameterizedTest
        @CsvSource({
            "15, 10, 11, 14",
            "10, 10, 12, 9",
            "5, 10, 13, 4"
        })
        void increasesBasedOnDaysAway(int sellIn, int quality, int expectedQuality, int expectedSellIn) {
            Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality) };
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(expectedQuality, app.items[0].quality);
            assertEquals(expectedSellIn, app.items[0].sellIn);
        }

        @Test
        void dropsToZeroAfterExpired() {
            Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 50) };
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(0, app.items[0].quality);
            assertEquals(-1, app.items[0].sellIn);
        }

        @Test
        void neverExceedsMaxQuality() {
            Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49) };
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(50, app.items[0].quality);
        }
    }

    @Nested
    class SulfurasTests {
        @Test
        void neverChanges() {
            Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 10, 80) };
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(80, app.items[0].quality);
            assertEquals(10, app.items[0].sellIn);
        }

        @Test
        void alwaysHasQualityEighty() {
            Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", -1, 50) };
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(80, app.items[0].quality);
        }
    }

    @Nested
    class ConjuredItemTests {
        @Test
        void decreasesByTwoBeforeExpiration() {
            Item[] items = new Item[] { new Item("Conjured Mana Cake", 5, 10) };
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(8, app.items[0].quality);
            assertEquals(4, app.items[0].sellIn);
        }

        @Test
        void decreasesByFourAfterExpiration() {
            Item[] items = new Item[] { new Item("Conjured Mana Cake", 0, 10) };
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(6, app.items[0].quality);
            assertEquals(-1, app.items[0].sellIn);
        }

        @Test
        void qualityNeverNegative() {
            Item[] items = new Item[] { new Item("Conjured Bread", 0, 1) };
            GildedRose app = new GildedRose(items);
            app.updateQuality();
            assertEquals(0, app.items[0].quality);
        }
    }

    @Nested
    class MultipleItemsTests {
        @Test
        void multipleItemsUpdateCorrectly() {
            Item[] items = new Item[] {
                new Item("Normal Item", 5, 10),
                new Item("Aged Brie", 5, 10),
                new Item("Sulfuras, Hand of Ragnaros", 5, 80),
                new Item("Conjured Mana Cake", 5, 10)
            };
            GildedRose app = new GildedRose(items);
            app.updateQuality();

            assertEquals(9, items[0].quality);
            assertEquals(11, items[1].quality);
            assertEquals(80, items[2].quality);
            assertEquals(8, items[3].quality);
        }
    }
}
