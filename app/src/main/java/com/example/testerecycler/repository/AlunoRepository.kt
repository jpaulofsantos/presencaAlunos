package com.example.testerecycler.repository

import android.content.Context
import com.example.testerecycler.model.AlunoModel

class AlunoRepository(context: Context) {

    private val getDataBase = AlunoDataBase.getDataBase(context).alunoDAO()

    fun insertAluno(alunoModel: AlunoModel): Boolean {
        return getDataBase.insertAluno(alunoModel) > 0
    }

    fun updateAluno(alunoModel: AlunoModel): Boolean {
        return getDataBase.updateAluno(alunoModel) > 0
    }

    fun deleteAluno(id: Int) {
        val aluno = selectAluno(id)
        return getDataBase.deleteAluno(aluno)
    }

    fun selectAluno(id: Int): AlunoModel {
        return getDataBase.selectAluno(id)
    }

    fun selectAllAlunos(): List<AlunoModel> {
        return  getDataBase.selectAllAlunos()
    }

    fun selectPresentes(): List<AlunoModel> {
        return getDataBase.selectAlunoPresente()
    }

    fun selectAusentes() : List<AlunoModel> {
        return getDataBase.selectAlunoAusente()
    }

}