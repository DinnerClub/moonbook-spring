package net.mooncloud.moonbook;

public class Constants
{
	// user status
	public static final short USER_STATUS = 0; // 正常
	public static final short USER_STATUS_DELETED = 1; // 删除
	public static final short USER_STATUS_ABNORMAL = 2; // 异常

	public static final short USER_STATUS_SIGUP = 1000; // 注册
	public static final short USER_STATUS_SIGUP_SUCCESS = 1001; // 注册成功
	public static final short USER_STATUS_SIGUP_USERNAME_ALEADY_EXIST = 1002; // 注册失败-用户名已存在

	public static final short USER_STATUS_SIGIN = 2000; // 登录
	public static final short USER_STATUS_SIGIN_SUCCESS = 2001; // 登录成功
	public static final short USER_STATUS_SIGIN_USERNAMW_NOT_EXIST = 2002; // 登录失败-用户名不存在
	public static final short USER_STATUS_SIGIN_PASSWORD_ERROR = 2003; // 登录失败-密码错误
}
