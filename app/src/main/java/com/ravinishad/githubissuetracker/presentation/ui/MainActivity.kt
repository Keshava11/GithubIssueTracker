package com.ravinishad.githubissuetracker.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ravinishad.githubissuetracker.R
import com.ravinishad.githubissuetracker.presentation.paging.IssuePagingAdapter
import com.ravinishad.githubissuetracker.presentation.ui.viewmodel.IssueViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var issueViewModel: IssueViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var issueAdapter: IssuePagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.issues_list)

        issueViewModel = ViewModelProvider(this).get(IssueViewModel::class.java)
        issueAdapter = IssuePagingAdapter()

        recyclerView.layoutManager = LinearLayoutManager(this)
        val itemDecorator = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(itemDecorator)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = issueAdapter

        issueViewModel.list.observe(this, Observer {
            issueAdapter.submitData(lifecycle, it)
        })

    }

}