package pammi.ibringmypet.utils


inline fun <reified T: Enum<T>> T.next(): T {
    val values = enumValues<T>()
    val nextOrdinal = (ordinal + 1) % values.size
    return values[nextOrdinal]
}

inline fun <reified T: Enum<T>> T.nextOrLast(): T {
    val values = enumValues<T>()
    val nextOrdinal = if ((ordinal + 1) >= values.size) ordinal else (ordinal+1)
    return values[nextOrdinal]
}


inline fun <reified T: Enum<T>> T.previousOrFirst(): T {
    val values = enumValues<T>()
    val previousOrdinal = if ((ordinal - 1) < 0) ordinal else (ordinal-1)
    return values[previousOrdinal]
}

inline fun <reified T: Enum<T>> T.previous(): T {
    val values = enumValues<T>()
    val previousOrdinal = if ((ordinal - 1) < 0) values.size -1 else (ordinal-1)
    return values[previousOrdinal]
}

