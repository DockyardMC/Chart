package io.github.dockyardmc.chart.tags

import io.github.dockyardmc.chart.BinaryTag
import io.github.dockyardmc.chart.BinaryTagReadable
import io.netty.buffer.ByteBuf

class BinaryTagByte(val value: Byte): BinaryTag {

    constructor(value: Boolean): this(if(value) 1 else 0)

    fun toBoolean(): Boolean = value != 0.toByte()
    fun toInt(): Int = value.toInt()

    override fun write(buffer: ByteBuf) {
        buffer.writeByte(value.toInt())
    }

    override fun toSNBT(): String {
        return "${value}B"
    }

    companion object: BinaryTagReadable<BinaryTagByte> {
        val ONE = BinaryTagByte(1)
        val ZERO = BinaryTagByte(0)

        override fun read(buffer: ByteBuf): BinaryTagByte {
            return BinaryTagByte(buffer.readByte())
        }
    }
}