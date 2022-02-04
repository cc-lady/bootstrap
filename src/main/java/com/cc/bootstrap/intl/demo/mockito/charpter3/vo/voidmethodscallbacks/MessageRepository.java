package com.cc.bootstrap.intl.demo.mockito.charpter3.vo.voidmethodscallbacks;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description 消息存储库界面查找错误代码，并从数据库中检索一条有意义的消息。
 * @createTime 2022年01月25日 16:27:00
 */
public interface MessageRepository {
    String lookUp(String... errorCode);
}
