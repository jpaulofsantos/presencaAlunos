package com.example.testerecycler.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testerecycler.model.AlunoModel
import com.example.testerecycler.repository.AlunoRepository

class AusentesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AlunoRepository(application)

    private val listaAusentes = MutableLiveData<List<AlunoModel>>()
    var ausentes: LiveData<List<AlunoModel>> = listaAusentes

    fun selectAusentes() {
        listaAusentes.value = repository.selectAusentes()
    }

    fun deleleAluno(id: Int) {
        repository.deleteAluno(id)
    }
}