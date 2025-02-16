package io.github.dockyardmc.chart.tags

import io.github.dockyardmc.chart.BinaryTag
import io.github.dockyardmc.chart.NbtNetworkReadable
import io.netty.buffer.ByteBuf

class BinaryTagIntArray(val value: Array<Int>) : BinaryTag, Iterable<Int> {

    val size = value.size

    operator fun get(index: Int): Int {
        return value[index]
    }

    override fun write(buffer: ByteBuf) {
        buffer.writeInt(size)
        value.forEach(buffer::writeInt)
    }

    override fun toSNBT(): String {
        val list = value.joinToString(",") { "$it" }
        return "[I;$list]"
    }

    override fun toString() = toSNBT()


    override fun iterator(): Iterator<Int> {
        return value.iterator()
    }

    companion object : NbtNetworkReadable<BinaryTagIntArray> {

        override fun read(buffer: ByteBuf): BinaryTagIntArray {
            val length = buffer.readInt()
            val inArray = buffer.readBytes(length * 4).array()
            val outArray = IntArray(length)

                        for(i in 0 until length) {
                val index = i * 4
                outArray[i] = (inArray[index].toInt() and 0xFF shl 24) or
                        (inArray[index + 1].toInt() and 0xFF shl 16) or
                        (inArray[index + 2].toInt() and 0xFF shl 8) or
                        (inArray[index + 3].toInt() and 0xFF)
            }

            return BinaryTagIntArray(outArray.toTypedArray())
        }
    }
}