package com.example.testerecycler.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testerecycler.databinding.RowAlunoBinding
import com.example.testerecycler.model.AlunoModel
import com.example.testerecycler.view.listener.OnAlunoListener
import com.example.testerecycler.view.viewholder.AlunoViewHolder

class AlunoAdapter: RecyclerView.Adapter<AlunoViewHolder>() {

    private var alunoList: List<AlunoModel> = listOf()
    private lateinit var listenerAdapter: OnAlunoListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlunoViewHolder {
        val item = RowAlunoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlunoViewHolder(item, listenerAdapter)
    }

    override fun onBindViewHolder(holder: AlunoViewHolder, position: Int) {
        holder.bind(alunoList[position])
    }

    override fun getItemCount(): Int {
        return alunoList.count()
    }

    fun getAlunoList(listaAlunos: List<AlunoModel>) {
        alunoList = listaAlunos
        notifyDataSetChanged()
    }

    fun getListener(listener: OnAlunoListener) {
        listenerAdapter = listener
    }

}