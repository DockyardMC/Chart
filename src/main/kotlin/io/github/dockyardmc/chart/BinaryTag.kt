package io.github.dockyardmc.chart

interface BinaryTag: BinaryTagWritable {

    fun toSNBT(): String
}