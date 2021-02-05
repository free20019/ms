package com.erxi.ms.service;

import com.erxi.ms.access.UserContext;
import com.erxi.ms.config.DS;
import com.erxi.ms.controller.CommonAction;
import com.erxi.ms.dao.CommonDao;
import com.erxi.ms.domain.User;
import com.erxi.ms.entity.Action;
import com.erxi.ms.exception.GlobalException;
import com.erxi.ms.redis.RedisService;
import com.erxi.ms.redis.UserKey;
import com.erxi.ms.result.CodeMsg;
import com.erxi.ms.result.FastJsonUtil;
import com.erxi.ms.result.Result;
import com.erxi.ms.util.UUIDUtil;
import com.erxi.ms.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommonService {
	private static Logger log = LoggerFactory.getLogger(CommonAction.class);

	public static final String COOKIE_NAME_TOKEN = "token";
	@Autowired
	CommonDao commonDao;

	@Autowired
	RedisService redisService;

	@DS("datasource1")
	public Result<List<Map<String, Object>>> fingzfqy() {
		return Result.success(commonDao.fingzfqy());
	}

	@DS("datasource1")
	public Result<List<Map<String, Object>>> fingzfbm() {
		return Result.success(commonDao.fingzfbm());
	}

	public User getByUsername(String username) {
		return commonDao.getByUsername(username);
	}
	
	public Integer insertLoginHistory(String username) {
		return commonDao.insertLoginHistory(username);
	}

	@DS("datasource1")
//	@DS("datasource2")
	public String login(HttpServletRequest request,
			HttpServletResponse response, LoginVo loginVo) {
		if (loginVo == null) {
			throw new GlobalException(CodeMsg.SERVER_ERROR);
		}
		String username = loginVo.getUsername();
		String formPass = loginVo.getPassword();

		// 判断用户否存在
		User user = getByUsername(username);
		if (user == null) {
			throw new GlobalException(CodeMsg.USERNAME_NOT_EXIST);
		}

		// 验证密码
		String PassDB = user.getPassword();
		if (!formPass.equals(PassDB)) {
			log.info("mm error");
			throw new GlobalException(CodeMsg.PASSWORD_IS_WRONG);
		}
		String token = UUIDUtil.Uuid();
		addCookie(response, token, user);
		insertLoginHistory(username);
		return token;
	}

	private void addCookie(HttpServletResponse response, String token, User user) {
		// 生成cookie
		// log.info(token);
		redisService.set(UserKey.token, token, user);
		Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
		cookie.setMaxAge(UserKey.token.expireSeconds());
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	public User getByToken(HttpServletResponse response, String token) {
		if (StringUtils.isEmpty(token)) {
			return null;
		}
		User user = redisService.get(UserKey.token, token, User.class);
		// 延长有效期
		if (user != null) {
			addCookie(response, token, user);
		}
		return user;
	}

	public String username() {
		return UserContext.getUser().getUsername();
	}

	@DS("datasource2")
	public void getFile(HttpServletRequest request,
			HttpServletResponse response, String key) {
		try {
			InputStream in = commonDao.getImage(key);
			if(in == null){
				System.out.println("pic not found");
				String path = "d:/nothing.jpg";
				File pf = new File(path);
				FileInputStream fin = new FileInputStream(pf);
				ServletOutputStream fout =  response.getOutputStream();
				byte bts [] = new byte[fin.available()];
				fin.read(bts);
				fout.write(bts);
				System.out.println("--------------------getFile nothing  ok--------------------");
				fin.close();
				fout.close();
			}else {
				System.out.println("pic exist");
				ServletOutputStream fout =  response.getOutputStream();
				byte[] content = new byte[1024];
	            int length = 0;
	            if (in != null) {
	                while ((length = in.read(content)) != -1) {
	                	fout.write(content, 0, length);
	                }
	            }
				System.out.println("--------------------getFile something ok--------------------");
				in.close();
				fout.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DS("datasource2")
	public void getFile2(HttpServletRequest request,
						HttpServletResponse response, String key) {
		try {
			InputStream in = commonDao.getImage2(key);
			if(in == null){
				System.out.println("pic not found");
				String path = "d:/nothing.jpg";
				File pf = new File(path);
				FileInputStream fin = new FileInputStream(pf);
				ServletOutputStream fout =  response.getOutputStream();
				byte bts [] = new byte[fin.available()];
				fin.read(bts);
				fout.write(bts);
				System.out.println("--------------------getFile nothing  ok--------------------");
				fin.close();
				fout.close();
			}else {
				System.out.println("pic exist");
				ServletOutputStream fout =  response.getOutputStream();
				byte[] content = new byte[1024];
				int length = 0;
				if (in != null) {
					while ((length = in.read(content)) != -1) {
						fout.write(content, 0, length);
					}
				}
				System.out.println("--------------------getFile something ok--------------------");
				in.close();
				fout.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DS("datasource1")
	public Result<List<Map<String, Object>>> menu() {
		String name = UserContext.getUser().getUsername();
		System.out.println("name:"+name);
		List<Map<String, Object>> list =commonDao.getMenu(name);
		String menu = list.get(0).get("menu").toString();
		String menus[] = menu.split(",");
		
		Map<String, Object> mn = new HashMap<String, Object>();
		List<Map<String, Object>> allmenu = new ArrayList<Map<String, Object>>();
		
		List<Map> xxfwMenu = new ArrayList<Map>();  
		List<Map> zhtjMenu = new ArrayList<Map>();
		List<Map> wsfwMenu = new ArrayList<Map>();
		List<Map> czgzMenu = new ArrayList<Map>();
		List<Map> czwxMenu = new ArrayList<Map>();
		List<Map> yjzhMenu = new ArrayList<Map>();
		List<Map> dzddfwMenu = new ArrayList<Map>();
		List<Map> jyxxMenu = new ArrayList<Map>();
		List<Map> zcsjMenu = new ArrayList<Map>();
		List<Map> xxgxybsMenu = new ArrayList<Map>();
		List<Map> qxglMenu = new ArrayList<Map>();
		List<Map> yqcxMenu = new ArrayList<Map>();
		List<Map> jiankongzlMenu = new ArrayList<Map>();

		System.out.println("menus length:"+menus.length);
		for (int i = 0; i < menus.length; i++) {
			if(menus[i] != ""){
				System.out.println("name:"+menus[i]);
				List<Map<String, Object>> listm = commonDao.getMenuOne(menus[i]);
				if(listm.size()>0){
					String n = listm.get(0).get("menu").toString();
//				    System.out.println("m:"+listm.get(0).get("list").toString());
					Map m = FastJsonUtil.stringToMap(listm.get(0).get("list").toString());
//				    System.out.println("n:"+n);
					if (n.equals("xxfwMenu")) {
						xxfwMenu.add(m);
						System.out.println("xxfwMenu coming:"+xxfwMenu.size());
					}else if(n.equals("zhtjMenu")){
						zhtjMenu.add(m);
					}else if(n.equals("wsfwMenu")){
						wsfwMenu.add(m);
					}else if(n.equals("czgzMenu")){
						czgzMenu.add(m);
					}else if(n.equals("czwxMenu")){
						czwxMenu.add(m);
					}else if(n.equals("yjzhMenu")){
						yjzhMenu.add(m);
					}else if(n.equals("dzddfwMenu")){
						dzddfwMenu.add(m);
					}else if(n.equals("jyxxMenu")){
						jyxxMenu.add(m);
					}else if(n.equals("zcsjMenu")){
						zcsjMenu.add(m);
					}else if(n.equals("xxgxybsMenu")){
						xxgxybsMenu.add(m);
					}else if(n.equals("qxglMenu")){
						qxglMenu.add(m);
						System.out.println("qxglMenu coming:"+qxglMenu.size());
					}else if(n.equals("yqcxMenu")){
						yqcxMenu.add(m);
						System.out.println("yqcxMenu coming:"+yqcxMenu.size());
					}else if(n.equals("jiankongzlMenu")){
						jiankongzlMenu.add(m);
						System.out.println("jiankongzlMenu coming:"+jiankongzlMenu.size());
					}
				}
			}
		}
		System.out.println("qxglMenu length"+qxglMenu.size());
		System.out.println("yqcxMenu length"+yqcxMenu.size());
		System.out.println("jiankongzlMenu length"+jiankongzlMenu.size());
		System.out.println("xxfwMenu length"+xxfwMenu.size());
		mn.put("xxfwMenu", xxfwMenu);
		mn.put("zhtjMenu", zhtjMenu);
		mn.put("wsfwMenu", wsfwMenu);
		mn.put("czgzMenu", czgzMenu);
		mn.put("czwxMenu", czwxMenu);
		mn.put("yjzhMenu", yjzhMenu);
		mn.put("dzddfwMenu", dzddfwMenu);
		mn.put("jyxxMenu", jyxxMenu);
		mn.put("zcsjMenu", zcsjMenu);
		mn.put("xxgxybsMenu", xxgxybsMenu);
		mn.put("qxglMenu", qxglMenu);
		mn.put("yqcxMenu", yqcxMenu);
		mn.put("jiankongzlMenu", jiankongzlMenu);

		System.out.println("mn"+mn.toString());
		allmenu.add(mn);
		return Result.success(allmenu);
	}

	@DS("datasource1")
	public Result<List<Map<String, Object>>> power() {
		String name = UserContext.getUser().getUsername();
		System.out.println("name:"+name);
		List<Map<String, Object>> list =commonDao.getMenu(name);
		return Result.success(list);
	}
	
	
	
	public static void main(String[] args) {
		String jsonString =  "{name: 'tsclcx', title: '目标车辆查找', icon: 'icon-tsclcz', href: 'app/xxfwgl/tsclcx.html'},";
		System.out.println(FastJsonUtil.stringToMap(jsonString));
	}

	@DS("datasource1")
	public void addDatabase(Action action) {
		commonDao.addDatabase(action);
	}
}
