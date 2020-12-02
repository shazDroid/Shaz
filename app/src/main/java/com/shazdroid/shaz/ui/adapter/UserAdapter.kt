package com.shazdroid.shaz.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.shazdroid.shaz.R
import com.shazdroid.shaz.databinding.UserItemRowBinding
import com.shazdroid.shaz.model.UserListResponse

class UserAdapter constructor(private val userListResponse: UserListResponse,private val onUserClickHandler: OnUserClickHandler) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<UserItemRowBinding>(inflater,R.layout.user_item_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.userName.text = userListResponse.data.get(position).name
        holder.binding.userEmail.text = "Email: ${userListResponse.data.get(position).email}"
        holder.binding.userGender.text = "Gender: ${userListResponse.data.get(position).gender}"
        holder.binding.userStatus.text = "Status: ${userListResponse.data.get(position).status}"
        holder.binding.root.setOnClickListener{
            onUserClickHandler.onUserClick(userListResponse.data.get(position).id)
        }
    }

    override fun getItemCount(): Int {
        return userListResponse.data.size
    }

    class ViewHolder(itemView: UserItemRowBinding) : RecyclerView.ViewHolder(itemView.root) {
        val binding = itemView
    }
}