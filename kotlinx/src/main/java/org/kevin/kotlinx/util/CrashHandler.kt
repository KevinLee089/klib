package org.kevin.kotlinx.util

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.os.Process
import org.kevin.kotlinx.getFormatString
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.io.PrintWriter
import java.util.*

class CrashHandler(private val context: Context): Thread.UncaughtExceptionHandler {

    companion object {
        fun initialize(context: Context) {
            CrashHandler(context.applicationContext)
        }
    }

    private var defaultHandler: Thread.UncaughtExceptionHandler? = null
    init {
        defaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(t: Thread?, e: Throwable) {
        try {
            writeToSdcard(e)
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        if (defaultHandler != null) {
            defaultHandler?.uncaughtException(t, e)
        } else {
            Process.killProcess(Process.myPid())
        }
    }

    @Throws(IOException::class, PackageManager.NameNotFoundException::class)
    private fun writeToSdcard(t: Throwable) {
        if (Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED) return
        val pkg = context.packageName
        val dir = File(Environment.getExternalStorageDirectory(), "$pkg${File.separator}crash")
        if (!dir.exists()) {
            dir.mkdirs()
        }

        val current = Calendar.getInstance().time.getFormatString()
        val file = File(dir, "$current.txt")
        if (file.exists()) {
            file.delete()
        }
        file.createNewFile()

        val writer = PrintWriter(FileWriter(file))
        writer.println(current)
        val pm = context.packageManager
        val pInfo = pm.getPackageInfo(pkg, 0)
        writer.println("App Version: ${pInfo.versionName}")
        writer.println("OS Version: ${Build.VERSION.RELEASE}")
        writer.println("Manufacturer: ${Build.MANUFACTURER}")
        writer.println("Model: ${Build.MODEL}")
        writer.println("CPU ABI: ${Build.SUPPORTED_ABIS.joinToString()}")
        t.printStackTrace(writer)
        writer.close()
    }
}