import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
    Timer timer;

    public GameTimer(int seconds) {
        timer = new Timer();

    }

    class RemindTask extends TimerTask {
        public void run() {
            System.out.println("Time's up!");
            timer.cancel();
        }
    }
}

