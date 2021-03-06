package io.github.gravetii.extractor;

import io.github.gravetii.pojo.LinkPreview;
import io.github.gravetii.pojo.UriDocument;

public class UriOpenGraphExtractor implements IUriExtractor {

  @Override
  public LinkPreview getMetaInfo(UriDocument document) {
    LinkPreview preview = new LinkPreview(document.getUrl());
    String title = getMetaContent(document, "title");
    String description = getMetaContent(document, "description");
    String thumbnailUrl = getMetaContent(document, "image");
    preview.setThumbnailUrl(thumbnailUrl)
        .setDescription(description)
        .setTitle(title);

    return preview;
  }

  private String getMetaContent(UriDocument document, String tag) {

    String sb = "meta[name=og:" + tag + "]";
    String data = document.getMetaInfo(sb, "content");
    if (data == null) {
      sb = "meta[property=og:" + tag + "]";
      data = document.getMetaInfo(sb, "content");
    }
    return data;
  }
}
