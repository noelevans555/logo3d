package com.noelevans555.logo3d.compiler.state;

import java.util.ArrayList;
import java.util.List;

import com.noelevans555.logo3d.compiler.exception.EntityReferenceException;
import com.noelevans555.logo3d.compiler.exception.InternalException;
import com.noelevans555.logo3d.compiler.exception.RuntimeLimitException;
import com.noelevans555.logo3d.compiler.program.parameter.result.Result;
import com.noelevans555.logo3d.compiler.turtle.Pose;

/**
 * Tracks the current value of named variables and marked turtle poses during
 * execution of a Logo3d program. Variables and poses are stored in independent
 * namespaces while all processing is case-insensitive, i.e. names with only
 * case differences will be treated as overwrites, and the value can be
 * retrieved with any name which features only case differences.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */
public class State {

    // Results and poses are stored in separate frames for independent namespaces.
    private final List<StackFrame<Result>> resultFrames = new ArrayList<>();
    private final List<StackFrame<Pose>> poseFrames = new ArrayList<>();

    private final int stackLimit;

    /**
     * Constructor. Initializes a new, empty state with a single stack frame.
     *
     * @param stackLimit The maximum number of frames the stack may be expanded to
     *        contain.
     */
    State(final int stackLimit) {
        this.stackLimit = stackLimit;
        resultFrames.add(new StackFrame<>());
        poseFrames.add(new StackFrame<>());
    }

    /**
     * Adds a new stack frame to the evaluation stack.
     *
     * @throws RuntimeLimitException If the maximum number of permitted stack frames
     *         has been exceeded.
     */
    public void pushStack() throws RuntimeLimitException {
        if (resultFrames.size() == stackLimit) {
            throw new RuntimeLimitException(RuntimeLimitException.MAXIMUM_STACK_DEPTH_EXCEEDED);
        }
        resultFrames.add(new StackFrame<>());
        poseFrames.add(new StackFrame<>());
    }

    /**
     * Removes the most recently added stack frame from the evaluation stack.
     *
     * @throws InternalException If no removable stack frames are present.
     */
    public void popStack() throws InternalException {
        if (resultFrames.size() == 1) {
            throw new InternalException("No remaining stack frames to pop");
        }
        resultFrames.remove(resultFrames.size() - 1);
        poseFrames.remove(poseFrames.size() - 1);
    }

    /**
     * Stores a result in the program state for later retrieval using the specified
     * name.
     *
     * @param name The name by which the result can be retrieved.
     * @param result The result to store.
     * @param isLocalScope Whether the value should be stored independently of
     *        values stored with the same name at previous stack frames (otherwise
     *        any value stored with matching name at a previous stack frame will be
     *        overwritten).
     */
    public void store(final String name, final Result result, final boolean isLocalScope) {
        getStorageFrame(resultFrames, name, isLocalScope).store(name, result);
    }

    /**
     * Stores a turtle pose in the program state for later retrieval using the
     * specified name.
     *
     * @param name The name by which the pose can be retrieved.
     * @param pose The turtle pose to store.
     * @param isLocalScope Whether the pose should be stored independently of poses
     *        stored with the same name at previous stack frames (otherwise any pose
     *        stored with matching name at a previous stack frame will be
     *        overwritten).
     */
    public void store(final String name, final Pose pose, final boolean isLocalScope) {
        getStorageFrame(poseFrames, name, isLocalScope).store(name, pose);
    }

    /**
     * Retrieves a result with the specified name, searching stack frames in order
     * of the most recently added.
     *
     * @param name The name of the result to retrieve.
     * @return The value of the named result.
     * @throws EntityReferenceException If no result is stored with the specified
     *         name.
     */
    public Result retrieveResult(final String name) throws EntityReferenceException {
        return getRetrievalFrame(resultFrames, name, EntityReferenceException.UNDEFINED_VARIABLE).retrieve(name);
    }

    /**
     * Retrieves a turtle pose with the specified name, searching stack frames in
     * order of the most recently added.
     *
     * @param name The name of the pose to retrieve.
     * @return The value of the named pose.
     * @throws EntityReferenceException If no pose is stored with the specified
     *         name.
     */
    public Pose retrievePose(final String name) throws EntityReferenceException {
        return getRetrievalFrame(poseFrames, name, EntityReferenceException.UNMARKED_POSITION).retrieve(name);
    }

    /**
     * Identifies the containing frame for item retrieval.
     *
     * @param <T> The type of values stored in the candidate frames.
     * @param stackFrames Candidates for frame selection.
     * @param name The name of the item being retrieved.
     * @param exceptionMessage Message to use if the named item is not found.
     * @return The storage frame in which the named item is contained.
     * @throws EntityReferenceException If the named item is not found.
     */
    private <T> StackFrame<T> getRetrievalFrame(final List<StackFrame<T>> stackFrames, final String name,
            final String exceptionMessage) throws EntityReferenceException {
        for (int i = stackFrames.size() - 1; i >= 0; i--) {
            if (stackFrames.get(i).containsName(name)) {
                return stackFrames.get(i);
            }
        }
        throw new EntityReferenceException(exceptionMessage, name);
    }

    /**
     * Identifies the appropriate frame for item storage.
     *
     * @param <T> The type of values stored in the candidate frames.
     * @param stackFrames Candidates for frame selection.
     * @param name The name by which the item can be retrieved.
     * @param isLocalScope Whether the item should be stored independently of items
     *        stored with the same name at previous stack frames.
     * @return The storage frame in which the named item should be stored.
     */
    private <T> StackFrame<T> getStorageFrame(final List<StackFrame<T>> stackFrames, final String name,
            final boolean isLocalScope) {
        if (isLocalScope) {
            return stackFrames.get(stackFrames.size() - 1);
        }
        for (int i = stackFrames.size() - 1; i >= 0; i--) {
            if (stackFrames.get(i).containsName(name)) {
                return stackFrames.get(i);
            }
        }
        return stackFrames.get(stackFrames.size() - 1);
    }

}
