import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { 
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException{
        String s = "";
        while(!(s = br.readLine()).equals("end")){
            if(check(s)) System.out.printf("<%s> is acceptable.\n", s);
            else System.out.printf("<%s> is not acceptable.\n", s);
        }
    }
    
    public static boolean check(String s){
        boolean o1 = true;
        boolean o2 = false;
        boolean o3 = false;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'){
                o1 = false;
                break;
            }
        }
        
        for(int i = 0; i < s.length() - 2; i++){
            int aCnt = 0;
            int bCnt = 0;
            for(int j = i; j <= i + 2; j++){
                char c = s.charAt(j);
                if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') aCnt++;
                else bCnt++;
            }
            if(aCnt == 3 || bCnt == 3){
                o2 = true;
                break;
            }
        }
        
        for(int i = 0; i < s.length() - 1; i++){
            int ci = s.charAt(i);
            int cj = s.charAt(i+1);
            if(ci == cj){
                if(ci == 'e' || ci == 'o') continue;
                o2 = true;
                break;
            }
        }
        return !o1 && !o2 && !o3;
    }
}