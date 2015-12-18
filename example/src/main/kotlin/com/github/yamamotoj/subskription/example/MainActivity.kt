package com.github.yamamotoj.subskription.example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.yamamotoj.subskription.AutoUnsubscribable
import com.github.yamamotoj.subskription.AutoUnsubscribableDelegate

class MainActivity : AppCompatActivity(), AutoUnsubscribable by AutoUnsubscribableDelegate() {

    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rootView = findViewById(android.R.id.content)
        presenter = MainPresenter(this, rootView)
        addAutoUnsubscribable(presenter)
    }

    override fun onPause() {
        super.onPause()
        unsubscribe("onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        unsubscribe()
    }

}
