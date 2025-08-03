package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {
    @Test
    fun `item name should not change after calling updateQuality`() {
        val item = updateQuality(
            passingDays = 10,
            Item(name = "regular item", sellIn = 1, quality = 0)
        )

        assertEquals("regular item", item.name)
    }

    @Test
    fun `0 day passed for regular item, sellIn and quality did not change`() {
        val item = updateQuality(
            passingDays = 0,
            Item(name = "regular item", sellIn = 1, quality = 0)
        )

        assertEquals(1, item.sellIn)
        assertEquals(0, item.quality)
    }

    @Test
    fun `1 day passed for regular item, sellIn is -1, quality did not change`() {
        val item = updateQuality(
            passingDays = 1,
            Item(name = "regular item", sellIn = 0, quality = 0)
        )

        assertEquals(-1, item.sellIn)
        assertEquals(0, item.quality)
    }

    @Test
    fun `quality degrades twice as fast once the regular item sell by date has passed`() {
        val item = updateQuality(
            passingDays = 2,
            // day 1: quality = 6 - 1; 5
            // day 2: passed sell by date, quality = 5 - 2; 3
            Item(name = "regular item", sellIn = 1, quality = 6)
        )

        assertEquals(-1, item.sellIn)
        assertEquals(3, item.quality)
    }

    @Test
    fun `Aged Brie increases in Quality the older it gets`() {
        val item = updateQuality(
            passingDays = 1,
            Item(name = "Aged Brie", sellIn = 2, quality = 1)
        )

        assertEquals(1, item.sellIn)
        assertEquals(2, item.quality)
    }

    @Test
    fun `Aged Brie increases in Quality twice as fast past the sell by date`() {
        val item = updateQuality(
            passingDays = 2,
            Item(name = "Aged Brie", sellIn = 1, quality = 1)
        )

        assertEquals(-1, item.sellIn)
        assertEquals(4, item.quality)
    }

    @Test
    fun `Aged Brie Quality cannot be more than 50`() {
        val item = updateQuality(
            passingDays = 100,
            Item(name = "Aged Brie", sellIn = 1, quality = 1)
        )

        assertEquals(-99, item.sellIn)
        assertEquals(50, item.quality)
    }

    @Test
    fun `Sulfuras quality does not drop and sellIn is the same, sellIn = 1`() {
        val item = updateQuality(
            passingDays = 100,
            Item(name = "Sulfuras, Hand of Ragnaros", sellIn = 1, quality = 80)
        )

        assertEquals(1, item.sellIn)
        assertEquals(80, item.quality)
    }

    @Test
    fun `Sulfuras quality does not drop and sellIn is the same, sellIn is -1 `() {
        // this is needed to get the false use case on line 41
        // TODO: the sulfuras use cases needed to be refactored to make more sense
        val item = updateQuality(
            passingDays = 100,
            Item(name = "Sulfuras, Hand of Ragnaros", sellIn = -1, quality = 80)
        )

        assertEquals(-1, item.sellIn)
        assertEquals(80, item.quality)
    }

    @Test
    fun `Backstage passes Quality increase by 1 when there are 10 days left`() {
        val item = updateQuality(
            passingDays = 1,
            Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 11, quality = 1)
        )

        assertEquals(10, item.sellIn)
        assertEquals(2, item.quality)
    }

    @Test
    fun `Backstage passes Quality increase by 2 when there less than 10 days left`() {
        val item = updateQuality(
            passingDays = 2,
            Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 11, quality = 1)
        )

        assertEquals(9, item.sellIn)
        assertEquals(4, item.quality)
    }

    @Test
    fun `Backstage passes Quality increase by 2 when there are 5 days left`() {
        val item = updateQuality(
            passingDays = 6,
            Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 11, quality = 1)
        )

        assertEquals(5, item.sellIn)
        assertEquals(12, item.quality)
    }

    @Test
    fun `Backstage passes Quality increase by 3 when there less than 5 days left`() {
        val item = updateQuality(
            passingDays = 7,
            Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 11, quality = 1)
        )

        assertEquals(4, item.sellIn)
        assertEquals(15, item.quality)
    }

    @Test
    fun `Backstage passes Quality drops to 0 after the concert`() {
        val item = updateQuality(
            passingDays = 12,
            Item(name = "Backstage passes to a TAFKAL80ETC concert", sellIn = 11, quality = 1)
        )

        assertEquals(-1, item.sellIn)
        assertEquals(0, item.quality)
    }

    private fun updateQuality(passingDays: Int, item: Item): Item {
        val gildedRose = GildedRose(listOf(item))

        for (i in 0..< passingDays) {
            gildedRose.updateQuality()
        }

        // to make testing easier, only going to deal with 1 item
        return gildedRose.items[0]
    }
}


