package com.example.tugaspertmuan6_dialog

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tugaspertmuan6_dialog.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var daySave = -1
    private var monthSave  = -1
    private var yearSave = -1
    private var hourSave = -1
    private var minuteSave = -1
    private val listOfMonth = arrayOf(
        "Januari",
        "Febuari",
        "Maret",
        "April",
        "Mei",
        "Juni",
        "Juli",
        "Agustus",
        "September",
        "Oktober",
        "November",
        "Desember"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val kehadiran = resources.getStringArray(R.array.kehadiran)

        with(binding){

            calendar.setOnDateChangeListener { _, year, month, dayOfMonth ->

                daySave = dayOfMonth
                monthSave  = month
                yearSave = year
            }

            val adapterKehadiran = ArrayAdapter(
                this@MainActivity,
                android.R.layout.simple_spinner_dropdown_item,
                kehadiran)

            adapterKehadiran.setDropDownViewResource(
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
            spinnerKehadiran.adapter = adapterKehadiran

            jam.setOnTimeChangedListener{_,hourOfDay,minute ->

                hourSave = hourOfDay
                minuteSave = minute
            }

            spinnerKehadiran.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long){

                        val keterangan = kehadiran[position]

                        if (position>0){
                            alasan.visibility = View.VISIBLE
                        }
                        else{
                            alasan.visibility = View.GONE
                        }

                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }
                }

            btnSubmit.setOnClickListener{
                val selectedTime = String.format("%02d:%02d", hourSave, minuteSave)
                val monthConvert = listOfMonth[monthSave]
                val selectedDate = "$daySave $monthConvert $yearSave"

                if (daySave == -1 || monthSave == -1 || yearSave == -1){
                    Toast.makeText(this@MainActivity, "Belum Memilih Tanggal", Toast.LENGTH_SHORT).show()
                }
                else if (hourSave == -1 || minuteSave == -1){
                    Toast.makeText(this@MainActivity, "Belum Memilih Jam", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this@MainActivity, "Presensi Berhasil $selectedDate jam $selectedTime", Toast.LENGTH_SHORT).show()
                }
            }


        }
    }
}

