package com.gildedrose

class GildedRose(val items: List<Item>) {
    fun updateQuality() {
        items.forEachIndexed { index, item ->
            val itemName = item.name
            val itemQuality = item.quality

            if (itemName != FineGoods.AGED_BRIE.rawName && itemName != FineGoods.BACKSTAGE_PASS.rawName) {
                if (itemQuality > 0) {
                    if (itemName != FineGoods.SULFURAS.rawName) {
                        items[index].quality = itemQuality - 1
                    }
                }
            } else {
                if (itemQuality < MAX_QUALITY_50) {
                    items[index].quality = itemQuality + 1

                    if (itemName == FineGoods.BACKSTAGE_PASS.rawName) {
                        if (item.sellIn <= SELL_IN_10_DAYS) {
                            // can't replace item.quality with itemQuality val; breaks unit tests
                            items[index].quality = item.quality + 1
                        }

                        if (item.sellIn <= SELL_IN_5_DAYS) {
                            // can't replace item.quality with itemQuality val; breaks unit tests
                            items[index].quality = item.quality + 1
                        }
                    }
                }
            }

            if (itemName != FineGoods.SULFURAS.rawName) {
                items[index].sellIn = item.sellIn - 1
            }

            if (item.sellIn < 0) {
                if (itemName != FineGoods.AGED_BRIE.rawName) {
                    if (itemName != FineGoods.BACKSTAGE_PASS.rawName) {
                        if (itemQuality > 0) {
                            if (itemName != FineGoods.SULFURAS.rawName) {
                                // can't replace item.quality with itemQuality val; breaks unit tests
                                items[index].quality = item.quality - 1
                            }
                        }
                    } else {
                        items[index].quality = 0
                    }
                } else {
                    if (itemQuality < MAX_QUALITY_50) {
                        // can't replace item.quality with itemQuality val; breaks unit tests
                        items[index].quality = item.quality + 1
                    }
                }
            }
        }
    }

    companion object {
        private const val MAX_QUALITY_50 = 50

        private const val SELL_IN_10_DAYS = 10
        private const val SELL_IN_5_DAYS = 5
    }
}

// don't want to deal with string literals
enum class FineGoods(val rawName: String) {
    AGED_BRIE("Aged Brie"),
    BACKSTAGE_PASS("Backstage passes to a TAFKAL80ETC concert"),
    SULFURAS("Sulfuras, Hand of Ragnaros")
}

