package com.java.demo.datastore.repositories;

import com.java.demo.datastore.models.UserModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserModel, Integer> {

    /**
     * Finds User Model by user name and active status true
     *
     * @param userName username
     * @return Optional of user model
     */
    Optional<UserModel> findByUserNameAndIsActiveTrue(String userName);

    /**
     * Finds User Model by email and active status true
     *
     * @param userName username
     * @return Optional of user model
     */
    Optional<UserModel> findByEmailIdAndIsActiveTrueAllIgnoreCase(String userName);

    /**
     * Finds User Model by email and active status false
     *
     * @param userName username
     * @return Optional of user model
     */
    Optional<UserModel> findByEmailIdAndIsActiveFalseAllIgnoreCase(String userName);

    /**
     * Finds Asset Model by user name or email id and active status true
     *
     * @param username username
     * @return Optional of user model
     */

    @Query(value = "select * from securebox_user user where (user.u_username=:username or user.u_email=:username or user.u_phonenumber=:username) and user.u_is_active=1", nativeQuery = true)
    Optional<UserModel> findByUserNameOrEmailIdAndIsActiveTrue(@Param("username") String username);

    /**
     * find user by id, username and emailId
     *
     * @param id       id
     * @param username username
     * @param emailId  email
     * @return Optional of user model
     */
    Optional<UserModel> findByIdAndUserNameAndEmailIdAndIsActiveTrue(int id, String username, String emailId);

    /**
     * finding user by id and isactive true
     *
     * @param userId user id
     * @return Optional of user model
     */

    Optional<UserModel> findByIdAndIsActiveTrue(int userId);

    /**
     * get active users count
     *
     * @return int count
     */
    @Query(value = "select count(*) from securebox_user user where user.securebox_role_role_id!=1 and user.u_is_active=1", nativeQuery = true)
    int countAll();

    /**
     * get active users count based on search value and role
     *
     * @param roleId     role id
     * @param firstName  first name
     * @param lastName   last name
     * @param middleName middle name
     * @param userName   user name
     * @param email      email id
     * @return int count
     */
    @Query(value = "select count(*) from securebox_user user where user.securebox_role_role_id=:roleId and (user.u_firstname like concat('%',:firstName,'%') or user.u_lastname like concat('%',:lastName,'%') or user.u_middlename like concat('%',:middleName,'%') or user.u_username like concat('%',:userName,'%') or user.u_email like concat('%',:email,'%')) and user.u_is_active=1", nativeQuery = true)
    int countBySearchValueAndRoleId(@Param("roleId") int roleId, @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("middleName") String middleName, @Param("userName") String userName, @Param("email") String email);

    /**
     * get active users count based on search values
     *
     * @param firstName  first name
     * @param lastName   last name
     * @param middleName middle name
     * @param userName   user name
     * @param email      email id
     * @return int count
     */
    @Query(value = "select count(*) from securebox_user user where user.securebox_role_role_id!=1 and (user.u_firstname like concat('%',:firstName,'%') or user.u_lastname like concat('%',:lastName,'%') or user.u_middlename like concat('%',:middleName,'%') or user.u_username like concat('%',:userName,'%') or user.u_email like concat('%',:email,'%')) and user.u_is_active=1", nativeQuery = true)
    int countBySearchValues(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("middleName") String middleName, @Param("userName") String userName, @Param("email") String email);

    /**
     * get active users count based on role
     *
     * @param roleId role id
     * @return int count
     */
    @Query(value = "select count(*) from securebox_user user where user.securebox_role_role_id=:roleId and user.u_is_active=1", nativeQuery = true)
    int countByRoleId(@Param("roleId") int roleId);

    /**
     * getting all users except admin
     *
     * @return List of user model
     */
    @Query(value = "select * from securebox_user right outer join `securebox-management`.securebox_role on `securebox-management`.securebox_role.r_id = `securebox-management`.securebox_user.securebox_role_role_id where r_id !=1 and u_is_active=1;", nativeQuery = true)
    List<UserModel> findByRole();

    /**
     * searching parameters are null so fetching all the users except GlobalAdmin with desc order
     *
     * @param offset offset
     * @param count  count
     * @return list of UserModel
     */
    @Query(value = "select * from securebox_user user where (user.securebox_role_role_id!=1 and user.u_is_active=1) order by user.u_created_date desc limit :offset,:count", nativeQuery = true)
    List<UserModel> findAllDescOrder(@Param("offset") int offset, @Param("count") int count);

    /**
     * searching parameters are null so fetching all the users except GlobalAdmin with asc order
     *
     * @param offset offset
     * @param count  count
     * @return list of UserModel
     */
    @Query(value = "select * from securebox_user user where (user.securebox_role_role_id!=1 and user.u_is_active=1) order by user.u_created_date asc limit :offset,:count", nativeQuery = true)
    List<UserModel> findAllAscOrder(@Param("offset") int offset, @Param("count") int count);

    /**
     * searching users by search parameters and role with desc order
     *
     * @param roleId     role id
     * @param firstName  first name
     * @param lastName   last name
     * @param middleName middle name
     * @param userName   user name
     * @param email      email id
     * @param offset     offset
     * @param count      count
     * @return list of UserModel
     */
    @Query(value = "select * from securebox_user user where user.securebox_role_role_id=:roleId and (user.u_firstname like concat('%',:firstName,'%') or user.u_lastname like concat('%',:lastName,'%') or user.u_middlename like concat('%',:middleName,'%') or user.u_username like concat('%',:userName,'%') or user.u_email like concat('%',:email,'%')) and user.u_is_active=1 order by user.u_created_date desc limit :offset,:count", nativeQuery = true)
    List<UserModel> findBySearchValueAndRoleIdDescOrder(@Param("roleId") int roleId, @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("middleName") String middleName, @Param("userName") String userName, @Param("email") String email, @Param("offset") int offset, @Param("count") int count);

    /**
     * searching users by search parameters and role with asc order
     *
     * @param roleId     role id
     * @param firstName  first name
     * @param lastName   last name
     * @param middleName middle name
     * @param userName   user name
     * @param email      email id
     * @param offset     offset
     * @param count      count
     * @return list of UserModel
     */
    @Query(value = "select * from securebox_user user where user.securebox_role_role_id=:roleId and (user.u_firstname like concat('%',:firstName,'%') or user.u_lastname like concat('%',:lastName,'%') or user.u_middlename like concat('%',:middleName,'%') or user.u_username like concat('%',:userName,'%') or user.u_email like concat('%',:email,'%')) and user.u_is_active=1 order by user.u_created_date asc limit :offset,:count", nativeQuery = true)
    List<UserModel> findBySearchValueAndRoleIdAscOrder(@Param("roleId") int roleId, @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("middleName") String middleName, @Param("userName") String userName, @Param("email") String email, @Param("offset") int offset, @Param("count") int count);

    /**
     * searching users by search parameters with desc order
     *
     * @param firstName  first name
     * @param lastName   last name
     * @param middleName middle name
     * @param userName   user name
     * @param email      email id
     * @param offset     offset
     * @param count      count
     * @return list of UserModel
     */
    @Query(value = "select * from securebox_user user where user.securebox_role_role_id!=1 and (user.u_firstname like concat('%',:firstName,'%') or user.u_lastname like concat('%',:lastName,'%') or user.u_middlename like concat('%',:middleName,'%') or user.u_username like concat('%',:userName,'%') or user.u_email like concat('%',:email,'%')) and user.u_is_active=1 order by user.u_created_date desc limit :offset,:count", nativeQuery = true)
    List<UserModel> findBySearchValuesDescOrder(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("middleName") String middleName, @Param("userName") String userName, @Param("email") String email, @Param("offset") int offset, @Param("count") int count);

    /**
     * searching users by search parameters with asc order
     *
     * @param firstName  first name
     * @param lastName   last name
     * @param middleName middle name
     * @param userName   user name
     * @param email      email id
     * @param offset     offset
     * @param count      count
     * @return list of UserModel
     */
    @Query(value = "select * from securebox_user user where user.securebox_role_role_id!=1 and (user.u_firstname like concat('%',:firstName,'%') or user.u_lastname like concat('%',:lastName,'%') or user.u_middlename like concat('%',:middleName,'%') or user.u_username like concat('%',:userName,'%') or user.u_email like concat('%',:email,'%')) and user.u_is_active=1 order by user.u_created_date asc limit :offset,:count", nativeQuery = true)
    List<UserModel> findBySearchValuesAscOrder(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("middleName") String middleName, @Param("userName") String userName, @Param("email") String email, @Param("offset") int offset, @Param("count") int count);

    /**
     * Searching users by role with desc order
     *
     * @param roleId role id
     * @return list of UserModel
     */
    @Query(value = "select * from securebox_user user where (user.securebox_role_role_id=:roleId and user.u_is_active=1) order by user.u_created_date desc limit :offset,:count", nativeQuery = true)
    List<UserModel> findByRoleIdDescOrder(@Param("roleId") int roleId, @Param("offset") int offset, @Param("count") int count);

    /**
     * Searching users by role with asc order
     *
     * @param roleId role id
     * @return list of UserModel
     */
    @Query(value = "select * from securebox_user user where (user.securebox_role_role_id=:roleId and user.u_is_active=1) order by user.u_created_date asc limit :offset,:count", nativeQuery = true)
    List<UserModel> findByRoleIdAscOrder(@Param("roleId") int roleId, @Param("offset") int offset, @Param("count") int count);

    /**
     * Searching users by user group
     *
     * @param ugId user group id
     * @return list of UserModel
     */
    @Query(value = "select * from securebox_user user where user.=:ugId and user.u_is_active=1", nativeQuery = true)
    List<UserModel> findByUserGroup(@Param("ugId") Integer ugId);

    /**
     * Searching users which are not in any user group
     *
     * @return list of UserModel
     */
    @Query(value = "select * from securebox_user user where user.securebox_user_group_ug_id is null and user.u_is_active=1", nativeQuery = true)
    List<UserModel> findByNullUserGroup();


    /**
     * Searching users which are not in any user group
     *
     * @return list of UserModel
     */
    @Query(value = "select * from securebox_user user where user.u_id NOT IN :userIds and user.securebox_role_role_id IN :roleIds and user.user.u_is_active=1", nativeQuery = true)
    List<UserModel> findByNotInUserListAndRoleIdListAndIsActiveTrue(@Param("userIds") List<Integer> userIds, @Param("roleIds") List<Integer> roleIds);

    /**
     * returns a list of users by given id list
     *
     * @param idList ids
     * @return list of user models
     */
    @Query(value = "select * from securebox_user user where user.u_id IN :idList and user.u_is_active=true", nativeQuery = true)
    List<UserModel> findByIdList(@Param("idList") List<Integer> idList);

    /**
     * Fetching users by ids and role
     *
     * @param idList ids
     * @param roleId role id
     * @return List of user
     */
    @Query(value = "select * from securebox_user user where user.u_id IN :idList and user.securebox_role_role_id=:roleId and user.u_is_active=true", nativeQuery = true)
    List<UserModel> findByIdListAndRole(@Param("idList") List<Integer> idList, @Param("roleId") int roleId);

    /**
     * getting global admin by id
     *
     * @param globalAdminId id
     * @return email
     */
    @Query(value = "select * from `securebox-management`.securebox_user user where user.securebox_role_role_id=:id and user.u_is_active=true", nativeQuery = true)
    List<UserModel> findEmailIdByRoleEntity(@Param("id") Integer globalAdminId);

    /**
     * finding site admins by id
     *
     * @param userIds     ids
     * @param siteAdminId role id
     * @return email
     */
    @Query(value = "select * from `securebox-management`.securebox_user user where user.u_id IN :idList and user.securebox_role_role_id=:id and user.u_is_active=true", nativeQuery = true)
    List<UserModel> findByUserIdsAndRole(@Param("idList") List<Integer> userIds, @Param("id") Integer siteAdminId);

    /**
     * count users for chat
     *
     * @param userId id
     * @return count
     */
    @Query(value = "select count(*) from securebox_user user where user.u_id!=:userId and user.u_is_active=1", nativeQuery = true)
    int countUsersForChat(@Param("userId") int userId);

    /**
     * searching user for chat descending order
     *
     * @param userId id
     * @return users
     */
    @Query(value = "select * from securebox_user user where (user.u_id!=:userId and user.u_is_active=1) order by user.u_created_date desc", nativeQuery = true)
    List<UserModel> findUsersForChatDescOrder(@Param("userId") int userId);

    /**
     * search user for chat ascending order
     *
     * @param userId id
     * @param offset offset
     * @param count  count
     * @return users
     */
    @Query(value = "select * from securebox_user user where (user.u_id!=:userId and user.u_is_active=1) order by user.u_created_date asc limit :offset,:count", nativeQuery = true)
    List<UserModel> findUsersForChatAscOrder(@Param("userId") int userId, @Param("offset") int offset, @Param("count") int count);

    /**
     * count users for chat by search value
     *
     * @param userId    id
     * @param searchVal searchVal
     * @return count
     */
    @Query(value = "select count(*) from securebox_user user where user.u_id!=:userId and (user.u_firstname like concat('%',:searchVal,'%') or user.u_lastname like concat('%',:searchVal,'%') or user.u_middlename like concat('%',:searchVal,'%')) and user.u_is_active=1", nativeQuery = true)
    int countUsersForChatBySearchValues(@Param("userId") int userId, @Param("searchVal") String searchVal);

    /**
     * search users for chat by search value descending order
     *
     * @param userId    id
     * @param searchVal searchVal
     * @return users
     */
    @Query(value = "select * from securebox_user user where user.u_id!=:userId and (user.u_firstname like concat('%',:searchVal,'%') or user.u_lastname like concat('%',:searchVal,'%') or user.u_middlename like concat('%',:searchVal,'%')) and user.u_is_active=1 order by user.u_created_date desc", nativeQuery = true)
    List<UserModel> findUsersForChatBySearchValuesDescOrder(@Param("userId") int userId, @Param("searchVal") String searchVal);

    /**
     * search users for chat by search value ascending order
     *
     * @param userId    id
     * @param searchVal searchVal
     * @param offset    offset
     * @param count     count
     * @return users
     */
    @Query(value = "select * from securebox_user user where user.u_id!=:userId and (user.u_firstname like concat('%',:searchVal,'%') or user.u_lastname like concat('%',:searchVal,'%') or user.u_middlename like concat('%',:searchVal,'%')) and user.u_is_active=1 order by user.u_created_date asc limit :offset,:count", nativeQuery = true)
    List<UserModel> findUsersForChatBySearchValuesAscOrder(@Param("userId") int userId, @Param("searchVal") String searchVal, @Param("offset") int offset, @Param("count") int count);


    /**
     * counting online users
     *
     * @param userId      id
     * @param currentDate date
     * @return count
     */
    @Query(value = "select count(*) from `securebox-management`.securebox_user usr where usr.u_id!=:userId and usr.u_is_active=1 and usr.u_online_date>:currentDate", nativeQuery = true)
    int countOnlineUsers(@Param("userId") int userId, @Param("currentDate") Timestamp currentDate);

    /**
     * fetching online users descending order
     *
     * @param userId      user id
     * @param currentDate date
     * @param offset      offset
     * @param count       count
     * @return list of users
     */
    @Query(value = "select * from `securebox-management`.securebox_user usr where usr.u_id!=:userId and usr.u_is_active=1 and usr.u_online_date>:currentDate order by usr.u_created_date desc limit :offset,:count", nativeQuery = true)
    List<UserModel> findOnlineUsersDescOrder(@Param("userId") int userId, @Param("currentDate") Timestamp currentDate, @Param("offset") int offset, @Param("count") int count);

    /**
     * fetching online users ascending order
     *
     * @param userId      user id
     * @param currentDate date
     * @param offset      offset
     * @param count       count
     * @return list of users
     */
    @Query(value = "select * from `securebox-management`.securebox_user usr where usr.u_id!=:userId and usr.u_is_active=1 and usr.u_online_date>:currentDate order by usr.u_created_date asc limit :offset,:count", nativeQuery = true)
    List<UserModel> findOnlineUsersAscOrder(@Param("userId") int userId, @Param("currentDate") Timestamp currentDate, @Param("offset") int offset, @Param("count") int count);

    /**
     * counting online users by search value
     *
     * @param userId      id
     * @param searchVal   search value
     * @param currentDate date
     * @return count
     */
    @Query(value = "select count(*) from `securebox-management`.securebox_user usr where usr.u_id!=:userId and usr.u_is_active=1 and usr.u_online_date>:currentDate and (usr.u_firstname like concat('%',:searchVal,'%') or usr.u_lastname like concat('%',:searchVal,'%') or usr.u_middlename like concat('%',:searchVal,'%') or usr.u_username like concat('%',:searchVal,'%') or usr.u_email like concat('%',:searchVal,'%'))", nativeQuery = true)
    int countOnlineUsersBySearchVal(@Param("userId") int userId, @Param("searchVal") String searchVal, @Param("currentDate") Timestamp currentDate);

    /**
     * fetching online users by search value descending order
     *
     * @param userId      user id
     * @param searchVal   search value
     * @param currentDate date
     * @param offset      offset
     * @param count       count
     * @return list of users
     */
    @Query(value = "select * from `securebox-management`.securebox_user usr where usr.u_id!=:userId and usr.u_is_active=1 and usr.u_online_date>:currentDate and (usr.u_firstname like concat('%',:searchVal,'%') or usr.u_lastname like concat('%',:searchVal,'%') or usr.u_middlename like concat('%',:searchVal,'%') or usr.u_username like concat('%',:searchVal,'%') or usr.u_email like concat('%',:searchVal,'%')) order by usr.u_created_date desc limit :offset,:count", nativeQuery = true)
    List<UserModel> findOnlineUsersBySearchValueDescOrder(@Param("userId") int userId, @Param("searchVal") String searchVal, @Param("currentDate") Timestamp currentDate, @Param("offset") int offset, @Param("count") int count);

    /**
     * fetching online users by search value ascending order
     *
     * @param userId      user id
     * @param searchVal   search value
     * @param currentDate date
     * @param offset      offset
     * @param count       count
     * @return list of users
     */
    @Query(value = "select * from `securebox-management`.securebox_user usr where usr.u_id!=:userId and usr.u_is_active=1 and usr.u_online_date>:currentDate and (usr.u_firstname like concat('%',:searchVal,'%') or usr.u_lastname like concat('%',:searchVal,'%') or usr.u_middlename like concat('%',:searchVal,'%') or usr.u_username like concat('%',:searchVal,'%') or usr.u_email like concat('%',:searchVal,'%')) order by usr.u_created_date asc limit :offset,:count", nativeQuery = true)
    List<UserModel> findOnlineUsersBySearchValueAscOrder(@Param("userId") int userId, @Param("searchVal") String searchVal, @Param("currentDate") Timestamp currentDate, @Param("offset") int offset, @Param("count") int count);

    /**
     * fetching user ids for chat
     *
     * @param userId id
     * @param idList list of ids
     * @return user ids
     */
    @Query(value = "select u_id from securebox_user usr where usr.u_id!=:userId and usr.u_id NOT IN :idList and usr.u_is_active=true", nativeQuery = true)
    List<Integer> findActiveUserIdsForChat(@Param("userId") int userId, @Param("idList") List<Integer> idList);

    @Query(value = "select * from `securebox-management`.securebox_user user where user.securebox_role_role_id=:id and user.u_is_active=true", nativeQuery = true)
    List<UserModel> findUserByRoleEntity(@Param("id") Integer globalAdminId);

    @Query(value = "select * from `securebox-management`.securebox_user user where user.securebox_role_role_id=:id and user.u_is_active=true and user.u_id!=:userId", nativeQuery = true)
    List<UserModel> findUserByRoleEntityExceptCurrentGA(@Param("id") Integer globalAdminId, @Param("userId") int userId);

    /**
     * update user logged in device mac-address
     * @param userId
     * @param macAddress
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE `securebox-management`.securebox_user user SET user.u_mac_address = :macAddress, user.u_firebase_token = :firebaseToken WHERE user.u_id = :userId", nativeQuery = true)
    int updateUserDeviceDetails(@Param("userId") int userId, @Param("macAddress") String macAddress, @Param("firebaseToken") String firebaseToken);

    @Query(value = "select * from securebox_user user where (user.u_username IN :userNameOrEmailList or user.u_email IN :userNameOrEmailList) and user.u_is_active=1", nativeQuery = true)
    List<UserModel> findByUserNameOrEmailIdListAndIsActiveTrue(@Param("userNameOrEmailList") List<String> userNameOrEmailList);
}
