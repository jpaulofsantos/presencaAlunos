package com.example.testerecycler.view.viewholder

import android.app.AlertDialog
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.testerecycler.databinding.RowAlunoBinding
import com.example.testerecycler.model.AlunoModel
import com.example.testerecycler.view.listener.OnAlunoListener

class AlunoViewHolder(private var bind: RowAlunoBinding, private var listener: OnAlunoListener) : RecyclerView.ViewHolder(bind.root) {

    fun bind(aluno: AlunoModel) {
        bind.nomeAluno.text = aluno.name

        bind.nomeAluno.setOnClickListener {
            listener.onClick(aluno.id)
        }

        bind.nomeAluno.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de Aluno")
                .setMessage("Deseja remover o aluno ${aluno.name}?")
                .setPositiveButton("Sim") { dialog, which ->
                    listener.onDelete(aluno.id)
            Toast.makeText(itemView.context, "Aluno ${aluno.name} removido!", Toast.LENGTH_SHORT).show()}
                .setNegativeButton("Não", null)
                .create()
                .show()
            true
        }
    }
}