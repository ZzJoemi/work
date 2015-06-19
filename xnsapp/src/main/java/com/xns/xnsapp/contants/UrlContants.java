package com.xns.xnsapp.contants;

public class UrlContants {
	public static String BASEURL="http://www.xinniangshuo.com/api/v2.0.3/release";
	public static String TESTBASEURL="http://www.xinniangshuo.com/api/v2.0.3/debug";
	//首页列表数据
	private static String mainListUrl="/get/home/list.php";
	private static String questionUrl="/get/comment/question.php";

	//个人主页相关URL
	private static String meUrl="/get/my/info.php";
	//个人主页-→获取个人主页下面的文章
	private static String meWzUrl="/get/my/lesson.php";
	//个人主页-→获取个人主页下面的关注
	private static String meGzUrl="/get/my/friends.php";
	//个人主页-→获取个人主页下面的备选
	private static String meBxUrl="/get/my/beixuan.php";
	//个人主页-→获取个人主页下面的活动
	private static String meHdUrl="/get/my/event.php";

	/*
	 * 首页，首页列表数据
	 */
	public static String getMainList(){
		return TESTBASEURL+mainListUrl;
	}

	/*
	 *取提问或者召集的评论
	 */
	public static String getQuestionComment(){return TESTBASEURL+questionUrl;}

	/*
	  取个人主页的信息
	 */
	public static String getMeInfo(){return TESTBASEURL+meUrl;}

	/*
	 *个人主页-→获取个人主页下面的文章
	 */
	public static String getMeWzUrl(){return TESTBASEURL+meWzUrl;}

	/*
	 *个人主页-→获取个人主页下面的关注
	 */
	public static String getMeGzUrl(){return TESTBASEURL+meGzUrl;}

	/*
	 *个人主页-→获取个人主页下面的备选
	 */
	public static String getMeBxUrl(){return TESTBASEURL+meBxUrl;}

	/*
	 *个人主页-→获取个人主页下面的活动
	 */
	public static String getMeHdUrl(){return TESTBASEURL+meHdUrl;}
}
