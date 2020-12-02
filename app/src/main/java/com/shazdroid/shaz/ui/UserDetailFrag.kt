package com.shazdroid.shaz.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.shazdroid.shaz.R
import com.shazdroid.shaz.model.ResultData
import com.shazdroid.shaz.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_user_detail.*

@AndroidEntryPoint
class UserDetailFrag : Fragment() {

    private val viewModel by viewModels<UserViewModel>()

    private val userId by navArgs<UserDetailFragArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // get user data
        viewModel.getUserDetail(userId.userId).observe(viewLifecycleOwner, Observer { result ->

            when (result) {
                is ResultData.Loading -> {

                }
                is ResultData.Success -> {
                    title.text = result.data?.data?.get(0)?.title
                    body.text = result.data?.data?.get(0)?.body
                }
                is ResultData.Failed -> {

                }
                is ResultData.Exception -> {

                }
            }
        })

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            UserDetailFrag().apply {

            }
    }
}