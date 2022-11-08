package com.ravinishad.githubissuetracker.presentation.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ravinishad.githubissuetracker.R
import com.ravinishad.githubissuetracker.common.utils.extension.appendAtToUser
import com.ravinishad.githubissuetracker.common.utils.extension.serverDateToLocal
import com.ravinishad.githubissuetracker.data.remote.dto.IssueItem
import com.squareup.picasso.Picasso

class IssuePagingAdapter : PagingDataAdapter<IssueItem, IssuePagingAdapter.IssueViewHolder>(COMPARATOR) {

    class IssueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val issueText: TextView = itemView.findViewById(R.id.issue_text)
        val issueUserImage: AppCompatImageView = itemView.findViewById(R.id.issue_user_image)
        val issueCreatedByWithDate: TextView = itemView.findViewById(R.id.issue_created_by_with_date)
        val issueClosedOn: TextView = itemView.findViewById(R.id.issue_closed_date)
    }

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {

            try {
                holder.issueText.text = item.title
                holder.issueCreatedByWithDate.text = buildString {
                    append("Created by ")
                    append(appendAtToUser(item.user.login))
                    append(" on ")
                    append(serverDateToLocal(item.created_at))
                }
                holder.issueClosedOn.text = buildString {
                    append("Closed on ")
                    append(serverDateToLocal(item.closed_at))
                }

                Picasso.get().load(item.user.avatar_url).error(R.drawable.ic_default_user)
                    .placeholder(R.drawable.ic_default_user).into(holder.issueUserImage)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item_issue, parent, false)
        return IssueViewHolder(view)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<IssueItem>() {
            override fun areItemsTheSame(oldItem: IssueItem, newItem: IssueItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: IssueItem, newItem: IssueItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}





















