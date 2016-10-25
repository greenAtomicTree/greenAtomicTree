package diaoyu;

public class StringCommon {
	 

	public static String getstateShow(String statenum) {
		String statenow = "";
		if (statenum != null) {
			if (statenum.equals("0")) {
				statenow = "预占";
			} else if (statenum.equals("1")) {
				statenow = "可以运行";
			} else if (statenum.equals("2")) {
				statenow = "正在运行";
			} else if (statenum.equals("3")) {
				statenow = "运行成功";
			} else if (statenum.equals("4")) {
				statenow = "运行失败";
			} else if (statenum.equals("5")) {
				statenow = "取消";
			} else if (statenum.equals("6")) {
				statenow = "终止";
			}
		}
		return statenow;
	}

	public static String getnulldealstate(String statenum) {
		String statenow = "";
		if (statenum != null) {
			if (statenum.equals("3") || statenum.equals("4")) {
				statenow = "未执行";
			}
		}
		return statenow;
	}
	/**
	 * 如果字符串为null返回空
	 * @param str
	 * @return
	 */
	public static String trimNull(String str){
		if(str==null||str.equalsIgnoreCase("null")){
			return "";
		}else{
			return str;
		}
	}
	
	/**
	 * 如果字符串为null返回true
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str){
		if(str==null||str.equals("")||str.equalsIgnoreCase("null")){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 是否为整数
	 * @param str
	 * @return
	 */
	public static boolean isInt(String str){
		if(str==null||str.equals("")||str.equalsIgnoreCase("null")){
			return false;
		}else{
			return str.matches("^\\+?\\-?[\\d]+$");
		}
	}
	/**
	 * 是否为整数
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		if(str==null||str.equals("")||str.equalsIgnoreCase("null")){
			return false;
		}else{
	       return str.matches("(^\\+?\\-?[\\d]+[.]?[\\d]+$)||(^\\+?\\-?[\\d]+$)");
		}
	  }

	/**
	 * 替换英文逗号为中文逗号换行为空格，换行符号为空格
	 * @param str
	 * @return
	 */
	public static String replaceCsvStr(String str){
		if(str==null||str.equalsIgnoreCase("null")){
			return "";
		}else{
			if(str.startsWith(",")){
				str=str.substring(1);
			}
			return str=str.replaceAll(",", "，").replaceAll("\r\n", " ").replaceAll("\n", " ");
		}
	}
	public static String nullToString(String str){
		if(str == null || "".equals(str))
		return " ";
		return str;
	}
	
	
	/**
	 * 是否为小数
	 * @param str
	 * @return
	 */
	public static boolean isDecimals(String str) {
		if(str==null||str.equals("")||str.equalsIgnoreCase("null")){
			return false;
		}else{
	       return str.matches("(^\\+?\\-?[\\d]+[.]?[\\d]+$)||(^\\+?\\-?[\\d]+$)");
		}
	  }
	/**
	 * 百分数
	 * @param str
	 * @return
	 */
	public static boolean isPercent(String str) {
		if(str==null||str.equals("")||str.equalsIgnoreCase("null")){
			return false;
		}else{
	       return str.matches("^\\+?\\-?\\d+\\.?\\d*\\%?$");
		}
	  }
	
	
	/**
	 * 是否是字母
	 * @param str
	 * @return
	 */
	public static boolean isAlpha(String str) {
		if (str == null) {
			return false;
		}

		int length = str.length();

		for (int i = 0; i < length; i++) {
			if (!Character.isLetter(str.charAt(i))) {
				return false;
			}
		}

		return true;
	}
	
	public static boolean isFormula( String str) {
		char[] vi_sFormula=str.toCharArray();
	    for (int i = 0; i<vi_sFormula.length; ++i) {
	        if (vi_sFormula[i] > '9' || vi_sFormula[i] < '0') {
	            if ("+".equals(vi_sFormula[i])) {
	                continue;
	            }
	            else if ("-".equals(vi_sFormula[i])) {
	                continue;
	            }
	            else if ("*".equals(vi_sFormula[i])) {
	                continue;
	            }
	            else if ("/".equals(vi_sFormula[i])) {
	                continue;
	            }
	            else if ("%".equals(vi_sFormula[i])) {
	                continue;
	            }
	            else if ("(".equals(vi_sFormula[i])) {
	                continue;
	            }
	            else if (")".equals(vi_sFormula[i])) {
	                continue;
	            }
	            else if (".".equals( vi_sFormula[i])) {
	                continue;
	            }
	            else if (" ".equals(vi_sFormula[i])) {
	                continue;
	            }
				else if ("@".equals(vi_sFormula[i])) {
//					for ( ; (vi_sFormula[i+1] != ')') && (vi_sFormula[i]); ++i){	}
					
					continue;
				}

	            return false;    
	        }
	    }	

	    return true;
	}

	/**
	 * 反转义消息中的字符
	 * @param str
	 * @return
	 */
	public static String[] OpTransform(String[] str){
		String oldValue = "\\#",newValue = "|";
		for(int i = 0; i < str.length; ++i){
			str[i].replaceAll(oldValue, newValue);
		}
		return str;
	}
}
