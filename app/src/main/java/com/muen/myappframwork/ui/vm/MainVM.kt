package com.muen.myappframwork.ui.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muen.myappframwork.MMKVManage
import com.muen.myappframwork.MMKVManage.ERROR_CODE
import com.muen.myappframwork.MMKVManage.SUCCESS_CODE
import com.muen.myappframwork.entity.Word
import com.muen.myappframwork.http.CommonHandler
import com.muen.myappframwork.repo.AppServiceRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(val repo: AppServiceRepo) : ViewModel(){
    val resultCode = MutableLiveData<Int?>()
    var resultMsg:String?=null
    var word:Word?=null

    /**
     * 调用登录接口
     */
    fun getRandomWord() {
        viewModelScope.launch(Dispatchers.Main) {
            repo.randomWord(object : CommonHandler<Word>() {
                override suspend fun onDataResult(data: Word?) {
                    super.onDataResult(data)
                    word = data
                    Log.d("word",word.toString())
                    resultCode.value= SUCCESS_CODE
                    MMKVManage.lastWord = word?.hitokoto
                }

                override suspend fun onCodeResult(code: Int, msg: String?) {
                    super.onCodeResult(code, msg)
                    //msg应该在code前赋值，不然在监听到code变化的时候msg仍然是null
                    resultMsg=msg
                    resultCode.value=code
                }

                override suspend fun onErrorResult(throwable: Throwable) {
                    super.onErrorResult(throwable)
                    resultCode.value= ERROR_CODE
                }
            })
        }
    }

}