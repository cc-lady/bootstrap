package com.cc.bootstrap.page.api;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cc.bootstrap.common.schema.Email;
import com.cc.bootstrap.page.dao.MailMapper;
import com.cc.bootstrap.page.vo.MailListVO;

/**
 * @ClassName: EmailController 
 * @Description: email测试
 * https://www.cnblogs.com/muliu/p/6017622.htmlspring boot也有单独的可以做，方便
 * 本例子是java mail本身的，不是springboot集成好的，可以取试试spring boot集成好的
 * @author CC  
 * @date 2018年11月30日 下午3:47:54 
 * @version V1.0 
 */
@Controller//不用RestController因为可能会跳转页面
@RequestMapping(value="/email")
public class EmailController {
	private static final String MAIL_SMTP_HOST = "smtp.qq.com";//smtp.qq.com
	private static final String MAIL_SMTP_USER = "907566076@qq.com";//163邮件登录账号
	private static final String MAIL_SMTP_PASSWORD = "quuhuifqvotibbic";//授权码，非注册时密码
	private static final String FROM = "907566076@qq.com";//163邮件登录账号
	private static Properties props = new Properties();
	
	  
	static {
		//设置代理
		/*props.setProperty("proxySet", "true");
		props.setProperty("socksProxyHost", "proxy.piccnet.com.cn");
		props.setProperty("socksProxyPort", "3128");*/

		props.setProperty("mail.transport.protocol", "smtp"); // 发送邮件协议 
		props.setProperty("mail.host", MAIL_SMTP_HOST); // 服务器 
		props.setProperty("mail.smtp.port", "465"); // 端口   496/587   496不行
		props.setProperty("mail.smtp.auth", "true");//允许smtp校验
//		props.setProperty("mail.smtp.connectiontimeout", "60000");//连接超时时间
//		props.setProperty("mail.smtp.timeout", "60000");//Socket I/O timeout value in milliseconds

		
		//QQ邮箱需要SSL验证
		/*MailSSLSocketFactory sf = null;
		try {
			sf = new MailSSLSocketFactory();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		sf.setTrustAllHosts(true);
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.socketFactory", sf);*/
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

	}
	  
	@Autowired
	private MailMapper emailMapper;
	
	//======================以下是学习java发送email的例子============================
	
	//1.跳转到java_email.jsp页面 http://localhost:9011/email/email
	@RequestMapping(value = "/email")
	public ModelAndView email() {
		long startTime = System.currentTimeMillis();
		List<Email> emails = emailMapper.findAllEmails();
		long endTime = System.currentTimeMillis();
		System.out.println("查询所有用户共用了"+(endTime - startTime)+"毫秒！");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/email/java_email");
		mav.addObject("emails", emails);
		return mav;
	}
		
	//2.新增一条email记录 http://localhost:9011/email/insert
	@ResponseBody
	@RequestMapping("/insert")
	public String insert(Email email){
		try {
			emailMapper.insertEmail(email);
			return "success";
		} catch (Exception e) {
			String errorMessage = "error：" + e.getMessage();
			return errorMessage;
		}
	}	
	
	//3.接收页面传来的邮箱名字和地址，发送邮件   http://localhost:9011/email/mailTo
	@ResponseBody
	@RequestMapping("/mailTo")
	public String handleUserList(MailListVO mailListVOs) {
		try {
			List<Email> emails = mailListVOs.getMailList();
			//发送邮件
			this.mailToPersons(emails);
			return "success";
		} catch (Exception e) {
			String errorMessage = "error：" + e.getMessage();
			e.printStackTrace();
			return errorMessage;
		}
	}

	/**
	 * @Title: mailToPersons 
	 * @Description: 给多人发送邮件
	 * @param @param emails   页面传来的收件人姓名和邮箱地址
	 * @return void  
	 * @throws
	 * @author CC
	 * @date 2018年11月30日 下午5:57:16
	 * @version V1.0
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	private void mailToPersons(List<Email> emails) throws AddressException, MessagingException {
		
		//确定权限（账户和密码）
        /*Authenticator authenticator = new Authenticator() {
       	 @Override
       	 public PasswordAuthentication getPasswordAuthentication() {
       		 return new PasswordAuthentication(MAIL_SMTP_USER, MAIL_SMTP_PASSWORD);
       	 }
        };
        
        Session session = Session.getInstance(props, authenticator);//创建邮件会话*/
		 Session session = Session.getInstance(props);
        session.setDebug(true);//开启session的调试模式，可以查看当前邮件发送状态
		
		//发送邮件
        Transport transport = session.getTransport("smtp");
        transport.connect(MAIL_SMTP_USER, MAIL_SMTP_PASSWORD);

        
		for (Email email: emails) {
			// 收件人电子邮箱
		     String to = email.getEmail().trim();
	         MimeMessage message = new MimeMessage(session);//由邮件会话新建一个消息对象
	     
	         message.setFrom(new InternetAddress(FROM));//设置发件人的地址
	         message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));//设置收件人,并设置其接收类型为TO
	         //设置信件内容
	         message.setText("你好，" + email.getUserName() +"！这是cc的测试邮件，请忽略！如有不便之处，请谅解cc！！"); //发送 纯文本 邮件 TODO
	         message.setSubject("cc-java学习发送邮件测试！");//设置标题
//	         message.setContent(content, "text/html;charset=gbk"); //发送HTML邮件，内容样式比较丰富
	         message.setSentDate(new Date());//设置发信时间
//	         message.saveChanges();//存储邮件信息

	         transport.sendMessage(message, message.getAllRecipients());//发送邮件,其中第二个参数是所有已设好的收件人地址
	         break;

		}
		
		transport.close();
	}
	
		
	//======================以上是学习java发送email的例子============================
}
