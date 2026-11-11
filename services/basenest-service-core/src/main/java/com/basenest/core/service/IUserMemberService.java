package com.basenest.core.service;

import com.basenest.core.dao.dos.UserMemberDO;

public interface IUserMemberService {
    Long addMember(UserMemberDO userMember);

    UserMemberDO getMemberById(Long id);

    UserMemberDO getMemberByUserId(Long userId);

    boolean updateMember(UserMemberDO userMember);

    boolean deleteMember(Long id);
}