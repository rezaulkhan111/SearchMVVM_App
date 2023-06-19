package com.example.androidrepositories.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.androidrepositories.data.model.RepositoryDetails
import com.example.androidrepositories.databinding.FragmentDetailsBinding
import com.example.androidrepositories.utils.AppConstant
import com.example.androidrepositories.view_model.SharedVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private val vmShared: SharedVM by activityViewModels()

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val argParameterRepository = AppConstant
            .getObjectFromJson(
                vmShared.sharedData, RepositoryDetails()
            ) as RepositoryDetails?
        argParameterRepository?.apply {
            AppConstant.loadImage(
                getOwner()!!.getAvatarUrl(),
                binding.ivOwnerImage
            )
            binding.tvOwnerName.text = getOwner()!!.getLogin()
            binding.tvRepositoryDescription.text = getDescription()
            binding.tvRepositoryLastUpdateDate.text =
                AppConstant.getSortDataTime(getUpdatedAt()!!)
        }
    }
}