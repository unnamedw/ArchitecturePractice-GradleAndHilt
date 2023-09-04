package com.doachgosum.listsampleapp.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.doachgosum.listsampleapp.databinding.ActivityMainBinding
import com.doachgosum.listsampleapp.domain.repository.PhotoRepository
import com.doachgosum.listsampleapp.presentation.adapter.PhotoListAdapter
import com.doachgosum.listsampleapp.presentation.util.setOnThrottleClickListener
import com.doachgosum.listsampleapp.presentation.util.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    @Inject lateinit var photoRepository: PhotoRepository
    @Inject lateinit var photoListAdapter: PhotoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subscribeViewModel()
        initView()
    }

    private fun subscribeViewModel() {

        lifecycleScope.launch {
            viewModel.isLoading
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collectLatest { isLoading ->
                    binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
                }
        }

        lifecycleScope.launch {
            viewModel.commonEvent
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collectLatest { event ->
                    when (event) {
                        is CommonEvent.ShowToast -> showToast(event.msg)
                    }
                }
        }

        lifecycleScope.launch {
            viewModel.photoList
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collectLatest {
                    photoListAdapter.submitList(it)

                    if (it.isEmpty()) {
                        binding.rvList.visibility = View.GONE
                        binding.frameEmpty.visibility = View.VISIBLE
                    } else {
                        binding.rvList.visibility = View.VISIBLE
                        binding.frameEmpty.visibility = View.GONE
                    }
                }
        }
    }

    private fun initView() {
        // setup recyclerview
        binding.rvList.layoutManager = LinearLayoutManager(this)
            .apply { orientation = LinearLayoutManager.VERTICAL }
        binding.rvList.adapter = photoListAdapter

        // setup buttons
        binding.btnRead.setOnThrottleClickListener(500) {
            viewModel.loadMorePhotos()
        }
        binding.btnClear.setOnClickListener {

            // 3초간 read 버튼 비활성화
            binding.btnRead.isEnabled = false
            binding.btnRead.postDelayed({
                binding.btnRead.isEnabled = true
            }, 3000)

            viewModel.clear()
        }
        binding.btnSort.setOnClickListener {
            viewModel.sortByTitleAsc()
        }
    }

}