package io.github.dockyardmc.chart.tags

import io.github.dockyardmc.chart.BinaryTag
import io.github.dockyardmc.chart.BinaryTagReadable
import io.github.dockyardmc.chart.BinaryTagRegistry
import io.netty.buffer.ByteBuf
import kotlin.reflect.full.companionObject
import kotlin.reflect.full.declaredMemberFunctions

class BinaryTagList<T : BinaryTag>(val values: Collection<T>) : BinaryTag {

    override fun write(buffer: ByteBuf) {
        val id = BinaryTagRegistry.getFromClass(values.first()::class)

        buffer.writeByte(id)
        buffer.writeInt(values.size)
        values.forEach { tag ->
            tag.write(buffer)
        }
    }

    companion object : BinaryTagReadable<BinaryTagList<*>> {
        override fun read(buffer: ByteBuf): BinaryTagList<*> {
            val list = mutableListOf<BinaryTag>()
            val id = buffer.readByte().toInt()
            val size = buffer.readInt()
            for (i in 0 until size) {
                val tagClass = BinaryTagRegistry.getFromId(id)
                val companionObject = tagClass.companionObject ?: throw IllegalStateException("${tagClass.simpleName} does not have companion object")
                val readFunction = companionObject.declaredMemberFunctions.find { it.name == "read" } ?: throw IllegalStateException("${tagClass.simpleName} does not have read function within companion object")
                val tag = readFunction.call(companionObject.objectInstance, buffer) as BinaryTag
                list.add(tag)
            }
            return BinaryTagList<BinaryTag>(list)
        }
    }

    override fun toSNBT(): String {
        return "[${values.joinToString(",") { tag -> tag.toSNBT() }}]"
    }
}