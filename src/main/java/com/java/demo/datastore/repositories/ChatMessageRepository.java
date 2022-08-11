package com.java.demo.datastore.repositories;

import com.java.demo.datastore.models.ChatMessageModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface ChatMessageRepository extends PagingAndSortingRepository<ChatMessageModel, Integer> {

    /**
     * counting chat messages by chat room id
     *
     * @param chatRoomId id
     * @return chat messages count
     */
    @Query(value = "select count(*) from securebox_chat_message cm where cm.cm_cr_id=:chatRoomId and cm" +
            ".cm_is_deleted=0",
            nativeQuery = true)
    long countByChatRoomId(@Param("chatRoomId") BigInteger chatRoomId);

    /**
     * fetching chat messages by chat room id
     *
     * @param chatRoomId id
     * @param offset     offset
     * @param count      count
     * @return list of chat messages
     */
    @Query(value = "select * from securebox_chat_message cm where cm.cm_cr_id=:chatRoomId and cm.cm_is_deleted=0 " +
            "order" +
            " " +
            "by" +
            " " +
            "cm" +
            ".cm_created_date desc limit :offset,:count", nativeQuery = true)
    List<ChatMessageModel> findByChatRoomId(@Param("chatRoomId") BigInteger chatRoomId, @Param("offset") int offset, @Param("count") int count);

    /**
     * fetching chat message model by id
     *
     * @param id id
     * @return chat message model
     */
    Optional<ChatMessageModel> findById(BigInteger id);

    /**
     * fetching unread messages of particular chat room
     *
     * @param chatRoomId id
     * @return list of unread messages
     */
    List<ChatMessageModel> findByChatRoomIdAndIsSeenFalse(BigInteger chatRoomId);

    /**
     * fetching user unread messages
     *
     * @param chatRoomId chat room id
     * @param senderId   sender id
     * @return list of chat message model
     */
    @Query(value = "select * from securebox_chat_message cm where cm.cm_cr_id=:chatRoomId and cm.cm_is_deleted=0 and " +
            "cm" +
            ".cm_is_seen=0 and cm.cm_sender!=:senderId", nativeQuery = true)
    List<ChatMessageModel> findByChatRoomIdAndSenderNotInAndIsSeenFalse(@Param("chatRoomId") BigInteger chatRoomId, @Param("senderId") BigInteger senderId);
}
