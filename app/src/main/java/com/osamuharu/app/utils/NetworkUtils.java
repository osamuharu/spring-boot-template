package com.osamuharu.app.utils;

import jakarta.servlet.http.HttpServletRequest;

public class NetworkUtils {

  private static final String[] IP_HEADERS = {
      "X-Forwarded-For",
      "X-Real-IP",
      "Proxy-Client-IP",
      "WL-Proxy-Client-IP",
      "HTTP_CLIENT_IP",
      "HTTP_X_FORWARDED_FOR"
  };

  public static String getClientIp(HttpServletRequest request) {
    for (String header : IP_HEADERS) {
      String ip = request.getHeader(header);
      if (isValidIp(ip)) {
        return extractFirstIp(ip);
      }
    }
    return request.getRemoteAddr();
  }

  private static boolean isValidIp(String ip) {
    return ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip);
  }

  private static String extractFirstIp(String ip) {
    int commaIndex = ip.indexOf(',');
    return commaIndex != -1 ? ip.substring(0, commaIndex).trim() : ip;
  }
}