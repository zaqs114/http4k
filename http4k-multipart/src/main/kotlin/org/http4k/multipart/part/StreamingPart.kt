package org.http4k.multipart.part

import org.http4k.multipart.stream.StreamUtil

import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

class StreamingPart(fieldName: String?, formField: Boolean, contentType: String?, fileName: String?, val inputStream: InputStream, headers: Map<String, String>) : PartMetaData(fieldName, formField, contentType, fileName, headers) {

    val contentsAsString: String
        @Throws(IOException::class)
        get() = getContentsAsString(StandardCharsets.UTF_8, 4096)

    @Throws(IOException::class)
    fun getContentsAsString(encoding: Charset, maxPartContentSize: Int): String = StreamUtil.readStringFromInputStream(inputStream, encoding, maxPartContentSize)

}
