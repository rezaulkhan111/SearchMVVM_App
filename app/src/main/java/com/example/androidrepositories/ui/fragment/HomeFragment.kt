package com.example.androidrepositories.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidrepositories.R
import com.example.androidrepositories.data.Status
import com.example.androidrepositories.data.model.RepositoryDetails
import com.example.androidrepositories.databinding.FragmentHomeBinding
import com.example.androidrepositories.ui.adapter.RepositoryAdapter
import com.example.androidrepositories.ui.callback.RepositoryCallback
import com.example.androidrepositories.utils.AppConstant
import com.example.androidrepositories.view_model.GithubVM
import com.example.androidrepositories.view_model.SharedVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), RepositoryCallback {
    private val viewModelGitHub: GithubVM by viewModels()
    private val vmShared: SharedVM by activityViewModels()

    private lateinit var binding: FragmentHomeBinding
    private lateinit var navController: NavController
    private lateinit var repoAdapter: RepositoryAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private var listRepositoryDet: MutableList<RepositoryDetails> = mutableListOf()
    private var lastRefreshTime: Long = 0

    private var isLoading = false
    private var qSearchWord = ""
    private var currentPage = 1
    private var perPage = 10

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        navController = findNavController()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repoAdapter = RepositoryAdapter(this)
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewModelGitHub.getOfflineRepositories().observe(viewLifecycleOwner) {
            listRepositoryDet = it.getItems()!!
            setUAdapterData()
        }

        binding.tietSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.tilSearchHeader.error = null
            }

            override fun afterTextChanged(p0: Editable?) {
                binding.tilSearchHeader.error = null
            }
        })

        binding.ibSearch.setOnClickListener {
            qSearchWord = binding.tietSearch.text.toString()
            if (qSearchWord.isNotEmpty()) {
                if (System.currentTimeMillis() - lastRefreshTime >= 30 * 60 * 1000) {
                    lastRefreshTime = System.currentTimeMillis()
                    fetchRepositories()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Next 30 minutes later can refresh the data",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                binding.tilSearchHeader.error = "Please search anything"
            }
        }

        binding.ibSort.setOnClickListener {
            val sortRepository =
                listRepositoryDet.sortedByDescending { it.getStargazersCount() }.toMutableList()
            listRepositoryDet = sortRepository
            setUAdapterData()
        }
        binding.rvRepository.addOnScrollListener(onScrollListener)
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

            if (!isLoading && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
                isLoading = true
                currentPage++
                fetchScrollRepositories()
            }
        }
    }

    private fun fetchRepositories() {
        viewModelGitHub.fetchRepositories(
            qSearchWord, currentPage, perPage
        ).observe(viewLifecycleOwner) {
            if (it != null && (it.status == Status.LOADING)) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
                listRepositoryDet = it.data.getItems()!!

                setUAdapterData()
            }
        }
    }

    private fun fetchScrollRepositories() {
        viewModelGitHub.fetchRepositories(
            qSearchWord, currentPage, perPage
        ).observe(viewLifecycleOwner) {
            if (it != null && (it.status == Status.LOADING)) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE

                val previousSize = listRepositoryDet.size
                val newRepositoryDet = it.data.getItems()!!
                listRepositoryDet = newRepositoryDet
                setUAdapterData()
                repoAdapter.notifyItemInserted(listRepositoryDet.size - 1)
                binding.rvRepository.scrollToPosition(previousSize)
                isLoading = false
            }
        }
    }

    private fun setUAdapterData() {
        repoAdapter.setRepository(listRepositoryDet)
        binding.rvRepository.layoutManager = layoutManager
        binding.rvRepository.adapter = repoAdapter

        val targetItemView = layoutManager.findViewByPosition(vmShared.currentViewItemPosition)
        val recyclerViewHeight = binding.rvRepository.height
        val targetTop = targetItemView?.top ?: 0
        val targetHeight = targetItemView?.height ?: 0
        val targetAtUICenter = targetTop + targetHeight / 2 + recyclerViewHeight / 2
        layoutManager.scrollToPositionWithOffset(vmShared.currentViewItemPosition, targetAtUICenter)
    }

    override fun repositoryItemClick(repositoryModel: RepositoryDetails, position: Int) {
        vmShared.currentViewItemPosition = position
        vmShared.sharedData = AppConstant.getJsonStringToObject(repositoryModel)
        navController.navigate(R.id.home_to_details)
    }
}