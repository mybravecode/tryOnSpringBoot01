package com.idol.controller;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.omg.CORBA.MARSHAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.idol.pojo.StudentResource;
import com.idol.pojo.UserT;
import com.idol.result.IdolResult;

/*
 *1. 请求参数为a和b,返回两个数的和a+b 
 *2. 请求参数为people,表示人的个数,返回一个列表表示每个人是几等奖
 * 
 * */

@Controller
public class LastTestController {
	
	// *1. 请求参数为a和b,返回两个数的和a+b 
	@ResponseBody
	@RequestMapping("/addFun1")
	//http://localhost:9999/addFun1?a=2&b=5
	//http://localhost:9999/addFun1 body部分 a=2 b=5
	public int addFun1(@RequestParam int a, @RequestParam int b) {
		return a+b;		
	}
	
	
	// *2. 请求参数为people,表示人的个数,返回一个列表表示每个人是几等奖
	@ResponseBody
	@RequestMapping("/scoreFun2")
	public int[] scoreFun2(@RequestParam int a) {
		int[] array = new int[a];
		for(int i = 0; i < a; i++) {
			array[i] = (int) (Math.random()*3 + 1);
		}
		return array;		
	}
	
	//*3. 请求参数为str和times,返回为该str重复times次，比如str为abc，times为3，则返回abcabcabc
	@ResponseBody
	@RequestMapping("/timesStrFun3")
	public IdolResult timesStrFun3(@RequestParam String input, @RequestParam int times) {
		if(times <= 0)
			return IdolResult.error("times > 0", null);
		
		StringBuilder sBuilder = new StringBuilder();
		for(int i = 0; i < times; i++) {
			sBuilder.append(input);
		}
		return IdolResult.ok("ok", sBuilder.toString());
	}
	
	//*4 输入为时间from（1995-10-23），to（1997-12-15），输出两者之间的天数
	@ResponseBody
	@RequestMapping("/dayInternalFun4")
	public IdolResult dayFun4(@RequestParam String fromDay, @RequestParam String toDay) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateFromTime = simpleDateFormat.parse(fromDay);
            Date dateToTime = simpleDateFormat.parse(toDay);
            int differentDays = differentDays(dateFromTime, dateToTime);
            return IdolResult.ok("相隔的天数", differentDays);
        } catch (Exception e) {
            e.printStackTrace();
            return IdolResult.error(e.getMessage(), null);
        }
        
        //
	}
	
	
	public static int differentDays(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //不同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年            
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            
            return timeDistance + (day2-day1) ;
        }
        else    //同一年
        {
            System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2-day1;
        }
    }
	
	//*5. 去哪儿吃饭 
	//自己创建好，然后弄到某个位置 where有多个选项，
	//自己后台随机选择一个(东一食堂，喻园食堂，西一食堂，皮皮猪，朝天门）
	@RequestMapping("/gotoeat")
	public String goToEat(ModelMap map) {
		String[] places = {"东一食堂","喻园餐厅","西一食堂","皮皮猪","朝天门"};
		int randomP = (int) (Math.random()*5);
		map.addAttribute("where",places[randomP]);
		return "freemarker/test/wheretoeat";
	}
	
	// nextInt -> 0 - max     
	//0-7    new Random.nextInt(8)  
	// min - max , nextInt(max-min+1) + min  
	//3-7    new Random.nextInt(7-3+1) + 3
	//nextInt(max-min+1)      0 - (max - min + 1)
	//0-(7-3+1)  new Random.nextInt(7-3+1)
//	@Autowired
//	private StudentResource studentResource;
//	
//	@RequestMapping("/studentftl")
//	public String studentFtl(ModelMap map) {
//		map.addAttribute("student",studentResource);
//		return "freemarker/center/center";
//	}

	
	//*5. 自己创建好，然后弄到某个位置
	//其中name是姓名（自己起一个），age是年龄（随机20-30），sex是性别（男女，随机弄）
	//info.amount是中奖金额，info.time为当前时间
//	@RequestMapping("/getscore")
//	public String getScore(@RequestParam String name, ModelMap map) {
	@RequestMapping("/getscore/{name}")
	public String getScore(@PathVariable String name, ModelMap map) {
		UserT user = new UserT();
		user.setName(name);
		int age = (int)(Math.random()*11) + 20;
		String sex = Math.random() < 0.5 ?"男":"女";
		user.setAge(age);
		user.setSex(sex);
		
		HashMap<String, Object> info = new HashMap<>();
		Date nowTime = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
		String nowTimeString = dateFormat.format(nowTime); 
		info.put("amount", (int)(Math.random()*100) + "万");
		info.put("time", nowTimeString);
		
		
		HashMap<String, Object> workkkker = new HashMap<>();
		String[] carrerArray = {"工程师","程序员","教师","搬砖","包工头"};
		int index = (int) (Math.random() * carrerArray.length);
		workkkker.put("carrer", carrerArray[index]);
		workkkker.put("workTime", "14h");
		workkkker.put("place", "天上");
		
		map.addAttribute("user", user);
		map.addAttribute("work", workkkker);
		map.addAttribute("info", info);
		
		return "/freemarker/test/hitluck";
	}
	
	@RequestMapping("/ajax")
	public String getAjax(ModelMap map) {
		return "thymeleaf/testajax";
	}
	
	@ResponseBody
	@RequestMapping("/try/ajax/change_info")
	public String changeAjax() {
		return "fzzAjax";
	}




}
