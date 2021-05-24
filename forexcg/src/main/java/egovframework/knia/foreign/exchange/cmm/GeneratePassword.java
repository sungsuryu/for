package egovframework.knia.foreign.exchange.cmm;

public class GeneratePassword {

	public static void main(String[] args) throws Exception { 
		System.out.println(getRamdomPassword(8)); 
	}
	
	public static String getRamdomPassword(int len) {
		char[] charSet = new char[] {
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
				'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
				'U', 'V', 'W', 'X', 'Y', 'Z', 
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
				'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 
				'u', 'v', 'w', 'x', 'y', 'z', 
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'!', '@', '#', '$', '%', '^', '&'
				};
		
		int idx = 0;
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < len; i++) { 
			idx = (int) (charSet.length * Math.random()); // 36 * 생성된 난수를 Int로 추출 (소숫점제거) 
			sb.append(charSet[idx]); 
		} 
		return sb.toString();
	}
}
