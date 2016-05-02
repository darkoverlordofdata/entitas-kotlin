package com.darkoverlordofdata.entitas

/**
 * Entitas
 *
 * pre alpha
 *
 */
object Version {
    val major = 0
    val minor = 0
    val build = 2

    val description = "pre-alpha"

    override fun toString(): String {
        return "$major.$minor.$build"
    }
}