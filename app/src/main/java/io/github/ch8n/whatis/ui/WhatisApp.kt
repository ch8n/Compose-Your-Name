package io.github.ch8n.whatis.ui

import android.app.Application
import com.google.android.gms.ads.MobileAds


class WhatisApp : Application() {
    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this) {

        }
    }
}