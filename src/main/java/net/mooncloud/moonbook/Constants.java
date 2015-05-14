package net.mooncloud.moonbook;

public class Constants
{
	// common status
	public static final short COMMON_STATUS = 0; // 正常
	public static final short COMMON_STATUS_DELETED = 1; // 删除
	public static final short COMMON_STATUS_ABNORMAL = 2; // 异常

	// user status
	public static final short USER_STATUS = 1000; // 正常
	public static final short USER_STATUS_DELETED = 1001; // 删除
	public static final short USER_STATUS_ABNORMAL = 1002; // 异常

	public static final short USER_STATUS_SIGUP = 1100; // 请求注册
	public static final short USER_STATUS_SIGUP_SUCCESS = 1110; // 注册成功
	public static final short USER_STATUS_SIGUP_FAILED = 1120; // 注册失败
	public static final short USER_STATUS_SIGUP_USERNAME_ALREADY_EXIST = 1121; // 注册失败-用户名已存在

	public static final short USER_STATUS_SIGIN = 1200; // 请求登录
	public static final short USER_STATUS_SIGIN_SUCCESS = 1210; // 登录成功
	public static final short USER_STATUS_SIGIN_FAILED = 1220; // 登录失败
	public static final short USER_STATUS_SIGIN_USERNAME_NOT_EXIST = 1221; // 登录失败-用户名不存在
	public static final short USER_STATUS_SIGIN_PASSWORD_ERROR = 1222; // 登录失败-密码错误

	public static final short USER_STATUS_UPDATE = 1300; // 请求更新
	public static final short USER_STATUS_UPDATE_SUCCESS = 1310; // 更新成功
	public static final short USER_STATUS_UPDATE_FAILED = 1320; // 更新失败
}
