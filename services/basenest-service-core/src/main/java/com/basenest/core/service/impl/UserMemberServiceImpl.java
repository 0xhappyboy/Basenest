package com.basenest.core.service.impl;

import com.basenest.core.dao.dos.UserMemberDO;
import com.basenest.core.dao.mapper.UserMemberMapper;
import com.basenest.core.service.IUserMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName UserMemberServiceImpl
 * @Description User member service impl
 * @Author happyboy
 * @Date 2026/11/11
 * @Version 1.0
 */
@Service
@Transactional
public class UserMemberServiceImpl implements IUserMemberService {

    @Autowired
    private UserMemberMapper userMemberMapper;

    @Override
    public Long addMember(UserMemberDO userMember) {
        userMemberMapper.insert(userMember);
        return userMember.getId();
    }

    @Override
    public UserMemberDO getMemberById(Long id) {
        return userMemberMapper.selectById(id);
    }

    @Override
    public UserMemberDO getMemberByUserId(Long userId) {
        return userMemberMapper.selectByUserId(userId);
    }

    @Override
    public boolean updateMember(UserMemberDO userMember) {
        return userMemberMapper.update(userMember) > 0;
    }

    @Override
    public boolean deleteMember(Long id) {
        return userMemberMapper.delete(id) > 0;
    }
}