package com.niknightarts.githubclient.utils

class EventLiveData {
    companion object {
        var progress: SingleLiveData<Boolean> = SingleLiveData()
        var appEvent: SingleLiveData<NavData> = SingleLiveData()

        fun setProgress(b: Boolean) = progress.postValue(b)
    }
}

data class NavData(val tag: String, val data: Any? = null)
