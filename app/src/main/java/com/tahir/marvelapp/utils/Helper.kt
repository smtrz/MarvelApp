package com.tahir.marvelapp.utils

import com.tahir.marvelapp.BuildConfig
import java.math.BigInteger
import java.security.MessageDigest

object Helper {

    fun getHash(): String {
        return md5("1" + BuildConfig.MARVEL_PRIVATE_KEY + BuildConfig.MARVEL_PUBLIC_KEY)
    }

    fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}