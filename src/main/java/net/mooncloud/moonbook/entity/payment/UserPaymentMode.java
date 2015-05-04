package net.mooncloud.moonbook.entity.payment;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias(value = "userPaymentMode")
public class UserPaymentMode implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long userid; // bigint(20) NOT NULL COMMENT '用户id',
	private int mid; // int(2) NOT NULL COMMENT '支付方式id',
	private String mname; // varchar(32) DEFAULT NULL COMMENT '支付方式',
	private int sid; // int(2) NOT NULL COMMENT '资金源id',
	private String sname; // varchar(32) DEFAULT NULL COMMENT '资金源',
	private long count; // bigint(20) DEFAULT '0' COMMENT ' 使用次数',

	private short syn; // int(2) DEFAULT '0' COMMENT '同步标识',
	private Date created; // timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT
							// '创建时间',
	private Date updated; // timestamp NULL DEFAULT '0000-00-00 00:00:00'
							// COMMENT
							// '更新时间',
	private short status; // int(2) DEFAULT '0' COMMENT '状态',

	public long getUserid()
	{
		return userid;
	}

	public void setUserid(long userid)
	{
		this.userid = userid;
	}

	public int getMid()
	{
		return mid;
	}

	public void setMid(int mid)
	{
		this.mid = mid;
	}

	public String getMname()
	{
		return mname;
	}

	public void setMname(String mname)
	{
		this.mname = mname;
	}

	public int getSid()
	{
		return sid;
	}

	public void setSid(int sid)
	{
		this.sid = sid;
	}

	public String getSname()
	{
		return sname;
	}

	public void setSname(String sname)
	{
		this.sname = sname;
	}

	public long getCount()
	{
		return count;
	}

	public void setCount(long count)
	{
		this.count = count;
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
