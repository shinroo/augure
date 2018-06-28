package com.gravetii.augure;

import com.gravetii.augure.pojo.LinkPreview;
import com.gravetii.augure.pojo.UriDocument;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by sandeepd on 28/06/18.
 */
public class Augure {
  private static String getFullUrl(String url) {
    url = url.trim();
    url = deCapitalize(url);
    int index = url.indexOf(" ");

    if (index > 0)
      url = url.substring(0, index);

    if (!(url.startsWith("http://") || (url.startsWith("https://")))) {
      url = "http://" + url;
    }

    return url;
  }

  private static String deCapitalize(String str) {
    if (StringUtils.isEmpty(str)) {
      return str;
    }

    char c[] = str.toCharArray();
    c[0] = Character.toLowerCase(c[0]);
    return new String(c);
  }

  public LinkPreview get(String url) throws Exception {
    url = getFullUrl(url);
    UriDocument document = new UriDocument(url);
    return document.getMetaInfo();
  }

  public static void main(String[] args) throws Exception {
    String url = "http://facebook.com/sandeep.dasika";
    Augure augure = new Augure();
    LinkPreview preview = augure.get(url);
    System.out.println(preview);
  }
}
