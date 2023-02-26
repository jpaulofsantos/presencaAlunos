package com.example.testerecycler.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testerecycler.model.AlunoModel
import com.example.testerecycler.repository.AlunoRepository

class AlunoFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AlunoRepository(application)

    private val listaAlunos = MutableLiveData<AlunoModel>()
    val aluno: LiveData<AlunoModel> = listaAlunos

    fun insert(alunoModel: AlunoModel) {
        repository.insertAluno(alunoModel)
    }

    fun getAluno(id: Int) {
        listaAlunos.value = repository.selectAluno(id)
    }

    fun updateAluno(alunoModel: AlunoModel) {
        repository.updateAluno(alunoModel)
    }
}