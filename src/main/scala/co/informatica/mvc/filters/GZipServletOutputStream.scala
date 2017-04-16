package co.informatica.mvc.filters

import java.io.{IOException, OutputStream}
import java.util.zip.GZIPOutputStream
import javax.servlet.{ServletOutputStream, WriteListener}

class GZipServletOutputStream @throws[IOException]
(val output: OutputStream) extends ServletOutputStream {
  private val gzipOutputStream = new GZIPOutputStream(output)

  @throws[IOException]
  override def close(): Unit = {
    this.gzipOutputStream.close()
  }

  @throws[IOException]
  override def flush(): Unit = {
    this.gzipOutputStream.flush()
  }

  @throws[IOException]
  override def write(b: Array[Byte]): Unit = {
    this.gzipOutputStream.write(b)
  }

  @throws[IOException]
  override def write(b: Array[Byte], off: Int, len: Int): Unit = {
    this.gzipOutputStream.write(b, off, len)
  }

  @throws[IOException]
  override def write(b: Int): Unit = {
    this.gzipOutputStream.write(b)
  }

  override def isReady: Boolean = ???

  override def setWriteListener(writeListener: WriteListener): Unit = {

  }
}
