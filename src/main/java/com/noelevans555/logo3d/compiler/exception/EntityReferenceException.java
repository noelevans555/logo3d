package com.noelevans555.logo3d.compiler.exception;

import lombok.Getter;

/**
 * Represents an exception encountered during Logo3d compilation which is due to
 * an entity reference issue.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@Getter
public class EntityReferenceException extends SemanticException {

    public static final String PROCEDURE_ALREADY_EXISTS = "Procedure already exists";
    public static final String UNDEFINED_VARIABLE = "Undefined variable";

    private static final long serialVersionUID = 1L;

    private final String entityName;

    /**
     * Constructor.
     *
     * @param message The detail message for the exception.
     * @param entityName The name of the entity which has the reference issue.
     */
    public EntityReferenceException(final String message, final String entityName) {
        super(message);
        this.entityName = entityName;
    }

    @Override
    public String toString() {
        return "EntityReferenceException [message=" + getMessage() + ", entityName=" + entityName + "]";
    }

}
