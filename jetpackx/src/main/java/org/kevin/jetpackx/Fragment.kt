package org.kevin.jetpackx

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import org.kevin.kotlinx.toast

@JvmOverloads
fun Fragment.toast(msg: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
    requireContext().toast(msg, duration)

@JvmOverloads
fun Fragment.toast(resId: Int, duration: Int = Toast.LENGTH_SHORT) =
    requireContext().toast(resId, duration)

@Throws(IllegalArgumentException::class)
fun FragmentManager.requireFragmentById(id: Int) =
    findFragmentById(id) ?: throw IllegalArgumentException("Cannot find fragment by id: $id")

fun <R> FragmentManager.transact(commitNow: Boolean = true, allowingStateLoss: Boolean = true,
    action: FragmentTransaction.() -> R): R {
    val ft = beginTransaction()
    val result = action(ft)
    if (commitNow) {
        if (allowingStateLoss) {
            ft.commitNowAllowingStateLoss()
        } else {
            ft.commitNow()
        }
    } else {
        if (allowingStateLoss) {
            ft.commitAllowingStateLoss()
        } else {
            ft.commit()
        }
    }
    return result
}