package com.krinitsyn.utils

import com.krinitsyn.utils.logger.Logger
import io.reactivex.exceptions.Exceptions

object RxThrowable {

    private const val Tag = "RxThrowable"

    private const val CallerIndex = 1

    fun printStackTrace(logger: Logger, propagate: Boolean = false): (Throwable) -> Unit {
        val stackTraceThrowable = Throwable()
        return { throwable ->
            val traces = stackTraceThrowable.stackTrace
            if (traces != null && traces.size > CallerIndex + 1) {
                val trace = traces[CallerIndex]

                val className = trace.className
                val fileName = trace.fileName
                val tag = if (fileName.isNullOrBlank()) className else fileName

                val lineNumber = trace.lineNumber
                val formattedLine = if (lineNumber > 0) String.format("at line $lineNumber") else ""
                val message = String.format("Exception in $className.${trace.methodName}() $formattedLine")

                logger.e(tag, message, throwable)
            } else {
                logger.e(Tag, throwable.message ?: "", throwable)
            }

            if (propagate) {
                Exceptions.propagate(throwable)
            }
        }
    }

}