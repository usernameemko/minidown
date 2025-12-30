package dev.emko.minidown.nodes

//Markdown hierarchy
sealed class MarkdownNode {
    data class Header(val level: Int, val text: String) : MarkdownNode()
    data class Paragraph(val text: String) : MarkdownNode()
    data class ListItem(val text: String) : MarkdownNode()
    data class UnorderedList(val items: List<ListItem>) : MarkdownNode()
}