package com.muen.myappframwork.ui.main

import androidx.activity.viewModels
import com.muen.myappframwork.MMKVManage
import com.muen.myappframwork.MMKVManage.SUCCESS_CODE
import com.muen.myappframwork.databinding.ActivityMainBinding
import com.muen.myappframwork.ui.main.vm.MainVM
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

        viewBinding.btnDbWord.setOnClickListener {
            viewModel.findWords()
        }
    }

    override fun observerData() {
        super.observerData()
        viewModel.resultCode.observe(this) {
            if (it == SUCCESS_CODE) {
                if (viewModel.word != null) {
                    //显示上一次的一言和本次的一言
                    viewBinding.txtLastWord.text = MMKVManage.lastWord
                    viewBinding.txtWord.text = viewModel.word!!.hitokoto
                    //保存本次的一言到数据库

                }
            }
        }

        viewModel.findResult.observe(this) {
            if (it.isNotEmpty()) {
                viewBinding.txtDbWord.text = it.size.toString()
            }
        }
    }

}