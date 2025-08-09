package com.gildedrose

class GildedRose(val items: List<Item>) {
    fun updateQuality() {
        items.forEach { item ->
            val itemName = item.name
            val currentQuality = item.quality

            if (itemName != FineGoods.AGED_BRIE.rawName && itemName != FineGoods.BACKSTAGE_PASS.rawName) {
                if (currentQuality > 0) {
                    if (itemName != FineGoods.SULFURAS.rawName) {
                        item.quality = currentQuality - 1
                    }
                }
            } else {
                if (currentQuality < MAX_QUALITY_50) {
                    if (itemName == FineGoods.BACKSTAGE_PASS.rawName) {
                        var updatedPassQuality = currentQuality + 1

                        if (item.sellIn <= SELL_IN_10_DAYS) {
                            updatedPassQuality = currentQuality + 2
                        }

                        if (item.sellIn <= SELL_IN_5_DAYS) {
                            updatedPassQuality = currentQuality + 3
                        }

                        item.quality = updatedPassQuality
                    } else {
                        item.quality = currentQuality + 1
                    }
                }
            }

            if (itemName != FineGoods.SULFURAS.rawName) {
                item.sellIn = item.sellIn - 1
            }

            if (item.sellIn < 0) {
                if (itemName != FineGoods.AGED_BRIE.rawName) {
                    if (itemName == FineGoods.BACKSTAGE_PASS.rawName) {
                        item.quality = 0
                    } else {
                        if (currentQuality > 0) {
                            if (itemName != FineGoods.SULFURAS.rawName) {
                                // can't replace item.quality with itemQuality val; breaks unit tests
                                item.quality = item.quality - 1
                            }
                        }
                    }
                } else {
                    if (currentQuality < MAX_QUALITY_50) {
                        // can't replace item.quality with itemQuality val; breaks unit tests
                        item.quality = item.quality + 1
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

