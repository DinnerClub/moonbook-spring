package net.mooncloud.moonbook.entity.payment;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias(value = "userPaymentCategory")
public class UserPaymentCategory implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long userid; // bigint(20) NOT NULL COMMENT '用户id',
	private int pid; // int(2) NOT NULL COMMENT '父类目id',
	private String pname; // varchar(32) DEFAULT NULL COMMENT '父类目',
	private int cid; // int(2) NOT NULL COMMENT '子类目id',
	private String cname; // varchar(32) DEFAULT NULL COMMENT '子类目',
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

	public int getPid()
	{
		return pid;
	}

	public void setPid(int pid)
	{
		this.pid = pid;
	}

	public String getPname()
	{
		return pname;
	}

	public void setPname(String pname)
	{
		this.pname = pname;
	}

	public int getCid()
	{
		return cid;
	}

	public void setCid(int cid)
	{
		this.cid = cid;
	}

	public String getCname()
	{
		return cname;
	}

	public void setCname(String cname)
	{
		this.cname = cname;
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
