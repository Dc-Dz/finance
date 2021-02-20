package com.qianxia.finance.mapper;

import com.qianxia.finance.domain.News;

import java.util.List;

public interface NewsMapper {
    /**
     * 查询所有新闻资讯
     * @return
     */
    List<News> queryNewsAll();
}
