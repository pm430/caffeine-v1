package org.example;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Timer;
import java.util.TimerTask;

public class Coffee {
    private static final int IDLE_TIME = 5 * 60 * 1000; // 5 minutes
    private static final int MOUSE_MOVE_DISTANCE = 1;

    public static void main(String[] args) throws AWTException {
        final Robot robot = new Robot();
        final Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            private Point lastMousePosition = MouseInfo.getPointerInfo().getLocation();

            @Override
            public void run() {
                Point currentMousePosition = MouseInfo.getPointerInfo().getLocation();

                if (currentMousePosition.equals(lastMousePosition)) {
                    // 마우스 포인터를 이동하여 화면이 잠기지 않게 함
                    robot.mouseMove(currentMousePosition.x + MOUSE_MOVE_DISTANCE, currentMousePosition.y);
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

                    // 원래 위치로 되돌리기
                    robot.mouseMove(currentMousePosition.x, currentMousePosition.y);
                }

                lastMousePosition = currentMousePosition;
            }
        }, IDLE_TIME, IDLE_TIME);
    }
}
