# Subskription

RxJava subscription helper for Kotlin

[http://qiita.com/boohbah/items/1ceaade6ce33321bdcbf](http://qiita.com/boohbah/items/1ceaade6ce33321bdcbf)

# Install

```
dependencies {
    compile 'com.github.yamamotoj:subskription:0.2.1'
}

repositories {
    maven { url "https://dl.bintray.com/yamamotoj/maven" }
}

```

# Usage

## Typical case for Android Activity  

```kotlin
// implement AutoUnsubscribable interface using class delegation by AutoUnsubscribableDelegate
class MainActivity : Activity(), AutoUnsubscribable by AutoUnsubscribableDelegate() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // use autoUnsubscribe() extension to add to CompositeSubscription in the AutoUnsubscribableDelegate
        Observable.interval(3, TimeUnit.SECONDS)
        .autoUnsubscribe()
        .subscribe { Log.d("Interval", "$it") }
    }

    override fun onDestroy() {
        super.onDestroy()
        
        // unsubscribe all subscriptions added to AutoUnsubscribableDelegate
        unsubscribe()
    }
}

```

## Hierarchical `unsubscribe()`

`AutoUnsubscribable` can have child element. When `unsubscribe()` is called for parent `AutoUnsubscribable`, `unsubscribe()` for child `AutoUnsubscribable` is called as well.

# License

```
The MIT License (MIT)

Copyright (c) 2016, Jumpei Yamamoto

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```