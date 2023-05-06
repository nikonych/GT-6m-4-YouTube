package com.example.gt_6m_4_youtube.core.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.gt_6m_4_youtube.utils.ConnectionLiveData

abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel> : AppCompatActivity() {

    protected lateinit var binding: VB
    protected abstract fun inflateViewBinding(): VB
    protected abstract val viewModel: VM
    protected lateinit var connectionLiveData: ConnectionLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateViewBinding()
        setContentView(binding.root)
        connectionLiveData = ConnectionLiveData(binding.root.context)
        isConnection()
        initViewModel()
        initViews()
        initListener()
    }

    open fun initViews() {} // Инициализации вью
    open fun initListener() {} // Все наши клики
    open fun initViewModel() {} // Все обзерверы нащего viewModel\'a
    open fun isConnection() {}
}// Проверка на подключение к интернету
