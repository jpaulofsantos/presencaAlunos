package com.example.testerecycler.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testerecycler.R
import com.example.testerecycler.databinding.ActivityAlunoFormBinding
import com.example.testerecycler.model.AlunoModel
import com.example.testerecycler.view.viewmodel.AlunoFormViewModel

class AlunoFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityAlunoFormBinding
    private lateinit var viewModel: AlunoFormViewModel
    private var alunoId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlunoFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AlunoFormViewModel::class.java)

        binding.btnSalvar.setOnClickListener(this)
        binding.radioPresente.isChecked = true

        loadMainData()
        observe()

    }

    override fun onClick(view: View) {
        if (view.id == R.id.btn_salvar) {
            var nomeAluno = binding.editName.text.toString()
            var radioPresenca = binding.radioPresente.isChecked

            var alunoModel = AlunoModel()
            alunoModel.id = alunoId
            alunoModel.name = nomeAluno
            alunoModel.presenca = radioPresenca

            if (alunoModel.id != 0) {
                viewModel.updateAluno(alunoModel)
                Toast.makeText(applicationContext, "Aluno ${alunoModel.name} alterado com sucesso!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                viewModel.insert(alunoModel)
                Toast.makeText(applicationContext, "Aluno ${alunoModel.name} cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun loadMainData() {
        val bundle = intent.extras
        if (bundle != null) {
            alunoId = bundle.getInt("id")
            viewModel.getAluno(alunoId)
        }
    }

    private fun observe() {
        viewModel.aluno.observe(this, Observer {
            binding.editName.setText(it.name)
            if (it.presenca) {
                binding.radioPresente.isChecked = true
                binding.radioAusente.isChecked = false
            } else {
                binding.radioPresente.isChecked = false
                binding.radioAusente.isChecked = true
            }
            binding.btnSalvar.text = "Editar"
        })
    }
}