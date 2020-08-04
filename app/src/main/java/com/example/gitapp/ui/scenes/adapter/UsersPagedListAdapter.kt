package com.example.gitapp.ui.scenes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.gitapp.R
import com.example.gitapp.ui.scenes.model.UserUI

open class UsersPagedListAdapter(
    private val glide: RequestManager,
    private val listener: OnUserClickListener
) :
    PagedListAdapter<UserUI, UsersPagedListAdapter.UserHolder>(UserDiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_user, parent, false)
        return UserHolder(view)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(glide, getItem(position), listener)
    }

    override fun onViewRecycled(holder: UserHolder) {
        super.onViewRecycled(holder)
        holder.recycle(glide)
    }

    class UserDiffUtilCallBack : DiffUtil.ItemCallback<UserUI>() {
        override fun areItemsTheSame(oldItem: UserUI, newItem: UserUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserUI, newItem: UserUI): Boolean {
            return oldItem == newItem
        }
    }

    class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val clUser: ConstraintLayout = itemView.findViewById(R.id.cl_user)
        private val ivAvatar: ImageView = itemView.findViewById(R.id.iv_avatar)
        private val tvId: TextView = itemView.findViewById(R.id.tv_id)
        private val tvLogin: TextView = itemView.findViewById(R.id.tv_login)
        private val ivAdmin: ImageView = itemView.findViewById(R.id.iv_admin)
        private val tvLink: TextView = itemView.findViewById(R.id.tv_link)
        fun bind(glide: RequestManager, user: UserUI?, listener: OnUserClickListener) {
            clUser.setOnClickListener {
                user?.id?.let { it1 -> listener.onClick(it1) }
            }

            tvId.text = user?.id.toString()
            tvLogin.text = user?.login
            tvLink.text = user?.gitHubUrl

            when (user?.type) {
                "User" -> ivAdmin.setImageResource(R.drawable.user)
                else -> ivAdmin.setImageResource(R.drawable.admin)
            }

            glide.load(user?.avatar)
                .placeholder(R.drawable.github_logo)
                .into(ivAvatar)
        }

        fun recycle(glide: RequestManager) {
            glide.clear(ivAvatar)
        }
    }

    interface OnUserClickListener {
        fun onClick(userId: Int)
    }
}