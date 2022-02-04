package com.cc.bootstrap.intl.demo.mockito.charpter3.test.mockitosettings;

import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.mockitosettings.Bar;
import com.cc.bootstrap.intl.demo.mockito.charpter3.vo.mockitosettings.Foo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

/**
 * @author ChenChen
 * @version 1.0.0
 * @ClassName
 * @Description 更改Mockito默认配置 - 单元测试
 *
 * 我们了解到，模拟对象的非存根方法返回默认值，例如对象为Null，布尔值为false 。
 * 但是，Mockito允许我们更改默认设置来返回其他非默认值；这些基本上是预先配 置的答案。
 * 允许的设置如下：
 * 1.RETURNS_DEFAULTS：这是默认设置，对对象返回null，布尔值返回falle，以此类 推。
 * 2.RETURNS_SMART_NULLS：这将返回智能的空根，这是一种类似于空根的存 根(如果您尝试调用存根，它们就会抛出异常。
 *   任何方法())，但是抛出异常比正常的空点异常更有用，通过提供关于它们来自 哪个调用的信息。
 * 3.RETURNS_MOCKS：这将返回对象的模拟值和原语的默认值。如果无法模拟 对象（如最后一个类），则返回一个Null值。
 * 4.RETURNS_DEEP_STUBS：这将返回一个深度存根。这对于我们需要存根方法链接 的遗留代码非常重要，
 *   例如，当Foo调用getBar().getTar().getName()时。 深度存根允许Foo直接存根getName()方法以返回一个值。
 *   否则，我们必须存 根Foo的getBar方法来返回一个模拟条对象，存根条的getTar()方法来返回一 个模拟Tar对象，、
 *   最后，存根Tar的getName方法来返回一个值。
 * 5.CALLS_REAL_METHODS：这将从模拟类的实际实现中调用相应的方法
 * @createTime 2022年01月27日 11:15:00
 */
@RunWith(MockitoJUnitRunner.class)
public class SettingsChangeTest {

    /*
     * 一、测试RETURNS_DEFAULTS：这是默认设置，对对象返回null，布尔值返回falle，以此类 推。
     */
    @Test
    public void when_default_settings() throws Exception {
        // test
        Foo fooWithReturnDefault = Mockito.mock(Foo.class, Mockito.RETURNS_DEFAULTS);

        // verify
        // default null is returned
        assertNull(fooWithReturnDefault.getBar());
    }

    /*
     * 二、测试RETURNS_SMART_NULLS：返回智能的空根，这是一种类似于空根的存 根
     */
    @Test
    public void when_changing_default_settings_to_return_smartNULLS(){
        Foo fooWithSmartNull = Mockito.mock(Foo.class, Mockito.RETURNS_SMART_NULLS);
        // a smart null is returned
        assertNotNull(fooWithSmartNull.getBar());
        System.out.println("fooWithSmartNull.getBar() =" + fooWithSmartNull.getBar());

        // 可被再次存根成其他值
        when(fooWithSmartNull.getBar()).thenReturn(new Bar());
        System.out.println("fooWithSmartNull.getBar() =" + fooWithSmartNull.getBar());
    }

    /*
     * 三、测试RETURNS_MOCKS:返回对象的模拟值和原语的默认值。如果无法模拟 对象（如final类），则返回一个Null值。
     */
    @Test
    public void when_changing_default_settings_to_return_mocks() {
        Foo fooWithReturnsMocks = Mockito.mock(Foo.class, Mockito.RETURNS_MOCKS);
        // a mock is returned
        Bar mockBar = fooWithReturnsMocks.getBar();
        assertNotNull(mockBar);
        assertNotNull(mockBar.getTar());
        assertNotNull(mockBar.getTar().getName());
        System.out.println("fooWithReturnsMocks.getBar()=" + mockBar);
        System.out.println("fooWithReturnsMocks.getBar().getTar()=" + mockBar.getTar());
        System.out.println("fooWithReturnsMocks.getBar().getTar().getName()={" + mockBar.getTar().getName()+"}");
    }

    /*
     * 四、测试RETURNS_DEEP_STUBS: 返回一个深度存根。这对于我们需要存根方法链接 的遗留代码非常重要，
     *   例如，当Foo调用getBar().getTar().getName()时。 深度存根允许Foo直接存根getName()方法以返回一个值。
     */
    @Test
    public void when_returns_deep_stub() throws Exception {
        Foo fooWithDeepStub = Mockito.mock(Foo.class, Mockito.RETURNS_DEEP_STUBS);
        when(fooWithDeepStub.getBar().getTar().getName()).thenReturn("Deep Stub");
        // a deep stubbed mock is returned
        System.out.println("fooWithDeepStub.getBar().getTar().getName()="+ fooWithDeepStub.getBar().getTar().getName());
        assertNotNull(fooWithDeepStub.getBar().getTar().getName());
    }

    /*
     * 五、从模拟类的实际实现中调用相应的方法 -- ?? 书上没说，我测试出来第一个模拟对象就是Null
     */
    @Test
    public void when_calls_real_methods() throws Exception {
        Foo fooRealMethods = Mockito.mock(Foo.class, Mockito.CALLS_REAL_METHODS);
        System.out.println("fooRealMethods=" + fooRealMethods);

        assertNotNull(fooRealMethods);
        assertNull(fooRealMethods.getBar());

        System.out.println("fooRealMethods.getBar()=" + fooRealMethods.getBar());
    }

}
