package com.noelevans555.logo3d.compiler.program;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.noelevans555.logo3d.compiler.exception.SemanticException;

/**
 * A registry of named procedures which have been defined within a Logo3d
 * program. Registration is two-phase (register & link) to allow recursive
 * procedures to be registered (and available for onward referencing) before
 * their program body is fully constructed.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

class ProcedureRegistry {

    private Map<String, Procedure> registeredProcedures = new HashMap<>();

    /**
     * Registers a new procedure by name.
     *
     * @param procedureName The name by which the procedure is registered.
     * @param parameterNames Names of the input parameters expected by the
     *        procedure.
     * @throws SemanticException If a procedure has already been registered with the
     *         specified name (case insensitive).
     */
    public void registerProcedure(final String procedureName, final List<String> parameterNames)
            throws SemanticException {
        if (getProcedure(procedureName).isPresent()) {
            throw new SemanticException(SemanticException.PROCEDURE_ALREADY_EXISTS, procedureName);
        }
        registeredProcedures.put(procedureName.toLowerCase(), new Procedure(procedureName, parameterNames));
    }

    /**
     * Attaches a program as a body to a registered procedure.
     *
     * @param procedureName The name of the procedure whose body to attach.
     * @param procedureBody The procedure body to attach.
     */
    public void linkProcedureBody(final String procedureName, final Program procedureBody) {
        getProcedure(procedureName).get().setProcedureBody(procedureBody);
    }

    /**
     * Retrieves the procedure registered with the specified name.
     *
     * @param procedureName The name of the procedure to retrieve.
     * @return The procedure registered with the specified name.
     */
    public Optional<Procedure> getProcedure(final String procedureName) {
        return Optional.ofNullable(registeredProcedures.get(procedureName.toLowerCase()));
    }

}
