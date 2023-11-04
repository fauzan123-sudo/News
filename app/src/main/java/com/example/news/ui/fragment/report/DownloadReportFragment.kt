package com.example.news.ui.fragment.report

//import com.itextpdf.text.Document
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.os.StrictMode
import android.view.View
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.data.adapter.TableAdapter
import com.example.news.data.model.NoItem
import com.example.news.databinding.FragmentDownloadReportBinding
import com.example.news.ui.fragment.BaseFragment
import com.example.news.ui.fragment.report.pdfService.AppPermission
import com.example.news.ui.fragment.report.pdfService.FileHandler
import com.example.news.ui.fragment.report.pdfService.PdfService
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class DownloadReportFragment :
    BaseFragment<FragmentDownloadReportBinding>(FragmentDownloadReportBinding::inflate) {

    private val userData = listOf(
        NoItem( "Amir", "Tan", "Khan", "fafi", "loren", "zo"),
        NoItem( "Michael", "Calvin", "Gonzalez", "fafi", "loren", "zo"),
        NoItem( "Alim", "Bin", "Hasan", "fafi", "loren", "zo"),
        NoItem( "John", "Carl", "Tean", "fafi", "loren", "zo"),
        NoItem( "James", "Brown", "Cold", "fafi", "loren", "zo"),
        NoItem( "Virats", "Kader", "Can", "fafi", "loren", "zo"),
        NoItem( "Lim", "Lui", "Pao", "fafi", "loren", "zo"),
        NoItem( "Endro", "Tava", "Pero", "fafi", "loren", "zo"),
        NoItem( "Dani", "Pedro", "Leo", "fafi", "loren", "zo"),
        NoItem( "Leonardo", "Chris", "Luiza", "fafi", "loren", "zo"),
        NoItem( "Ahmad", "Dwy", "Fauzan", "fafi", "loren", "zo"),
        NoItem( "Siti", "Nur", "Azizah", "fafi", "loren", "zo"),
        NoItem( "Maria", "Lopez", "Martinez", "fafi", "loren", "zo"),
        NoItem( "David", "Liam", "Johnson", "fafi", "loren", "zo"),
        NoItem( "Fadil", "Rahman", "Putra", "fafi", "loren", "zo"),
        NoItem( "Cindy", "Wati", "Sari", "fafi", "loren", "zo"),
        NoItem( "Robert", "William", "Smith", "fafi", "loren", "zo"),
        NoItem( "Nina", "Sofia", "Hernandez", "fafi", "loren", "zo"),
        NoItem( "Andi", "Maulana", "Siregar", "fafi", "loren", "zo"),
        NoItem("Linda", "Chen", "Wu", "fafi", "loren", "zo"),
        NoItem("Budi", "Santoso", "Pranoto", "fafi", "loren", "zo"),
        NoItem("Mia", "Amanda", "Lestari", "fafi", "loren", "zo"),
        NoItem("Steven", "Adams", "Lee", "fafi", "loren", "zo"),
        NoItem("Rizky", "Ramadhan", "Perdana", "fafi", "loren", "zo"),
        NoItem("Aisha", "Fatimah", "Zulkarnain", "fafi", "loren", "zo"),
        NoItem("Paul", "Martin", "Davis", "fafi", "loren", "zo"),
        NoItem("Dina", "Wulandari", "Saputra", "fafi", "loren", "zo"),
        NoItem("Alex", "Andersen", "Jensen", "fafi", "loren", "zo"),
        NoItem("Eko", "Purnomo", "Utomo", "fafi", "loren", "zo"),
        NoItem("Monica", "Kristanti", "Surya", "fafi", "loren", "zo"),
        NoItem("George", "Taylor", "Williams", "fafi", "loren", "zo"),
        NoItem("Surya", "Putra", "Wijaya", "fafi", "loren", "zo"),
        NoItem("Karin", "Larsen", "Pedersen", "fafi", "loren", "zo"),
        NoItem("Rini", "Susanti", "Siregar", "fafi", "loren", "zo"),
        NoItem("Mark", "James", "Anderson", "fafi", "loren", "zo"),
        NoItem("Hani", "Indriani", "Kusuma", "fafi", "loren", "zo"),
        NoItem("Chris", "Hernandez", "Gomez", "fafi", "loren", "zo"),
        NoItem("Wawan", "Suhendra", "Saputra", "fafi", "loren", "zo"),
        NoItem("Megan", "Brown", "Wilson", "fafi", "loren", "zo"),
        NoItem("Anita", "Widya", "Sari", "fafi", "loren", "zo"),
        NoItem("William", "Jones", "Miller", "fafi", "loren", "zo"),
        NoItem("Lina", "Haryati", "Saputri", "fafi", "loren", "zo"),
        NoItem("Henry", "Lewis", "Harris", "fafi", "loren", "zo"),
        NoItem("Siti", "Aisyah", "Rahman", "fafi", "loren", "zo"),
        NoItem("Tom", "Harrison", "Young", "fafi", "loren", "zo"),
        NoItem("Siska", "Yuliana", "Wijaya", "fafi", "loren", "zo"),
        NoItem("Charles", "Brown", "Davis", "fafi", "loren", "zo"),
        NoItem("Dewi", "Cahyani", "Putri", "fafi", "loren", "zo"),
        NoItem("Samuel", "Wilson", "Smith", "fafi", "loren", "zo"),
        NoItem("Sandra", "Sari", "Lubis", "fafi", "loren", "zo")
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            downloadPdf.setOnClickListener {
                createPdf()
            }

            val tableAdapter = TableAdapter()

            tableAdapter.differ.submitList(userData)
            rvProduct.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = tableAdapter
            }
        }
        if (!AppPermission.permissionGranted(this)) {
            AppPermission.requestPermission(this)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == AppPermission.REQUEST_PERMISSION) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                AppPermission.requestPermission(this)
                toastErrorMessage("Permission should be allowed")
            }
        }
    }

    private fun isFileExists(fileName: String): Boolean {
        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val file = File(path, fileName)
        return file.exists()
    }

    private fun createPdf() {
        val onError: (Exception) -> Unit = { toastErrorMessage(it.message.toString()) }
        val onFinish: (File) -> Unit = { openFile(it) }
        val pdfService = PdfService()
        // Nama file awal
        val baseFileName = "Data Barang"
        var fileName = baseFileName
        var fileIndex = 1

        // Mengecek apakah file dengan nama yang sama sudah ada
        while (isFileExists(fileName)) {
            fileName = "$baseFileName ($fileIndex).pdf"
            fileIndex++
        }

        pdfService.createUserTable(
            data = userData,
            fileName = fileName,
            onFinish = onFinish,
            onError = onError
        )
    }

    private fun openFile(file: File) {
        val path = FileHandler().getPathFromUri(requireContext(), file.toUri())
        val pdfFile = path?.let { File(it) }
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        builder.detectFileUriExposure()
        val pdfIntent = Intent(Intent.ACTION_VIEW)
        pdfIntent.setDataAndType(pdfFile?.toUri(), "application/pdf")
        pdfIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        try {
            startActivity(pdfIntent)
        } catch (e: ActivityNotFoundException) {
            toastErrorMessage("Can't read pdf file")
        }
    }

    private fun toastErrorMessage(s: String) {
        Toast.makeText(requireContext(), s, Toast.LENGTH_SHORT).show()
    }
}