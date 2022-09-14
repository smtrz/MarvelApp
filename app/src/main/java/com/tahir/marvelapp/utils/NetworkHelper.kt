package com.tahir.marvelapp.utils

object NetworkHelper {
    const val MAX_RETRIES = 3L
    const val INITIAL_BACKOFF = 2000L
    fun getBackoffDelay(attempt: Long) = INITIAL_BACKOFF * (attempt + 1)

}