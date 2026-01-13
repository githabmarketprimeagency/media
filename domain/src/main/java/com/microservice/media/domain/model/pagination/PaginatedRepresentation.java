package com.microservice.media.domain.model.pagination;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PaginatedRepresentation<T> {
  @Getter
  @Setter
  public static class Embedded<U> {
    private List<U> items = new ArrayList<>();
    public Embedded(List<U> items) {
      this.items = items;
    }
  }
  @Getter
  @Setter
  public static class PageNavigationLink {
    private String href;
    public PageNavigationLink(String href) {
      this.href = href;
    }
  }
  @Getter
  @Setter
  public static class PageNavigationLinks {
    private PageNavigationLink self;
    private PageNavigationLink first;
    private PageNavigationLink last;
    private PageNavigationLink next;
    private PageNavigationLink previous;
  }
  private Integer pageNumber;
  private Integer pageSize;
  private Integer totalPages;
  private Integer total;
  private PageNavigationLinks links;
  private Embedded<T> embedded;

  protected PaginatedRepresentation(List<T> content, int totalPages, int pageSize, int pageNumber,
          long totalElements, PageNavigationLinks pageNavigationlinks) {
      setEmbedded(new Embedded<T>(content));
      setPageSize(pageSize);
      setPageNumber(pageNumber);
      setTotal((int) totalElements);
      setTotalPages(totalPages);
      setLinks(pageNavigationlinks);
    }

}