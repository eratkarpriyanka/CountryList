package com.example.regions.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.regions.R
import com.example.regions.ui.compose.CircularProgressBar
import com.example.regions.ui.compose.EmptyListUI
import com.example.regions.ui.compose.RegionListUI
import com.example.regions.viewmodels.RegionsApiResponseState
import com.example.regions.viewmodels.RegionsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegionsListActivity : AppCompatActivity(), LifecycleOwner {

    private val viewModel: RegionsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoadList()
        }

        retrieveListInfo()
    }

    private fun retrieveListInfo() {
        // launches coroutine without blocking the UI thread
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Invokes the Regions API anytime the activity is started
                viewModel.loadRegions()
            }
        }
    }

    /**
     * observe the regions list @see [RegionsViewModel.listState] state flow
     */
    @Preview
    @Composable
    private fun LoadList() {

        /* recompose jetpack compose ui based on changes to viewModel.state */
        val stateFlow = viewModel.listState.collectAsState()
        when (val state = stateFlow.value) {
            is RegionsApiResponseState.Loading -> {
                Log.d(RegionsListActivity::class.simpleName, "loading the list")
                CircularProgressBar() // show circular progress
            }
            is RegionsApiResponseState.Error -> {
                Log.d(
                    RegionsListActivity::class.simpleName,
                    "error occurred loading the list ${state.errorStringRes}"
                )
                // display error message
                Toast.makeText(
                    this,
                    stringResource(R.string.error_loading_list),
                    Toast.LENGTH_SHORT
                ).show()
                EmptyListUI() // show error UI
            }
            is RegionsApiResponseState.Success -> {
                Log.d(
                    RegionsListActivity::class.simpleName,
                    "successfully fetched the list. Details of 1st regions ${state.data}"
                )
                // populate the regions list
                RegionListUI(state.data, viewModel)
            }
        }
    }
}

