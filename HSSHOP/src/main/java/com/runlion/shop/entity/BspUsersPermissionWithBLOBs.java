package com.runlion.shop.entity;

public class BspUsersPermissionWithBLOBs extends BspUsersPermission {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bsp_users_permission.description
     *
     * @mbggenerated Fri Aug 28 17:18:32 CST 2015
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bsp_users_permission.replyMsg
     *
     * @mbggenerated Fri Aug 28 17:18:32 CST 2015
     */
    private String replymsg;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bsp_users_permission.description
     *
     * @return the value of bsp_users_permission.description
     *
     * @mbggenerated Fri Aug 28 17:18:32 CST 2015
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bsp_users_permission.description
     *
     * @param description the value for bsp_users_permission.description
     *
     * @mbggenerated Fri Aug 28 17:18:32 CST 2015
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bsp_users_permission.replyMsg
     *
     * @return the value of bsp_users_permission.replyMsg
     *
     * @mbggenerated Fri Aug 28 17:18:32 CST 2015
     */
    public String getReplymsg() {
        return replymsg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bsp_users_permission.replyMsg
     *
     * @param replymsg the value for bsp_users_permission.replyMsg
     *
     * @mbggenerated Fri Aug 28 17:18:32 CST 2015
     */
    public void setReplymsg(String replymsg) {
        this.replymsg = replymsg;
    }
}