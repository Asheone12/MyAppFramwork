package com.muen.myappframwork.ui.wordinfo

import android.widget.Toast
import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.muen.myappframwork.ARouteAddress
import com.muen.myappframwork.databinding.ActivityWordInfoBinding
import com.muen.myappframwork.source.local.entity.WordEntity
import com.muen.myappframwork.ui.wordinfo.vm.WordInfoVM
import com.muen.myappframwork.util.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@Route(path = ARouteAddress.APP_WORD_INFO)
@AndroidEntryPoint
class WordInfoActivity : BaseActivity<ActivityWordInfoBinding>() {
    private val viewModel by viewModels<WordInfoVM>()

    @JvmField
    @Autowired(name = ARouteAddress.EXTRA_WORD_ENTITY)
    var wordEntity: WordEntity? = null

    @JvmField
    @Autowired(name = ARouteAddress.EXTRA_POSITION)
    var position: Int? = 0

    override fun onCreateViewBinding(): ActivityWordInfoBinding {
        return ActivityWordInfoBinding.inflate(layoutInflater)
    }

    override fun initView() {
        super.initView()
        //注入ARouter参数
        ARouter.getInstance().inject(this)
        viewBinding.position.text = position.toString()
        viewBinding.wid.text = wordEntity?.wid
        viewBinding.word.setText(wordEntity?.word)
        viewBinding.author.setText(wordEntity?.author)
        viewBinding.source.setText(wordEntity?.source)
        viewBinding.date.setText(wordEntity?.date)
    }

    override fun initListener() {
        super.initListener()
        viewBinding.btnUpdate.setOnClickListener {
            val newWord = WordEntity(
                wordEntity?.wid!!,
                viewBinding.word.text.toString(),
                viewBinding.author.text.toString(),
                viewBinding.source.text.toString(),
                viewBinding.date.text.toString()
            )
            viewModel.updateWord(newWord)
            Toast.makeText(this, "更新成功！", Toast.LENGTH_SHORT).show()
            finish()
        }
    }


}