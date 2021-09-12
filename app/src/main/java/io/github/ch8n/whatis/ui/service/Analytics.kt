package io.github.ch8n.whatis.ui.service

import android.util.Log
import androidx.core.os.bundleOf
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import java.util.*

interface AnalyticsEngine {
    fun log(
        event: String,
        vararg attributes: Pair<String, Any?>
    )
}

object AppAnalytics {

    private val sessionId = UUID.randomUUID().toString()

    private val engine = listOf<AnalyticsEngine>(
        FirebaseAnalytics(),
        Logger()
    )

    fun log(
        event: String,
        vararg attributes: Pair<String, Any?>
    ) {


        engine.forEach {
            it.log(
                event, *attributes, "SessionId" to sessionId
            )
        }
    }

}

class FirebaseAnalytics : AnalyticsEngine {

    private val analytics by lazy { Firebase.analytics }

    override fun log(event: String, vararg attributes: Pair<String, Any?>) {
        analytics.logEvent(
            event,
            attributes.fold(bundleOf()) { bundle, (key, value) ->
                bundle.putString(key, value.toString())
                bundle
            }
        )
    }
}

class Logger : AnalyticsEngine {
    override fun log(event: String, vararg attributes: Pair<String, Any?>) {
        Log.d(event, attributes.joinToString(",") { "${it.first}->${it.second}" })
    }
}