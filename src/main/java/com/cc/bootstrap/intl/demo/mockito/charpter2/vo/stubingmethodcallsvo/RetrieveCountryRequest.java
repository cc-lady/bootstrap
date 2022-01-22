package com.cc.bootstrap.intl.demo.mockito.charpter2.vo.stubingmethodcallsvo;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description RetrieveCountryRequest
 * @createTime 2022年01月07日 10:50:00
 */
public class RetrieveCountryRequest {
    private Long page;
    private Integer rowPerPage;
    private String sortOrder;
    private String sortname;
    private String serachQuery;

    public RetrieveCountryRequest() {
    }

    public RetrieveCountryRequest(Long page, Integer rowPerPage, String sortOrder,
                                  String sortname, String serachQuery) {
        this.page = page;
        this.rowPerPage = rowPerPage;
        this.sortOrder = sortOrder;
        this.sortname = sortname;
        this.serachQuery = serachQuery;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Integer getRowPerPage() {
        return rowPerPage;
    }

    public void setRowPerPage(Integer rowPerPage) {
        this.rowPerPage = rowPerPage;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getSortname() {
        return sortname;
    }

    public void setSortname(String sortname) {
        this.sortname = sortname;
    }

    public String getSerachQuery() {
        return serachQuery;
    }

    public void setSerachQuery(String serachQuery) {
        this.serachQuery = serachQuery;
    }
}
