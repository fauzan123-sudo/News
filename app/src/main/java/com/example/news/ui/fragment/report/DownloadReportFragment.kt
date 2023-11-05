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
        User(1, "Amir", "Tan", "Khan", "fafi", "loren", "zo"),
        User(2, "Michael", "Calvin", "Gonzalez", "fafi", "loren", "zo"),
        User(3, "Alim", "Bin", "Hasan", "fafi", "loren", "zo"),
        User(4, "John", "Carl", "Tean", "fafi", "loren", "zo"),
        User(5, "James", "Brown", "Cold", "fafi", "loren", "zo"),
        User(6, "Virats", "Kader", "Can", "fafi", "loren", "zo"),
        User(7, "Lim", "Lui", "Pao", "fafi", "loren", "zo"),
        User(8, "Endro", "Tava", "Pero", "fafi", "loren", "zo"),
        User(9, "Dani", "Pedro", "Leo", "fafi", "loren", "zo"),
        User(10, "Leonardo", "Chris", "Luiza", "fafi", "loren", "zo"),
        User(11, "Ahmad", "Dwy", "Fauzan", "fafi", "loren", "zo"),
        User(12, "Siti", "Nur", "Azizah", "fafi", "loren", "zo"),
        User(13, "Maria", "Lopez", "Martinez", "fafi", "loren", "zo"),
        User(14, "David", "Liam", "Johnson", "fafi", "loren", "zo"),
        User(15, "Fadil", "Rahman", "Putra", "fafi", "loren", "zo"),
        User(16, "Cindy", "Wati", "Sari", "fafi", "loren", "zo"),
        User(17, "Robert", "William", "Smith", "fafi", "loren", "zo"),
        User(18, "Nina", "Sofia", "Hernandez", "fafi", "loren", "zo"),
        User(19, "Andi", "Maulana", "Siregar", "fafi", "loren", "zo"),
        User(20, "Linda", "Chen", "Wu", "fafi", "loren", "zo"),
        User(21, "Budi", "Santoso", "Pranoto", "fafi", "loren", "zo"),
        User(22, "Mia", "Amanda", "Lestari", "fafi", "loren", "zo"),
        User(23, "Steven", "Adams", "Lee", "fafi", "loren", "zo"),
        User(24, "Rizky", "Ramadhan", "Perdana", "fafi", "loren", "zo"),
        User(25, "Aisha", "Fatimah", "Zulkarnain", "fafi", "loren", "zo"),
        User(26, "Paul", "Martin", "Davis", "fafi", "loren", "zo"),
        User(27, "Dina", "Wulandari", "Saputra", "fafi", "loren", "zo"),
        User(28, "Alex", "Andersen", "Jensen", "fafi", "loren", "zo"),
        User(29, "Eko", "Purnomo", "Utomo", "fafi", "loren", "zo"),
        User(30, "Monica", "Kristanti", "Surya", "fafi", "loren", "zo"),
        User(31, "George", "Taylor", "Williams", "fafi", "loren", "zo"),
        User(32, "Surya", "Putra", "Wijaya", "fafi", "loren", "zo"),
        User(33, "Karin", "Larsen", "Pedersen", "fafi", "loren", "zo"),
        User(34, "Rini", "Susanti", "Siregar", "fafi", "loren", "zo"),
        User(35, "Mark", "James", "Anderson", "fafi", "loren", "zo"),
        User(36, "Hani", "Indriani", "Kusuma", "fafi", "loren", "zo"),
        User(37, "Chris", "Hernandez", "Gomez", "fafi", "loren", "zo"),
        User(38, "Wawan", "Suhendra", "Saputra", "fafi", "loren", "zo"),
        User(39, "Megan", "Brown", "Wilson", "fafi", "loren", "zo"),
        User(40, "Anita", "Widya", "Sari", "fafi", "loren", "zo"),
        User(41, "William", "Jones", "Miller", "fafi", "loren", "zo"),
        User(42, "Lina", "Haryati", "Saputri", "fafi", "loren", "zo"),
        User(43, "Henry", "Lewis", "Harris", "fafi", "loren", "zo"),
        User(44, "Siti", "Aisyah", "Rahman", "fafi", "loren", "zo"),
        User(45, "Tom", "Harrison", "Young", "fafi", "loren", "zo"),
        User(46, "Siska", "Yuliana", "Wijaya", "fafi", "loren", "zo"),
        User(47, "Charles", "Brown", "Davis", "fafi", "loren", "zo"),
        User(48, "Dewi", "Cahyani", "Putri", "fafi", "loren", "zo"),
        User(49, "Samuel", "Wilson", "Smith", "fafi", "loren", "zo"),
        User(50, "Sandra", "Sari", "Lubis", "fafi", "loren", "zo")
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