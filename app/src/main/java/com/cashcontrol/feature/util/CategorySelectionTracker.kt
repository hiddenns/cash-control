package com.cashcontrol.feature.util

import androidx.recyclerview.selection.SelectionTracker

abstract class SelectionTracker<K : Any> : SelectionTracker.SelectionPredicate<K>() {
    override fun canSetStateForKey(key: K, nextState: Boolean):
            Boolean {
        return true
    }

    override fun canSetStateAtPosition(
        position: Int, nextState:
        Boolean
    ): Boolean {
        return true
    }

    override fun canSelectMultiple(): Boolean {
        return false
    }
}

class CategorySelectionTracker<K : Any> : SelectionTracker.SelectionPredicate<K>() {
    override fun canSetStateForKey(key: K, nextState: Boolean):
            Boolean {
        return true
    }

    override fun canSetStateAtPosition(
        position: Int, nextState:
        Boolean
    ): Boolean {
        return true
    }

    override fun canSelectMultiple(): Boolean {
        return false
    }

}