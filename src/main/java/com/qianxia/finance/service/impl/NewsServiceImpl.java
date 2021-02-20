package com.qianxia.finance.service.impl;

import com.qianxia.finance.domain.News;
import com.qianxia.finance.mapper.NewsMapper;
import com.qianxia.finance.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public List<News> queryNewsAll() {
        return newsMapper.queryNewsAll();
    }
}
