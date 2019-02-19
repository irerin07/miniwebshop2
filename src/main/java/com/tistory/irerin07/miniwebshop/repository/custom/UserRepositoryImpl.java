package com.tistory.irerin07.miniwebshop.repository.custom;

import com.querydsl.jpa.JPQLQuery;
import com.tistory.irerin07.miniwebshop.domain.QUser;
import com.tistory.irerin07.miniwebshop.domain.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepositoryCustom{
    public UserRepositoryImpl(){
        super(User.class);
    }


    @Override
    public List<User> getUsers(String searchKind, String searchStr) {
        QUser qUser = QUser.user;

        JPQLQuery<User> jpqlQuery = from(qUser).innerJoin(qUser.roles).fetchJoin().distinct();

        if("NAME_SEARCH".equals(searchKind)){
            jpqlQuery.where(qUser.name.like("%" + searchStr + "%"));
        }else if("NICKNAME_SEARCH".equals(searchKind)){
            jpqlQuery.where(qUser.nickname.like("%" + searchStr + "%"));
        }else if("EMAIL_SEARCH".equals(searchKind)){
            jpqlQuery.where(qUser.address.like("%" + searchStr + "%"));
        }else if("ADDRESS_SEARCH".equals(searchKind)){
            jpqlQuery.where(qUser.email.like("%" + searchStr + "%"));
        }else if("CONTACT_NUMBER_SEARCH".equals(searchKind)){
            jpqlQuery.where(qUser.contactNumber.like("%" + searchStr + "%"));
        }
        jpqlQuery.orderBy(qUser.id.desc());
        return jpqlQuery.fetch();


    }

    @Override
    public Long getUserCount(String searchKind, String searchStr) {
        return null;
    }
}
