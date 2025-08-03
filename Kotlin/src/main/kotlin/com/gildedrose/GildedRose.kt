package com.gildedrose

class GildedRose(val items: List<Item>) {

    fun updateQuality() {
        for (i in items.indices) {
            val itemName = items[i].name
            val itemQuality = items[i].quality

            if (itemName != FineGoods.AGED_BRIE.rawName && itemName != FineGoods.BACKSTAGE_PASS.rawName) {
                if (itemQuality > 0) {
                    if (itemName != FineGoods.SULFURAS.rawName) {
                        items[i].quality = items[i].quality - 1
                    }
                }
            } else {
                if (itemQuality < 50) {
                    items[i].quality = items[i].quality + 1

                    if (itemName == FineGoods.BACKSTAGE_PASS.rawName) {
                        if (items[i].sellIn < 11) {
                            if (itemQuality < 50) {
                                items[i].quality = items[i].quality + 1
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (itemQuality < 50) {
                                items[i].quality = items[i].quality + 1
                            }
                        }
                    }
                }
            }

            if (itemName != FineGoods.SULFURAS.rawName) {
                items[i].sellIn = items[i].sellIn - 1
            }

            if (items[i].sellIn < 0) {
                if (itemName != FineGoods.AGED_BRIE.rawName) {
                    if (itemName != FineGoods.BACKSTAGE_PASS.rawName) {
                        if (itemQuality > 0) {
                            if (itemName != FineGoods.SULFURAS.rawName) {
                                items[i].quality = items[i].quality - 1
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality
                    }
                } else {
                    if (itemQuality < 50) {
                        items[i].quality = items[i].quality + 1
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

