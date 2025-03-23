package com.dojah.kyc_sdk_kotlin.domain

data class Country(
    val id: String,
    val name: String,
    val code: String,
    val path: String,
    var selected: Boolean = false,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Country

        if (id != other.id) return false
        if (name != other.name) return false
        if (code != other.code) return false
        if (path != other.path) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + code.hashCode()
        result = 31 * result + path.hashCode()
        return result
    }
}
