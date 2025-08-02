package com.gildedrose

class GildedRose(val items: List<Item>) {

    fun updateQuality() {
        for (i in items.indices) {
            if (items[i].name != FineGoods.AGED_BRIE.rawName && items[i].name != FineGoods.BACKSTAGE_PASS.rawName) {
                if (items[i].quality > 0) {
                    if (items[i].name != FineGoods.SULFURAS.rawName) {
                        items[i].quality = items[i].quality - 1
                    }
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1

                    if (items[i].name == FineGoods.BACKSTAGE_PASS.rawName) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1
                            }
                        }
                    }
                }
            }

            if (items[i].name != FineGoods.SULFURAS.rawName) {
                items[i].sellIn = items[i].sellIn - 1
            }

            if (items[i].sellIn < 0) {
                if (items[i].name != FineGoods.AGED_BRIE.rawName) {
                    if (items[i].name != FineGoods.BACKSTAGE_PASS.rawName) {
                        if (items[i].quality > 0) {
                            if (items[i].name != FineGoods.SULFURAS.rawName) {
                                items[i].quality = items[i].quality - 1
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality
                    }
                } else {
                    if (items[i].quality < 50) {
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

