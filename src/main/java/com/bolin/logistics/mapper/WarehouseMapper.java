package com.bolin.logistics.mapper;

import com.bolin.logistics.model.Warehouse;
import com.bolin.logistics.model.WarehouseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WarehouseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table warehouse
     *
     * @mbg.generated Tue Mar 08 14:25:09 CST 2022
     */
    long countByExample(WarehouseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table warehouse
     *
     * @mbg.generated Tue Mar 08 14:25:09 CST 2022
     */
    int deleteByExample(WarehouseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table warehouse
     *
     * @mbg.generated Tue Mar 08 14:25:09 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table warehouse
     *
     * @mbg.generated Tue Mar 08 14:25:09 CST 2022
     */
    int insert(Warehouse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table warehouse
     *
     * @mbg.generated Tue Mar 08 14:25:09 CST 2022
     */
    int insertSelective(Warehouse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table warehouse
     *
     * @mbg.generated Tue Mar 08 14:25:09 CST 2022
     */
    List<Warehouse> selectByExample(WarehouseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table warehouse
     *
     * @mbg.generated Tue Mar 08 14:25:09 CST 2022
     */
    Warehouse selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table warehouse
     *
     * @mbg.generated Tue Mar 08 14:25:09 CST 2022
     */
    int updateByExampleSelective(@Param("record") Warehouse record, @Param("example") WarehouseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table warehouse
     *
     * @mbg.generated Tue Mar 08 14:25:09 CST 2022
     */
    int updateByExample(@Param("record") Warehouse record, @Param("example") WarehouseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table warehouse
     *
     * @mbg.generated Tue Mar 08 14:25:09 CST 2022
     */
    int updateByPrimaryKeySelective(Warehouse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table warehouse
     *
     * @mbg.generated Tue Mar 08 14:25:09 CST 2022
     */
    int updateByPrimaryKey(Warehouse record);
}