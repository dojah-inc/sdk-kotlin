package com.dojah.kyc_sdk_kotlin.domain

data class Country(
    val id: String,
    val name: String,
    val code: String,
    val path: String,
    val states: List<CountryState> = emptyList(),
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

data class CountryState(
    val name: String,
    val subdivision: List<String>,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CountryState

        return name == other.name
    }

    override fun hashCode(): Int {
        return 31 * name.hashCode()
    }
}
