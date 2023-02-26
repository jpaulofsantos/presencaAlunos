package com.example.testerecycler.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testerecycler.databinding.ActivityMainBinding
import com.example.testerecycler.view.adapter.AlunoAdapter
import com.example.testerecycler.view.listener.OnAlunoListener
import com.example.testerecycler.view.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = AlunoAdapter()
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding.recycler.layoutManager = LinearLayoutManager(applicationContext)
        binding.recycler.adapter = adapter

        binding.btnCadastrar.setOnClickListener {
            startActivity(Intent(applicationContext, AlunoFormActivity::class.java))
        }

        binding.btnPresentes.setOnClickListener {
            startActivity(Intent(applicationContext, PresentesActivity::class.java))
            finish()
        }

        binding.btnAusentes.setOnClickListener {
            startActivity(Intent(applicationContext, AusentesActivity::class.java))
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
                viewModel.selectAll()
            }
        }

        adapter.getListener(listener)

        observe()
    }

    override fun onResume() {
        super.onResume()
        viewModel.selectAll()
    }

    fun observe() {
        viewModel.alunoModel.observe(this, Observer {
            adapter.getAlunoList(it)
        })
    }
}