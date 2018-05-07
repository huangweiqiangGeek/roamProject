package com.guoll.modules.product_customer.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.guoll.modules.product_customer.bean.Product;
import com.guoll.modules.product_customer.bean.ProductGroup;

public interface ProductGroupMapper {

	void addNewProductGroup(ProductGroup productGroup);//新增

	void updateProductGroup(ProductGroup productGroup);//更新

	int deleteByIdOrProductsMark(@Param("id") Integer id);//删除
	
	int deleteProductGroupLinkByID(@Param("groupId") Integer groupId);

	List<ProductGroup> queryProductGroupByList(ProductGroup productGroup);//根据模型查询
	
	Integer queryCountOfProductGroup(ProductGroup productGroup);//查询数量

	void addNewProductGroupLink(@Param("link")Map<String, Object> link);//新增产品 - 组 关系

	List<Product> queryProductListOfGroupByGroupId(Integer groupId);//根据产品组id查询组内产品

	void deleteProductGroupLink(Map<String, Object> link);//删除产品-组关系
	
	public ProductGroup queryProductGroupByGroupID(@Param("id") int id);
	
	public List<ProductGroup> queryProductGroupListByProvince(ProductGroup productGroup);
	
	public int deleteProductGroupLinkByGroupId(@Param("groupId") int groupId);
}
