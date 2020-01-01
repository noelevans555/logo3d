package com.noelevans555.logo3d.compiler.program.parameter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.noelevans555.logo3d.model.LogoColor;

@RunWith(MockitoJUnitRunner.class)
public class ColorDefinitionTest extends AbstractParameterTest {

    @Test
    public void evaluate_withNumericParameters_returnsExpectedColor() throws Exception {
        ColorDefinition makeColorParameter = new ColorDefinition(false, new NumericConstant(23.0),
                new NumericConstant(82.5), new NumericConstant(71.9));
        LogoColor result = makeColorParameter.evaluate(state).getColor("test");
        assertColorsAreEqual(new LogoColor(23.0, 82.5, 71.9), result);
    }

    @Test
    public void evaluate_withNumericParametersAndNegated_returnsNegatedColor() throws Exception {
        ColorDefinition makeColorParameter = new ColorDefinition(true, new NumericConstant(51.0),
                new NumericConstant(3.5), new NumericConstant(228.8));
        LogoColor result = makeColorParameter.evaluate(state).getColor("test");
        assertColorsAreEqual(new LogoColor(204.0, 251.5, 26.2), result);
    }

    private void assertColorsAreEqual(final LogoColor expected, final LogoColor actual) {
        assertEquals("unequal red", expected.getRed(), actual.getRed(), DELTA);
        assertEquals("unequal green", expected.getGreen(), actual.getGreen(), DELTA);
        assertEquals("unequal blue", expected.getBlue(), actual.getBlue(), DELTA);
    }

}
