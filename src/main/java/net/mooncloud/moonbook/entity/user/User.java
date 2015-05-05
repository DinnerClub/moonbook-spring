package net.mooncloud.moonbook.entity.user;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias(value = "user")
public class User implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long userid; // bigint(20) NOT NULL COMMENT '用户id',
	private String username; // varchar(20) DEFAULT NULL COMMENT '登录名',
	private String usernick; // varchar(20) DEFAULT NULL COMMENT '用户昵称',
	private String password; // varchar(256) DEFAULT NULL COMMENT '登陆密码',
	private String salt; // varchar(64) DEFAULT NULL COMMENT '备用密码',
	private String email; // varchar(64) DEFAULT NULL COMMENT '邮箱',
	private String mobile; // varchar(64) DEFAULT NULL COMMENT '手机号码',

	private short syn; // smallint(5) DEFAULT '0' COMMENT '同步标识',
	private Date created; // timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT
							// '创建时间',
	private Date updated; // timestamp NULL DEFAULT '0000-00-00 00:00:00'
							// COMMENT '更新时间',
	private short status; // smallint(5) DEFAULT '0' COMMENT '状态',

	public long getUserid()
	{
		return userid;
	}

	public void setUserid(long userid)
	{
		this.userid = userid;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getUsernick()
	{
		return usernick;
	}

	public void setUsernick(String usernick)
	{
		this.usernick = usernick;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getSalt()
	{
		return salt;
	}

	public void setSalt(String salt)
	{
		this.salt = salt;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getMobile()
	{
		return mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public short getSyn()
	{
		return syn;
	}

	public void setSyn(short syn)
	{
		this.syn = syn;
	}

	public Date getCreated()
	{
		return created;
	}

	public void setCreated(Date created)
	{
		this.created = created;
	}

	public Date getUpdated()
	{
		return updated;
	}

	public void setUpdated(Date updated)
	{
		this.updated = updated;
	}

	public short getStatus()
	{
		return status;
	}

	public void setStatus(short status)
	{
		this.status = status;
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		Class<?> clazz = this.getClass();
		sb.append(clazz.getName()).append(" [");
		for (Field field : this.getClass().getDeclaredFields())
		{
			PropertyDescriptor pd;
			try
			{
				pd = new PropertyDescriptor(field.getName(), this.getClass());
				Method wM = pd.getReadMethod();// 获得写方法
				sb.append(field.getName()).append("=").append(wM.invoke(this)).append(", ");
			}
			catch (Exception e)
			{
				// e.printStackTrace();
			}
		}
		sb.append("]");
		return sb.toString();
	}
}
