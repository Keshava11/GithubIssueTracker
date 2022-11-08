package com.ravinishad.githubissuetracker.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ravinishad.githubissuetracker.presentation.repository.IssuePagingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IssueViewModel @Inject constructor(private val repository: IssuePagingRepository) : ViewModel() {
    val list = repository.getClosedIssues().cachedIn(viewModelScope)
}