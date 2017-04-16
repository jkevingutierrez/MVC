package co.informatica.mvc.filters

import java.io.IOException
import javax.servlet._
import javax.servlet.http.{HttpServletRequest, HttpServletResponse}


class GZipServletFilter extends Filter {
  @throws[ServletException]
  override def init(filterConfig: FilterConfig): Unit = {
  }

  override def destroy(): Unit = {
  }

  @throws[IOException]
  @throws[ServletException]
  override def doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain): Unit = {
    val httpRequest = request.asInstanceOf[HttpServletRequest]
    val httpResponse = response.asInstanceOf[HttpServletResponse]
    if (acceptsGZipEncoding(httpRequest)) {
      httpResponse.addHeader("Content-Encoding", "gzip")
      val gzipResponse = new GZipServletResponseWrapper(httpResponse)
      chain.doFilter(request, gzipResponse)
      gzipResponse.close
    }
    else chain.doFilter(request, response)
  }

  private def acceptsGZipEncoding(httpRequest: HttpServletRequest) = {
    val acceptEncoding = httpRequest.getHeader("Accept-Encoding")
    acceptEncoding != null && acceptEncoding.indexOf("gzip") != -1
  }
}
