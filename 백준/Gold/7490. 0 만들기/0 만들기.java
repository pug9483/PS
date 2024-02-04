import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static StringBuilder sb = new StringBuilder();
    
	public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0){
            N = Integer.parseInt(br.readLine());
            comb(1, "1");
            sb.append("\n");
        }
        System.out.print(sb);
    }
    
   static void comb(int num, String str){
		
		if(num == N) {
			if(calculate(str) == 0) sb.append(str+"\n");
			return;
		}
		
		comb(num+1, str+ ' ' + (num+1));
		comb(num+1, str+ '+' + (num+1));
		comb(num+1, str+ '-' + (num+1));
		
	}
	
	static int calculate(String str){
		str = str.replaceAll(" ", "");
		Iterator<Integer> it= Arrays.stream(str.split("[+,-]"))
				.map(Integer::parseInt)
				.collect(Collectors.toList()).iterator();
		int result = it.next();
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i) == '+') {
				result += it.next();
			}else if(str.charAt(i) == '-') {
				result -= it.next();
			}
		}
		return result;
		
	}
}