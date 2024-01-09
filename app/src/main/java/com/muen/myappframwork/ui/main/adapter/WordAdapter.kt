package com.muen.myappframwork.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.muen.myappframwork.R
import com.muen.myappframwork.source.local.entity.WordEntity

class WordAdapter(private val words:List<WordEntity>): RecyclerView.Adapter<WordAdapter.WordAdapterVH>() {

    var updateClickListener :((WordEntity)->Unit)?=null
    var delClickListener :((WordEntity)->Unit)?=null

    class WordAdapterVH(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtWord: TextView = itemView.findViewById(R.id.txt_item_word)
        val editAuthor:EditText = itemView.findViewById(R.id.edit_author)
        val btnUpdate: Button = itemView.findViewById(R.id.btn_update)
        val btnDel:Button = itemView.findViewById(R.id.btn_del)

        companion object{
            fun create(parent: ViewGroup):WordAdapterVH{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_word,parent,false)
                return WordAdapterVH(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordAdapterVH {
        return WordAdapterVH.create(parent)
    }

    override fun getItemCount(): Int {
        return words.size
    }

    override fun onBindViewHolder(holder: WordAdapterVH, position: Int) {
        val word = words[position]
        holder.txtWord.text = word.word
        holder.editAuthor.setText(word.author)
        holder.btnDel.setOnClickListener {
            delClickListener?.invoke(word)
        }
        holder.btnUpdate.setOnClickListener {
            word.author = holder.editAuthor.text.toString()
            updateClickListener?.invoke(word)
        }
    }
}