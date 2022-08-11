package com.java.demo.datastore.repositories;

import com.java.demo.datastore.models.UserToUserGroup;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserToUserGroupRepository extends PagingAndSortingRepository<UserToUserGroup, Integer> {

    /**
     * fetching records by user id and user group id
     *
     * @param userId      id
     * @param userGroupId id
     * @return user to user group model
     */
    Optional<UserToUserGroup> findByUserIdAndUserGroupId(int userId, int userGroupId);

    /**
     * fetching records by user id and user group id and is active
     *
     * @param userId      id
     * @param userGroupId id
     * @return user to user group model
     */
    Optional<UserToUserGroup> findByUserIdAndUserGroupIdAndIsActiveTrue(int userId, int userGroupId);

    /**
     * getting all user ids by user group id
     *
     * @return list of user ids
     */
    @Query(value = "select u_id from securebox_user_to_usergroup uug where uug.ug_id=:ugId and uug.is_active=true", nativeQuery = true)
    List<Integer> findByUserGroupId(@Param("ugId") int ugId);

    /**
     * getting all user group ids by user id
     *
     * @return list of user group ids
     */
    @Query(value = "select ug_id from securebox_user_to_usergroup uug where uug.u_id=:userId and uug.is_active=true", nativeQuery = true)
    List<Integer> findUserGroupIdsByUserId(@Param("userId") int userId);

    /**
     * deleting user user group records
     *
     * @param ugId   user group id
     * @param idList user ids
     */
    @Transactional
    @Modifying
    @Query(value = "delete from securebox_user_to_usergroup where securebox_user_to_usergroup.ug_id=:ugId and securebox_user_to_usergroup.u_id IN :idList", nativeQuery = true)
    void deleteUserUserGroups(@Param("ugId") int ugId, @Param("idList") List<Integer> idList);

    /**
     * delete user group from ug-ag table
     *
     * @param ugId id
     */
    @Transactional
    @Modifying
    @Query(value = "delete from securebox_user_to_usergroup where securebox_user_to_usergroup.ug_id=:ugId", nativeQuery = true)
    void deleteByUserGroupId(@Param("ugId") int ugId);

    /**
     * fetching the user to user_group records from user id
     *
     * @param userId id
     * @return list of user to user_group
     */
    List<UserToUserGroup> findByUserId(@Param("userId") int userId);

    /**
     * find ug id list from table by user id
     *
     * @param id user id
     * @return list of id
     */
    @Query(value = "select ug_id from `securebox-management`.securebox_user_to_usergroup utu where utu.u_id=:id and utu.is_active=1", nativeQuery = true)
    List<Integer> findUgIdListByUserId(@Param("id") int id);

    /**
     * getting user group ids using user id list
     */
    @Query(value = "select ug_id from securebox_user_to_usergroup where securebox_user_to_usergroup.u_id=:id and is_active=true", nativeQuery = true)
    List<Integer> findUserGroupIdByUserId(@Param("id") int id);

    /**
     * getting user to user group details using user group id
     */
    @Query(value = "select * from securebox_user_to_usergroup where securebox_user_to_usergroup.ug_id IN :idList and securebox_user_to_usergroup.is_active=true", nativeQuery = true)
    List<UserToUserGroup> findByUserGroupIds(@Param("idList") List<Integer> idList);

    /**
     * getting user group ids using user id
     */
    @Query(value = "select ug_id from securebox_user_to_usergroup uug where uug.u_id=:userId and uug.is_active=1", nativeQuery = true)
    List<Integer> findByUserIds(@Param("userId") int userId);

    /**
     * getting user ids from user group ids
     *
     * @param idList user group ids
     * @return list of user ids
     */
    @Query(value = "select u_id from securebox_user_to_usergroup uug where uug.ug_id IN :idList and uug.is_active=true", nativeQuery = true)
    List<Integer> findUserIdsByUgIds(@Param("idList") List<Integer> idList);

    @Query(value = "select * from securebox_user_to_usergroup uug where uug.is_active=true", nativeQuery = true)
    List<UserToUserGroup> findAllByIsActiveTrue();

    @Query(value = "select * from securebox_user_to_usergroup uug where uug.u_id=:userId and uug.is_active=true", nativeQuery = true)
    Optional<UserToUserGroup> findDetailsByUserId(@Param("userId") Integer userId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from securebox_user_to_usergroup uug where uug.u_id=:userId", nativeQuery = true)
    void deleteByUserId(@Param("userId") Integer userId);
}
