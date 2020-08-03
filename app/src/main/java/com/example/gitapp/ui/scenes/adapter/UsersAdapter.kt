package com.example.gitapp.ui.scenes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gitapp.R
import com.example.gitapp.ui.scenes.model.UserUI

class UsersAdapter(private val listener: OnUserClickListener) : RecyclerView.Adapter<UsersAdapter.UserHolder>() {
    private var usersList: List<UserUI> = mutableListOf()

    fun setUsersList(users: List<UserUI>) {
        val diffUtilCallBack = UserDiffUtilCallBack(usersList, users)
        val result = DiffUtil.calculateDiff(diffUtilCallBack)

        this.usersList = users
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_user, parent, false)
        return UserHolder(view)
    }

    override fun getItemCount(): Int = usersList.count()

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(usersList[position], listener)
    }

    class UserDiffUtilCallBack(private val oldUsers: List<UserUI>, private val newUsers: List<UserUI>) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldUsers[oldItemPosition].id == newUsers[newItemPosition].id
        }

        override fun getOldListSize(): Int {
            return oldUsers.count()
        }

        override fun getNewListSize(): Int {
            return newUsers.count()
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldUsers[oldItemPosition] == newUsers[newItemPosition]
        }

    }

    class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val clUser: ConstraintLayout = itemView.findViewById(R.id.cl_user)
        private val ivAvatar: ImageView = itemView.findViewById(R.id.iv_avatar)
        private val tvId: TextView = itemView.findViewById(R.id.tv_id)
        private val tvLogin: TextView = itemView.findViewById(R.id.tv_login)
        private val ivAdmin: ImageView = itemView.findViewById(R.id.iv_admin)
        private val tvLink: TextView = itemView.findViewById(R.id.tv_link)
        fun bind(user: UserUI, listener: OnUserClickListener) {
            clUser.setOnClickListener {
                listener.onClick(user.id)
            }

            tvId.text = user.id.toString()
            tvLogin.text = user.login
            tvLink.text = user.gitHubUrl

            when(user.type) {
                "User" -> ivAdmin.setImageResource(R.drawable.user)
                else -> ivAdmin.setImageResource(R.drawable.admin)
            }

            Glide.with(itemView.context)
                .load(user.avatar)
                .placeholder(R.drawable.github_logo)
                .fitCenter()
                .into(ivAvatar)
        }
    }

    interface OnUserClickListener {
        fun onClick(userId: Int)
    }
}