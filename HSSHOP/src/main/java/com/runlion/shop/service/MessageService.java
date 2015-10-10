package com.runlion.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runlion.shop.dao.MessageMapper;
import com.runlion.shop.entity.BspMessage;

@Service
public class MessageService {
	@Autowired
	private MessageMapper messageMapper;

	/**
	 * 保存
	 * 
	 * @return
	 */
	public int insertMessage(BspMessage bspMessage) {
		if (bspMessage != null) {
			return messageMapper.insertMessage(bspMessage);
		} else {
			return 0;
		}
	}

	/**
	 * 查询所有信息
	 * 
	 * @return
	 */
	public List<BspMessage> getMessageList(int startNum, int limitNum) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("START_NUM", startNum);
		par.put("LIMIT_NUM", limitNum);
		return messageMapper.getMessageList(par);
	}

	/**
	 * 查询所有短信总数
	 * 
	 * @return
	 */
	public int countMessage() {
		Map<String, Object> par = new HashMap<String, Object>();
		return messageMapper.countMessage(par);
	}
}
