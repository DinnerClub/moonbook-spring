package net.mooncloud.moonbook.entity.income;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias(value = "userIncomeDailySubtotal")
public class UserIncomeDailySubtotal implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected long userid; // bigint(20) DEFAULT NULL COMMENT '用户id',
	protected int thedate; // int(4) DEFAULT NULL COMMENT '日期yyyymmdd',
	protected short year; // int(2) DEFAULT NULL COMMENT '年',
	protected byte month; // int(1) DEFAULT NULL COMMENT '月',
	protected byte date; // int(1) DEFAULT NULL COMMENT '日',
	protected byte day; // int(1) DEFAULT NULL COMMENT '星期',
	protected double money; // decimal(12,2) DEFAULT NULL COMMENT '收入金额',

	protected short syn; // int(2) DEFAULT '0' COMMENT '同步标识',
	protected Date created; // timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT
							// '创建时间',
	protected Date updated; // timestamp NULL DEFAULT '0000-00-00 00:00:00'
							// COMMENT
							// '更新时间',
	protected short status; // int(2) DEFAULT '0' COMMENT '状态',

	public long getUserid()
	{
		return userid;
	}

	public void setUserid(long userid)
	{
		this.userid = userid;
	}

	public int getThedate()
	{
		return thedate;
	}

	public void setThedate(int thedate)
	{
		this.thedate = thedate;
	}

	public short getYear()
	{
		return year;
	}

	public void setYear(short year)
	{
		this.year = year;
	}

	public byte getMonth()
	{
		return month;
	}

	public void setMonth(byte month)
	{
		this.month = month;
	}

	public byte getDate()
	{
		return date;
	}

	public byte getDay()
	{
		return day;
	}

	public void setDay(byte day)
	{
		this.day = day;
	}

	public void setDate(byte date)
	{
		this.date = date;
	}

	public double getMoney()
	{
		return money;
	}

	public void setMoney(double money)
	{
		this.money = money;
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
