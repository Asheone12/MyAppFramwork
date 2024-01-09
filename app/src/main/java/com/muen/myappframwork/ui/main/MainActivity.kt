package com.muen.myappframwork.ui.main

import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.muen.myappframwork.MMKVManage
import com.muen.myappframwork.MMKVManage.SUCCESS_CODE
import com.muen.myappframwork.databinding.ActivityMainBinding
import com.muen.myappframwork.source.local.entity.WordEntity
import com.muen.myappframwork.ui.main.adapter.WordAdapter
import com.muen.myappframwork.ui.main.vm.MainVM
import com.muen.myappframwork.util.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val viewModel by viewModels<MainVM>()
    private lateinit var adapter: WordAdapter
    private var words = arrayListOf<WordEntity>()
    override fun onCreateViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
        super.initView()
        viewBinding.rvWords.isNestedScrollingEnabled = false    //禁止滑动
        val layoutManager = LinearLayoutManager(this)
        adapter = WordAdapter(words)
        viewBinding.rvWords.layoutManager = layoutManager
        viewBinding.rvWords.adapter = adapter
    }

    override fun initData() {
        super.initData()
        viewModel.findWords()
    }

    override fun initListener() {
        super.initListener()
        viewBinding.btnWord.setOnClickListener {
            viewModel.getRandomWord()
        }

        adapter.delClickListener = {
            viewModel.deleteWord(it)
            Toast.makeText(this, "删除成功！", Toast.LENGTH_SHORT).show()
        }

        adapter.updateClickListener = {
            viewModel.updateWord(it)
            Toast.makeText(this, "更新成功！", Toast.LENGTH_SHORT).show()
        }
    }

    override fun observerData() {
        super.observerData()
        viewModel.resultCode.observe(this) {
            if (it == SUCCESS_CODE) {
                if (viewModel.word != null) {
                    //显示上一次的一言和本次的一言
                    viewBinding.txtLastWord.text = "上一次:" + MMKVManage.lastWord
                    viewBinding.txtWord.text = "本次:" + viewModel.word!!.hitokoto
                    //保存本次的一言到数据库

                }
            }
        }

        viewModel.findResult.observe(this) {
            if (it.isNotEmpty()) {
                viewBinding.txtDbWord.text = "数据库内的一言数:" + it.size
                words.clear()
                words.addAll(it)
                adapter.notifyDataSetChanged()
            }
        }
    }

}