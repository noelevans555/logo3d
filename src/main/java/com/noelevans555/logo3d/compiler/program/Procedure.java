package com.noelevans555.logo3d.compiler.program;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * A named subprogram which can be invoked on demand in the larger Logo3d
 * program. The procedure defines named parameters whose values are to be
 * supplied on invocation.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Procedure {
    private final String name;
    private final List<String> parameterNames;
    private Program procedureBody;
}
