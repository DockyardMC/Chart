package io.github.dockyardmc.chart

import io.github.dockyardmc.chart.tags.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.reflect.KClass

object NbtTagRegistry {

    private val tags: MutableMap<Int, KClass<out BinaryTag>> = mutableMapOf()
    private val reversed: MutableMap<KClass<out BinaryTag>, Int> = mutableMapOf()
    private val protocolIdCounter = AtomicInteger()

    val TAG_END = register(NbtTagEnd::class)
    val TAG_BYTE = register(NbtTagByte::class)
    val TAG_SHORT = register(NbtTagShort::class)
    val TAG_INT = register(NbtTagInt::class)
    val TAG_LONG = register(NbtTagLong::class)
    val TAG_FLOAT = register(NbtTagFloat::class)
    val TAG_DOUBLE = register(NbtTagDouble::class)
    val TAG_BYTE_ARRAY = register(NbtTagByteArray::class)
    val TAG_LIST = register(NbtTagList::class)

    fun getFromIdOrNull(id: Int): KClass<out BinaryTag>? {
        return tags[id]
    }

    fun getFromId(id: Int): KClass<out BinaryTag> {
        return tags[id] ?: throw NoSuchElementException("No element in nbt tag registry with id $id")
    }

    fun getFromClassOrNull(value: KClass<out BinaryTag>): Int? {
        return reversed[value]
    }

    fun getFromClass(value: KClass<out BinaryTag>): Int {
        return getFromClassOrNull(value) ?: throw NoSuchElementException("No element in nbt tag registry with class ${value.simpleName}")
    }

    inline fun <reified T: BinaryTag> getReified() {
        val klass = T::class

    }

    fun register(binaryTag: KClass<out BinaryTag>): KClass<out BinaryTag> {
        val id = protocolIdCounter.getAndIncrement()
        tags[id] = binaryTag
        reversed[binaryTag] = id

        return binaryTag
    }

}