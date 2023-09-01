package com.example.androidrepositories.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.androidrepositories.data.model.RepositoryDetails
import com.example.androidrepositories.databinding.RvViewItem1Binding
import com.example.androidrepositories.databinding.RvViewItem2Binding
import com.example.androidrepositories.databinding.RvViewItemBinding
import com.example.androidrepositories.ui.callback.RepositoryCallback
import com.example.androidrepositories.utils.AppConstant

class MultiLayoutVBAdapter(private val repositoryCallback: RepositoryCallback) :
    RecyclerView.Adapter<MultiLayoutVBAdapter.MultipleViewVH>() {


    private var listRepositoryDet: MutableList<RepositoryDetails> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultipleViewVH {
        return when (viewType) {
            AppConstant.VIEW_TYPE -> {
                MultipleViewVH(
                    RvViewItemBinding.inflate(
                        LayoutInflater
                            .from(parent.context), parent, false
                    )
                )
            }

            AppConstant.VIEW_TYPE1 -> {
                MultipleViewVH(
                    RvViewItem1Binding.inflate(
                        LayoutInflater
                            .from(parent.context), parent, false
                    )
                )
            }

            AppConstant.VIEW_TYPE2 -> {
                MultipleViewVH(
                    RvViewItem2Binding.inflate(
                        LayoutInflater
                            .from(parent.context), parent, false
                    )
                )
            }

            else -> {
                throw IllegalArgumentException("Invalid type")
            }
        }
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
            holder.bind(listRepositoryDet[position], listRepositoryDet[position].getViewType()!!)
            holder.itemView.setOnClickListener {
                val accountInformation = listRepositoryDet[position]
                repositoryCallback.repositoryItemClick(accountInformation, position)
            }
        } catch (_: Exception) {
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (listRepositoryDet[position].getViewType()!!) {
            0 -> AppConstant.VIEW_TYPE
            1 -> AppConstant.VIEW_TYPE1
            2 -> AppConstant.VIEW_TYPE2
            else -> {
                AppConstant.VIEW_TYPE
            }
        }
    }

    inner class MultipleViewVH(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

        private var bindingItem0: RvViewItemBinding? = null
        private var bindingItem1: RvViewItem1Binding? = null
        private var bindingItem2: RvViewItem2Binding? = null

//        constructor(binding: ViewBinding) : super(binding.root) {
//        }

        private fun viewType(repoModel: RepositoryDetails) {
            bindingItem0 = binding as RvViewItemBinding

//            itemView.findViewById<TextView>(R.id.tv_full_name).text =
//                "Full name: " + repoModel.getFullName()
//            itemView.findViewById<TextView>(R.id.tv_description).text =
//                "Description: " + repoModel.getDescription()
//            itemView.findViewById<TextView>(R.id.tv_forks_count).text =
//                "Forks count: " + repoModel.getForksCount()
            bindingItem0!!.tvFullName.text =
                "Full name: " + repoModel.getFullName()
            bindingItem0!!.tvDescription.text =
                "Description: " + repoModel.getDescription()
            bindingItem0!!.tvForksCount.text =
                "Forks count: " + repoModel.getForksCount()
        }

        private fun viewType1(repoModel: RepositoryDetails) {
            bindingItem1 = binding as RvViewItem1Binding

            AppConstant.loadImage(
                repoModel.getOwner()!!.getAvatarUrl(),
                bindingItem1!!.ivOwnerAvatar
            )
//            itemView.findViewById<TextView>(R.id.tv_full_name).text =
//                "Full name: " + repoModel.getFullName()
//            itemView.findViewById<TextView>(R.id.tv_description).text =
//                "Description: " + repoModel.getDescription()
//            itemView.findViewById<TextView>(R.id.tv_star_count).text =
//                "Star count: " + repoModel.getStargazersCount().toString()
//            itemView.findViewById<TextView>(R.id.tv_last_update).text =
//                "Last update " + AppConstant.getSortDataTime(repoModel.getUpdatedAt()!!)

            bindingItem1!!.tvFullName.text =
                "Full name: " + repoModel.getFullName()
            bindingItem1!!.tvDescription.text =
                "Description: " + repoModel.getDescription()
            bindingItem1!!.tvStarCount.text =
                "Star count: " + repoModel.getStargazersCount().toString()
            bindingItem1!!.tvLastUpdate.text =
                "Last update " + AppConstant.getSortDataTime(repoModel.getUpdatedAt()!!)
        }

        private fun viewType2(repoModel: RepositoryDetails) {
            bindingItem2 = binding as RvViewItem2Binding

            bindingItem2!!.tvFullName.text =
                "Full name: " + repoModel.getFullName()
            bindingItem2!!.tvDescription.text =
                "Description: " + repoModel.getDescription()
            bindingItem2!!.tvLastUpdate.text =
                "Last update " + AppConstant.getSortDataTime(repoModel.getUpdatedAt()!!)
            bindingItem2!!.pbStarCount.apply {
                min = 0
                max = 100
                progress = (repoModel.getStargazersCount()!! / 200)
            }
        }

        fun bind(dataModel: RepositoryDetails, position: Int) {
            when (position) {
                AppConstant.VIEW_TYPE -> viewType(dataModel)
                AppConstant.VIEW_TYPE1 -> viewType1(dataModel)
                AppConstant.VIEW_TYPE2 -> viewType2(dataModel)
            }
        }
    }
}