package com.example.news.util

import com.itextpdf.text.Document
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfPTable

class PdfTable(private val document: Document) {

    fun addHeaderRow(header: Array<String>) {
        // Buat tabel
        val table = PdfPTable(header.size)
        table.widthPercentage = 100f

        // Tambahkan header
        for (headerItem in header) {
            table.addCell(Paragraph(headerItem))
        }

        // Tambahkan tabel ke dokumen
        document.add(table)
    }

    fun addRow(data: Array<Any>) {
        // Buat baris
        val row = PdfPTable(data.size)
        row.widthPercentage = 100f

        // Tambahkan data ke baris
        for (dataItem in data) {
            row.addCell(Paragraph(dataItem.toString()))
        }

        // Tambahkan baris ke tabel
        document.add(row)
    }
//
//    fun save(fileName: String) {
//        document.save(fileName)
//    }
}
