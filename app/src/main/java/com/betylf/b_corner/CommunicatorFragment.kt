package com.betylf.b_corner

import androidx.fragment.app.Fragment

abstract class CommunicatorFragment : Fragment() {
    abstract fun onReceive(key: String, value: Boolean)
    abstract fun registerReceiver(dataReceiver: (key: String, value: Boolean) -> Unit)

}