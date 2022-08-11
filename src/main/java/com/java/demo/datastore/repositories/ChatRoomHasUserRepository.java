package com.java.demo.datastore.repositories;

import com.java.demo.datastore.models.ChatRoomHasUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;

public interface ChatRoomHasUserRepository extends PagingAndSortingRepository<ChatRoomHasUser, Integer> {

    /**
     * fetching active chat room ids by userId
     *
     * @param userId user id
     * @return chat room ids
     */
    @Query(value = "select cru_cr_id from securebox_chat_room_has_user cru where cru.cru_cu_id=:userId and cru.cru_is_active=1", nativeQuery = true)
    List<BigInteger> findByUserIdAndIsActiveTrue(@Param("userId") BigInteger userId);

    /**
     * fetching chat room has user model information by userId and chatRoomId
     *
     * @param userId     user id
     * @param chatRoomId chat room id
     * @return chat room has user model
     */
    ChatRoomHasUser findByChatUserIdAndChatRoomIdAndIsActiveTrue(@Param("userId") BigInteger userId, @Param("chatRoomId") BigInteger chatRoomId);

    /**
     * finding ChatRoomHasUser
     *
     * @param chatRoomId chat room id
     * @param userId     user id
     * @return ChatRoomHasUser model
     */
    @Query(value = "select * from securebox_chat_room_has_user cru where cru.cru_cr_id=:chatRoomId and cru.cru_cu_id!=:userId and cru.cru_is_active=1", nativeQuery = true)
    ChatRoomHasUser findByChatRoomIdAndUserIdNotIn(@Param("chatRoomId") BigInteger chatRoomId, @Param("userId") BigInteger userId);
}
