package com.example.hm2mouth6

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import androidx.core.view.isVisible
import com.example.hm2mouth6.databinding.ActivityMainBinding
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var list = arrayListOf<String>()
    private lateinit var adapter: SearchAdapter
    private var replaceWord = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        invis()
        initAdapter()
        saveText()
    }

    private fun saveText() {
        binding.btnSend.setOnClickListener {
            searchHashtag()
            binding.etEnter.text.clear()
        }
    }

    private fun searchHashtag() {
        val words = binding.btnSend.text.split(" ")
        for (word in words) {
            if (word.startsWith("#")) {
                val newWord = word.replace(Regex("[-+,;:{}]"), "")
                list.add(newWord)
            }
        }
    }

    private fun initAdapter() {
        adapter = SearchAdapter(list, this::clickListener)
        binding.recycler.adapter = adapter
    }

    @SuppressLint("SetTextI18n")
    private fun clickListener(hashTags: String) {
        binding.etEnter.setText(binding.etEnter.text.toString().replace(replaceWord,""))
        binding.etEnter.setText("${binding.etEnter.text}#${hashTags} ")
        binding.etEnter.setSelection(binding.etEnter.length())
    }
    private fun invis() {
        binding.etEnter.setOnClickListener {
            if (list.isNotEmpty()) {
                binding.recycler.isVisible = true
            }
        }
        binding.etEnter.addTextChangedListener {
            object : TextWatcher {
                override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    TODO("Not yet implemented")
                }
                override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    val words = text?.split(" ")
                    if (words != null) {
                        for (word in words) {
                            replaceWord = word
                            binding.recycler.isVisible = word.startsWith("#")
                        }
                    }
                }
                override fun afterTextChanged(p0: Editable?) {
                    TODO("Not yet implemented")
                }
            }
        }
    }
}
