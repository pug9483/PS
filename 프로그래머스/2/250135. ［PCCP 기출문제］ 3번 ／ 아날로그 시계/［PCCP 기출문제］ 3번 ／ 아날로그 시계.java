class Solution {
    // 시간 : 360 / 12 * 60 * 60 degree/s
    // 분 : 360 / 60 * 60 degree/s
    // 초 : 360 / 60 degree/s
    // 초침이 시침/분침과 겹칠때 알람이 울린다.
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int ret = 0;
        
        double time1 = h1 * 3600 + m1 * 60 + s1;
        double time2 = h2 * 3600 + m2 * 60 + s2;
        
        if(time1 == 0 || time1 == 12 * 3600) ret++;
        
        while(time1 < time2){
            double h = time1 / 120 % 360;
            double m = time1 / 10 % 360;
            double s = time1 * 6 % 360;
            
            double nh = (time1 + 1) / 120 % 360 == 0 ? 360 : (time1 + 1) / 120 % 360;
            double nm = (time1 + 1) / 10 % 360 == 0 ? 360 : (time1 + 1) / 10 % 360;
            double ns = (time1 + 1) * 6 % 360 == 0 ? 360 : (time1 + 1) * 6 % 360;
            
            if(s < h && nh <= ns)ret++;
            if(s < m && nm <= ns)ret++;
            if(ns == nh && nh == nm) ret--;
            
            time1++;
        }
        
        return ret;
    }
}