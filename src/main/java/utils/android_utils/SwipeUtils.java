package utils.android_utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import utils.enums.Direction;

import java.time.Duration;
import java.util.Collections;

public interface SwipeUtils {
    default void swipeScreen(AppiumDriver driver, Direction direction) {
        Dimension size = driver.manage().window().getSize();
        int startX, endX, startY, endY;
        int middleX = size.width / 2;
        int middleY = size.height / 2;
        switch (direction) {
            case RIGHT -> {
                startX = (int) (size.width * 0.2);
                endX = (int) (size.width * 0.8);
                startY = endY = middleY;
            }
            case LEFT -> {
                startX = (int) (size.width * 0.8);
                endX = (int) (size.width * 0.2);
                startY = endY = middleY;
            }
            case DOWN -> {
                startY = (int) (size.height * 0.2);
                endY = (int) (size.height * 0.8);
                startX = endX = middleX;
            }
            case UP -> {
                startY = (int) (size.height * 0.8);
                endY = (int) (size.height * 0.2);
                startX = endX = middleX;
            }
            default -> throw new IllegalArgumentException("Wrong direction" + direction);
        }

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(swipe));
    }

    default void swipeInsideElement(AppiumDriver driver, WebElement element, Direction direction){
        Rectangle size = element.getRect();
        int startX, endX;
        int middleY = size.y + size.height / 2;
        switch (direction) {
            case RIGHT -> {
                startX = size.x + (int) (size.width * 0.2);
                endX = size.x + (int) (size.width * 0.8);
            }
            case LEFT -> {
                startX = size.x + (int) (size.width * 0.8);
                endX = size.x + (int) (size.width * 0.2);
            }
            default -> throw new IllegalArgumentException("Wrong direction" + direction);
        }
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, middleY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), endX, middleY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(swipe));
    }
}
