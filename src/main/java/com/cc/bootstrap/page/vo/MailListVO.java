package com.cc.bootstrap.page.vo;

import java.util.List;

import com.cc.bootstrap.common.schema.Email;

/**
 * @ClassName: MailListVO 
 * @Description: mybatis前后台传值List类型使用VO类--MailListVO
 * @author CC  
 * @date 2018年11月30日 下午5:46:58 
 * @version V1.0 
 */
public class MailListVO {
	private List<Email> mailList;

	public List<Email> getMailList() {
		return mailList;
	}

	public void setMailList(List<Email> mailList) {
		this.mailList = mailList;
	}
	
	
}
