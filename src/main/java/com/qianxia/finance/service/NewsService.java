package com.qianxia.finance.service;

import com.qianxia.finance.domain.News;

import java.util.List;

public interface NewsService {

    /**
     * 查询所有新闻资讯
     * @return
     */
    List<News> queryNewsAll();
}
