package org.kevin.compat

import android.content.pm.PackageManager
import android.os.Build

object PackageManagerCompat {
    @JvmStatic
    val MATCH_UNINSTALLED_PACKAGES =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) PackageManager.MATCH_UNINSTALLED_PACKAGES else PackageManager.GET_UNINSTALLED_PACKAGES
}