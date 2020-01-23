package org.kevin.jetpackx

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import org.kevin.kotlinx.get
import org.kevin.kotlinx.loge
import org.kevin.kotlinx.put
import org.kevin.kotlinx.require
import java.lang.ref.WeakReference

object SPUtils {
    private var mContext: WeakReference<Context>? = null
    @JvmStatic
    fun init(context: Context) {
        if (mContext?.get() != null) {
            loge("init must be called once.")
            return
        }
        mContext = WeakReference(context.applicationContext)
    }

    @JvmStatic
    val defaultSharedPreferences: SharedPreferences
        get() {
            val context =
                mContext?.get() ?: throw IllegalStateException("Must be called after init.")
            return PreferenceManager.getDefaultSharedPreferences(context)
        }

    @JvmStatic
    @Throws(IllegalStateException::class)
    fun getSharedPreferences(name: String?, mode: Int): SharedPreferences {
        return if (name.isNullOrEmpty()) defaultSharedPreferences else {
            val context =
                mContext?.get() ?: throw IllegalStateException("Must be called after init.")
            context.getSharedPreferences(name, mode)
        }
    }

    @JvmStatic
    @JvmOverloads
    fun getBoolean(key: String, defValue: Boolean = false, name: String? = null,
        mode: Int = Context.MODE_PRIVATE) =
        getSharedPreferences(name, mode).getBoolean(key, defValue)

    @JvmStatic
    @JvmOverloads
    fun getInt(key: String, defValue: Int = 0, name: String? = null,
        mode: Int = Context.MODE_PRIVATE) = getSharedPreferences(name, mode).getInt(key, defValue)

    @JvmStatic
    @JvmOverloads
    fun getLong(key: String, defValue: Long = 0, name: String? = null,
        mode: Int = Context.MODE_PRIVATE) = getSharedPreferences(name, mode).getLong(key, defValue)

    @JvmStatic
    @JvmOverloads
    fun getFloat(key: String, defValue: Float = 0f, name: String? = null,
        mode: Int = Context.MODE_PRIVATE) = getSharedPreferences(name, mode).getFloat(key, defValue)

    @JvmStatic
    @JvmOverloads
    fun getString(key: String, defValue: String? = null, name: String? = null,
        mode: Int = Context.MODE_PRIVATE) =
        getSharedPreferences(name, mode).getString(key, defValue)

    @JvmStatic
    @JvmOverloads
    fun getStringSet(key: String, defValue: Set<String>? = null, name: String? = null,
        mode: Int = Context.MODE_PRIVATE) =
        getSharedPreferences(name, mode).getStringSet(key, defValue)

    @JvmStatic
    @JvmOverloads
    inline fun <reified T> get(key: String, defValue: T? = null, name: String? = null,
        mode: Int = Context.MODE_PRIVATE) = getSharedPreferences(name, mode).get(key, defValue)

    @JvmStatic
    @JvmOverloads
    inline fun <reified T : Number> require(key: String, defValue: T, name: String? = null,
        mode: Int = Context.MODE_PRIVATE) = getSharedPreferences(name, mode).require(key, defValue)

    fun put(vararg pair: Pair<String, Any>) = edit {
        for ((key, value) in pair) {
            put(key, value)
        }
    }

    @JvmStatic
    @JvmOverloads
    fun edit(commit: Boolean = false, name: String? = null, mode: Int = Context.MODE_PRIVATE,
        action: SharedPreferences.Editor.() -> Unit) =
        getSharedPreferences(name, mode).edit(commit, action)
}