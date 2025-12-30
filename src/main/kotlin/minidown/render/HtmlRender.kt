package dev.emko.minidown.render

import dev.emko.minidown.nodes.MarkdownNode

class HtmlRender {
    fun render(nodes: List<MarkdownNode>): String {
        val sb = StringBuilder()

        for (node in nodes) {
            val html = when (node) {
                is MarkdownNode.Header -> "<h${node.level}>${parseInline(node.text)}</h${node.level}>"
                is MarkdownNode.Paragraph -> "<p>${parseInline(node.text)}</p>"
                is MarkdownNode.ListItem -> "<li>${parseInline(node.text)}</li>"
                is MarkdownNode.UnorderedList -> {
                    val listItems = node.items.joinToString("") { render(listOf(it)) }
                    "<ul>\n$listItems</ul>"
                }
            }
            sb.append(html).append("\n")
        }

        return sb.toString()
    }

    private fun parseInline(text: String): String {
        var processed = text
        processed = processed.replace(Regex("\\*\\*(.*?)\\*\\*"), "<strong>$1</strong>")
        processed = processed.replace(Regex("\\*(.*?)\\*"), "<em>$1</em>")
        return processed
    }
}