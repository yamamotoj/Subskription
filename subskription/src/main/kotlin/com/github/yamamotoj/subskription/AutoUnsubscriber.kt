package com.github.yamamotoj.subskription

import rx.Observable
import rx.Subscription
import rx.lang.kotlin.lift
import rx.lang.kotlin.subscriber
import rx.subscriptions.CompositeSubscription


class AutoUnsubscriber : AutoUnsubscribable {

    private val subscriptionMap = hashMapOf<Any?, CompositeSubscription>()
    private val children = arrayListOf<AutoUnsubscribable>()
    private var parent: AutoUnsubscribable? = null

    private fun <T> Observable<T>.addSubscriber(key: Any?): Observable<T> =
            lift { s ->
                compositeSubscriptionForKey(key).add(s)
                subscriber<T>()
                        .onNext { e -> s.onNext(e) }
                        .onCompleted { s.onCompleted() }
                        .onError { t -> s.onError(t) }
            }

    private fun compositeSubscriptionForKey(key: Any?) =
            subscriptionMap[key] ?: CompositeSubscription().apply { subscriptionMap[key] = this }

    override fun <T> Observable<T>.autoUnsubscribe(): Observable<T> = addSubscriber(null)
    override fun <T> Observable<T>.autoUnsubscribe(key: Any): Observable<T> = addSubscriber(key)

    override fun Subscription.autoUnsubscribe() = compositeSubscriptionForKey(null).add(this)
    override fun Subscription.autoUnsubscribe(key: Any) = compositeSubscriptionForKey(key).add(this)

    override fun unsubscribe(key: Any) {
        subscriptionMap[key]?.unsubscribe()
        subscriptionMap.remove(key)
        children.forEach { it.unsubscribe(key) }
    }

    override fun unsubscribe() {
        subscriptionMap.values.forEach { it.unsubscribe() }
        subscriptionMap.clear()
        parent?.removeAutoUnsubscrivable(this)
        children.forEach { it.unsubscribe() }
    }

    override fun addAutoUnsubscrivable(autoUnsubscribable: AutoUnsubscribable) =
            children.add(autoUnsubscribable).let {
                (autoUnsubscribable as? AutoUnsubscriber)?.parent = this
                Unit
            }
    override fun removeAutoUnsubscrivable(autoUnsubscribable: AutoUnsubscribable) =
            children.remove(autoUnsubscribable).let {
                (autoUnsubscribable as? AutoUnsubscriber)?.parent = null
                Unit
            }
}
