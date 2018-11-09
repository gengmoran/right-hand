package com.misterfat.righthand.component;

import org.springframework.stereotype.Component;

@Component
public class SystemEmailSender {

	/**
	 * 
	 * 功能描述：发送邮件
	 *
	 * @param subject
	 *            主题
	 * @param content
	 *            内容
	 * @param to
	 *            收件人
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2018年6月14日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public String send(String subject, String content, String... to) {
		return null;
	}

	/**
	 * 
	 * 功能描述：发送账号添加成功的邮件
	 *
	 * @param to
	 * @return
	 * 
	 * @author 耿沫然
	 *
	 * @since 2018年6月14日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public String sendUserAddedEmail(String... to) {
		return null;
	}

}
