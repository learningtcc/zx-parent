package com.ink.platform.api.util;

public interface SecurityConstant {

    public static final Integer Deleted=1;
    public static final Integer Ndelete=0;

 /**
  * 备注信息
  * @author lww
  *
  */
    public static final class REMARK{
    	 public static final String ORG="机构";
    	 public static final String USR="用户";
    }
    
    /**
     * 状态
     * @author Lenovo-pc
     *
     */
    public static final class Status_Type{
    	
        public static final Integer Enable_Status=1;//启用
        public static final Integer Disable_Status=2;//停用
        public static final Integer Cancel_Status=9;//注销
     }

    /**
     * 删除标识
     * @author lww
     *
     */
    public static final class Delete_Type{
    	
      	 public static final int yes=1;// 删除
      	 public static final int No=0; //正常
      }
    /**
     * 主题类型
     */
    public static final class Subject_Type{
    	
   	 public static final int ORG=1;// 组织机构
   	 public static final int USR=2; //用户
   }
    
    public static final class EasyUi{
      	 public static final String away="_self";// 组织机构
    
      }
    /**
     * 操作动作
     * @author lww
     *
     */
    public static class Action{
     	 public static final String Edit   = "编辑";// 组织机构
     	 public static final String ADD    = "添加";
     	 public static final String DELLF  = "删除";
     	 public static final String LOGIN  = "登录";
     	 public static final String LOGOUT = "退出";
     	 public static final String AUTH   = "授权";
     	 public static final String ResetPWD   = "密码重置";
     }
    /**
     * 操作类型
     * @author lww
     *
     */
    public static class Action_Type{
    	 public static final String USER   = "用户";// 组织机构
    	 public static final String ORG    = "组织机构";
    	 public static final String operation  = "操作";
    	 public static final String Role  = "角色";
    	 public static final String Resource = "资源";
    	 public static final String PlatForm   = "平台";
    	 public static final String User_Org_Role_Relation   = "用户-组织机构&角色关联";
    	 public static final String Role_Resource_Relation   = "角色&资源关联";
    	 public static final String User_Org_Relation   = "用户&组织机构关联";
    }
    /**
     * 操作结果
     * @author lww
     *
     */
    public static class Result{
   	 public static final String SUCC   = "1";// 
   	 public static final String Erro   = "0";
 	 public static final boolean b_SUCC   = true;// 
   	 public static final boolean b_Erro   = false;
   	
   }
    /**
     * 操作结果
     * @author lww
     *
     */
    public static class title_Type{
   	 public static final int major_Job    = 1;// 主岗
   	 public static final int deputy_Job   = 2;//副岗
   	
   }
    /**
     * 权限表达式
     * @author lww
     *
     */
    public static class resource_permision{
      	 public static final String permision = "resourceBaseInfo";
      	
      }
    public static class Key{
    	public static final String userKey ="platform-shiro-userkey";
    	public static final String resourceKey ="platform-shiro-resourcekey";
    	public static final String roleKey ="platform-shiro-roleKey";
    }
}
