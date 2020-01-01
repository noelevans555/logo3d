package com.noelevans555.logo3d.compiler.program.parameter;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.noelevans555.logo3d.model.LogoColor;

@RunWith(MockitoJUnitRunner.class)
public class RandomColorTest extends AbstractParameterTest {

    @Test
    public void evaluate_forRandom_returnsRandom() throws Exception {
        // given
        RandomColor randomColorParameter = new RandomColor(false);
        Double minRed = 127.5d;
        Double maxRed = 127.5d;
        Double minGreen = 127.5d;
        Double maxGreen = 127.5d;
        Double minBlue = 127.5d;
        Double maxBlue = 127.5d;
        for (int i = 0; i < 5000; i++) {
            // when
            LogoColor result = randomColorParameter.evaluate(state).getColor("test");
            minRed = Math.min(minRed, result.getRed());
            maxRed = Math.max(maxRed, result.getRed());
            minGreen = Math.min(minGreen, result.getGreen());
            maxGreen = Math.max(maxGreen, result.getGreen());
            minBlue = Math.min(minBlue, result.getBlue());
            maxBlue = Math.max(maxBlue, result.getBlue());
        }
        // then
        assertTrue("minRed=" + minRed, minRed >= 0 && minRed < 1);
        assertTrue("maxRed=" + maxRed, maxRed > 254 && maxRed <= 255);
        assertTrue("minGreen=" + minGreen, minGreen >= 0 && minGreen < 1);
        assertTrue("maxGreen=" + maxGreen, maxGreen > 254 && maxGreen <= 255);
        assertTrue("minBlue=" + minBlue, minBlue >= 0 && minBlue < 1);
        assertTrue("maxBlue=" + maxBlue, maxBlue > 254 && maxBlue <= 255);

        // also assert that the same values are not being returned for each component
        assertNotEquals(minRed, minGreen, DELTA);
        assertNotEquals(minGreen, minBlue, DELTA);
        assertNotEquals(minRed, minBlue, DELTA);
    }

}
