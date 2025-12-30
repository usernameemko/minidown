package dev.emko.minidown

import dev.emko.minidown.nodes.MarkdownNode
import dev.emko.minidown.render.HtmlRender

class MarkdownParser {
    private val renderer = HtmlRender()

    fun parse(input: String): String {
        val nodes = parseBlocks(input)
        return renderer.render(nodes)
    }

    private fun parseBlocks(input: String): List<MarkdownNode> {
        val nodes = mutableListOf<MarkdownNode>()
        val lines = input.lines()
        var i = 0

        while (i < lines.size) {
            val line = lines[i]
            val trimmed = line.trim()

            if (trimmed.isEmpty()) {
                i++
                continue
            }

            if (trimmed.startsWith("#")) {
                val level = trimmed.takeWhile { it == '#' }.length
                val text = trimmed.drop(level).trim()
                nodes.add(MarkdownNode.Header(level, text))
                i++
                continue
            }

            if (trimmed.startsWith("- ")) {
                val listItems = mutableListOf<MarkdownNode.ListItem>()
                while (i < lines.size && lines[i].trim().startsWith("- ")) {
                    listItems.add(MarkdownNode.ListItem(lines[i].trim().drop(2)))
                    i++
                }
                nodes.add(MarkdownNode.UnorderedList(listItems))
                continue
            }

            // Paragraph
            val paragraphLines = mutableListOf<String>()
            while (i < lines.size) {
                val currentTrimmed = lines[i].trim()
                if (currentTrimmed.isEmpty() || currentTrimmed.startsWith("#") || currentTrimmed.startsWith("- ")) {
                    break
                }
                paragraphLines.add(currentTrimmed)
                i++
            }
            if (paragraphLines.isNotEmpty()) {
                nodes.add(MarkdownNode.Paragraph(paragraphLines.joinToString(" ")))
            }
        }
        return nodes
    }
}