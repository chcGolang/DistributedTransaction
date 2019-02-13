package com.njta.jpa_jdbc.springboot_njta_jpa_jdbc.dao.user;

import com.njta.jpa_jdbc.springboot_njta_jpa_jdbc.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chc
 * @create 2019-02-05 20:43
 **/
public interface UserRepository extends JpaRepository<User,Long> {

    @Modifying
    @Query(value = "update user_info set deposit = deposit - :deposit where id=:id",nativeQuery = true)
    void updateToDeposit(@Param("deposit") Integer deposit,@Param("id") Long id);
}
