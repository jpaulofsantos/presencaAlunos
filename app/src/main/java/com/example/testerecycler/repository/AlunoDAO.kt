package com.example.testerecycler.repository

import androidx.room.*
import com.example.testerecycler.model.AlunoModel

@Dao
interface AlunoDAO {

    @Insert
    fun insertAluno(alunoModel: AlunoModel): Long

    @Update
    fun updateAluno(alunoModel: AlunoModel): Int

    @Delete
    fun deleteAluno(alunoModel: AlunoModel)

    @Query("SELECT * FROM Aluno WHERE id = :id")
    fun selectAluno(id: Int): AlunoModel

    @Query("SELECT * FROM Aluno")
    fun selectAllAlunos(): List<AlunoModel>

    @Query("SELECT * FROM Aluno WHERE presenca = 1")
    fun selectAlunoPresente(): List<AlunoModel>

    @Query("SELECT * FROM Aluno WHERE presenca = 0")
    fun selectAlunoAusente(): List<AlunoModel>




}