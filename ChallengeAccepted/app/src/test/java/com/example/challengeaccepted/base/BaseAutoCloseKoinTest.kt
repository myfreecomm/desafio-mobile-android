package com.example.challengeaccepted.base

import android.os.Handler
import android.os.Looper
import org.koin.test.AutoCloseKoinTest
import org.robolectric.Shadows
import org.robolectric.annotation.LooperMode
import java.time.Duration

@LooperMode(LooperMode.Mode.PAUSED)
open class BaseAutoCloseKoinTest : AutoCloseKoinTest() {


    fun invokePrivateMethod(objClass: Any, methodName: String) {
        Handler(Looper.getMainLooper()).post {
            val method = objClass.javaClass.getDeclaredMethod(methodName)
            method.isAccessible = true
            method.invoke(objClass)
        }
    }

    fun idle(delay: Long? = null) {
        if(delay == null)
            Shadows.shadowOf(Looper.getMainLooper()).idle();
        else
            Shadows.shadowOf(Looper.getMainLooper()).idleFor(Duration.ofMillis(delay));
    }

}