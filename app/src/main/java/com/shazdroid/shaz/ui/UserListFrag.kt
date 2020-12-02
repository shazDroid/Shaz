package com.shazdroid.shaz.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.shazdroid.shaz.R
import com.shazdroid.shaz.model.ResultData
import com.shazdroid.shaz.ui.adapter.OnUserClickHandler
import com.shazdroid.shaz.ui.adapter.UserAdapter
import com.shazdroid.shaz.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_user_list.*

@AndroidEntryPoint
class UserListFrag : Fragment(), OnUserClickHandler {
    private val viewModel by viewModels<UserViewModel>()


    val TAG = "shaz"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // by default show page no = 1
        renderUserList()

        // on prev click
        prevBtn.setOnClickListener {
            // check if current page no is greater then 1
            if (viewModel.currentVisiblePage.value!! > 1){
                viewModel.setCurrentVisiblePage(viewModel.currentVisiblePage.value!! - 1)
                renderUserList()
            }else{
                prevBtn.isEnabled = false
            }
        }

        // on next click
        nextBtn.setOnClickListener {
            // check if page no does'nt get greater then available pages
            if (viewModel.currentVisiblePage.value!! < viewModel.totalPages.value!!){
                viewModel.setCurrentVisiblePage(viewModel.currentVisiblePage.value!! + 1)
                renderUserList()
            }
        }



    }

    fun renderUserList(){
        viewModel.currentVisiblePage.observe(viewLifecycleOwner, Observer {
            currentPageCount ->
            // get user data
            viewModel.getUserList(currentPageCount).observe(viewLifecycleOwner, Observer { result ->

                when (result) {
                    is ResultData.Loading -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                        noListAvailable.visibility = View.GONE
                    }
                    is ResultData.Success -> {
                        if (result.data?.code == 200) {
                            // hide progress bar and show data
                            recyclerView.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                            noListAvailable.visibility = View.GONE

                            // set page no
                            currentPage.text = "Current page: ${currentPageCount}"

                            // set total pages available
                            if (viewModel.totalPages.value!! == 0) {
                                viewModel.totalPages.postValue(result.data.meta.pagination.total)
                            }

                            // set adapter
                            recyclerView.adapter = UserAdapter(result.data,this)

                        } else {
                            progressBar.visibility = View.GONE
                            recyclerView.visibility = View.GONE
                            noListAvailable.visibility = View.VISIBLE
                        }
                    }
                    is ResultData.Failed -> {
                        progressBar.visibility = View.GONE
                        recyclerView.visibility = View.GONE
                        noListAvailable.visibility = View.VISIBLE
                        noListAvailable.text = "Oops something went wrong..."
                    }
                    is ResultData.Exception -> {
                        progressBar.visibility = View.GONE
                        recyclerView.visibility = View.GONE
                        noListAvailable.visibility = View.VISIBLE
                        noListAvailable.text = "Oops something went wrong...\n${result.message}"
                    }
                }
            })
        })
    }


    companion object {

        @JvmStatic
        fun newInstance() =
            UserListFrag().apply {

            }
    }


    override fun onUserClick(userId: Int) {
        findNavController().navigate(UserListFragDirections.actionUserListFragToUserDetailFrag(userId))
    }
}