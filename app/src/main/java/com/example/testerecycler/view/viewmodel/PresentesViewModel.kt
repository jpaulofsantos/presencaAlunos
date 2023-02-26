package com.example.testerecycler.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testerecycler.model.AlunoModel
import com.example.testerecycler.repository.AlunoRepository

class PresentesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AlunoRepository(application)

    private val alunoGet = MutableLiveData<List<AlunoModel>>()
    var alunosLista: LiveData<List<AlunoModel>> = alunoGet

    fun updateAluno(alunoModel: AlunoModel) {
        repository.updateAluno(alunoModel)
    }

    fun deleteAluno(id: Int) {
        repository.deleteAluno(id)
    }

    fun getAluno(id: Int) {

    }

    fun selectPresentes() {
        alunoGet.value = repository.selectPresentes()
    }
}