package com.vikas.scatteredgriddemo.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.vikas.scatteredgriddemo.adapter.MainAdapter
import com.vikas.scatteredgriddemo.R
import com.vikas.scatteredgriddemo.adapter.UserHomeItemsAdapter
import com.vikas.scatteredgriddemo.model.AppViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainAdapter = UserHomeItemsAdapter(this)
    private var coroutineJob: Job? = null

    private val appViewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coroutineJob?.cancel()

        itemsRv.layoutManager = LinearLayoutManager(this)
        itemsRv.adapter = mainAdapter

        coroutineJob = lifecycleScope.launch {
            /*@OptIn(ExperimentalCoroutinesApi::class)
            mainViewModel.imageData().collectLatest {
                it.let {
                    mainAdapter.submitData(it)
                }
            }*/



            appViewModel?.imageData().collect {
                it.let {
                    mainAdapter.submitData(it)
                }
            }

        }

        subscribeObservers()
    }

    private fun subscribeObservers() {
        mainAdapter.addLoadStateListener { loadState ->

            /*
            * loadState.refresh - represents the load state for loading the PagingData for the first time.
              loadState.prepend - represents the load state for loading data at the start of the list.
              loadState.append - represents the load state for loading data at the end of the list.
            * */

            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading)
                showProgressBar(true)
            else {
                showProgressBar(false)

                // If we have an error, show a toast
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error ->  loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(this, it.error.toString(), Toast.LENGTH_LONG).show()
                }

            }
        }
    }


    // UPDATE UI ----
    private fun showProgressBar(display : Boolean)
    {
        if(!display)
            progress_bar.visibility = View.GONE
        else
            progress_bar.visibility = View.VISIBLE
    }
}