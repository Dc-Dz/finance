package com.qianxia.finance.domain;

import java.util.Date;

/**
 * 新闻资讯表
 */
public class News {

   private Integer id;  // 新闻资讯id
   private String title;  // 标题
   private String newsDesc;  // 内容
   private Date createTime;  // 新闻发布时间


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNewsDesc() {
        return newsDesc;
    }

    public void setNewsDesc(String newsDesc) {
        this.newsDesc = newsDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
