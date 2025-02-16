package io.github.dockyardmc.chart.tags

import io.github.dockyardmc.chart.BinaryTag
import io.github.dockyardmc.chart.NbtNetworkReadable
import io.netty.buffer.ByteBuf

class BinaryTagCompound(inputTags: Map<String, BinaryTag>): BinaryTag {

    private val tags = inputTags.toMutableMap()

    override fun write(buffer: ByteBuf) {
        tags.forEach { (name, tag) ->
            BinaryTag.writeFullyFormattedTag(buffer, name, tag)
        }
        BinaryTag.writeTagEnd(buffer)
    }

    override fun toSNBT(): String {
        val tagStr = tags.map { entry ->
            "\"${entry.key.replace("\"", "\\\"")}\":${entry.value.toSNBT()}"
        }.joinToString(",")
        return "{$tagStr}"
    }

    operator fun set(key: String, value: BinaryTag) {
        tags[key] = value
    }

    fun getOrNull(key: String): BinaryTag? {
        return tags[key]
    }

    operator fun get(key: String): BinaryTag {
        return getOrNull(key) ?: throw NoSuchElementException("No binary tag with key $key in compound tag")
    }

    override fun toString(): String {
        return toSNBT()
    }


    companion object: NbtNetworkReadable<BinaryTagCompound> {
        override fun read(buffer: ByteBuf): BinaryTagCompound {
            val tags = mutableMapOf<String, BinaryTag>()
            do {
                val tag = BinaryTag.readFullyFormattedTag(buffer)
                tags[tag.first] = tag.second

            } while (tag.second !is BinaryTagEnd)
            return BinaryTagCompound(tags)
        }
    }
}