package com.muen.myappframwork.ui

import androidx.activity.viewModels
import com.muen.myappframwork.MMKVManage.SUCCESS_CODE
import com.muen.myappframwork.databinding.ActivityMainBinding
import com.muen.myappframwork.ui.vm.MainVM
import com.muen.myappframwork.util.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val viewModel by viewModels<MainVM>()
    override fun onCreateViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
        super.initView()
    }

    override fun initData() {
        super.initData()
    }

    override fun initListener() {
        super.initListener()
        viewBinding.btnWord.setOnClickListener {
            viewModel.getRandomWord()
        }
    }

    override fun observerData() {
        super.observerData()
        viewModel.resultCode.observe(this){
            if (it==SUCCESS_CODE){
                if(viewModel.word != null){
                    viewBinding.txtWord.text = viewModel.word!!.hitokoto
                }
            }
        }
    }

}