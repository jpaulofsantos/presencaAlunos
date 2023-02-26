package com.example.testerecycler.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.testerecycler.model.AlunoModel
import com.example.testerecycler.repository.AlunoRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AlunoRepository(application)

    private val listaAlunos = MutableLiveData<List<AlunoModel>>()
    val alunoModel: LiveData<List<AlunoModel>> = listaAlunos

    fun selectAll() {
        listaAlunos.value = repository.selectAllAlunos()
    }

    fun deleteAluno(id: Int) {
        repository.deleteAluno(id)
    }


}