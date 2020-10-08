package de.werwolf_spielleiter.extension;

import java.util.concurrent.CountDownLatch;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

import com.sun.javafx.application.PlatformImpl;

/**
 * This extension can be used to run JUnit-Tests on the JavaFx-Thread.
 * This class can be used as a parameter to the
 * {@link ExtendWith} annotation. Example:
 *
 * <pre>
 * <code>
 * @ExtendWith(JfxTestExtension.class)
 * public class MyUnitTest {
 *   @Test
 *   public void testMyMethod() {
 *    //...
 *   }
 * }
 * </code>
 * </pre>
 *
 * @author okr
 * @author Eric De Ron
 * @date 14.04.2020
 *
 */
public class JfxTestExtension implements TestInstancePostProcessor {

    /**
     * Creates a test extension, that initializes the JavaFx runtime
     * @author okr
     * @author Eric De Ron
     * @param testInstance
     * @param context
     */
    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) {
        try {
            setupJavaFX();
        } catch (InterruptedException e) {
            System.out.println("Could not initialize the JavaFx platform.");
            e.printStackTrace();
        }
    }

    /**
     * Initialize javafx environment for test class
     * @author okr
     * @author Eric De Ron
     * @throws InterruptedException if the test class is malformed
     */
    private static void setupJavaFX() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        // Initializes javafx environment
        PlatformImpl.startup(() -> { /* No need to do anything here */ });

        latch.countDown();
        latch.await();
    }
}