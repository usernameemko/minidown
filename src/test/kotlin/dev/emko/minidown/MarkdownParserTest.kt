package dev.emko.minidown

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MarkdownParserTest {
    private val parser = MarkdownParser()

    @Test
    fun testHeaderParsing() {
        val input = "# Header 1"
        val expected = "<h1>Header 1</h1>\n"
        assertEquals(expected, parser.parse(input))
    }

    @Test
    fun testParagraphParsing() {
        val input = "This is a paragraph."
        val expected = "<p>This is a paragraph.</p>\n"
        assertEquals(expected, parser.parse(input))
    }

    @Test
    fun testListItemParsing() {
        val input = "- Item 1\n- Item 2"
        val expected = "<ul>\n<li>Item 1</li>\n<li>Item 2</li>\n</ul>\n"
        assertEquals(expected, parser.parse(input))
    }

    @Test
    fun testMultiLineParagraph() {
        val input = "Line 1\nLine 2"
        val expected = "<p>Line 1 Line 2</p>\n"
        assertEquals(expected, parser.parse(input))
    }

    @Test
    fun testBoldAndItalic() {
        val input = "**bold** and *italic*"
        val expected = "<p><strong>bold</strong> and <em>italic</em></p>\n"
        assertEquals(expected, parser.parse(input))
    }
}
