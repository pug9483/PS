import java.util.Scanner;

public class Main {
    private static int[][] gears = new int[4][8];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 4; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < s.length(); j++) gears[i][j] = s.charAt(j) - '0';
        }

        int K = sc.nextInt();
        for (int i = 0; i < K; i++) {
            int selectionGear = sc.nextInt()-1;
            int rotationDirection = sc.nextInt();
            solve(selectionGear, rotationDirection);
        }
        int ret = computeSum(gears);
        System.out.println(ret);
    }

    private static int computeSum(int[][] gears) {
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            if(gears[i][0] == 1){ //S극이면
                sum += gears[i][0] * Math.pow(2, i);
            }
        }
        return sum;
    }


    private static void solve(int selectionGear, int rotationDirection){
        int tmpSelectionGear = selectionGear;
        int left = selectionGear - 1;
        int right = selectionGear + 1;
        while(left >= 0 && gears[left][2] != gears[tmpSelectionGear][6]){
            tmpSelectionGear = left;
            left--;
        }

        tmpSelectionGear = selectionGear;
        while(right < 4 && gears[right][6] != gears[tmpSelectionGear][2]){
            tmpSelectionGear = right;
            right++;
        }

        roll(selectionGear, rotationDirection);
        for (int i = selectionGear-1, num = 1; i > left; i--, num++) {
            roll(i, rotationDirection * (int)Math.pow(-1, num));
        }
        for (int i = selectionGear+1, num = 1; i < right; i++, num++) {
            roll(i,rotationDirection * (int)Math.pow(-1, num));
        }
    }

    private static void roll(int selectGear, int rotationDirection) {
        if(rotationDirection == 1) { //시계방향
            int tmp = gears[selectGear][7];
            for (int i = 7; i > 0 ; i--) {
                gears[selectGear][i] = gears[selectGear][i-1];
            }
            gears[selectGear][0] = tmp;
        }
        else{
            int tmp = gears[selectGear][0];
            for (int i = 0; i < 7; i++) {
                gears[selectGear][i] = gears[selectGear][i+1];
            }
            gears[selectGear][7] = tmp;
        }
    }
}