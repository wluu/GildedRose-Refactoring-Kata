package com.gildedrose

class GildedRose(val items: List<Item>) {

    fun updateQuality() {
        items.forEachIndexed { index, item ->
            val itemName = item.name
            val itemQuality = item.quality

            if (itemName != FineGoods.AGED_BRIE.rawName && itemName != FineGoods.BACKSTAGE_PASS.rawName) {
                if (itemQuality > 0) {
                    if (itemName != FineGoods.SULFURAS.rawName) {
                        items[index].quality = item.quality - 1
                    }
                }
            } else {
                if (itemQuality < 50) {
                    items[index].quality = item.quality + 1

                    if (itemName == FineGoods.BACKSTAGE_PASS.rawName) {
                        if (item.sellIn < 11) {
                            if (itemQuality < 50) {
                                items[index].quality = item.quality + 1
                            }
                        }

                        if (item.sellIn < 6) {
                            if (itemQuality < 50) {
                                items[index].quality = item.quality + 1
                            }
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
                                items[index].quality = item.quality - 1
                            }
                        }
                    } else {
                        items[index].quality = item.quality - item.quality
                    }
                } else {
                    if (itemQuality < 50) {
                        items[index].quality = item.quality + 1
                    }
                }
            }
        }
    }
}

// don't want to deal with string literals
enum class FineGoods(val rawName: String) {
    AGED_BRIE("Aged Brie"),
    BACKSTAGE_PASS("Backstage passes to a TAFKAL80ETC concert"),
    SULFURAS("Sulfuras, Hand of Ragnaros")
}

