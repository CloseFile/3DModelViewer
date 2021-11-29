package com.ctapk.poly.features.sarch.view

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.academy.fundamentals.homework.di.Model3dRepositoryProvider
import com.ctapk.poly.R
import com.ctapk.poly.extensions.exhaustive
import com.ctapk.poly.features.sarch.viewmodel.Model3dListViewModelFactory
import com.ctapk.poly.features.sarch.viewmodel.Model3dListViewModelImpl
import com.ctapk.poly.features.sarch.viewmodel.Model3dListViewState
import com.ctapk.poly.model.Model3d


class Modlel3dListFragment : Fragment(), Modle3dAdapter.OnItemClickListener {

    private var fileUri: Uri? = null

    private val viewModel: Model3dListViewModelImpl by viewModels {
        Model3dListViewModelFactory((requireActivity() as Model3dRepositoryProvider).provideMovieRepository())
    }

    lateinit var progressBar: ProgressBar
    lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return inflater.inflate(R.layout.sketchfab_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.progress_bar)
        progressBar.visibility = View.GONE

        searchView = view.findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(OnQueryTextListener())

        view.findViewById<RecyclerView>(R.id.thumbnails_recycler).apply {
            this.layoutManager = LinearLayoutManager(this.context)
            val adapter = Modle3dAdapter(this@Modlel3dListFragment)
            this.adapter = adapter

            loadDataToAdapter(adapter)
        }

    }

    private fun loadDataToAdapter(adapter: Modle3dAdapter) {
        viewModel.model3dListStateOutput.observe(viewLifecycleOwner, { state ->
            when (state) {
                is Model3dListViewState.Model3dLoaded -> {
                    progressBar.visibility = View.GONE
                    adapter.setData(state.modele3dList)
                }
                is Model3dListViewState.FailedToLoad -> Toast.makeText(
                    requireContext(),
                    R.string.error_network_failed,
                    Toast.LENGTH_SHORT
                ).show()
            }.exhaustive
        })
    }

    override fun onItemClicked(position: Int, model3d: Model3d?) {
//        if (model3d != null) {
//            viewModel.downloadModel3d(model3d.uid)
//            viewModel.getDownloadAssetState(model3d.uid).observe(viewLifecycleOwner,
//                Observer {
//
//                })
//        }
        Toast.makeText(activity, "Start download", Toast.LENGTH_SHORT).show()

    }

    inner class OnQueryTextListener : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            progressBar.visibility = View.VISIBLE

            query?.let { viewModel.loadModel3dList(query) }
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }
    }

}



