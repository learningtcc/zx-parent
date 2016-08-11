//package com.ink.user.core.dao;
//
//import org.apache.ibatis.annotations.Param;
//
//import com.ink.base.EntityDao;
//import com.ink.user.core.po.AccOrg;
//
///**
// * 渠道信息dao层
// * @author yangchen
// * @date 2016年5月24日 下午6:01:15
// */
//public interface IAccOrgDao extends EntityDao<AccOrg, Integer>{
//	int deleteByPrimaryKey(Long id);
//
//    int insert(AccOrg record);
//
//    /**
//     * 
//     * @Title: insertSelective 
//     * @Description: 新增机构信息
//     * 创建人：guojie.gao ,  2015年10月26日  上午11:31:13
//     * 修改人：guojie.gao ,  2015年10月26日  上午11:31:13
//     * @param
//     * @return
//     * @throws
//     *
//     */
//    int insertSelective(AccOrg record);
//
//    AccOrg selectByPrimaryKey(Long id);
//    /**
//     * 
//     * @Title: updateByPrimaryKeySelective 
//     * @Description: 根据id更新机构信息
//     * 创建人：guojie.gao ,  2015年10月26日  上午11:30:50
//     * 修改人：guojie.gao ,  2015年10月26日  上午11:30:50
//     * @param
//     * @return
//     * @throws
//     *
//     */
//    int updateByPrimaryKeySelective(AccOrg record);
//
//    int updateByPrimaryKey(AccOrg record);
//    
//    /**
//     * 
//     * @Title: findByOrgId 
//     * @Description: 根据机构编号查询机构信息
//     * @param @param orgId
//     * @param @return
//     * @return AccOrg
//     * @throws
//     *
//     */
//    public AccOrg findByOrgId(Long orgId);
//    
//    /**
//     * 
//     * @Title: updateByOrgId 
//     * @Description: 根据orgId更新机构信息
//     * 创建人：guojie.gao ,  2015年10月26日  上午11:45:13
//     * 修改人：guojie.gao ,  2015年10月26日  上午11:45:13
//     * @param
//     * @return
//     * @throws
//     *
//     */
//    public int updateByOrgId(AccOrg accOrg);
//    
//    /**
//     * 
//     * @Title: findListPageCount 
//     * @Description: 分页查询记录总记录数
//     * 创建人：zx ,  2015年10月28日  下午4:28:02
//     * 修改人：zx ,  2015年10月28日  下午4:28:02
//     * @param
//     * @return
//     * @throws
//     *
//     */
//    public int findListPageCount(@Param("accOrgVO")AccOrg accOrg);
//    
//    /**
//	 * 
//	 * @FunctionName checkAccOrg
//	 * @Description 检查渠道是否存在
//	 * @author guojie.gao
//	 * @date 2015年11月25日 上午10:53:02
//	 * @version 1.0
//	 * @history guojie.gao, 2015年11月25日 上午10:53:02 create
//	 * 
//	 * @param orgId
//	 * @return
//	 * @throws Exception
//	 */
//	public AccOrg checkAccOrg(Long orgId) throws Exception;
//}
