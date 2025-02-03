package io.github.dockyardmc.lodestone.tags

import io.github.dockyardmc.lodestone.BinaryTag
import io.netty.buffer.ByteBuf
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream

class NbtTagString(val value: String): BinaryTag {

    override fun write(buffer: ByteBuf) {
        val outputStream = ByteArrayOutputStream()
        DataOutputStream(outputStream).writeUTF(value)
        buffer.writeBytes(outputStream.toByteArray())
    }

    override fun toSNBT(): String {
        val escaped = value.replace("\"", "\\\"")
        return "\"$escaped\""
    }

    companion object {
        fun read(buffer: ByteBuf): NbtTagString {
            val inputStream = ByteArrayInputStream(buffer.copy().array())
            val string = DataInputStream(inputStream).readUTF()

            return NbtTagString(string)
        }
    }
}