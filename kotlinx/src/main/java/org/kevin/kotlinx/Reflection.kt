package org.kevin.kotlinx

inline fun <reified T> T.set(property: String, value: Any?) {
    val cls = T::class.java
    try {
        val field = cls.getDeclaredField(property)
        field.isAccessible = true
        field.set(this, value)
    } catch (e: NoSuchFieldException) {
    } catch (e: IllegalAccessException) {
    } catch (e: IllegalArgumentException) {
    }
}