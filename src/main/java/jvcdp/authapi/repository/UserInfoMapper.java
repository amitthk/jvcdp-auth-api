package jvcdp.authapi.repository;

import jvcdp.authapi.model.UserInfo;
import java.util.List;

public interface UserInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UserInfo
     *
     * @mbg.generated Tue Sep 12 11:39:12 SGT 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UserInfo
     *
     * @mbg.generated Tue Sep 12 11:39:12 SGT 2017
     */
    Long insert(UserInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UserInfo
     *
     * @mbg.generated Tue Sep 12 11:39:12 SGT 2017
     */
    int insertSelective(UserInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UserInfo
     *
     * @mbg.generated Tue Sep 12 11:39:12 SGT 2017
     */

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UserInfo
     *
     * @mbg.generated Tue Sep 12 11:39:12 SGT 2017
     */
    UserInfo selectByPrimaryKey(Long id);
    UserInfo selectByEmail(String id);
    List<UserInfo> selectAll();
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UserInfo
     *
     * @mbg.generated Tue Sep 12 11:39:12 SGT 2017
     */
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UserInfo
     *
     * @mbg.generated Tue Sep 12 11:39:12 SGT 2017
     */
    int updateByPrimaryKeySelective(UserInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UserInfo
     *
     * @mbg.generated Tue Sep 12 11:39:12 SGT 2017
     */
    int updateByPrimaryKey(UserInfo record);
}