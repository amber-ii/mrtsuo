package com.mrtsuo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.mrtsuo.NotFoundException;
import com.mrtsuo.model.News;
import com.mrtsuo.repository.NewsRepository;
import com.mrtsuo.service.NewsService;
import com.mrtsuo.util.MyBeanUtils;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsRepository newsRepository;

//	根據ＩＤ查詢
	@Override
	public News getNews(Long id) {
		return newsRepository.findOne(id);
	}

//	分頁查詢
	@Override
	public Page<News> listNews(Pageable pageable, News news) {
		return newsRepository.findAll(new Specification<News>() {

			@Override
			public Predicate toPredicate(Root<News> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				if (!"".equals(news.getTitle()) && news.getTitle() != null) {
					predicates.add(cb.like(root.<String>get("title"), "%" + news.getTitle() + "%"));
				}
				query.where(predicates.toArray(new Predicate[predicates.size()]));
				return null;
			}
		}, pageable);
	}

//	編輯修改
	@Override
	public News updateNews(Long id, News news) {
		News n = newsRepository.findOne(id);
		if (n == null) {
			throw new NotFoundException("該則消息不存在");
		}
		BeanUtils.copyProperties(news, n, MyBeanUtils.getNullPropertyNames(news));
		n.setUpdateTime(new Date());
		return newsRepository.save(n);
	}

//	刪除
	@Override
	public void deleteNews(Long id) {
		newsRepository.delete(id);
	}

//	新增
	@Override
	public News saveNews(News news) {
		if(news.getId() == null) {
			news.setCreateTime(new Date());
			news.setUpdateTime(new Date());
		}else {
			news.setUpdateTime(new Date());
		}
		return newsRepository.save(news);
	}

//	根據標題查詢
	@Override
	public News getNewsByTitle(String title) {
		return null;
	}

}
