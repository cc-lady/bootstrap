package com.cc.bootstrap.intl.demo.design_pattern.proxy.self_jdk;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 自己手写的代理类，自动生成实际代理类的工具
 * @author: ChenChen
 * @date: 2025-10-11 14:58
 */
public class SelfProxy {
    public static final String ln = "\r\n";

    public static Object newProxyInstance(SelfClassLoader loader,
                                          Class<?>[] interfaces,
                                          SelfInvocationHandler h) {
        try {
            // 动态生成源代码.java文件
            String src = generateSrc(interfaces);

            // java文件输出磁盘
            String filePath = SelfProxy.class.getResource("").getPath();
            File file = new File(filePath + "Proxy0.java");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(src);
            fileWriter.flush();
            fileWriter.close();

            // 把生成的java文件编译成.class文件
            JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager standardJavaFileManager = javaCompiler.getStandardFileManager(null, null, null);
            Iterable<? extends JavaFileObject> javaFileObjects = standardJavaFileManager.getJavaFileObjects(file);

            JavaCompiler.CompilationTask task = javaCompiler.getTask(null, standardJavaFileManager, null, null, null, javaFileObjects);
            task.call();
            standardJavaFileManager.close();

            // 把编译的.class文件加载到jvm中
            Class proxyClass = loader.findClass("Proxy0");
            Constructor c = proxyClass.getConstructor(SelfInvocationHandler.class);
            file.delete();

            // 返回字节码重组后的新的代理对象
            return c.newInstance(h);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String generateSrc(Class<?>[] interfaces) {
        StringBuilder sb = new StringBuilder();
        sb.append("package com.cc.bootstrap.intl.demo.design_pattern.proxy.self_jdk;" + ln);
        sb.append("import com.cc.bootstrap.intl.demo.design_pattern.proxy.jdk.Person;" + ln);
        sb.append("import java.lang.reflect.*;" + ln);
        sb.append("public class Proxy0 implements " + interfaces[0].getName() + "{" + ln);
        sb.append("SelfInvocationHandler h;" + ln);
        sb.append("public Proxy0() {} " + ln);
        sb.append("public Proxy0(SelfInvocationHandler h) { " + ln);
        sb.append("this.h = h;" + ln);
        sb.append("}" + ln);
        for (Method m : interfaces[0].getMethods()) {
            Class<?>[] params = m.getParameterTypes();
            StringBuilder paramNames = new StringBuilder();
            StringBuilder paramValues = new StringBuilder();
            StringBuilder paramClasses = new StringBuilder();

            for (int i = 0; i < params.length; i++) {
                Class clazz = params[i];
                String type = clazz.getName();
                String paramName = toLowerFirstCase(clazz.getSimpleName());
                paramNames.append(type + " " + paramName);
                paramValues.append(paramName);
                paramClasses.append(clazz.getName() + ".class");
                if (i > 0 && i < params.length - 1) {
                    paramNames.append(",");
                    paramClasses.append(",");
                    paramValues.append(",");
                }
            }
            sb.append("public " + m.getReturnType().getName() + " " + m.getName() + "("
                    + paramNames.toString() + ") {" + ln);
            sb.append("try{" + ln);
            sb.append("Method m=" + interfaces[0].getName() + ".class.getMethod(\"" + m.getName() + "\",new Class[]{" + paramClasses.toString() + "});" + ln);
            sb.append((hasReturnValue(m.getReturnType()) ? "return" : "") +
                    getCaseCode("this.h.invoke(this,m, new Object[]{" + paramValues + "})", m.getReturnType()) + ";" + ln);
            sb.append("}catch(Error _ex) { }");
            sb.append("catch(Throwable e){" + ln);
            sb.append("throw new UndeclaredThrowableException(e);" + ln);
            sb.append("}");
            sb.append(getReturnEmptyCode(m.getReturnType()));
            sb.append("}");
        }
        sb.append("}" + ln);
        return sb.toString();
    }

    private static String toLowerFirstCase(String src) {
        char[] chars = src.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    private static boolean hasReturnValue(Class<?> returnType) {
        return returnType != void.class;
    }

    private static Map<Class, Class> mappings = new HashMap<Class, Class>();

    static {
        mappings.put(int.class, Integer.class);
    }

    private static String getReturnEmptyCode(Class<?> returnClass) {
        if (mappings.containsKey(returnClass)) {
            return "return 0;";
        } else if (returnClass == void.class) {
            return "";
        } else {
            return "return null;";
        }
    }

    private static String getCaseCode(String code,Class<?> returnClass) {
        if (mappings.containsKey(returnClass)) {
            return "((" + mappings.get(returnClass).getName() + ")" + code + ")." + returnClass.getSimpleName() + "Value()";
        }
        return code;
    }



}
