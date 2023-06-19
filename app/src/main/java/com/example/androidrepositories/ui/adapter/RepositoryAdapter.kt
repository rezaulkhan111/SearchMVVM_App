package com.example.androidrepositories.ui.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidrepositories.data.model.RepositoryDetails
import com.example.androidrepositories.databinding.RvRepositoryItemBinding
import com.example.androidrepositories.ui.callback.RepositoryCallback
import com.example.androidrepositories.utils.AppConstant

class RepositoryAdapter(private val repositoryCallback: RepositoryCallback) :
    RecyclerView.Adapter<RepositoryAdapter.RepositoryVH>() {

    private var listRepositoryDet: MutableList<RepositoryDetails> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryVH {
        return RepositoryVH(
            RvRepositoryItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
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

    override fun onBindViewHolder(holder: RepositoryVH, position: Int) {
        try {
            holder.onBind(position)
            holder.itemView.setOnClickListener {
                val accountInformation = listRepositoryDet[position]
                repositoryCallback.repositoryItemClick(accountInformation, position)
            }
        } catch (_: Exception) {
        }
    }

    inner class RepositoryVH(val binding: RvRepositoryItemBinding) : BaseViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        override fun onBind(position: Int) {
            super.onBind(position)
            val repoModel = listRepositoryDet[position]

            binding.tvFullName.text = "Full name: " + repoModel.getFullName()
            binding.tvDescription.text = "Description: " + repoModel.getDescription()
            binding.tvStarCount.text = "Star count: " + repoModel.getStargazersCount().toString()
            binding.tvLastUpdate.text =
                "Last update " + AppConstant.getSortDataTime(repoModel.getUpdatedAt()!!)
        }
    }
}