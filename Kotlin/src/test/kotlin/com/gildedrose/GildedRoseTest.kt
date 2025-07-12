package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {
    @Test
    fun `0 day passed for regular item, sellIn and quality did not change`() {
        val item = updateQuality(
            passingDays = 0,
            Item(name = "regular item", sellIn = 1, quality = 0)
        )

        assertEquals("regular item", item.name)
        assertEquals(1, item.sellIn)
        assertEquals(0, item.quality)
    }

    @Test
    fun `1 day passed for regular item, sellIn is -1, quality did not change`() {
        val item = updateQuality(
            passingDays = 1,
            Item(name = "regular item", sellIn = 0, quality = 0)
        )

        assertEquals("regular item", item.name)
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

    private fun updateQuality(passingDays: Int, item: Item): Item {
        val gildedRose = GildedRose(listOf(item))

        for (i in 0..< passingDays) {
            gildedRose.updateQuality()
        }

        // to make testing easier, only going to deal with 1 item
        return gildedRose.items[0]
    }
}


