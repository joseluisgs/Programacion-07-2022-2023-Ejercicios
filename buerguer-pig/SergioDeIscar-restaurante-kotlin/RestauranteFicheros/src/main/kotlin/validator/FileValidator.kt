package validator

import java.io.File

fun canReed(file: File): Boolean{
    return file.exists() && file.canRead()
}

fun canWrite(file: File): Boolean{
    return !file.exists() || (file.exists() && file.canWrite())
}