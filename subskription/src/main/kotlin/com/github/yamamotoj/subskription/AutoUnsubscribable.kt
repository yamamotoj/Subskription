package com.github.yamamotoj.subskription

import rx.Observable
import rx.Subscription

interface AutoUnsubscribable {
    fun <T> Observable<T>.autoUnsubscribe() :Observable<T>
    fun <T> Observable<T>.autoUnsubscribe(key:Any) :Observable<T>
    fun Subscription.autoUnsubscribe():Unit
    fun Subscription.autoUnsubscribe(key:Any):Unit
    fun unsubscribe():Unit
    fun unsubscribe(key:Any):Unit
    fun addAutoUnsubscrivable(autoUnsubscribable: AutoUnsubscribable):Unit
    fun removeAutoUnsubscrivable(autoUnsubscribable: AutoUnsubscribable):Unit
}
