package se.mattec.kotlinlist

import android.app.Application

import io.realm.Realm

class KotlinListApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }

}
