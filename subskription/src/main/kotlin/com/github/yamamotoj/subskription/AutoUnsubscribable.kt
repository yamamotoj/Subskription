package com.github.yamamotoj.subskription

import rx.Observable
import rx.Subscription

interface AutoUnsubscribable {

    /**
     * Extension function for a [Observable] to add [Subscription] to this [AutoUnsubscribable].
     * The [Subscription] will be subscribed on [unsubscribe].
     */
    fun <T> Observable<T>.autoUnsubscribe(): Observable<T>

    /**
     * Extension function for a {@link Observable} to add [Subscription] to this [AutoUnsubscribable] with specified key.
     * The [Subscription] will be subscribed on [unsubscribe] for the key, and on [unsubscribe] as well.
     */
    fun <T> Observable<T>.autoUnsubscribe(key: Any): Observable<T>

    /**
     * Extension function for a [Subscription] to add itself to this [AutoUnsubscribable].
     * The [Subscription] will be subscribed on [unsubscribe].
     */
    fun Subscription.autoUnsubscribe(): Unit

    /**
     * Extension function for a [Subscription] to add itself to this [AutoUnsubscribable] with specified key.
     * The [Subscription] will be unsubscribed on [unsubscribe] for the key, and on [unsubscribe] as well.
     */
    fun Subscription.autoUnsubscribe(key: Any): Unit


    /**
     * Add [Subscription] to this [AutoUnsubscribable].
     * The [Subscription] will be unsubscribed on [unsubscribe] for the key, and on [unsubscribe] as well.
     */
    fun addSubscription(subscription: Subscription): Unit

    /**
     * Add [Subscription] to this [AutoUnsubscribable].
     * The [Subscription] will be unsubscribed on [unsubscribe] for the key, and on [unsubscribe] as well.
     */
    fun addSubscription(subscription: Subscription, key: Any): Unit

    /**
     * Unsubscribe subscriptions for specified key.
     * This will call [unsubscribe] for added [AutoUnsubscribable] by [addAutoUnsubscribable].
     */
    fun unsubscribe(key: Any): Unit


    /**
     * Unsubscribe all subscriptions.
     * This will unsubscribe subscriptions with key as well.
     * This will call [unsubscribe] for added [AutoUnsubscribable] by [addAutoUnsubscribable].
     * This will remove all [AutoUnsubscribable]s.
     * When this [AutoUnsubscribable] is added to parent by [addAutoUnsubscribable], this will removed.
     */
    fun unsubscribe(): Unit

    /**
     * Add [AutoUnsubscribable] to this [AutoUnsubscribable].
     * When [unsubscribe] is called for parent [AutoUnsubscribable], [unsubscribe] is callded for add [AutoUnsubscribable] with or without key.
     * The added [AutoUnsubscribable] is removed from parent when [unsubscribe] is called for parent [AutoUnsubscribable] without specified key.
     * When [unsubscribe] is called for added [AutoUnsubscribable] without specified key, it remove itself from parent [AutoUnsubscribable] as well.
     */
    fun addAutoUnsubscribable(autoUnsubscribable: AutoUnsubscribable): Unit

    /**
     * Remove [AutoUnsubscribable] from parent [AutoUnsubscribable].
     */
    fun removeAutoUnsubscribable(autoUnsubscribable: AutoUnsubscribable): Unit
}
