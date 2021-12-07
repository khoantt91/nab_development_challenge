package com.example.nabchallenge.utils.log

import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

// Debug Log Extension
fun eLog(string: Any) = DbLog.writeLog("e", string)
fun dLog(string: Any) = DbLog.writeLog("d", string)
fun wLog(string: Any) = DbLog.writeLog("w", string)
fun iLog(string: Any) = DbLog.writeLog("i", string)
fun wtfLog(string: Any) = DbLog.writeLog("wtf", string)

object DbLog {

    private var isDebug: Boolean = true                         // Will replace by BuildType Variant

    fun writeLog(type: String, obj: Any) {
        if (!isDebug) return

        /* Get info of log line */
        val pair = getMethodNames(Throwable().stackTrace)
        val className = pair.first
        val methodName = pair.second
//        val text = "[$methodName]\n$obj"
        val text = "$obj"

        when (type) {
            "e" -> Log.e(className, text)
            "i" -> Log.i(className, text)
            "d" -> Log.d(className, text)
            "w" -> Log.w(className, text)
            "wtf" -> Log.wtf(className, text)
        }
    }

    //region Private Support Methods
    private fun getMethodNames(sElements: Array<StackTraceElement>): Pair<String, String> {
        return Pair(sElements[2].fileName, sElements[2].methodName)
    }

    private fun parseToJsonOrNull(text: String?): Any? = text?.let {
        try {
            JSONObject(text)
        } catch (ex: JSONException) {
            try {
                JSONArray(text)
            } catch (ex1: JSONException) {
                null
            }
        }
    }
    //endregion

}