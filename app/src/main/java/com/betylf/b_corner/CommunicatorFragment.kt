package com.betylf.b_corner

import androidx.fragment.app.Fragment

abstract class CommunicatorFragment : Fragment() {
    abstract fun processReceivedData(key: String, value: Boolean)
    abstract fun registerDataCallback(callback: (key: String, value: Boolean) -> Unit)
}