package com.github.yamamotoj.subskription

import rx.Observable
import rx.Subscription

interface AutoUnsubscribable {

    /**
     * Extension function for a {@link Observable} to add {@link Subscription} to this {@code AutoUnsubscribable}.
     * The {@link Subscription} whill be subscribed on {@code unsubscribe()}.
     */
    fun <T> Observable<T>.autoUnsubscribe() :Observable<T>

    /**
     * Extension function for a {@link Observable} to add {@link Subscription} to this {@code AutoUnsubscribable} with specified {@code key}.
     * The {@link Subscription} whill be subscribed on {@code unsubscribe(key:Any)} for the key, and on {@code unsubscribe()} as well.
     */
    fun <T> Observable<T>.autoUnsubscribe(key:Any) :Observable<T>

    /**
     * Extension function for a {@link Subscription} to add itself to this {@code AutoUnsubscribable}.
     * The {@link Subscription} whill be subscribed on {@code unsubscribe()}.
     */
    fun Subscription.autoUnsubscribe():Unit

    /**
     * Extension function for a {@link Subscription} to add itself to this {@code AutoUnsubscribable} with specified {@code key}.
     * The {@link Subscription} will be unsubscribed on {@code unsubscribe(key:Any)} for the key, and on {@code unsubscribe()} as well.
     */
    fun Subscription.autoUnsubscribe(key:Any):Unit


    /**
     * Add {@link Subscription} to this {@code AutoUnsubscribable}.
     * The {@link Subscription} will be unsubscribed on {@code unsubscribe(key:Any)} for the key, and on {@code unsubscribe()} as well.
     */
    fun addSubscription(subscription:Subscription):Unit

    /**
     * Add {@link Subscription} to this {@code AutoUnsubscribable}.
     * The {@link Subscription} will be unsubscribed on {@code unsubscribe(key:Any)} for the key, and on {@code unsubscribe()} as well.
     */
    fun addSubscription(subscription: Subscription, key: Any):Unit

    /**
     * Unsubscribe subscriptions for specified key.
     * This will call {@code unsubscribe(key:Any)} for added {@code AutoUnsubscribable} by {@code addAutoUnsubscribable()}.
     */
    fun unsubscribe(key:Any):Unit


    /**
     * Unsubscribe all subscriptions.
     * This will unsubscribe subscriptions with key as well.
     * This will call {@code unsubscribe()} for added {@code AutoUnsubscribable} by {@code addAutoUnsubscribable()}.
     * This will remove oll {@code AutoUnsubscribable}s.
     * When this {@code AutoUnsubscribable} is added to parent by {@code addAutoUnsubscribable(autoUnsubscribable: AutoUnsubscribable)}, this will removed.
     */
    fun unsubscribe():Unit

    /**
     * Add {@code AutoUnsubscribable} to this {@code AutoUnsubscribable}.
     * When {@code unsubscribe()} is called for parent {@code AutoUnsubscribable}, {@code unsubscribe()} is callded for add {@code AutoUnsubscribable} with or without key.
     * The added {@code AutoUnsubscribable} is removed from parent when {@code unsubscribe()} is called for parent {@code AutoUnsubscribable} without specified key.
     * When {@code unsubscribe()} is called for added {@code AutoUnsubscribable} without specified key, it remove itself from parent {@code AutoUnsubscribable} as well.
     */
    fun addAutoUnsubscribable(autoUnsubscribable: AutoUnsubscribable):Unit

    /**
     * Remove {@code AutoUnsubscribable} from parent {@code AutoUnsubscribable}.
     */
    fun removeAutoUnsubscribable(autoUnsubscribable: AutoUnsubscribable):Unit
}
