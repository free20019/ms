package com.erxi.ms.annotation;

import com.erxi.ms.access.UserContext;
import com.erxi.ms.domain.User;
import com.erxi.ms.entity.Action;
import com.erxi.ms.redis.RedisService;
import com.erxi.ms.service.CommonService;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.*;

/**
 * @author: xianlehuang
 * @Description: aop操作
 * @date: 2020/3/11 - 11:20
 */

@Aspect
@Component
@SuppressWarnings("all")
public class SystemLogAspect {
    //注入Service用于把日志保存数据库
    @Resource
    private CommonService commonService;

    @Autowired
    RedisService redisService;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    //本地异常日志记录对象
    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    //Service层切点
    @Pointcut("@annotation(com.erxi.ms.annotation.SystemServiceLog)")
    public void serviceAspect(){
    }

    //Controller层切点
    @Pointcut("@annotation(com.erxi.ms.annotation.SystemControllerLog)")
    public void controllerAspect(){
    }

    //Dao层切点
    @Pointcut("execution(* com.erxi.ms.dao..*.findczxxcx(..))||execution(* com.erxi.ms.dao..*.findczxxcxdc(..))")
    private void pc(){
    }
    /**
     * @Description  环绕通知  用于拦截Controller层记录用户的操作
     * @date 2020/3/11 - 11:22
     */
    @Around("controllerAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取用户和ip
        User user = UserContext.getUser();
        String remoteAddr = findIp(request);
//        InetAddress ip = null;
//        try {
//            ip = Inet4Address.getLocalHost();
//            System.out.println(ip.getHostAddress());
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }

        //获取请求方法的参数
        String params = "";
        String[] paramNames = ((CodeSignature)joinPoint.getSignature()).getParameterNames();
        Object[] paramValues =joinPoint.getArgs();
        if (paramNames!=null&&paramNames.length>0){
            for (int i = 0; i < paramNames.length; i++) {
                params += paramNames[i]+":"+paramValues[i]+";";
            }
        }
        Object result = null;
        try {
            //*========控制台输出=========*//
            System.out.println("==============环绕通知开始==============");
            System.out.println("请求方法" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName()));
            System.out.println("方法描述：" + getControllerMethodDescription(joinPoint));
            System.out.println("请求人："+user.getUsername());
            System.out.println("请求ip："+remoteAddr);
            System.out.println("请求参数:" + params);

            try {
                result = joinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
//            String sql = getMybatisSql(joinPoint,sqlSessionFactory);
            //*========数据库日志=========*//
            Action action = new Action();
            action.setActionDes(getControllerMethodDescription(joinPoint));
            action.setActionType("0");
            action.setActionIp(remoteAddr);
            action.setUserName(user.getUsername());
            action.setActionTime(new Date());
            action.setMethodName((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName()));
            action.setMethodParams(params);
            action.setSql(sql);
            action.setResult(result==null?"":String.valueOf(result));
            //保存数据库
            commonService.addDatabase(action);
        }catch (Exception e){
            //记录本地异常日志
            logger.error("==环绕通知异常==");
            logger.error("异常信息：{}",e.getMessage());
        }
        System.out.println("==============环绕通知结束==============");
        return result;
    }

    public String sql = "";
    /**
     * @Description  dao环绕通知  获取sql
     * @date 2020/3/11 - 11:22
     */
    @Around("pc()")
    public Object doAround2(ProceedingJoinPoint joinPoint){
        //1.从redis中获取主数据库，若获取不到直接退出，否则判断当前数据源是会否为主，若不为主，则切换到主数据源
        //2.调用目标方法
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        //3.获取SQL

        try {
            sql = getMybatisSql(joinPoint,sqlSessionFactory);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //4.通知同步程序
        return proceed;
    }

    private String findIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if("127.0.0.1".equals(ip)||"0:0:0:0:0:0:0:1".equals(ip)){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip= inet.getHostAddress();
            }
        }
        return ip;
    }


    /**
     * @Description  前置通知  用于拦截Controller层记录用户的操作
     * @date 2020/3/11 - 11:22
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint){
        //获取用户和ip
        User user = UserContext.getUser();
        InetAddress ip = null;
        try {
            ip = Inet4Address.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        //获取请求方法的参数
        String params = "";
        String[] paramNames = ((CodeSignature)joinPoint.getSignature()).getParameterNames();
        Object[] paramValues =joinPoint.getArgs();
        if (paramNames!=null&&paramNames.length>0){
            for (int i = 0; i < paramNames.length; i++) {
                params += paramNames[i]+":"+paramValues[i]+";";
            }
        }

        try {
            //*========控制台输出=========*//
