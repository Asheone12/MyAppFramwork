package com.muen.myappframwork.ui.main

import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.muen.myappframwork.ARouteAddress
import com.muen.myappframwork.MMKVManage
import com.muen.myappframwork.MMKVManage.SUCCESS_CODE
import com.muen.myappframwork.databinding.ActivityMainBinding
import com.muen.myappframwork.ui.main.adapter.WordAdapter
import com.muen.myappframwork.ui.main.vm.MainVM
import com.muen.myappframwork.util.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@Route(path = ARouteAddress.APP_MAIN)
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val viewModel by viewModels<MainVM>()
    private lateinit var adapter: WordAdapter
    override fun onCreateViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
        super.initView()
        viewBinding.rvWords.isNestedScrollingEnabled = false    //禁止滑动
        val layoutManager = LinearLayoutManager(this)
        adapter = WordAdapter()
        viewBinding.rvWords.layoutManager = layoutManager
        viewBinding.rvWords.adapter = adapter
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

        adapter.clickListener = { word,position->
            ARouter.getInstance().build(ARouteAddress.APP_WORD_INFO)
                .withParcelable(ARouteAddress.EXTRA_WORD_ENTITY,word)
                .withInt(ARouteAddress.EXTRA_POSITION,position)
                .navigation()
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
                }
            }
        }

        viewModel.findWords {
            if (it.isNotEmpty()) {
                viewBinding.txtDbWord.text = "数据库内的一言数:" + it.size
                val words = it
                //使用submitList()时，每次需要传入一个新的List，否则更新无效。
                //与recyclerview不同，它要求每次传入的list都是同一个对象
                adapter.submitList(words)
            }
        }

    }

}