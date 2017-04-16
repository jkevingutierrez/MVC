package co.informatica.mvc.filters

import java.io.{IOException, OutputStreamWriter, PrintWriter}
import javax.servlet.ServletOutputStream
import javax.servlet.http.{HttpServletResponse, HttpServletResponseWrapper}

class GZipServletResponseWrapper(val response: HttpServletResponse) extends HttpServletResponseWrapper(response) {
  private var gzipOutputStream: GZipServletOutputStream = null
  private var printWriter: PrintWriter = null

  def close(): Unit = { //PrintWriter.close does not throw exceptions.
    //Hence no try-catch block.
    if (this.printWriter != null) this.printWriter.close()
    if (this.gzipOutputStream != null) this.gzipOutputStream.close
  }

  override def flushBuffer(): Unit = { //PrintWriter.flush() does not throw exception
    if (this.printWriter != null) this.printWriter.flush()
    var exception1: IOException = null
    try
        if (this.gzipOutputStream != null) this.gzipOutputStream.flush
    catch {
      case e: IOException =>
        exception1 = e
    }
    var exception2: IOException = null
    try
      super.flushBuffer()
    catch {
      case e: IOException =>
        exception2 = e
    }
    if (exception1 != null) throw exception1
    if (exception2 != null) throw exception2
  }

  override def getOutputStream: ServletOutputStream = {
    if (this.printWriter != null) throw new IllegalStateException("PrintWriter obtained already - cannot get OutputStream")
    if (this.gzipOutputStream == null) this.gzipOutputStream = new GZipServletOutputStream(getResponse.getOutputStream)
    this.gzipOutputStream
  }

  override def getWriter: PrintWriter = {
    if (this.printWriter == null && this.gzipOutputStream != null) throw new IllegalStateException("OutputStream obtained already - cannot get PrintWriter")
    if (this.printWriter == null) {
      this.gzipOutputStream = new GZipServletOutputStream(getResponse.getOutputStream)
      this.printWriter = new PrintWriter(new OutputStreamWriter(this.gzipOutputStream, getResponse.getCharacterEncoding))
    }
    this.printWriter
  }

  override def setContentLength(len: Int): Unit = {
    //ignore, since content length of zipped content
    //does not match content length of unzipped content.
  }
}