package com.mrtsuo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.mrtsuo.model.News;

public interface NewsService {
	// 查詢所有消息
	Page<News> listNews(Pageable pageable, News news);

	// 根據id查詢單一消息
	News getNews(Long id);

	// 根據標題查詢消息
	News getNewsByTitle(String title);

	// 更新消息
	News updateNews(Long id, News news);

	// 刪除消息
	void deleteNews(Long id);

	// 新增消息
	News saveNews(News news);
	
	Page<News> listNews(Pageable pageable);
	
	News getAndConvert(Long id);
}
