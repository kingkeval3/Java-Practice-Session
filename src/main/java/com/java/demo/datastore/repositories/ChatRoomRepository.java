package com.java.demo.datastore.repositories;

import com.java.demo.datastore.models.ChatRoomModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends PagingAndSortingRepository<ChatRoomModel, Integer> {

    /**
     * count chat rooms by type and ids
     *
     * @param type type
     * @param ids  ids
     * @return count
     */
    @Query(value = "select count(*) from securebox_chat_room cr where cr.cr_type=:type and cr.cr_id in :ids and cr.cr_is_active=1", nativeQuery = true)
    long countByTypeAndIdInIsActiveTrue(@Param("type") String type, @Param("ids") List<BigInteger> ids);

    /**
     * fetching chat rooms by type and id
     *
     * @param type type
     * @param ids  ids
     * @return chat room model
     */
    @Query(value = "select * from securebox_chat_room cr where cr.cr_type=:type and cr.cr_id in :ids and cr.cr_is_active=1 order by cr.cr_created_date desc limit :offset,:count", nativeQuery = true)
    List<ChatRoomModel> findByTypeAndIdInIsActiveTrue(@Param("type") String type, @Param("ids") List<BigInteger> ids, @Param("offset") int offset, @Param("count") int count);

    /**
     * count chat rooms by type, name and ids
     *
     * @param type type
     * @param name name
     * @param ids  ids
     * @return count
     */
    @Query(value = "select count(*) from securebox_chat_room cr where cr.cr_type=:type and cr.cr_name=:name and cr.cr_id in :ids and cr.cr_is_active=1", nativeQuery = true)
    long countByTypeAndNameAndIdInIsActiveTrue(@Param("type") String type, @Param("name") String name, @Param("ids") List<BigInteger> ids);

    /**
     * fetching chat rooms by type, name and id
     *
     * @param type type
     * @param name name
     * @param ids  ids
     * @return chat room model
     */
    @Query(value = "select * from securebox_chat_room cr where cr.cr_type=:type and cr.cr_name=:name and cr.cr_id in :ids and cr.cr_is_active=1 order by cr.cr_created_date desc limit :offset,:count", nativeQuery = true)
    List<ChatRoomModel> findByTypeAndNameAndIdInIsActiveTrue(@Param("type") String type, @Param("name") String name, @Param("ids") List<BigInteger> ids, @Param("offset") int offset, @Param("count") int count);

    /**
     * counting active chat rooms by ids
     *
     * @return count
     */
    long countByIdInAndIsActiveTrue(@Param("ids") List<BigInteger> ids);

    /**
     * fetching active chat rooms
     *
     * @param offset offset
     * @param count  count
     * @return list of chat rooms
     */
    @Query(value = "select * from securebox_chat_room cr where cr.cr_id in :ids and cr.cr_is_active=1 order by cr.cr_created_date desc limit :offset,:count", nativeQuery = true)
    List<ChatRoomModel> findByIdsAndIsActiveTrue(@Param("ids") List<BigInteger> ids, @Param("offset") int offset, @Param("count") int count);

    /**
     * fetching all active chat rooms by ids
     *
     * @return list of chat rooms
     */
    @Query(value = "select * from securebox_chat_room cr where cr.cr_type=:type and cr.cr_id in :ids and cr.cr_is_active=1 order by cr.cr_modified_date desc", nativeQuery = true)
    List<ChatRoomModel> findByTypeAndIdsAndIsActiveTrue(@Param("type") String type, @Param("ids") List<BigInteger> ids);

    /**
     * find active chat room by id
     *
     * @param chatRoomId id
     * @return chat model optional
     */
    Optional<ChatRoomModel> findByIdAndIsActiveTrue(BigInteger chatRoomId);

    /**
     * finding active chat room  by name
     *
     * @param name name
     * @return chat room optional
     */
    Optional<ChatRoomModel> findByNameAndIsActiveTrue(String name);

    /**
     * fetching active chat room by name and type
     *
     * @param chatRoomNameOne name one
     * @param chatRoomNameTwo name two
     * @param type            type
     * @return chat room optional
     */
    @Query(value = "select * from securebox_chat_room cr where (cr.cr_name=:chatRoomNameOne or cr.cr_name=:chatRoomNameTwo) and cr.cr_type=:type and cr.cr_is_active=1", nativeQuery = true)
    Optional<ChatRoomModel> findByNameAndTypeAndIsActiveTrue(@Param("chatRoomNameOne") String chatRoomNameOne, @Param("chatRoomNameTwo") String chatRoomNameTwo, @Param("type") String type);

}
