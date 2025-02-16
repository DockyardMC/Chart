package io.github.dockyardmc.chart

import io.github.dockyardmc.chart.extensions.read
import io.github.dockyardmc.chart.tags.BinaryTagEnd
import io.netty.buffer.ByteBuf
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream
import kotlin.reflect.full.companionObject

object BinaryTagWriterUtil {

    fun writeTagEnd(buffer: ByteBuf) {
        val id = NbtTagRegistry.getFromClassOrNull(BinaryTagEnd::class)
            ?: throw IllegalArgumentException("No ID for binary tag ${BinaryTagEnd::class.simpleName} (Not registered in registry)")
        buffer.writeByte(id)
    }

    fun writeFullyFormattedTag(buffer: ByteBuf, name: String, tag: BinaryTag) {
        val id = NbtTagRegistry.getFromClass(tag::class)
        buffer.writeByte(id)

        val outputStream = ByteArrayOutputStream()
        DataOutputStream(outputStream).writeUTF(name)
        buffer.writeBytes(outputStream.toByteArray())

        tag.write(buffer)
    }

    fun readFullyFormattedTag(buffer: ByteBuf): Pair<String, BinaryTag> {
        val id = buffer.readByte()
        val tag = NbtTagRegistry.getFromId(id.toInt())

        if (tag::class == BinaryTagEnd::class) return "" to BinaryTagEnd()

        val byteArray = ByteArray(0)
        val inputStream = ByteArrayInputStream(byteArray)
        val name = DataInputStream(inputStream).readUTF()

        return name to tag.read(buffer)
    }
}