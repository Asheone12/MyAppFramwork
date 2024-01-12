package com.muen.myappframwork.ui.main.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muen.myappframwork.MMKVManage
import com.muen.myappframwork.http.CommonHandler
import com.muen.myappframwork.repo.AppServiceRepo
import com.muen.myappframwork.source.local.dao.WordDao
import com.muen.myappframwork.source.local.entity.WordEntity
import com.muen.myappframwork.source.network.entity.Word
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(private val repo: AppServiceRepo, private val wordDao: WordDao) :
    ViewModel() {
    val getResult = MutableLiveData<Boolean>()
    var word: Word? = null

    /**
     * 调用获取一言接口
     */
    fun getRandomWord() {
        viewModelScope.launch(Dispatchers.Main) {
            repo.randomWord(object : CommonHandler<Word>() {
                override suspend fun onDataResult(data: Word?) {
                    super.onDataResult(data)
                    word = data
                    Log.d("word", word.toString())
                    getResult.value = true
                    word?.apply {
                        MMKVManage.lastWord = this.hitokoto
                        insertWord(
                            WordEntity(
                                this.id,
                                this.hitokoto,
                                this.author,
                                this.source,
                                this.date
                            )
                        )
                    }
                }

            })
        }
    }

    /**
     * 查询数据库中所有的一言,不能用suspend修饰，否则在其他地方不能直接使用
     */
    fun findWords(handler: (List<WordEntity>) -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            wordDao.loadWords().collect {
                handler.invoke(it)
            }
        }
    }

    /**
     * 向数据库中插入一条一言,不能用suspend修饰，否则在其他地方不能直接使用
     */
    fun insertWord(word: WordEntity) {
        viewModelScope.launch(Dispatchers.Main) {
            wordDao.insertWord(word)
        }
    }

    /**
     * 在数据库中删除一条一言,不能用suspend修饰，否则在其他地方不能直接使用
     */
    fun deleteWord(word: WordEntity) {
        viewModelScope.launch(Dispatchers.Main) {
            wordDao.deleteWord(word)
        }
    }

}