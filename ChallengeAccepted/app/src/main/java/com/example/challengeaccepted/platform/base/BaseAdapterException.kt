package com.example.challengeaccepted.platform.base

sealed class BaseAdapterException(override val message: String?) : Exception(message) {

    class ItemNotFoundException(indexOfItemAttempt: Int) :
        BaseAdapterException("Item not found by index: $indexOfItemAttempt")

    class PositionInvalidToBindViewHolderException(position: Int) :
        BaseAdapterException("Item not found to bind ${BaseViewHolder::class.java} by position: $position")

    class NotFoundClassDataBindingGeneratedException(className: String) :
        BaseAdapterException("Class Not Found to cast: $className")
}
