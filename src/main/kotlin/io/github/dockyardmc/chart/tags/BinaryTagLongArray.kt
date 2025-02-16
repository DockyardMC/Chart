package io.github.dockyardmc.chart.tags

import io.github.dockyardmc.chart.BinaryTag
import io.github.dockyardmc.chart.NbtNetworkReadable
import io.netty.buffer.ByteBuf

class BinaryTagLongArray(val value: Array<Long>) : BinaryTag, Iterable<Long> {

    val size = value.size

    operator fun get(index: Int): Long {
        return value[index]
    }


    override fun write(buffer: ByteBuf) {
        buffer.writeInt(size)
        value.forEach(buffer::writeLong)
    }


    companion object : NbtNetworkReadable<BinaryTagLongArray> {

        override fun read(buffer: ByteBuf): BinaryTagLongArray {
            val length = buffer.readInt()
            val inArray = buffer.readBytes(length * 8).array()
            val outArray = LongArray(length)

            for (i in 0 until length) {
                val index = i * 8
                outArray[i] = (inArray[index].toLong() shl 56) or
                        ((inArray[index + 1].toLong() and 0xFF) shl 48) or
                        ((inArray[index + 2].toLong() and 0xFF) shl 40) or
                        ((inArray[index + 3].toLong() and 0xFF) shl 32) or
                        ((inArray[index + 4].toLong() and 0xFF) shl 24) or
                        ((inArray[index + 5].toLong() and 0xFF) shl 16) or
                        ((inArray[index + 6].toLong() and 0xFF) shl 8) or
                        (inArray[index + 7].toLong() and 0xFF)
            }

            return BinaryTagLongArray(outArray.toTypedArray())
        }
    }

    override fun toSNBT(): String {
        val list = value.joinToString(",") { "${it}L" }
        return "[L;$list]"
    }

    override fun iterator(): Iterator<Long> {
        return value.iterator()
    }

}