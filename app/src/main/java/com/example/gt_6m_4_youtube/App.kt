package com.example.gt_6m_4_youtube

import android.app.Application
import com.example.gt_6m_4_youtube.repository.Repository

class App: Application() {

    companion object {
        val repository: Repository by lazy {
            Repository()
        }
    }
}