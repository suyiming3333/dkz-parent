package com.atgugui.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.atgugui.enums.exceptionals.StateEnum;
import com.atgugui.exceptions.BaseException;
import com.atgugui.facade.UserFacade;
import com.atgugui.jdbc.user.BizUserMapper;
import com.atgugui.model.user.BizUser;

public class UserFacadeImpl implements UserFacade {

	@Autowired
	private BizUserMapper bizUserMapper;
	
	@Override
	public BizUser getUserByUser(BizUser bizUser) {
		//查询用户信息
		BizUser selectOneBizUser = bizUserMapper.selectOne(bizUser);
		return selectOneBizUser;
	}

	@Override
	@Transactional
	public int updateBizUser(BizUser bizUser) {
		Integer updateById = bizUserMapper.updateById(bizUser);
		if (updateById != 1) {
			throw new BaseException(StateEnum.ERROR_SYSTEM);
		}
		return updateById;
	}

}