//            System.out.println("==============前置通知开始==============");
//            System.out.println("请求方法" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName()));
//            System.out.println("方法描述：" + getControllerMethodDescription(joinPoint));
//            System.out.println("请求人："+user.getUsername());
//            System.out.println("请求ip："+ip.getHostAddress());
//            System.out.println("请求参数:" + params);
//            //*========数据库日志=========*//
//            Action action = new Action();
//            action.setActionDes(getControllerMethodDescription(joinPoint));
//            action.setActionType("0");
//            action.setActionIp(ip.getHostAddress());
//            action.setUserName(user.getUsername());
//            action.setActionTime(new Date());
//            action.setMethodName((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName()));
//            action.setMethodParams(params);
//            action.setSql("");
//            action.setResult("");
        }catch (Exception e){
            //记录本地异常日志
            logger.error("==前置通知异常==");
            logger.error("异常信息：{}",e.getMessage());
        }
    }

    /**
     * @Description  异常通知 用于拦截service层记录异常日志
     * @date 2018年9月3日 下午5:43
     */
    @AfterThrowing(pointcut = "serviceAspect()",throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint,Throwable e){
        //获取用户和ip
        User user = UserContext.getUser();
        InetAddress ip = null;
        try {
            ip = Inet4Address.getLocalHost();
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        }

        //获取请求方法的参数
        String params = "";
        String[] paramNames = ((CodeSignature)joinPoint.getSignature()).getParameterNames();
        Object[] paramValues =joinPoint.getArgs();
        if (paramNames!=null&&paramNames.length>0){
            for (int i = 0; i < paramNames.length; i++) {
                params += paramNames[i]+":"+paramValues[i]+";";
            }
        }
        try{
            /*========控制台输出=========*/
            System.out.println("=====异常通知开始=====");
            System.out.println("异常代码:" + e.getClass().getName());
            System.out.println("异常信息:" + e.getMessage());
            System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            System.out.println("方法描述:" + getServiceMethodDescription(joinPoint));
            System.out.println("请求人:" + user.getUsername());
            System.out.println("请求IP:" + ip.getHostAddress());
            System.out.println("请求参数:" + params);
            /*==========数据库日志=========*/
            Action action = new Action();
            action.setActionDes(getServiceMethodDescription(joinPoint));
            action.setActionType("1");
            action.setUserName(user.getUsername());
            action.setActionIp(ip.getHostAddress());
            action.setActionTime(new Date());
            action.setMethodName((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName()));
            action.setMethodParams(params);
            action.setSql("");
            action.setResult("");
        }catch (Exception ex){
            //记录本地异常日志
            logger.error("==异常通知异常==");
            logger.error("异常信息:{}", ex.getMessage());
        }
    }


    /**
     * @Description  获取注解中对方法的描述信息 用于service层注解
     * @date 2018年9月3日 下午5:05
     */
    public static String getServiceMethodDescription(JoinPoint joinPoint)throws Exception{
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method:methods) {
            if (method.getName().equals(methodName)){
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length==arguments.length){
                    description = method.getAnnotation(SystemServiceLog.class).description();
                    break;
                }
            }
        }
        return description;
    }



    /**
     * @Description  获取注解中对方法的描述信息 用于Controller层注解
     * @date 2018年9月3日 上午12:01
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();//目标方法名
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method:methods) {
            if (method.getName().equals(methodName)){
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length==arguments.length){
                    description = method.getAnnotation(SystemControllerLog.class).description();
                    break;
                }
            }
        }
        return description;
    }

    /**
     * 获取aop中的SQL语句
     * @param pjp
     * @param sqlSessionFactory
     * @return
     * @throws IllegalAccessException
     */
    public static String getMybatisSql(ProceedingJoinPoint pjp, SqlSessionFactory sqlSessionFactory) throws IllegalAccessException {
        Map<String,Object> map = new HashMap<String,Object>();
        //1.获取namespace+methdoName
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        String namespace = method.getDeclaringClass().getName();
        String methodName = method.getName();
        //2.根据namespace+methdoName获取相对应的MappedStatement
        Configuration configuration = sqlSessionFactory.getConfiguration();
        MappedStatement mappedStatement = configuration.getMappedStatement(namespace+"."+methodName);
//        //3.获取方法参数列表名
//        Parameter[] parameters = method.getParameters();
        //4.形参和实参的映射
        Object[] objects = pjp.getArgs(); //获取实参
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0;i<parameterAnnotations.length;i++){
            Object object = objects[i];
            if (parameterAnnotations[i].length == 0){ //说明该参数没有注解，此时该参数可能是实体类，也可能是Map，也可能只是单参数
                if (object.getClass().getClassLoader() == null && object instanceof Map){
                    map.putAll((Map<? extends String, ?>) object);
                    System.out.println("该对象为Map");
                }else{//形参为自定义实体类
                    map.putAll(objectToMap(object));
                    System.out.println("该对象为用户自定义的对象");
                }
            }else{//说明该参数有注解，且必须为@Param
                for (Annotation annotation : parameterAnnotations[i]){
                    if (annotation instanceof Param){
                        map.put(((Param) annotation).value(),object);
                    }
                }
            }
        }
        //5.获取boundSql
        BoundSql boundSql = mappedStatement.getBoundSql(map);
        return showSql(configuration,boundSql);
    }

    /**
     * 解析BoundSql，生成不含占位符的SQL语句
     * @param configuration
     * @param boundSql
     * @return
     */
    private  static String showSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (parameterMappings.size() > 0 && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    String[] s =  metaObject.getObjectWrapper().getGetterNames();
                    s.toString();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    }
                }
            }
        }
        return sql;
    }
    /**
     * 若为字符串或者日期类型，则在参数两边添加''
     * @param obj
     * @return
     */
    private static String getParameterValue(Object obj) {
        String value = null;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(new Date()) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }
        }
        return value;
    }

    /**
     * 获取利用反射获取类里面的值和名称
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    private static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<String, Object>();
        Class<?> clazz = obj.getClass();
        System.out.println(clazz);
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, value);
        }
        return map;
    }


}
