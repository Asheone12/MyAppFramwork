package com.muen.myappframwork.ui.wordinfo.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muen.myappframwork.repo.AppServiceRepo
import com.muen.myappframwork.source.local.entity.WordEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordInfoVM @Inject constructor(private val repo: AppServiceRepo) : ViewModel() {

    /**
     * 在数据库中更新一条一言,不能用suspend修饰，否则在其他地方不能直接使用
     */
    fun updateWord(word: WordEntity) {
        viewModelScope.launch(Dispatchers.Main) {
            repo.updateWord(word)
        }
    }
}