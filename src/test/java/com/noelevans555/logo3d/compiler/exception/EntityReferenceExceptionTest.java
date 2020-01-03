package com.noelevans555.logo3d.compiler.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EntityReferenceExceptionTest {

    private static final String DETAIL = "The program is bad";
    private static final String ENTITY_NAME = "myEntity";

    private static final String EXPECTED_TOSTRING = String
            .format("EntityReferenceException [message=%s, entityName=%s]", DETAIL, ENTITY_NAME);

    private EntityReferenceException entityReferenceException = new EntityReferenceException(DETAIL, ENTITY_NAME);

    @Test
    public void toString_returnsExpectedString() {
        assertEquals(EXPECTED_TOSTRING, entityReferenceException.toString());
    }
}
