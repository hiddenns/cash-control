package com.cashcontrol.domain.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.closestDI

abstract class BaseFragment<T : ViewBinding>(
    private val createViewBinding: (inflater: LayoutInflater, container: ViewGroup?, attachToParent: Boolean) -> T
) : Fragment(), DIAware {
    private var _binding: T? = null
    protected val binding: T get() = _binding!!
    override val di: DI by closestDI()

    val navController: NavController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = createViewBinding(layoutInflater, container, false)
        return binding.root
    }

}


