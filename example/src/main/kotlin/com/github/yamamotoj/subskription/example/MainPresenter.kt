package com.github.yamamotoj.subskription.example

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.github.yamamotoj.subskription.AutoUnsubscribable
import com.github.yamamotoj.subskription.AutoUnsubscribableDelegate
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class MainPresenter(val context: Context, val view: View) : AutoUnsubscribable by AutoUnsubscribableDelegate() {

    val emitter = Observable.interval(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread())

    val textView1 = view.findViewById(R.id.textView1) as TextView
    val textView2 = view.findViewById(R.id.textView2) as TextView

    init {
        emitter.autoUnsubscribe().subscribe { textView1.text = it.toString() }
        emitter.autoUnsubscribe().subscribe { textView2.text = it.toString() }
        textView1.setOnClickListener {
            Observable.timer(3, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .autoUnsubscribe("onPause")
                    .doOnSubscribe { textView2.visibility = View.VISIBLE }
                    .doOnUnsubscribe {
                        textView2.visibility = View.INVISIBLE
                        Toast.makeText(context, "unsubscribed", Toast.LENGTH_SHORT).show()
                    }
                    .subscribe()
        }
    }
}
