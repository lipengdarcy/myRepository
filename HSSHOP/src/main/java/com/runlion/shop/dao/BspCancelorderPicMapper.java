package com.runlion.shop.dao;

import com.runlion.shop.entity.BspCancelorderPic;
import com.runlion.shop.entity.BspCancelorderPicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BspCancelorderPicMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_cancelOrder_pic
     *
     * @mbggenerated Tue Sep 08 13:45:09 CST 2015
     */
    int countByExample(BspCancelorderPicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_cancelOrder_pic
     *
     * @mbggenerated Tue Sep 08 13:45:09 CST 2015
     */
    int deleteByExample(BspCancelorderPicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_cancelOrder_pic
     *
     * @mbggenerated Tue Sep 08 13:45:09 CST 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_cancelOrder_pic
     *
     * @mbggenerated Tue Sep 08 13:45:09 CST 2015
     */
    int insert(BspCancelorderPic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_cancelOrder_pic
     *
     * @mbggenerated Tue Sep 08 13:45:09 CST 2015
     */
    int insertSelective(BspCancelorderPic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_cancelOrder_pic
     *
     * @mbggenerated Tue Sep 08 13:45:09 CST 2015
     */
    List<BspCancelorderPic> selectByExample(BspCancelorderPicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_cancelOrder_pic
     *
     * @mbggenerated Tue Sep 08 13:45:09 CST 2015
     */
    BspCancelorderPic selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_cancelOrder_pic
     *
     * @mbggenerated Tue Sep 08 13:45:09 CST 2015
     */
    int updateByExampleSelective(@Param("record") BspCancelorderPic record, @Param("example") BspCancelorderPicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_cancelOrder_pic
     *
     * @mbggenerated Tue Sep 08 13:45:09 CST 2015
     */
    int updateByExample(@Param("record") BspCancelorderPic record, @Param("example") BspCancelorderPicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_cancelOrder_pic
     *
     * @mbggenerated Tue Sep 08 13:45:09 CST 2015
     */
    int updateByPrimaryKeySelective(BspCancelorderPic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_cancelOrder_pic
     *
     * @mbggenerated Tue Sep 08 13:45:09 CST 2015
     */
    int updateByPrimaryKey(BspCancelorderPic record);
}