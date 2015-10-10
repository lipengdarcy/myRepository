package com.runlion.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.runlion.shop.entity.Cart;
import com.runlion.shop.vo.CartSnapVO;

public interface CartMapper {
	/**
	 * 保存
	 * @param cart
	 * @return
	 */
	public int insert(Cart cart);
	
	/**
	 * 根据用户名获取购物车中产品数量
	 * @param userId
	 * @return
	 */
	public Integer getCartCountByUserId(int userId);
	
	/**
	 * 根据用户id获取购物车快照
	 * @param userId
	 * @return
	 */
	public List<CartSnapVO> getCartSnapByUserId(int userId);

	/**
	 * 根据用户id和产品id获取购物车
	 * @param userId
	 * @param productId
	 * @return
	 */
	public Cart getCart(Cart cart);

	/**
	 * 增加购物车数量
	 * @param cart
	 * @return
	 */
	public int updateAddCount(Cart cart);
	
	/**
	 * 删除购物车
	 * @param cart
	 * @return
	 */
	public int deleteCartByUserIdAndPid(Cart cart);

	/**
	 * 根据用户id删除购物车
	 * @param cart
	 * @return
	 */
	public int deleteCartByUserId(int userId);

	/**
	 * 更新数量
	 * @param cart
	 * @return
	 */
	public int updateCount(Cart cart);

	public CartSnapVO getCartSnapByUidAndPid(@Param("userId") Integer userId, @Param("pid") Integer pid);

}
