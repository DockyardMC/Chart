package io.github.dockyardmc.chart.tags

import io.github.dockyardmc.chart.BinaryTag
import io.github.dockyardmc.chart.BinaryTagReadable
import io.netty.buffer.ByteBuf
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream

class BinaryTagString(val value: String): BinaryTag {

    override fun write(buffer: ByteBuf) {
        val outputStream = ByteArrayOutputStream()
        DataOutputStream(outputStream).writeUTF(value)
        buffer.writeBytes(outputStream.toByteArray())
    }

    override fun toSNBT(): String {
        val escaped = value.replace("\"", "\\\"")
        return "\"$escaped\""
    }

    companion object: BinaryTagReadable<BinaryTagString> {
        override fun read(buffer: ByteBuf): BinaryTagString {
            val inputStream = ByteArrayInputStream(buffer.copy().array())
            val string = DataInputStream(inputStream).readUTF()

            return BinaryTagString(string)
        }
    }
}