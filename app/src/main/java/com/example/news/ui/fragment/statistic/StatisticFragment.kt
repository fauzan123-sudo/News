package com.example.news.ui.fragment.statistic

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import com.example.news.databinding.FragmentStatisticBinding
import com.example.news.ui.fragment.BaseFragment
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatisticFragment :
    BaseFragment<FragmentStatisticBinding>(FragmentStatisticBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpData()

    }

    private fun setUpData() {
        val dataValues = arrayOf(0, 0, 0, 0, 0)
        val lineChart = binding.lineChart
        val entries = ArrayList<Entry>()
        for (i in dataValues.indices) {
            entries.add(Entry(i.toFloat() + 1, dataValues[i].toFloat()))
        }

        // Konfigurasi dataset
        val dataSet = LineDataSet(entries, "Data Set 1")
        dataSet.valueTextColor = Color.RED
        dataSet.color = Color.parseColor("#00A4FF")
        dataSet.valueTextColor = Color.WHITE
        dataSet.valueTextSize = 12f
        dataSet.circleHoleColor = Color.parseColor("#00FF85")
        dataSet.circleRadius = 8f
        dataSet.circleHoleRadius = 4f
        dataSet.setCircleColor(Color.WHITE)
        dataSet.lineWidth = 3f
        dataSet.setDrawValues(false)

        // Membuat gradien
        dataSet.setDrawFilled(true)
        dataSet.fillDrawable = getGradientDrawable()

        // Konfigurasi LineData
        val lineData = LineData(dataSet)

        // Menggunakan mode CUBIC_BEZIER untuk membuat garis cekung
        dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER

        // Konfigurasi LineChart
        lineChart.data = lineData
        lineChart.setTouchEnabled(true)
        lineChart.setPinchZoom(true)
        lineChart.description.isEnabled = false

        // Konfigurasi sumbu X
        val xAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.textColor = Color.BLACK
        xAxis.setLabelCount(dataValues.size, true)

        // Konfigurasi sumbu Y
        val leftYAxis = lineChart.axisLeft
        leftYAxis.textColor = Color.WHITE
        leftYAxis.setDrawGridLines(false)
        val rightYAxis = lineChart.axisRight
        rightYAxis.setDrawGridLines(false)

        // Konfigurasi legenda
        val legend = lineChart.legend
        legend.textColor = Color.WHITE

        // Menambahkan listener untuk menangani klik pada titik data
        lineChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry?, h: Highlight?) {

            }

            override fun onNothingSelected() {

            }
        })
    }

    private fun getGradientDrawable(): Drawable {
        val colors = intArrayOf(
            Color.parseColor("#66C8FE"),
            Color.parseColor("#73CDFF"),
            Color.parseColor("#008AD5FF"),
        )
        return GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors)
    }
}


