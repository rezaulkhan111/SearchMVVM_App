package com.example.androidrepositories.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidrepositories.R
import com.example.androidrepositories.data.model.RepositoryDetails
import com.example.androidrepositories.ui.callback.RepositoryCallback
import com.example.androidrepositories.utils.AppConstant

class MultipleLayoutAdapter(private val repositoryCallback: RepositoryCallback) :
    RecyclerView.Adapter<MultipleLayoutAdapter.MultipleViewVH>() {
    companion object {
        val VIEW_TYPE = 0
        val VIEW_TYPE1 = 1
        val VIEW_TYPE2 = 2
    }

    private var listRepositoryDet: MutableList<RepositoryDetails> = mutableListOf()
//    RvRepositoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultipleViewVH {
        val layout = when (viewType) {
            VIEW_TYPE -> R.layout.rv_view_item
            VIEW_TYPE1 -> R.layout.rv_view_item1
            VIEW_TYPE2 -> R.layout.rv_view_item2
            else -> {
                throw IllegalArgumentException("Invalid type")
            }
        }
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layout, parent, false)
        return MultipleViewVH(view, viewType)
    }

    override fun getItemCount(): Int {
        return if (listRepositoryDet.size > 0) {
            listRepositoryDet.size
        } else {
            0
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setRepository(listRepository: MutableList<RepositoryDetails>) {
        listRepositoryDet = listRepository
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MultipleViewVH, position: Int) {
        try {
            holder.bind(listRepositoryDet[position], position)
            holder.itemView.setOnClickListener {
                val accountInformation = listRepositoryDet[position]
                repositoryCallback.repositoryItemClick(accountInformation, position)
            }
        } catch (_: Exception) {
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> VIEW_TYPE
            1 -> VIEW_TYPE1
            2 -> VIEW_TYPE2
            else -> {
                VIEW_TYPE
            }
        }
    }

    inner class MultipleViewVH(itemView: View, val viewType: Int) :
        RecyclerView.ViewHolder(itemView) {

        private fun viewType(repoModel: RepositoryDetails) {
            itemView.findViewById<TextView>(R.id.tv_full_name).text =
                "Full name: " + repoModel.getFullName()
            itemView.findViewById<TextView>(R.id.tv_description).text =
                "Description: " + repoModel.getDescription()
            itemView.findViewById<TextView>(R.id.tv_star_count).text =
                "Star count: " + repoModel.getStargazersCount().toString()
            itemView.findViewById<TextView>(R.id.tv_last_update).text =
                "Last update " + AppConstant.getSortDataTime(repoModel.getUpdatedAt()!!)
        }

        private fun viewType1(repoModel: RepositoryDetails) {
            val ivOwnerAvatar = itemView.findViewById<ImageView>(R.id.iv_owner_avatar)
            AppConstant.loadImage(
                repoModel.getOwner()!!.getAvatarUrl(),
                ivOwnerAvatar
            )
            itemView.findViewById<TextView>(R.id.tv_full_name).text =
                "Full name: " + repoModel.getFullName()
            itemView.findViewById<TextView>(R.id.tv_description).text =
                "Description: " + repoModel.getDescription()
            itemView.findViewById<TextView>(R.id.tv_star_count).text =
                "Star count: " + repoModel.getStargazersCount().toString()
            itemView.findViewById<TextView>(R.id.tv_last_update).text =
                "Last update " + AppConstant.getSortDataTime(repoModel.getUpdatedAt()!!)
        }

        private fun viewType2(repoModel: RepositoryDetails) {
            itemView.findViewById<TextView>(R.id.tv_full_name).text =
                "Full name: " + repoModel.getFullName()
            itemView.findViewById<TextView>(R.id.tv_description).text =
                "Description: " + repoModel.getDescription()
            itemView.findViewById<TextView>(R.id.tv_last_update).text =
                "Last update " + AppConstant.getSortDataTime(repoModel.getUpdatedAt()!!)
        }

        fun bind(dataModel: RepositoryDetails, position: Int) {
            when (viewType) {
                VIEW_TYPE -> viewType(dataModel)
                VIEW_TYPE1 -> viewType1(dataModel)
                VIEW_TYPE2 -> viewType2(dataModel)
            }
        }
    }
}