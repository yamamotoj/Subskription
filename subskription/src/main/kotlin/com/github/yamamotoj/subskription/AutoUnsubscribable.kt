package com.github.yamamotoj.subskription

import rx.Observable
import rx.Subscription

interface AutoUnsubscribable {
    fun <T> Observable<T>.autoUnsubscribe() :Observable<T>
    fun <T> Observable<T>.autoUnsubscribe(key:Any) :Observable<T>
    fun Subscription.autoUnsubscribe():Unit
    fun Subscription.autoUnsubscribe(key:Any):Unit
    fun addSubscription(subscription:Subscription):Unit
    fun addSubscription(subscription: Subscription, key: Any):Unit
    fun unsubscribe():Unit
    fun unsubscribe(key:Any):Unit
    fun addAutoUnsubscribable(autoUnsubscribable: AutoUnsubscribable):Unit
    fun removeAutoUnsubscribable(autoUnsubscribable: AutoUnsubscribable):Unit
}
