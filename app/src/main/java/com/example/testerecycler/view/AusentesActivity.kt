package com.example.testerecycler.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testerecycler.R
import com.example.testerecycler.databinding.ActivityAusentesBinding
import com.example.testerecycler.databinding.ActivityMainBinding
import com.example.testerecycler.view.adapter.AlunoAdapter
import com.example.testerecycler.view.listener.OnAlunoListener
import com.example.testerecycler.view.viewmodel.AusentesViewModel

class AusentesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAusentesBinding
    private lateinit var viewModel: AusentesViewModel
    private var adapter = AlunoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAusentesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AusentesViewModel::class.java)

        binding.recyclerAusentes.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerAusentes.adapter = adapter

        binding.btnVoltar.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }

        val listener = object : OnAlunoListener {
            override fun onClick(id: Int) {
                val intent = Intent(applicationContext, AlunoFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt("id", id)
                intent.putExtras(bundle)

                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                viewModel.deleleAluno(id)
                viewModel.selectAusentes()
            }

        }

        adapter.getListener(listener)
        observe()

    }

    override fun onResume() {
        super.onResume()
        viewModel.selectAusentes()
    }

    fun observe() {
        viewModel.ausentes.observe(this, Observer {
            adapter.getAlunoList(it)
        })
    }
}