package com.java.demo.datastore.repositories;

import com.java.demo.datastore.models.UserGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserGroupRepository extends PagingAndSortingRepository<UserGroup, Integer> {

    /**
     * Finds User Group by user group name
     *
     * @param userGroupName user group name
     * @return Optional of user group
     */
    Optional<UserGroup> findByUgNameAndUgIsActiveTrue(String userGroupName);

    /**
     * @param id user group ud
     * @return Optional of user group
     */
    Optional<UserGroup> findByUgIdAndUgIsActiveTrue(int id);

    /**
     * get active user group count
     *
     * @return int count
     */
    int countByUgIsActiveTrue();

    /**
     * find the list of user group names
     */
    @Query(value = "select ug_name from securebox_user_group usr_grp where usr_grp.ug_is_active=1 order by usr_grp.ug_name asc", nativeQuery = true)
    List<String> findUgName();

    /**
     * get active user group count based on search value
     *
     * @param searchVal search value
     * @return int count
     */
    @Query(value = "select count(*) from securebox_user_group usr_grp where usr_grp.ug_is_active=1 and usr_grp.ug_name like concat('%',:searchVal,'%')", nativeQuery = true)
    int countBySearchValue(@Param("searchVal") String searchVal);

    /**
     * fetching all active user group with desc order
     *
     * @param offset offset
     * @param count  count
     * @return List of user groups
     */
    @Query(value = "select * from securebox_user_group usr_grp where usr_grp.ug_is_active=1 order by usr_grp.ug_created_date desc limit :offset,:count", nativeQuery = true)
    List<UserGroup> findAllDescOrder(@Param("offset") int offset, @Param("count") int count);

    /**
     * fetching all active user group with asc order
     *
     * @param offset offset
     * @param count  count
     * @return List of user groups
     */
    @Query(value = "select * from securebox_user_group usr_grp where usr_grp.ug_is_active=1 order by usr_grp.ug_created_date asc limit :offset,:count", nativeQuery = true)
    List<UserGroup> findAllAscOrder(@Param("offset") int offset, @Param("count") int count);

    /**
     * searching user group by search value with desc order
     *
     * @param searchVal search value
     * @param offset    offset
     * @param count     count
     * @return List of UserGroup
     */
    @Query(value = "select * from securebox_user_group usr_grp where (usr_grp.ug_is_active=1 and usr_grp.ug_name like concat('%',:searchVal,'%')) order by usr_grp.ug_created_date desc limit :offset,:count", nativeQuery = true)
    List<UserGroup> findBySearchValueDescOrder(@Param("searchVal") String searchVal, @Param("offset") int offset, @Param("count") int count);

    /**
     * searching user group by search value with asc order
     *
     * @param searchVal search value
     * @param offset    offset
     * @param count     count
     * @return List of UserGroup
     */
    @Query(value = "select * from securebox_user_group usr_grp where (usr_grp.ug_is_active=1 and usr_grp.ug_name like concat('%',:searchVal,'%')) order by usr_grp.ug_created_date asc limit :offset,:count", nativeQuery = true)
    List<UserGroup> findBySearchValueAscOrder(@Param("searchVal") String searchVal, @Param("offset") int offset, @Param("count") int count);

    /**
     * getting user groups by list of user group ids
     *
     * @param idList user group ids
     * @return list of user group
     */
    @Query(value = "select * from securebox_user_group ug where ug.ug_id IN :idList", nativeQuery = true)
    List<UserGroup> findByIdList(@Param("idList") List<Integer> idList);

    /**
     * find user group names by id list
     *
     * @param idList id list
     * @return name list
     */
    @Query(value = "select ug_name from securebox_user_group ug where ug.ug_id IN :idList", nativeQuery = true)
    List<String> findNameByIdList(@Param("idList") List<Integer> idList);

    @Query(value = "select * from securebox_user_group ug where ug.ug_name IN :userGroupNameList", nativeQuery = true)
    List<UserGroup> findByUserGroupNameList(@Param("userGroupNameList") Set<String> userGroupNameList);
}
