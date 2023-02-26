package com.example.testerecycler.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testerecycler.R
import com.example.testerecycler.databinding.ActivityPresentesBinding
import com.example.testerecycler.view.adapter.AlunoAdapter
import com.example.testerecycler.view.listener.OnAlunoListener
import com.example.testerecycler.view.viewmodel.PresentesViewModel

class PresentesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPresentesBinding
    private lateinit var viewModel: PresentesViewModel
    private var adapter = AlunoAdapter()
    private var alunoId = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityPresentesBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this).get(PresentesViewModel::class.java)

        binding.recyclerPresentes.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerPresentes.adapter = adapter

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
                viewModel.deleteAluno(id)
                viewModel.selectPresentes()
            }
        }

        observe()
        adapter.getListener(listener)
    }

    override fun onResume() {
        super.onResume()
        viewModel.selectPresentes()
    }

    fun observe() {
        viewModel.alunosLista.observe(this, Observer {
            adapter.getAlunoList(it)
        })
    }
}