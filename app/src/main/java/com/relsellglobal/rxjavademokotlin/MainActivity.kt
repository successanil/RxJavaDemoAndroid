package com.relsellglobal.rxjavademokotlin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.relsellglobal.rxjavademokotlin.pojo.Task
import com.relsellglobal.rxjavademokotlin.util.StringUtilM
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val taskObservable: Observable<Task> = Observable       // create a new Observable object
            .fromIterable(DataSource.createTasksList())         // apply 'fromIterable' operator
            .subscribeOn(Schedulers.io())                       // designate worker thread (background)
            .map {
                var description = StringUtilM.convertSentenceIntoCamelCase(it.description)
                Task(description, it.isComplete, it.priority)
            }
            .map {
                var description = StringUtilM.reverseString(it.description)
                Task(description, it.isComplete, it.priority)
            }
            .filter {
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {

                }
                true
            }
            .observeOn(AndroidSchedulers.mainThread()) // designate observer thread (main thread)

        taskObservable.subscribe(object : Observer<Task> {

            override fun onNext(t: Task) {
                Log.d("TAG", "onNext: " + Thread.currentThread().name)
                Log.d("TAG", "onNext:: " + t.description)

            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable) {

            }

        })


    }
}