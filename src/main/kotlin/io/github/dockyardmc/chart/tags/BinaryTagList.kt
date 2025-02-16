package io.github.dockyardmc.chart.tags

import io.github.dockyardmc.chart.BinaryTag
import io.github.dockyardmc.chart.NbtNetworkReadable
import io.github.dockyardmc.chart.NbtTagRegistry
import io.netty.buffer.ByteBuf
import kotlin.reflect.full.companionObject

class BinaryTagList<T: BinaryTag>(val values: Collection<T>): BinaryTag {

    override fun write(buffer: ByteBuf) {
        val id = NbtTagRegistry.getFromClass(values.first()::class)

        buffer.writeByte(id)
        buffer.writeInt(values.size)
        values.forEach { tag ->
            tag.write(buffer)
        }
    }

    companion object: NbtNetworkReadable<BinaryTagList<BinaryTag>> {

        override fun read(buffer: ByteBuf): BinaryTagList<BinaryTag> {

            var list = mutableListOf<BinaryTag>()

            val id = buffer.readByte()
            val tag = NbtTagRegistry.getFromIdOrNull(id.toInt()) ?: throw IllegalStateException("Binary Tag with id $id is not in the nbt tag registry!")
            val size = buffer.readInt()


            val companionObject = tag.companionObject ?: throw IllegalStateException("${tag.simpleName} does not have a companion object!")
            if(companionObject !is NbtNetworkReadable<*>) throw IllegalStateException("companion object is not NbtNetworkReadable!")

            for (i in 0 until size) {
                list.add(companionObject.read(buffer))
            }

            return BinaryTagList<BinaryTag>(list)
        }

    }

    override fun toSNBT(): String {
        return "[${values.joinToString(",") { tag -> tag.toSNBT() }}]"
    }
}