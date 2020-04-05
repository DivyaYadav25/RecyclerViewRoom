package com.example.airit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.airit.BR
import com.example.airit.R
import com.example.airit.databinding.ItemLayoutListBinding
import com.example.airit.room.ActorEntity
import com.squareup.picasso.Picasso

class ActorAdapter: RecyclerView.Adapter<ActorAdapter.DetailViewHolder>(){

    private  var detailList: MutableList<ActorEntity> = ArrayList<ActorEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val binding = DataBindingUtil.inflate<ItemLayoutListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_layout_list,parent,false)
        return DetailViewHolder(binding)
    }

    override fun getItemCount()= detailList.size


    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val detailList = detailList[position]
        holder.bind(detailList)
    }

    fun clear(){
        detailList.clear()
        notifyDataSetChanged()
    }
    private fun removeActor(adapterPosition: Int) {
        detailList.removeAt(adapterPosition)
        notifyItemRemoved(adapterPosition)
    }
    internal fun setActor(actor: MutableList<ActorEntity>) {
        this.detailList = actor
        notifyDataSetChanged()
    }

    inner class DetailViewHolder(var itemRowBinding : ItemLayoutListBinding): RecyclerView.ViewHolder(itemRowBinding.root){
        init{
            itemRowBinding.ivDelete.setOnClickListener{
                removeActor(adapterPosition)
            }
        }
        fun bind(detailList: ActorEntity){
            itemRowBinding.setVariable(BR.model,detailList)
            itemRowBinding.executePendingBindings()
            Picasso.get().load(detailList.image)
                .placeholder(R.drawable.ic_person)
                .into(itemRowBinding.ivProfile)

        }
    }
}