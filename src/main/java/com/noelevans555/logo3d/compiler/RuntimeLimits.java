package com.noelevans555.logo3d.compiler;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Specifies various thresholds which are not to be exceeded during program
 * execution, ensuring the continued stability of the host application.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@AllArgsConstructor
@Getter
public class RuntimeLimits {

    static final RuntimeLimits DEFAULT_LIMITS = new RuntimeLimits(2, 100, 1_000_000);

    private final int timeoutInSeconds;
    private final int maximumStackDepth;
    private final int maximumLogoLines;

}
