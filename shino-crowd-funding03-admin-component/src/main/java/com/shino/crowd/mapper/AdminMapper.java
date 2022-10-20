package com.shino.crowd.mapper;

import com.shino.crowd.entity.Admin;
import com.shino.crowd.entity.AdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin
     *
     * @mbggenerated Tue Oct 18 15:49:33 CST 2022
     */
    int countByExample(AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin
     *
     * @mbggenerated Tue Oct 18 15:49:33 CST 2022
     */
    int deleteByExample(AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin
     *
     * @mbggenerated Tue Oct 18 15:49:33 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin
     *
     * @mbggenerated Tue Oct 18 15:49:33 CST 2022
     */
    int insert(Admin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin
     *
     * @mbggenerated Tue Oct 18 15:49:33 CST 2022
     */
    int insertSelective(Admin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin
     *
     * @mbggenerated Tue Oct 18 15:49:33 CST 2022
     */
    List<Admin> selectByExample(AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin
     *
     * @mbggenerated Tue Oct 18 15:49:33 CST 2022
     */
    Admin selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin
     *
     * @mbggenerated Tue Oct 18 15:49:33 CST 2022
     */
    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin
     *
     * @mbggenerated Tue Oct 18 15:49:33 CST 2022
     */
    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin
     *
     * @mbggenerated Tue Oct 18 15:49:33 CST 2022
     */
    int updateByPrimaryKeySelective(Admin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin
     *
     * @mbggenerated Tue Oct 18 15:49:33 CST 2022
     */
    int updateByPrimaryKey(Admin record);
}