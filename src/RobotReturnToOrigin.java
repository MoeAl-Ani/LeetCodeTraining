public class RobotReturnToOrigin {
    public boolean judgeCircle(String moves) {
        int countX = 0;
        int countY = 0;

        for (int i = 0; i < moves.length(); i++) {
            char cmd = moves.charAt(i);
            if (cmd == 'U') {
                countY++;
            } else if(cmd == 'D') {
                countY--;
            } else if (cmd == 'R') {
                countX++;
            } else if (cmd == 'L') {
                countX--;
            }
        }

        return countX == 0 && countY==0;
    }
}
