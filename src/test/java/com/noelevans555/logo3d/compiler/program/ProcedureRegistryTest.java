package com.noelevans555.logo3d.compiler.program;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.google.common.collect.ImmutableList;
import com.noelevans555.logo3d.compiler.exception.EntityReferenceException;

@RunWith(MockitoJUnitRunner.class)
public class ProcedureRegistryTest {

    private static final String PROCEDURE_NAME = "myProcedure";
    private static final List<String> PARAMETER_NAMES = ImmutableList.of("paramOne", "paramTwo", "paramThree");

    @Mock
    private Program program;

    private ProcedureRegistry procedureRegistry = new ProcedureRegistry();

    @Test
    public void getProcedure_whenRegistered_returnsExpectedProcedure() throws Exception {
        procedureRegistry.registerProcedure(PROCEDURE_NAME, PARAMETER_NAMES);
        Procedure procedure = procedureRegistry.getProcedure(PROCEDURE_NAME.toUpperCase()).get();
        assertEquals(PROCEDURE_NAME, procedure.getName());
        assertSame(PARAMETER_NAMES, procedure.getParameterNames());
        assertNull(procedure.getProcedureBody());
    }

    @Test
    public void getProcedure_whenRegisteredAndLinked_returnsExpectedProcedure() throws Exception {
        procedureRegistry.registerProcedure(PROCEDURE_NAME, PARAMETER_NAMES);
        procedureRegistry.linkProcedureBody(PROCEDURE_NAME, program);
        Procedure procedure = procedureRegistry.getProcedure(PROCEDURE_NAME.toLowerCase()).get();
        assertEquals(PROCEDURE_NAME, procedure.getName());
        assertSame(PARAMETER_NAMES, procedure.getParameterNames());
        assertSame(program, procedure.getProcedureBody());
    }

    @Test(expected = EntityReferenceException.class)
    public void registerProcedure_whenProcedureAlreadyExists_throwsBadEntityException() throws Exception {
        procedureRegistry.registerProcedure(PROCEDURE_NAME, PARAMETER_NAMES);
        procedureRegistry.registerProcedure(PROCEDURE_NAME.toUpperCase(), PARAMETER_NAMES);
    }

}
